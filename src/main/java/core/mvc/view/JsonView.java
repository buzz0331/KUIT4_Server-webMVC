package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View{

    // request에 있는 모든 parameter를 가져와 Map자료구조에 저장하고
    // map에 있는 모든 자료구조를 json으로 변환하여 사용자에게 응답
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // model에 저장된 answer의 정보들이 json으로 변환되어 사용자에게 반환
        out.print(objectMapper.writeValueAsString(createModel(request)));
    }

    // cmd + option + m : 메소드 추출
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

    // pdfView 클래스 생성 후, view interface구현하도록 코드 작성.
    // 구현체를 controller가 반환하도록 하면
    // dispatcherservlet에서 코드 변경 없이 확장 가능

}
