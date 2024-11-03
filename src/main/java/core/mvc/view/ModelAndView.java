package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

    View view;
    Map<String, Object> model = new HashMap<>();

    public ModelAndView(View view) {
        this.view = view;
    }

    // 모델에 데이터 추가
    public ModelAndView addObject(String attributeName, Object attributeValue){
        model.put(attributeName, attributeValue);
        return this;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) {
        view.render(model,req,resp); // json뷰는 model을 사용해서 렌더링 해야 하므로, model도 넘긴다.

    }
}