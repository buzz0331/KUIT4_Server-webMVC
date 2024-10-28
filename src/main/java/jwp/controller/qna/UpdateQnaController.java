package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQnaController implements Controller {

    QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Question modifyQna = questionDao.findByQuestionId(questionId); //수정하려는 Question

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            if (user.getUserId().equals(modifyQna.getWriter())) {
                throw new IllegalArgumentException("올바르지 않은 접근입니다.");
            }
        }

        modifyQna.update(
                req.getParameter("title"),
                req.getParameter("writer"),
                req.getParameter("contents")
        );

        questionDao.update(modifyQna);

        return "redirect:/";
    }
}
