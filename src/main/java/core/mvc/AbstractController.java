package core.mvc;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;

public interface AbstractController extends Controller{
    default ModelAndView jspView(String viewName){
        return new ModelAndView(new JspView(viewName));
    };
    default ModelAndView jsonView(){
        return new ModelAndView(new JsonView());
    };
}
