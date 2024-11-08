package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

// 사용자에게 웹페이지를 보여주는 render메소드
public interface View {

    void render(Map<String,Object> model,HttpServletRequest request, HttpServletResponse response) throws Exception;

}