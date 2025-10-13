package com.midas.novel.ai.service;

import com.midas.novel.common.exception.CustomException;
import com.midas.novel.common.exception.CustomExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AiPromptService {

    private final ChatModel chatModel;

    @Autowired
    public AiPromptService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String callAiPrompt(String text) throws CustomException {
        Prompt prompt = new Prompt(text);
        try {
            ChatResponse chatResponse = chatModel.call(prompt);
            return chatResponse.getResult().getOutput().getText();
        }catch (Exception ex){
            log.error("AI Prompt call failed : {}", ex.getMessage());
            throw CustomException.of(CustomExceptionType.SERVER_ERROR);
        }
    }

}
