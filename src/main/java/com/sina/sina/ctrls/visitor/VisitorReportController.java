/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.visitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sina.sina.dao.CmDao;
import com.sina.sina.dao.DrDao;
import com.sina.sina.dao.DsDao;
import com.sina.sina.dao.OrderDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Cm;
import com.sina.sina.models.Dr;
import com.sina.sina.models.Ds;
import com.sina.sina.models.Order;
import com.sina.sina.models.Visitor;
import java.util.List;
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
public class VisitorReportController {

    @Autowired
    OrderDao orderDao;

    @Autowired
    DrDao drDao;

    @Autowired
    DsDao dsDao;

    @Autowired
    VisitorDao visitorDao;

    @Autowired
    CmDao cmDao;

    @GetMapping("/visitor/reports")
    public ArrayNode getReports(HttpSession httpSession) {
        if (httpSession.getAttribute("visitor") == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        Visitor currVisitor = (Visitor) httpSession.getAttribute("visitor");
        List<Order> list = orderDao.findByVid(currVisitor.getId());
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
        if (order.getCmid() != null) {
            Cm visitor = cmDao.findById(order.getCmid());
            objectNode.putPOJO("cm", visitor);
        }
        if (order.getVid() != null){
            Visitor visitor = visitorDao.findById(order.getVid());
            objectNode.putPOJO("v", visitor);
        }
    }

    @GetMapping("/visitor/reports/received")
    public ArrayNode getReceivedOrder(HttpSession httpSession) {
        if (httpSession.getAttribute("visitor") == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        Visitor currVisitor = (Visitor) httpSession.getAttribute("visitor");
        List<Order> list = orderDao.findReceivedRequest(currVisitor.getId());
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Order order : list) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.putPOJO("order", order);
            fillOrder(order, objectNode);
            arrayNode.add(objectNode);
        }
        return arrayNode;
    }

    @GetMapping("/visitor/reports/next")
    public ArrayNode getNextOrder(HttpSession httpSession) {
        if (httpSession.getAttribute("visitor") == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        Visitor currVisitor = (Visitor) httpSession.getAttribute("visitor");
        List<Order> list = orderDao.findNextVisit(currVisitor.getId());
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Order order : list) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.putPOJO("order", order);
            fillOrder(order, objectNode);
            arrayNode.add(objectNode);
        }
        return arrayNode;
    }

    @GetMapping("/visitor/reports/forwarded")
    public ArrayNode getForwardedOrder(HttpSession httpSession) {
        if (httpSession.getAttribute("visitor") == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        Visitor currVisitor = (Visitor) httpSession.getAttribute("visitor");
        List<Order> list = orderDao.findForwarded(currVisitor.getId());
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Order order : list) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.putPOJO("order", order);
            fillOrder(order, objectNode);
            arrayNode.add(objectNode);
        }
        return arrayNode;
    }

    @GetMapping("/visitor/profile")
    public Visitor getProfile(HttpSession httpSession) {
        if (httpSession.getAttribute("visitor") == null) {
            return null;
        }
        Visitor currVisitor = (Visitor) httpSession.getAttribute("visitor");
        return visitorDao.findById(currVisitor.getId());
    }

    @GetMapping("/visitor/login")
    public String login(HttpSession httpSession, @RequestParam("id") int id) {
        Visitor vis = new Visitor();
        vis.setId(id);
        httpSession.setAttribute("visitor", vis);
        return "done";
    }

    @GetMapping("/visitor/is")
    public boolean isLogin(HttpSession httpSession) {
        return httpSession.getAttribute("visitor") != null;
    }

}
