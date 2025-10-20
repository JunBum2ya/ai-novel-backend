package com.midas.novel.scenario.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Sin3AiPromptResultDto {
    private boolean state;
    private String message;

    @Builder
    public Sin3AiPromptResultDto(boolean state, String message) {
        this.state = state;
        this.message = message;
    }
}
