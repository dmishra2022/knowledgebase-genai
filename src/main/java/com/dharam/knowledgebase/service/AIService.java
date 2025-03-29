package com.dharam.knowledgebase.service;

import dev.ai4j.openai4j.OpenAiClient;
import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIService {

    //private final ConversationalRetrievalChain chain;
    @Value("${spring.ai.openai.api-key}")
    private String openAIKey;

    @Value("${spring.ai.openai.timeout}")
    private int openAITimeout;

    public String askQuestion(String question) {

        OpenAiChatModel build = OpenAiChatModel.builder()
                .apiKey(openAIKey)
                .timeout(Duration.ofSeconds(openAITimeout))
                .build();
        log.info("======================================================");
        log.info("Question: " + question);
        String answer = build.generate(question);
        log.info("Answer: " + answer);
        log.info("======================================================");
        return answer;
    }



}
