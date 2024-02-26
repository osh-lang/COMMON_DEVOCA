package com.ssafy.devoca.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/*
 * token 발급 및 유효성 검사 기능 구현
 * @author Ryu jiyun
 * @Github https://github.com/Ryujy
 * */

@Component
@Slf4j
public class JwtUtil {
    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.access-token.expiretime}")
    private long accessTokenExpireTime;

    @Value("${jwt.refresh-token.expiretime}")
    private long refreshTokenExpireTime;

    public String createAccessToken(String userId){
        return create(userId, "access-token", accessTokenExpireTime);
    }

    public String createRefreshToken(String userId){
        return create(userId, "refresh-token", refreshTokenExpireTime);
    }

    private String create(String userId, String subject, long expireTime){
        Claims claims = Jwts.claims()
                .setSubject(subject).setIssuedAt(new Date());
//                .setExpiration(new Date(System.currentTimeMillis() + expireTime)); // 만료기간 제외
        claims.put("userId", userId);

        String jwt = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        log.info("userId : {} , jwt : {}", userId, jwt);
        return jwt;
    }

    private byte[] generateKey() {
        byte[] key = null;
        try{
            key = salt.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e){
            if(log.isInfoEnabled()){
                e.printStackTrace();
            } else {
                log.error("Making JWT Key Error ::: {}", e.getMessage());
            }
        }
        return key;
    }

    public boolean checkToken(String token){
         try{
             Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
             log.info("claims", claims);
             return true;
         } catch (Exception e){
             log.error("Checking token Error ::: {}", e.getMessage());
             return false;
         }
    }

    public String getUserId(String token){
        Jws<Claims> claims = null;
        try{
            claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
        } catch (Exception e){
            log.error("JWT get userId Error ::: {}", e.getMessage());
        }
        Map<String, Object> value = claims.getBody();
        log.info("value : {}", value);
        return (String) value.get("userId");
    }
}
