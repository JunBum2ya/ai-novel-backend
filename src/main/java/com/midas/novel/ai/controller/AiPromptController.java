package com.midas.novel.ai.controller;

import com.midas.novel.ai.service.AiPromptService;
import com.midas.novel.common.dto.response.CommonResponse;
import com.midas.novel.common.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiPromptController {

    private final AiPromptService aiPromptService;

    @Autowired
    public AiPromptController(AiPromptService aiPromptService) {
        this.aiPromptService = aiPromptService;
    }

    @PostMapping("/test")
    public ResponseEntity<CommonResponse<String>> sin1(@RequestParam String text) throws CustomException {
        String chatResponse = aiPromptService.callAiPrompt(text);
        return ResponseEntity.ok(CommonResponse.success(chatResponse));
    }

}
