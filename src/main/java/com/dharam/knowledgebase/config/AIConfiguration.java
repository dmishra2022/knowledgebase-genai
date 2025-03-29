package com.dharam.knowledgebase.config;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static com.dharam.knowledgebase.constant.Prompt.PROMPT_TEMPLATE_2;

@Configuration
@Slf4j
public class AIConfiguration {

    @Value("${spring.ai.openai.api-key}")
    private String openAIKey;

    @Value("${spring.ai.openai.timeout}")
    private int openAITimeout;


    /*@Bean
    public ConversationalRetrievalChain chain() {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        log.info("Building ConversationalRetrievalChain ...");

        ConversationalRetrievalChain chain = ConversationalRetrievalChain.builder()
                .chatLanguageModel(OpenAiChatModel.builder()
                        .apiKey(openAIKey)
                        .timeout(Duration.ofSeconds(openAITimeout))
                        .build()
                )
                .promptTemplate(PromptTemplate.from(PROMPT_TEMPLATE_2))
                .chatMemory(chatMemory)
//                .retriever(loggingRetriever)
                .build();

        log.info("Spring Boot knowledge base is ready!");
        return chain;

    }*/
}
