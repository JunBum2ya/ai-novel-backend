package com.midas.novel.scenario.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.midas.novel.ai.service.AiPromptService;
import com.midas.novel.scenario.dto.Sin2AiPromptResultDto;
import com.midas.novel.scenario.dto.Sin3AiPromptResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {
    private final AiPromptService aiPromptService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ScenarioService(AiPromptService aiPromptService, ObjectMapper objectMapper) {
        this.aiPromptService = aiPromptService;
        this.objectMapper = objectMapper;
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

    public Sin2AiPromptResultDto sin2(String problem, String text) throws JsonProcessingException {
        String promptText = "너는 비주얼 노벨에서 청년 농부 홍길동 역활을 맡고있어\n" +
                "너의 방금전의 대사는 \"" + problem + "이야\n" +
                "우리마을의 사회문제에 대하여 불만을 가진 청년이지\n" +
                "시장님은 " + problem + " 대한 좋은 아이디어를 입력할꺼야.\n" +
                "입력값을 바탕으로 고령화 문제를 해결할 수 있는지 판단해주고 만약 고령화를 해결할 수 있다 하면 \"{\"state\" : \"true\", \"message\" : \"아이디어가 좋으면 긍정적으로 시장님은 대단해요! 이런느낌 아이디어가 안좋으면 화내면서 진짜 짜증을 내줘\"}\" 이러한 형식으로 반환해줘.\n" +
                "홍길동씨 이름은 안반환해줘도돼  \"사용자 답변 :\"저런것도 하지말고 그냥 위에 json대로만 반환해줘 코드블럭으로 감싸주세요.\" +"+
                "======================================\n" +
                "사용자 입력 : " + text;
        String result =  aiPromptService.callAiPrompt(promptText);
        return objectMapper.readValue(result, Sin2AiPromptResultDto.class);
    }

    public Sin3AiPromptResultDto sin3(String text) throws JsonProcessingException {
        String promptText = "너는 비주얼 노벨에서 지역특산물 농업 관계자 박진수의 역활을 맡고있어\n" +
                "너의 방금전의 대사는 \"{박진수 : 최근 기상 이변으로 인해 특산물인 납작 복숭아 등에서 못난이 과일의 비율이 무려 20%나 달하고 있습니다. 이로 인해 판매에 큰 어려움을 겪고 있습니다. \n" +
                "\n" +
                "박진수 : 이러한 문제점을 해결하려고 주말 농장 체험등을 진행했지만 여전히 못난이 과일이 잘 팔리지 않고 있습니다.}\"\n" +
                "시장님이 못난이 과일문제에 대한 좋은 아이디어를 입력할꺼야.\n" +
                "입력값을 바탕으로 못난이 과일 문제를 해결할 수 있는지 판단해주고 만약 못난이 과일문제를 해결할 수 있다 하면 \"{\"state\" : \"true\", \"message\" : \"좋은 경우 흥분한 반응을 랜덤으로 생성해주고 적합하지 않은경우엔 경악하며 실망한 답변을 해줘\"}\" 이러한 형식으로 반환해줘.\n" +
                "박진수씨 이름은 안반환해줘도돼"+
                "경악하며 실망한 답변: 이런거 하지마  \"사용자 답변 :\" spring에서 바로 json 파싱할 수 있도록 json 형식에 맞게 보내주세요.\" +"+
                "======================================\n" +
                "사용자 입력 : " + text;
        String result =  aiPromptService.callAiPrompt(promptText);
        return objectMapper.readValue(result, Sin3AiPromptResultDto.class);
    }
}
