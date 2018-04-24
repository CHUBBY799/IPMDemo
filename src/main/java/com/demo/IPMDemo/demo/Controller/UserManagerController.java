package com.demo.IPMDemo.demo.Controller;

import com.demo.IPMDemo.demo.Dao.UserDao;
import com.demo.IPMDemo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserManagerController {
    @Autowired
    UserDao userDao;
    @RequestMapping(path="/usermanager")
    public String usermanager(){
        return "Easyui";
    }
    @RequestMapping(path = "/alluser")
    @ResponseBody
    public Map selectAllUser(){
        List<User> list=userDao.selectAllUser();
        Map map=new HashMap();
        map.put("rows",list);
        map.put("total",20);
        return map;
    }
    @RequestMapping(path = "/adduser")
    @ResponseBody
    public Map adduser(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("identity") int identity,
                       @RequestParam("email") String email){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIdentity(identity);
        user.setEmail(email);
        userDao.addUser(user);
        Map map= new HashMap();
        map.put("success",true);
        return map;
    }
    @RequestMapping(path="/updateuser")
    @ResponseBody
    public Map updateuser(@RequestParam("id") int id,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("identity") int identity,
                          @RequestParam("email") String email){

        User user=new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setIdentity(identity);
        user.setEmail(email);
        userDao.updateuser(user);
        Map map=new HashMap();
        map.put("success",true);
        return map;
    }
    @RequestMapping(path="/destroyuser")
    @ResponseBody
    public Map destroyuser(@RequestParam("id") int id){
        userDao.deleteuser(id);
        Map map=new HashMap();
        map.put("success",true);
        return map;
    }
}
