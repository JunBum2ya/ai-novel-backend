package com.midas.novel.scenario.controller;

import com.midas.novel.scenario.dto.request.Sin1AiPromtResultDto;
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

}
