package com.laioffer.Twitch_Jupiter.controller;

import com.laioffer.Twitch_Jupiter.service.RecommendationException;
import com.laioffer.Twitch_Jupiter.service.RecommendationService;
import com.laioffer.Twitch_Jupiter.entity.db.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @RequestMapping(value = "/recommendation", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Item>> recommendation(HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession(false);
        Map<String, List<Item>> itemMap;
        try {
            if (session == null) {
                itemMap = recommendationService.recommendByDefault();
            } else {
                String userId = (String) request.getSession().getAttribute("user_id");
                itemMap = recommendationService.recommendItemByUser(userId);
            }
        } catch (RecommendationException e) {
            throw new ServletException(e);
        }

        return itemMap;
    }
}
