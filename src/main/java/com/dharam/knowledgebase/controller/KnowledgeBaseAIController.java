package com.dharam.knowledgebase.controller;


import com.dharam.knowledgebase.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/knowledgebaseai")
@RestController
@RequiredArgsConstructor
public class KnowledgeBaseAIController {
     private final AIService aiService;

     @PostMapping("/chat")
     public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> request) {
         String userInput = request.get("message");
         String answer = aiService.askQuestion(userInput);
         return ResponseEntity.ok(answer);
     }
}
