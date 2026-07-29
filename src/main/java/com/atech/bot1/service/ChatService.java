package com.atech.bot1.service;

import java.io.IOException;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.atech.bot1.payload.CricketResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

@Service
public class ChatService {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private StreamingChatModel streamingChatModel;

    @Autowired
    private ResourceLoader resourceLoader;


    public String generateResponse(String inputText) {
        return chatModel.call(inputText);
    }


    public Flux<String> generateStreamResponse(String inputText) {
        return streamingChatModel.stream(inputText);
    }


    public CricketResponse generateCricketResponse(String inputText)
            throws IOException, JsonProcessingException {

        Resource resource = resourceLoader
                .getResource("classpath:prompts/cricket_bot.st");

        PromptTemplate promptTemplate = new PromptTemplate(resource);

        Prompt prompt = promptTemplate.create(
                java.util.Map.of("inputText", inputText)
        );

        ChatResponse response = chatModel.call(prompt);

        String responseString = response.getResult()
                .getOutput()
                .getText();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(responseString, CricketResponse.class);
    }
}