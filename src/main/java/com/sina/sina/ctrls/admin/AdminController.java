/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.admin;

import com.sina.sina.dao.CmDao;
import com.sina.sina.dao.OrderDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Cm;
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
public class AdminController {
    
    @Autowired
    OrderDao orderDao;
    
    @Autowired
    VisitorDao visitorDao;
    
    @Autowired
    CmDao cmDao;
    
    @GetMapping("/admin/requests")
    public List<Order> listRequests(HttpSession httpSession){
        if (httpSession.getAttribute("admin") == null) {
            return new ArrayList<>();
        }
        return orderDao.findAllDsOrder();
    }
    
    
    @GetMapping("/admin/reports/visits")
    public List<Order> listVisits(HttpSession httpSession){
        if (httpSession.getAttribute("admin") == null) {
            return new ArrayList<>();
        }
        return orderDao.findAll();
    }
    
    @GetMapping("/admin/visitor")
    public List<Visitor> listVisitor(HttpSession httpSession){
        if (httpSession.getAttribute("admin") == null) {
            return new ArrayList<>();
        }
        return visitorDao.findAll();
    }
    
    @GetMapping("/admin/cm")
    public List<Cm> listCm(HttpSession httpSession){
        if (httpSession.getAttribute("admin") == null) {
            return new ArrayList<>();
        }
        return cmDao.findAll();
    }
    
    
    @GetMapping("/admin/login")
    public String login(HttpSession httpSession){
        httpSession.setAttribute("admin", true);
        return "done";
    }
}
