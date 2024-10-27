package jwp.controller;

import core.mvc.Controller;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {

    private final UserDao userDao = new UserDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {

            req.setAttribute("users", userDao.findAll());
            return "/user/list.jsp";
        }
        return "redirect:/user/loginForm";
    }
}