package com.midas.novel.scenario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Sin2AiPromptResultDto {
    private boolean state;
    private String message;

    @Builder
    public Sin2AiPromptResultDto(boolean state, String message) {
        this.state = state;
        this.message = message;
    }
}
