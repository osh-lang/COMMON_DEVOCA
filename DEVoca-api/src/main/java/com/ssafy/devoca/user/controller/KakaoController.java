package com.ssafy.devoca.user.controller;

import com.ssafy.devoca.user.model.KakaoDTO;
import com.ssafy.devoca.user.service.KakaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("kakao")
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;
    @GetMapping("/callback")
    public ResponseEntity<KakaoDTO> callback(HttpServletRequest request) throws Exception {
        // "code" 가 카카오에서 전달해준 인증 코드
        log.info("code : {}", request.getParameter("code"));
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));
        return ResponseEntity.status(HttpStatus.OK).body(kakaoInfo);
    }

    @GetMapping("/logout")
    public void logoutKakao(HttpServletResponse response) throws Exception{
        String kakaoLogoutURL = kakaoService.logoutKakao();
        response.sendRedirect(kakaoLogoutURL);
    }

}
