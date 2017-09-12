/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.ds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sina.sina.dao.DrugsDao;
import com.sina.sina.dao.DsDao;
import com.sina.sina.dao.OrderDao;
import com.sina.sina.dao.OrderDrugsDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Drugs;
import com.sina.sina.models.Ds;
import com.sina.sina.models.Order;
import com.sina.sina.models.OrderDrugs;
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
    OrderDrugsDao orderDrugsDao;
    
    @Autowired
    DrugsDao drugsDao;
    
    @Autowired
    DsDao dsDao;
    
    @GetMapping("/ds/reports")
    public ArrayNode orders(HttpSession httpSession){
        if (httpSession.getAttribute("ds") == null){
            return null;
        }
        Ds ds = (Ds)httpSession.getAttribute("ds");
        List<Order> list = orderDao.findByDs(ds.getId());
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
    
    
    
    @GetMapping("/ds/reports/seen")
    public ArrayNode seenOrder(HttpSession httpSession){
        if (httpSession.getAttribute("ds") == null){
            return null;
        }
        Ds ds = (Ds)httpSession.getAttribute("ds");
        List<Order> list = orderDao.findSeenByDs(ds.getId());
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
    
    
    @GetMapping("/ds/reports/finished")
    public ArrayNode finishedOrder(HttpSession httpSession){
        if (httpSession.getAttribute("ds") == null){
            return null;
        }
        Ds ds = (Ds)httpSession.getAttribute("ds");
        List<Order> list = orderDao.findFinishedByDs(ds.getId());
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
    
    
    public void fillOrder(Order order, ObjectNode objectNode){
        List<OrderDrugs> list = orderDrugsDao.findByOrder(order.getId());
        ArrayNode arrayNode = objectNode.putArray("order_drugs");
        for (OrderDrugs orderDrug : list){
            Drugs drug = drugsDao.findById(orderDrug.getDrugId());
            orderDrug.setDrugName(drug.getName());
            arrayNode.addPOJO(orderDrug);
        }
    }
    
}
