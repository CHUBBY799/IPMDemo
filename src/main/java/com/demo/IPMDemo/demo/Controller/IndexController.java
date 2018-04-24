package com.demo.IPMDemo.demo.Controller;

import com.demo.IPMDemo.demo.Service.ProjectService;
import com.demo.IPMDemo.demo.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    private static final Logger logger=LoggerFactory.getLogger(IndexController.class);
    @Autowired
    ProjectService projectService;
    @RequestMapping(path="/")
    public String index(Model model){
        List<Project> list= projectService.SelectProjectByStatus(4);
        model.addAttribute("vos",list);
        return "index";
    }
}
