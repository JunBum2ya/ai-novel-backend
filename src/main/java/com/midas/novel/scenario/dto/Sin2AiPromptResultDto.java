package com.midas.novel.scenario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Sin2AiPromptResultDto {
    private boolean state;
    private String message;

    public Sin2AiPromptResultDto() {}

    @JsonProperty("state")
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
