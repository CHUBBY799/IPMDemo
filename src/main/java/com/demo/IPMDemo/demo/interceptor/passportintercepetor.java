package com.demo.IPMDemo.demo.interceptor;

import com.demo.IPMDemo.demo.Dao.LoginTicketDao;
import com.demo.IPMDemo.demo.Dao.UserDao;
import com.demo.IPMDemo.demo.model.HostHolder;
import com.demo.IPMDemo.demo.model.LoginTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
@Component
public class passportintercepetor implements HandlerInterceptor {
    @Autowired
    LoginTicketDao loginTicketDAO;

    @Autowired
    UserDao userDao;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket=null;
        if (httpServletRequest.getCookies()!=null){
            for(Cookie cookie:httpServletRequest.getCookies()){
                if(cookie.getName().equals("ticket")){
                    ticket=cookie.getValue();
                    break;}
            }
        }
        if(ticket!=null){
            LoginTicket loginTicket=loginTicketDAO.selectByTicket(ticket);
            if(loginTicket==null||loginTicket.getExpired().before(new Date())||loginTicket.getStatus()!=0){
                return true;
            }
            HostHolder.setUser(userDao.selectById(loginTicket.getUserId()));

        }



        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null&&HostHolder.getUser()!=null)
            modelAndView.addObject("user",HostHolder.getUser());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        HostHolder.clear();
    }
}
