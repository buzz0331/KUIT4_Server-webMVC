package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQnaFormController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);

        HttpSession session = req.getSession();
        User value = (User) session.getAttribute("user");

        if (question != null && value != null) {
            if (question.getWriter().equals(value.getUserId())) {
                req.setAttribute("question", question);
                return "/qna/updateForm.jsp";
            }
        }
        return "redirect:/";
    }
}
