package com.demo.IPMDemo.demo.Service;

import com.demo.IPMDemo.demo.Dao.LoginTicketDao;
import com.demo.IPMDemo.demo.Dao.UserDao;
import com.demo.IPMDemo.demo.model.LoginTicket;
import com.demo.IPMDemo.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    LoginTicketDao loginTicketDao;
    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDao.addTicket(ticket);
        return ticket.getTicket();
    }
    public Map register(String username,String password,String identity){
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isEmpty(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userDao.selectByUsername(username);

        if (user != null) {
            map.put("msg", "用户名已经被注册");
            return map;
        }


        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (identity.equals("学生")) user.setIdentity(1);
        if(identity.equals("老师")) user.setIdentity(2);
        userDao.addUser(user);

        // 登陆
        user=userDao.selectByUsername(username);
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }
    public Map login(String username,String password){
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isEmpty(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }
        User user = userDao.selectByUsername(username);

        if (user == null) {
            map.put("msg", "用户名不存在");
            return map;
        }
        if(!user.getPassword().equals(password)){
            map.put("msg","密码不正确");
        }
        String ticket=addLoginTicket(user.getId());
        if(user.getIdentity()==0) map.put("root",true);
        map.put("ticket",ticket);
        return map;
    }
    public void logout(String ticket) {
        loginTicketDao.updateStatus(ticket,1);
    }




































































}
