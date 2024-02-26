package com.ssafy.devoca.user.service;

import com.ssafy.devoca.user.model.KakaoDTO;
import com.ssafy.devoca.user.model.mapper.UserMapper;
import com.ssafy.devoca.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Value("${kakao.client.id}")
    private String KAKAO_CLIENT_ID;

    @Value("${kakao.client.secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.redirect.url}")
    private String KAKAO_REDIRECT_URL;

    @Value("${kakao.logout.redirect.url}")
    private String KAKAO_LOGOUT_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com"; //https://kauth.kakao.com/oauth/token
    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&redirect_uri=" + KAKAO_REDIRECT_URL
                + "&response_type=code";
    }

    public KakaoDTO getKakaoInfo(String code) throws Exception {
        if (code == null) throw new Exception("Failed get authorization code");

        String accessToken = "";
        String refreshToken = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type"   , "authorization_code");
            params.add("client_id"    , KAKAO_CLIENT_ID);
            params.add("client_secret", KAKAO_CLIENT_SECRET);
            params.add("code"         , code); // kakao에서 전달받은 인증 코드
            params.add("redirect_uri" , KAKAO_REDIRECT_URL);

            RestTemplate restTemplate = new RestTemplate();
            // 요청의 본문(body)과 헤더(headers)를 설정한다.
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

            // 인증받은 코드로 카카오 인증 서버에 액세스 토큰 발급 요청
            // RestTemplate의 exchange 메서드는 다양한 유형의 HTTP 요청을 처리할 수 있다.
            ResponseEntity<String> response = restTemplate.exchange( // token을 요청하고 그 응답 결과를 담는 객체
                    KAKAO_AUTH_URI + "/oauth/token", // HTTP 요청을 보낼 대상 URL
                    HttpMethod.POST, // POST 유형의 HTTP 요청
                    httpEntity, // 인증 코드를 포함한 다양한 데이터가 들어있는 HttpEntity 객체
                    String.class // HTTP 응답 내용을 문자열로 받아오기.
                    // ResponseEntity<String> response에 실제 응답 결과가 담긴다.
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            accessToken  = (String) jsonObj.get("access_token");
            refreshToken = (String) jsonObj.get("refresh_token");
            log.debug("access" + accessToken);
            log.debug("refresh" + refreshToken);
        } catch (Exception e) {
            throw new Exception("API call failed", e);
        }

        return getUserInfoWithToken(accessToken);
    }
    
    // 전달받은 액세스 토큰을 통해 사용자 정보 가져오기
    private KakaoDTO getUserInfoWithToken(String accessToken) throws Exception {
        //HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader 담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        //Response 데이터 파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj    = (JSONObject) jsonParser.parse(response.getBody());

        long id = (long) jsonObj.get("id");

        // 회원/비회원 확인
        int result = userMapper.checkUser(id);
        accessToken = jwtUtil.createAccessToken(String.valueOf(id));
        log.info("id : " + id);
        if (result > 0) {
            log.info("기존 회원");
            return KakaoDTO.builder()
                    .id(id)
                    .token(accessToken)
                    .userYn(true).build();
        } else {
            log.info("비회원");
            return KakaoDTO.builder()
                    .id(id)
                    .token(accessToken)
                    .userYn(false).build();
        }
    }

    public String getToken(long id) {
        String accessToken = "";



        return accessToken;
    }
    public String logoutKakao(){
        return KAKAO_AUTH_URI + "/oauth/logout"
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&logout_redirect_uri=" + KAKAO_LOGOUT_REDIRECT_URL;
    }
}
