package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 사용자에게 웹페이지를 보여주는 render메소드
public interface View {
    void render(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
