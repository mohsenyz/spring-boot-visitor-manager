/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.admin;

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
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @Autowired
    DrDao drDao;
    
    @Autowired
    DsDao dsDao;
    
    @Autowired
    OrderDrugsDao orderDrugsDao;
    
    @Autowired
    DrugsDao drugsDao;

    @GetMapping("/admin/requests")
    public ArrayNode listRequests(HttpSession httpSession) {
        if (httpSession.getAttribute("admin") == null) {
            return null;
        }
        List<Order> list = orderDao.findAllDsOrder();
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

    @GetMapping("/admin/reports/visits")
    public ArrayNode listVisits(HttpSession httpSession,
            @RequestParam("name") String cmName,
            @RequestParam("phone") String cmPhone,
            @RequestParam("v") String v,
            @RequestParam("from") String from,
            @RequestParam("to") String to) {
        if (httpSession.getAttribute("admin") == null) {
            return null;
        }
        Timestamp fromTime = null, toTime = null;
        if (from != null && !from.isEmpty())
            fromTime = TimeHelper.parseTimestamp(from, null);
        if (to != null && !to.isEmpty())
            toTime = TimeHelper.parseTimestamp(to, null);
        System.out.println(fromTime.toString() + toTime.toString());
        List<Order> list = orderDao.findByCmsByVisitorByTime(cmPhone, cmName, v, fromTime, toTime);
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

    @GetMapping("/admin/visitor")
    public List<Visitor> listVisitor(HttpSession httpSession) {
        return visitorDao.listAll();
    }

    @GetMapping("/admin/cm")
    public List<Cm> listCm(HttpSession httpSession) {
        return cmDao.listAll();
    }


    @GetMapping("/admin/ds/unaccepted")
    public List<Ds> unacceptedDs(HttpSession httpSession){
        if (httpSession.getAttribute("admin") == null) {
            return new ArrayList<>();
        }
        return dsDao.findUnAccepted();
    }




    @GetMapping("/admin/ds/accept")
    public String acceptDs(HttpSession httpSession,
                           @RequestParam("id") int id) {
        if (httpSession.getAttribute("admin") == null) {
            return null;
        }
        Ds ds = dsDao.findById(id);
        ds.setVerified(true);
        dsDao.update(ds);
        return "done";
    }


    @GetMapping("/admin/req/accept")
    public String acceptReq(HttpSession httpSession,
                           @RequestParam("id") int id) {
        if (httpSession.getAttribute("admin") == null) {
            return null;
        }
        Order order = orderDao.findById(id);
        order.setSubmited(true);
        order.setSubmitTime(TimeHelper.getCurrentTimestamp());
        order.setFromId(null);
        orderDao.update(order);
        return "done";
    }


    @GetMapping("/admin/req/trans")
    public String transReq(HttpSession httpSession,
                            @RequestParam("id") int id,
                           @RequestParam("to") int to) {
        if (httpSession.getAttribute("admin") == null) {
            return null;
        }
        Order order = orderDao.findById(id);
        order.setSubmited(false);
        order.setSubmitTime(null);
        order.setFromId(to);
        orderDao.update(order);
        return "done";
    }
}
