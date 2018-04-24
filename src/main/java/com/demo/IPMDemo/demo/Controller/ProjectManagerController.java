package com.demo.IPMDemo.demo.Controller;

import com.demo.IPMDemo.demo.Dao.ProjectDao;
import com.demo.IPMDemo.demo.model.Project;
import com.demo.IPMDemo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectManagerController {
    @Autowired
    ProjectDao projectDao;
    @RequestMapping(path="/projectmanager")
    public String projectmanager(){
        return "Project";
    }
    @RequestMapping(path = "/allproject")
    @ResponseBody
    public Map selectAllProject(){
        List<Project> list=projectDao.selectAllProject();
        Map map=new HashMap();
        map.put("rows",list);
        map.put("total",20);
        return map;
    }
    @RequestMapping(path="/addproject")
    @ResponseBody
    public Map addProject(
                          @RequestParam("name") String name,
                          @RequestParam("manager") String manager,
                          @RequestParam("teacherId") int teacherId,
                          @RequestParam("type") String type,
                          @RequestParam("level") int level,
                          @RequestParam("status") int status
                          ){
        Project project=new Project();
        project.setName(name);
        project.setManager(manager);
        project.setTeacherId(teacherId);
        project.setType(type);
        project.setLevel(level);
        project.setStatus(status);
        projectDao.addProject(project);
        Map map= new HashMap();
        map.put("success",true);
        return map;
    }
    @RequestMapping(path="/updateproject")
    @ResponseBody
    public Map updateProject(@RequestParam("id") int id,
                          @RequestParam("name") String name,
                          @RequestParam("manager") String manager,
                          @RequestParam("teacherId") int teacherId,
                          @RequestParam("type") String type,
                          @RequestParam("level") int level,
                          @RequestParam("status") int status
    ){
        Project project=new Project();
        project.setName(name);
        project.setId(id);
        project.setManager(manager);
        project.setTeacherId(teacherId);
        project.setType(type);
        project.setLevel(level);
        project.setStatus(status);
        projectDao.updateproject(project);
        Map map= new HashMap();
        map.put("success",true);
        return map;
    }
    @RequestMapping(path="/destroyproject")
    @ResponseBody
    public Map destroyproject(@RequestParam("id") int id){
        projectDao.deleteproject(id);
        Map map=new HashMap();
        map.put("success",true);
        return map;
    }


}
