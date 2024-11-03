package core.mvc;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;

public interface AbstractController extends Controller{
    default ModelAndView jspView(JspView view){
        ModelAndView mv = new ModelAndView(view);
        return mv;
    };
    default ModelAndView jsonView(JsonView view){
        ModelAndView mv = new ModelAndView(view);
        return mv;
    };
}
