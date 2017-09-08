/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.ds;

import com.sina.sina.dao.DsDao;
import com.sina.sina.dao.OrderDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Ds;
import com.sina.sina.models.Order;
import com.sina.sina.models.Visitor;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mphj
 */
@RestController
public class DsController {
    @Autowired
    OrderDao orderDao;
    
    
    @Autowired
    DsDao dsDao;
    
    @PostMapping("/ds/login")
    public boolean login(@RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession httpSession){
        Ds ds = dsDao.findByUsername(username.trim());
        if (ds == null || !ds.getPassword().equals(password.trim())){
            return false;
        }
        httpSession.setAttribute("ds", ds);
        return true;
    }
    
    
    @GetMapping("/ds/reports/seen")
    public List<Order> seenOrder(HttpSession httpSession){
        if (httpSession.getAttribute("ds") == null){
            return new ArrayList<>();
        }
        Ds currVisitor = (Ds)httpSession.getAttribute("ds");
        return orderDao.findSeenByDs(currVisitor.getId());
    }
    
    
    @GetMapping("/ds/reports/finished")
    public List<Order> finishedOrder(HttpSession httpSession){
        if (httpSession.getAttribute("ds") == null){
            return new ArrayList<>();
        }
        Ds currVisitor = (Ds)httpSession.getAttribute("ds");
        return orderDao.findFinishedByDs(currVisitor.getId());
    }
}
