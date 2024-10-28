package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQnaController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(req.getParameter("title"),
                req.getParameter("writer"),
                req.getParameter("contents"));

        questionDao.insert(question);
        System.out.println("질문하기 완료");

        return "redirect:/";
    }
}
