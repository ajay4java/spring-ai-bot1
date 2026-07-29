package com.atech.bot1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atech.bot1.payload.CricketResponse;
import com.atech.bot1.service.ChatService;

import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/api/v1/chat")
public class ChatController {
	@Autowired
	private ChatService chatService;

	@GetMapping("/fetchData")
	public ResponseEntity<String> generateResponse(@RequestParam(value = "inputText") String inputText) {
		String responseText = chatService.generateResponse(inputText);
		return ResponseEntity.ok(responseText);

	}

	@GetMapping(value = "/fetchStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> generateStreamResponse(@RequestParam String inputText) {

		return chatService.generateStreamResponse(inputText);
	}

	@GetMapping("/cricket-bot")
	public ResponseEntity<CricketResponse> generateCricketResponse(@RequestParam(value = "inputText") String inputText)
			throws IOException {

		CricketResponse cricketResponse = chatService.generateCricketResponse(inputText);
		return ResponseEntity.ok(cricketResponse);
	}

}
