package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View{
    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(objectMapper.writeValueAsString(model));
    }

//    private static Map<String, Object> createModel(HttpServletRequest request) {
//        Enumeration<String> names = request.getParameterNames();
//        Map<String, Object> model = new HashMap<>();
//        while (names.hasMoreElements()) {
//            String name = names.nextElement();
//            model.put(name, request.getParameter(name));
//        }
//        return model;
//    }

}
