package com.demo.IPMDemo.demo.Controller;

import com.demo.IPMDemo.demo.Dao.ReviewDao;
import com.demo.IPMDemo.demo.model.HostHolder;
import com.demo.IPMDemo.demo.model.Review;
import com.demo.IPMDemo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
    @Autowired
    ReviewDao reviewDao;

    @RequestMapping(path = "/review",method = RequestMethod.POST)
    public String review(
                         @RequestParam("name") String name,
                         @RequestParam("review") String review,
                         @RequestParam("overview") String overview
                         ){
        User user= HostHolder.getUser();
        if(user==null) return "redirect:/relogin";
        Review Treview=new Review();
        Treview.setName(name);
        Treview.setReview(review);
        Treview.setOverview(overview);
        Treview.setTeacherId(user.getId());
        reviewDao.addReview(Treview);
        return "redirect:/";
    }
}
