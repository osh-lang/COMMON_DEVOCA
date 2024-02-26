//package com.ssafy.devoca.user.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class OauthService{
//
//    @Value("${kakao.client.id}")
//    private String KAKAO_CLIENT_ID;
//
//    @Value("${kakao.client.secret}")
//    private String KAKAO_CLIENT_SECRET;
//
//    @Value("${kakao.redirect.url}")
//    private String KAKAO_REDIRECT_URL;
//
//    @Value("${kakao.logout.redirect.url}")
//    private String KAKAO_LOGOUT_REDIRECT_URL;
//
//    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
//    private final static String KAKAO_API_URI = "https://kapi.kakao.com";
//
//    public String getKakaoLogin() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded");
//
//        Map<String, String> params = new HashMap<>();
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(params, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                KAKAO_AUTH_URI + "/oauth/authorize"
//                        + "?client_id=" + KAKAO_CLIENT_ID
//                        + "&redirect_uri=" + KAKAO_REDIRECT_URL
//                        + "&response_type=code",
//                HttpMethod.POST,
//                httpEntity,
//                String.class
//        );
//
//
//        return response.getBody();
//    }
//
//    public String logoutKakao(){
//        return KAKAO_AUTH_URI + "/oauth/logout"
//                + "?client_id=" + KAKAO_CLIENT_ID
//                + "&logout_redirect_uri=" + KAKAO_LOGOUT_REDIRECT_URL;
//    }
//}
