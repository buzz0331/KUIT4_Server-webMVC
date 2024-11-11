package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

// model에 저장된 answer의 정보들이 json으로 변환되어 사용자에게 반환
// 보안상 문제 발생가능 -> ModelAndView 필요
public class JsonView implements View{

    // request에 있는 모든 parameter를 가져와 Map자료구조에 저장하고
    // map에 있는 모든 자료구조를 json으로 변환하여 사용자에게 응답
    @Override
    public void render(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Model -> Json 변환을 위한 객체
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8"); // 응답 타입 json으로 설정

        // PrintWriter : 클라이언트에게 응답을 보낼 준비
        PrintWriter out = response.getWriter();

        // model null값 검사
        if(model != null) {
            // model에 저장된 데이터 JSON으로 변환
            out.print(objectMapper.writeValueAsString(model)); // createModel(request)대신 model로 전달
        }
        else {
            out.print("{}");
        }
    }

    // cmd + option + m : 메소드 추출
    // req의 모든 내용을 Mdoel로 변환
    private static Map<String, Object> createModel(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
        Map<String, Object> model = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            // setAttribute로 인해 추가해줬던 savedAnswer가 model에 저장
            model.put(name, request.getParameter(name));
        }
        return model;
    }

}
