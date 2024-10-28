package jwp.controller.qna;

import core.mvc.Controller;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QnaFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        HttpSession session = req.getSession();

        if (UserSessionUtils.isLogined(session)) {
            return "/qna/form.jsp";
        }
        return "redirect:/user/loginForm";

    }
}
