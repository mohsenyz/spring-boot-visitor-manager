/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.cm;

import com.sina.sina.dao.OrderDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Cm;
import com.sina.sina.models.Order;
import com.sina.sina.models.Visitor;
import com.utils.time.TimeHelper;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.dialect.TimesTenDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mphj
 */
@RestController
public class CmController {
    
    @Autowired
    OrderDao orderDao;
    
    @Autowired
    VisitorDao visitorDao;
    
    @GetMapping("/cm/reports/visits")
    public List<Order> getOrders(HttpSession httpSession){
        if (httpSession.getAttribute("cm") == null){
            return new ArrayList<>();
        }
        Cm cm = (Cm)httpSession.getAttribute("cm");
        return orderDao.findByCm(cm.getId());
    }
    
    
    @GetMapping("/cm/visitors")
    public List<Visitor> listVisitors(HttpSession httpSession){
        if (httpSession.getAttribute("cm") == null){
            return new ArrayList<>();
        }
        Cm cm = (Cm)httpSession.getAttribute("cm");
        return visitorDao.findByCm(cm.getId());
    }
    
    
    @GetMapping("/cm/reports/visitors")
    public List<Order> visitorReports(
            @RequestParam("visitors") String visitors,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            HttpSession httpSession){
        if (httpSession.getAttribute("cm") == null){
            return new ArrayList<>();
        }
        Cm cm = (Cm)httpSession.getAttribute("cm");
        Timestamp fromTime = TimeHelper.parseTimestamp(from, null);
        Timestamp toTime = TimeHelper.parseTimestamp(to, null);
        return orderDao.findByCmByVisitorByTime(cm.getId(), visitors, fromTime, toTime);
    }
    
    
    @GetMapping("/cm/reports/received")
    public List<Order> getReceivedReports(HttpSession httpSession){
        if (httpSession.getAttribute("cm") == null){
            return new ArrayList<>();
        }
        Cm cm = (Cm)httpSession.getAttribute("cm");
        return orderDao.receivedByCm(cm.getId());
    }
    
    
    @GetMapping("/cm/reports/finished")
    public List<Order> getFinishedReports(HttpSession httpSession){
        if (httpSession.getAttribute("cm") == null){
            return new ArrayList<>();
        }
        Cm cm = (Cm)httpSession.getAttribute("cm");
        return orderDao.findFinishedByCm(cm.getId());
    }
}
