//package com.ssafy.devoca.user.controller;
//
//import com.ssafy.devoca.user.model.KakaoDTO;
//import com.ssafy.devoca.user.service.OauthService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/user")
//public class OauthController {
//
//    private final OauthService oauthService;
//    @GetMapping("/kakao/login")
//    public void kakaoLogin(HttpServletResponse response) throws Exception{
//        String kakaoLoginURL = oauthService.getKakaoLogin();
////        response.sendRedirect(kakaoLoginURL);
//    }
//
//    @GetMapping("/kakao/logout")
//    public void logoutKakao(HttpServletResponse response) throws Exception{
//        String kakaoLogoutURL = oauthService.logoutKakao();
//        response.sendRedirect(kakaoLogoutURL);
//    }
//
//}
