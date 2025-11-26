package com.example.moviechat.controller;

import com.example.moviechat.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Map<String, Object> chat(@RequestBody Map<String, String> payload) {
        String question = payload.get("question");
        return chatService.getAnswer(question);
    }
}