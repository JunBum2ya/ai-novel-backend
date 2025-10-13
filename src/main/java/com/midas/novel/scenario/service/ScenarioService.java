package com.midas.novel.scenario.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.midas.novel.ai.service.AiPromptService;
import com.midas.novel.scenario.dto.request.Sin1AiPromtResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {
    private final AiPromptService aiPromptService;

    @Autowired
    public ScenarioService(AiPromptService aiPromptService) {
        this.aiPromptService = aiPromptService;
    }

    public String sin1(String text) {
        String promptText = """
                당신은 비주얼 노벨에서 김춘배라는 마을이장 역할을 맡고있습니다.
                당신의 방금전의 대사는 "{안녕하세요, 시장님! 저는 이 마을의 이장, 김춘배입니다. 제발 도와주십시오! 요즘 마을이 정말 말라 비틀어지고 있어요! 다들 도망가듯 떠나고 있어요!
               \s
                인구소멸을 해결하기 위한 시장님의 특출난 아이디어가 필요합니다…
                이렇게 점점 시간이 지나다보면 저희 마을이 사라질 수도 있을 것 같습니다…..}"
                시장님이 인구소멸에 대한 좋은 아이디어를 입력할 겁니다..
                입력값을 바탕으로 인구소멸 문제를 해결할 수 있는지 판단해주고 만약 인구소멸을 해결할 수 있다 하면 
                김춘배씨 이름은 반환하지 말고 답변해주세요.\
                ======================================
                사용자 입력 :\s""" + text;
        return aiPromptService.callAiPrompt(promptText);
    }
}
