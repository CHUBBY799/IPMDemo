package com.demo.IPMDemo.demo.Controller;

import com.demo.IPMDemo.demo.Dao.ApplyDao;
import com.demo.IPMDemo.demo.model.apply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplyController {
    @Autowired
    ApplyDao applyDao;
    @RequestMapping(path = "/apply",method = RequestMethod.POST)
    public String apply(Model model, @RequestParam("name") String name,@RequestParam("content") String content,
                        @RequestParam("innovation") String innovation,@RequestParam("budget") int budget,
                        @RequestParam("expect") String expect){

        apply apply=new apply();
        apply.setName(name);
        apply.setContent(content);
        apply.setContent(innovation);
        apply.setBudget(budget);
        apply.setExpect(expect);
        applyDao.addApply(apply);
        return "redirect:/";
    }
}
