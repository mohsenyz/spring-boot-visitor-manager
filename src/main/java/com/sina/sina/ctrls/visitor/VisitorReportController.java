/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.visitor;

import com.sina.sina.dao.OrderDao;
import com.sina.sina.models.Order;
import com.sina.sina.models.Visitor;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mphj
 */
@RestController
public class VisitorReportController {
    
    @Autowired
    OrderDao orderDao;
    
    @GetMapping("/visitor/reports")
    public List<Order> getReports(HttpSession httpSession){
        if (httpSession.getAttribute("visitor") == null){
            return new ArrayList<>();
        }
        Visitor currVisitor = (Visitor)httpSession.getAttribute("visitor");
        return orderDao.findByVid(currVisitor.getId());
    }
    
    
    @GetMapping("/visitor/reports/received")
    public List<Order> getReceivedOrder(HttpSession httpSession){
        if (httpSession.getAttribute("visitor") == null){
            return new ArrayList<>();
        }
        Visitor currVisitor = (Visitor)httpSession.getAttribute("visitor");
        return orderDao.findReceivedRequest(currVisitor.getId());
    }
    
    
    @GetMapping("/visitor/reports/next")
    public List<Order> getNextOrder(HttpSession httpSession){
        if (httpSession.getAttribute("visitor") == null){
            return new ArrayList<>();
        }
        Visitor currVisitor = (Visitor)httpSession.getAttribute("visitor");
        return orderDao.findNextVisit(currVisitor.getId());
    }
    
    
    @GetMapping("/visitor/reports/forwarded")
    public List<Order> getForwardedOrder(HttpSession httpSession){
        if (httpSession.getAttribute("visitor") == null){
            return new ArrayList<>();
        }
        Visitor currVisitor = (Visitor)httpSession.getAttribute("visitor");
        return orderDao.findForwarded(currVisitor.getId());
    }
    
    
    @GetMapping("/visitor/profile")
    public Visitor getProfile(HttpSession httpSession){
        if (httpSession.getAttribute("visitor") == null){
            return null;
        }
        Visitor currVisitor = (Visitor)httpSession.getAttribute("visitor");
        return currVisitor;
    }
    
    
    @GetMapping("/visitor/login")
    public String login(HttpSession httpSession){
        Visitor vis = new Visitor();
        vis.setId(22);
        httpSession.setAttribute("visitor", vis);
        return "done";
    }
    
    
    @GetMapping("/visitor/is")
    public boolean isLogin(HttpSession httpSession){
        return httpSession.getAttribute("visitor") != null;
    }
    
    
}
