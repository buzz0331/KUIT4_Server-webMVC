package core.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View {

    private final String viewName;
    private static final String REDIRECT_PREFIX = "redirect:";

    public JspView(String viewName) {
        this.viewName = viewName;
    }

    // JSP에 모델 데이터 전달 / 클라이언트로 페이지 응답
    @Override
    public void render(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // "redirect:"라면 해당 URL로 리다이렉트
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }

        // model을 request에 저장
        // model.forEach((key, value) -> request.setAttribute(key, value)); 람다 표현식
        model.forEach(request::setAttribute);

        // JSP 페이지로 forward
        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
    }
}
