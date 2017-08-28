/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.ds;

import com.sina.sina.dao.OrderDao;
import com.sina.sina.models.Ds;
import com.sina.sina.models.Order;
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
public class DsController {
    @Autowired
    OrderDao orderDao;
    
    
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
