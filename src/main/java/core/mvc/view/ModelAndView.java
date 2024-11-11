package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

// 컨트롤러가 데이터를 View에 전달 할 때 사용하는 객체
public class ModelAndView {

    View view;
    Map<String, Object> model = new HashMap<>();
    // 필요한 정보만 Map에 저장하여 전달

    public ModelAndView(View view) {
        this.view = view;
    }

    // 모델에 데이터 추가
    public ModelAndView addObject(String attributeName, Object attributeValue){
        model.put(attributeName, attributeValue);
        return this; // 인스턴스.addObject.addObject 등등 가능
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        view.render(model,req,resp); // json뷰는 model을 사용해서 렌더링 해야 하므로, model도 넘긴다.

    }
}