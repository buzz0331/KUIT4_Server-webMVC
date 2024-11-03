package core.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements View {

    private final String viewName;
    private static final String REDIRECT_PREFIX = "redirect:";

    public JspView(String viewName) {
        this.viewName = viewName;
    }

    // 해당 JSP로 redirect or forward
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // dispatcher servlet의 move로직
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
    }
}
