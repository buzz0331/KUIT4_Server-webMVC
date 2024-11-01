package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowQnaController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int questionId = Integer.parseInt(req.getParameter("questionId"));

        req.setAttribute("question", questionDao.findByQuestionId(questionId));
        req.setAttribute("answers", answerDao.findAllAnswer(questionId));

        return "/qna/show.jsp";
    }
}
