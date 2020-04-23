package com.oauth.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SessionAttributes("authorizationRequest")
@Controller
public class OAuthController {


    @RequestMapping("/oauth/confirm_access")
    public ModelAndView accessConfirmation(Map<String, Object> model
            , HttpServletRequest request) {

        if (request.getAttribute("_csrf") != null) {
            model.put("_csrf", request.getAttribute("_csrf"));
        }

        ModelAndView modelView = new ModelAndView();

        modelView.setViewName("index.html");
        modelView.addAllObjects(model);

        return modelView;
    }

}
