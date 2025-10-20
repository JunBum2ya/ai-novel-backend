package com.midas.novel.scenario.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.midas.novel.ai.service.AiPromptService;
import com.midas.novel.common.exception.CustomException;
import com.midas.novel.common.exception.CustomExceptionType;
import com.midas.novel.scenario.dto.Sin2AiPromptResultDto;
import com.midas.novel.scenario.dto.Sin3AiPromptResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {
    private final AiPromptService aiPromptService;

    @Autowired
    public ScenarioService(AiPromptService aiPromptService, ObjectMapper objectMapper) {
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

    public Sin2AiPromptResultDto sin2(String problem, String text) throws Exception {
        String promptText = "너는 청년 농부 홍길동 역활을 맡고있어\n" +
                "너의 방금전의 대사는 \"" + problem + "이야\n" +
                "우리마을의 사회문제에 대하여 불만을 가진 청년이지\n" +
                "시장님은 " + problem + " 대한 좋은 아이디어를 입력할꺼야.\n" +
                "입력값을 바탕으로 고령화 문제를 해결할 수 있는지 판단해주고 만약 고령화를 해결할 수 있다 하면 \"예,\" 로 문자열을 시작하고 뒤에 좋은 아이디어인 이유를 써주세요." +
                "반대로 해결책이 될 수 없으면 \"아니요,\" 로 문자열을 시작하고 근거가 될 수 없는 이유를 써주세요." +
                "======================================\n" +
                "사용자 입력 : " + text;
        String result =  aiPromptService.callAiPrompt(promptText);
        String[] chunks = result.split(",");
        if (chunks.length != 2) {
            return Sin2AiPromptResultDto.builder()
                    .state("예".equals(chunks[0]))
                    .message(chunks[1].trim())
                    .build();
        }else {
            throw new Exception("AI 에러 발생");
        }

    }

    public Sin3AiPromptResultDto sin3(String text) throws Exception {
        String promptText = "너는 지역특산물 농업 관계자 박진수의 역활을 맡고있어\n" +
                "너의 방금전의 대사는 \"{박진수 : 최근 기상 이변으로 인해 특산물인 납작 복숭아 등에서 못난이 과일의 비율이 무려 20%나 달하고 있습니다. 이로 인해 판매에 큰 어려움을 겪고 있습니다. \n" +
                "\n" +
                "박진수 : 이러한 문제점을 해결하려고 주말 농장 체험등을 진행했지만 여전히 못난이 과일이 잘 팔리지 않고 있습니다.}\"\n" +
                "시장님이 못난이 과일문제에 대한 좋은 아이디어를 입력할꺼야.\n" +
                "입력값을 바탕으로 못난이 과일 문제를 해결할 수 있는지 판단해주고 만약 못난이 과일문제를 해결할 수 있다 하면 \"예,\"로 문자열을 시작해주고 뒤에 흥분한 반응을 랜덤으로 생성해주세요. 반대로 적합하지 않은경우엔 \"아니요,\"로 대답을 시작하고 뒤에 경악하며 실망한 답변을 해줘.\n" +
                "박진수씨 이름은 안반환해줘도돼"+
                "======================================\n" +
                "사용자 입력 : " + text;
        String result =  aiPromptService.callAiPrompt(promptText);
        String[] chunks = result.split(",");
        if (chunks.length != 2) {
            return Sin3AiPromptResultDto.builder()
                    .state("예".equals(chunks[0]))
                    .message(chunks[1].trim())
                    .build();
        }else {
            throw new CustomException(CustomExceptionType.SERVER_ERROR.getCode(), "AI 프롬프트 오류");
        }
    }

    public String sin4(String sin1, String sin2, String sin3) throws Exception {
        String url = aiPromptService.callAiImagePrompt(sin1);
        return url;
    }
}
