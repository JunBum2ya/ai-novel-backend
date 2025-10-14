package com.midas.novel.scenario.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.midas.novel.scenario.dto.Sin2AiPromptResultDto;
import com.midas.novel.scenario.dto.Sin3AiPromptResultDto;
import com.midas.novel.scenario.dto.request.Sin2Request;
import com.midas.novel.scenario.dto.request.Sin3Request;
import com.midas.novel.scenario.dto.request.TextRequest;
import com.midas.novel.scenario.service.ScenarioService;
import com.midas.novel.common.dto.response.CommonResponse;
import com.midas.novel.common.exception.CustomException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scenario")
public class ScenarioController {

    private final ScenarioService scenarioService;

    @Autowired
    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @GetMapping("/sin1")
    public ResponseEntity<CommonResponse<String>> sin1(@Valid TextRequest request) throws CustomException {
        String result = scenarioService.sin1(request.getText());
        return ResponseEntity.ok(CommonResponse.success(result));
    }

    @GetMapping("/sin2")
    public ResponseEntity<CommonResponse<Sin2AiPromptResultDto>> sin2(@Valid Sin2Request request) throws JsonProcessingException {
        Sin2AiPromptResultDto sin2AiPromptResultDto = scenarioService.sin2(request.getProblem(), request.getText());
        return ResponseEntity.ok(CommonResponse.success(sin2AiPromptResultDto));
    }

    @GetMapping("/sin3")
    public ResponseEntity<CommonResponse<Sin3AiPromptResultDto>> sin3(@Valid Sin3Request request) throws JsonProcessingException {
        Sin3AiPromptResultDto sin3AiPromptResultDto = scenarioService.sin3(request.getText());
        return ResponseEntity.ok(CommonResponse.success(sin3AiPromptResultDto));
    }

}
