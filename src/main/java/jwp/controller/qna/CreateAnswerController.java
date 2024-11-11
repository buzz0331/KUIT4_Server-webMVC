package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 답변 폼을 전송받고, 실제로 폼의 내용을 답변으로 변환시키는 컨트롤러
public class CreateAnswerController extends AbstractController {

    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("[CreateAnswerController] 실행");

        // getParameter : 사용자 폼에서 questionId추출
        int questionId = Integer.parseInt(req.getParameter("questionId"));

        // 답변 할 answer객체 생성, 답변자 / 답변 내용 포함
        Answer answer = new Answer(questionId,
                req.getParameter("writer"),
                req.getParameter("contents"));

        // DB에 답변 저장
        Answer savedAnswer = answerDao.insert(answer);

        // 해당 질문에 대한 답변의 개수 증가
        // questionId로 DB에서 질문 가져와, question객체에 저장
        Question question = questionDao.findByQuestionId(questionId);
        question.increaseCountOfAnswer();
        // questionDB에 답변개수 갱신
        questionDao.updateCountOfAnswer(question);

        // 클라이언트에게 json으로 응답보내기 위한 객체
        // 페이지 새로고침 없이 답변 데이터를 화면으로
        ModelAndView mav = jsonView();
        mav.addObject("answer", savedAnswer);

        return mav;
    }
}
