package com.ssafy.devoca.util.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * RestController for server health check
 *
 * @author Shin Juyong <a href="mailto:cheesecat47@gmail.com">cheesecat47@gmail.com</a>
 */
@RestController
@RequestMapping("health")
@Slf4j
public class HealthController {

    @GetMapping("")
    public ResponseEntity<Map<String, String>> health() {
        log.info("health check");

        return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>() {{
            put("health", "good");
        }});
    }
}
