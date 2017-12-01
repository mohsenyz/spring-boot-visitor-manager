/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.cm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sina.sina.dao.CmDao;
import com.sina.sina.dao.DrDao;
import com.sina.sina.dao.DrugsDao;
import com.sina.sina.dao.DsDao;
import com.sina.sina.dao.OrderDao;
import com.sina.sina.dao.OrderDrugsDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Cm;
import com.sina.sina.models.Dr;
import com.sina.sina.models.Drugs;
import com.sina.sina.models.Ds;
import com.sina.sina.models.Order;
import com.sina.sina.models.OrderDrugs;
import com.sina.sina.models.Visitor;
import com.utils.time.TimeHelper;
import java.sql.Timestamp;
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
public class CmController {
    
    @Autowired
    OrderDao orderDao;
    
    @Autowired
    VisitorDao visitorDao;

    @Autowired
    DrDao drDao;

    @Autowired
    DsDao dsDao;

    @Autowired
    CmDao cmDao;
    
    @Autowired
    OrderDrugsDao orderDrugsDao;
    
    @Autowired
    DrugsDao drugsDao;
    
    @GetMapping("/cm/reports/visits")
    public ArrayNode getOrders(HttpSession httpSession){
        if (httpSession.getAttribute("cm") == null){
            return null;
        }
        Cm cm = (Cm)httpSession.getAttribute("cm");
        List<Order> list = orderDao.findByCm(cm.getId());
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Order order : list) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.putPOJO("order", order);
            fillOrder(order, objectNode);
            arrayNode.add(objectNode);
        }
        return arrayNode;
    }
    
    
    public void fillOrder(Order order, ObjectNode objectNode) {
        if (order.getDrid() != null) {
            Dr dr = drDao.findById(order.getDrid());
            objectNode.putPOJO("dr", dr);
        } else if (order.getDsid() != null) {
            Ds ds = dsDao.findById(order.getDsid());
            objectNode.putPOJO("ds", ds);
        }
        if (order.getForwardToVid() != null) {
            Visitor visitor = visitorDao.findById(order.getForwardToVid());
            objectNode.putPOJO("visitor", visitor);
        }
        if (order.getVid() != null){
            Visitor visitor = visitorDao.findById(order.getVid());
            objectNode.putPOJO("v", visitor);
        }
        List<OrderDrugs> list = orderDrugsDao.findByOrder(order.getId());
        ArrayNode arrayNode = objectNode.putArray("order_drugs");
        for (OrderDrugs orderDrug : list){
            Drugs drug = drugsDao.findById(orderDrug.getDrugId());
            orderDrug.setDrugName(drug.getName());
            arrayNode.addPOJO(orderDrug);
        }
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
    public ArrayNode visitorReports(
            @RequestParam("visitors") String visitors,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            HttpSession httpSession){
        if (httpSession.getAttribute("cm") == null){
            return null;
        }
        Cm cm = (Cm)httpSession.getAttribute("cm");
        Timestamp fromTime = TimeHelper.parseTimestamp(from, null);
        Timestamp toTime = TimeHelper.parseTimestamp(to, null);
        List<Order> list = orderDao.findByCmByVisitorByTime(cm.getId(), visitors, fromTime, toTime);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Order order : list) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.putPOJO("order", order);
            fillOrder(order, objectNode);
            arrayNode.add(objectNode);
        }
        return arrayNode;
    }
    
    
    @GetMapping("/cm/reports/received")
    public ArrayNode getReceivedReports(HttpSession httpSession){
        if (httpSession.getAttribute("cm") == null){
            return null;
        }
        Cm cm = (Cm)httpSession.getAttribute("cm");
        List<Order> list = orderDao.receivedByCm(cm.getId());
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Order order : list) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.putPOJO("order", order);
            fillOrder(order, objectNode);
            arrayNode.add(objectNode);
        }
        return arrayNode;
    }
    
    
    @GetMapping("/cm/reports/finished")
    public ArrayNode getFinishedReports(HttpSession httpSession){
        if (httpSession.getAttribute("cm") == null){
            return null;
        }
        Cm cm = (Cm)httpSession.getAttribute("cm");
        List<Order> list = orderDao.findFinishedByCm(cm.getId());
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Order order : list) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.putPOJO("order", order);
            fillOrder(order, objectNode);
            arrayNode.add(objectNode);
        }
        return arrayNode;
    }
}
