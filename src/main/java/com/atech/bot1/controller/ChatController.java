package com.atech.bot1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atech.bot1.service.ChatService;

@Controller
@RequestMapping("/api/v1/chat")
public class ChatController {
	@Autowired
	private ChatService chatService;
	@GetMapping("/fetchData")
	public ResponseEntity<String> generateResponse(@RequestParam(value="inputText",required=true )String inputText) {
		String responseText= chatService.generateResponse(inputText);
		return ResponseEntity.ok(responseText);
		
	}

}
