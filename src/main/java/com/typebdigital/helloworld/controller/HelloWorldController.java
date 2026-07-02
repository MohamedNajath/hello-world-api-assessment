package com.typebdigital.helloworld.controller;


import com.typebdigital.helloworld.dto.ErrorResponse;
import com.typebdigital.helloworld.dto.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private static final char MID_ALPHABET_BOUNDARY = 'm';

    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld(@RequestParam(required = false) String name) {
        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid Input"));
        }

        char firstChar = Character.toLowerCase(name.trim().charAt(0));

        if (!Character.isLetter(firstChar)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid Input"));
        }

        if (firstChar >= 'a' && firstChar <= MID_ALPHABET_BOUNDARY) {
            return ResponseEntity.ok(new MessageResponse("Hello " + capitalize(name.trim())));
        }

        return ResponseEntity.badRequest().body(new ErrorResponse("Invalid Input"));
    }

    private String capitalize(String value) {
        return Character.toUpperCase(value.charAt(0)) + value.substring(1).toLowerCase();
    }
}
