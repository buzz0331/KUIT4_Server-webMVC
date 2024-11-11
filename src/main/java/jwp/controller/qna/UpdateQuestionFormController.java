package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 질문을 수정하기 위한 폼을 제시
// 로그인 여부 확인 / 수정 할 질문 조회 후, 정보를 JSP로 전달
public class UpdateQuestionFormController extends AbstractController {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        // 로그인 여부부터 검사
        if (!UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return jspView("redirect:/user/loginForm");
        }

        // 업데이트 할 질문 식별
        String questionId = req.getParameter("questionId");
        // DB에서 특정 ID에 해당하는 question객체 가져오기
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        // 현재 유저세션 가져와서 저장하기
        User user = UserSessionUtils.getUserFromSession(session);

        // 현재 로그인된 유저와 질문 작성자가 같은지 확인
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException();
        }

        req.setAttribute("question", question);
        return jspView("/qna/updateForm.jsp");
    }

}
