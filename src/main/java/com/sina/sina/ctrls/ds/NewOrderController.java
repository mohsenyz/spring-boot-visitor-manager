/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.ds;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.sina.dao.DocsDao;
import com.sina.sina.dao.DrDao;
import com.sina.sina.dao.DsDao;
import com.sina.sina.dao.OrderDao;
import com.sina.sina.dao.OrderDrugsDao;
import com.sina.sina.models.Ds;
import com.sina.sina.models.Order;
import com.sina.sina.models.OrderDrugs;
import com.sina.sina.pojo.Drugs_1;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mphj
 */

@RestController
public class NewOrderController {
    
    @Autowired
    DsDao dsDao;

    @Autowired
    DrDao drDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderDrugsDao orderDrugsDao;

    @Autowired
    DocsDao docsDao;
    
    @PostMapping("/ds/order")
    public String newOrder(@RequestParam("json") String jsonBody,
            HttpSession httpSession) throws IOException{
        if (httpSession.getAttribute("ds") == null){
            return "403";
        }
        Ds currVisitor = (Ds)httpSession.getAttribute("ds");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Order order = new Order();
        JsonNode jsonNode = objectMapper.readTree(jsonBody);
        String content = jsonNode.get("request").asText();
        String desc = jsonNode.get("desc").asText();
        order.setDsid(currVisitor.getId());
        order.setContent(content);
        order.setDesc(desc);
        orderDao.insert(order);
        Drugs_1[] result_drugs = objectMapper.treeToValue(jsonNode.get("content_drugs"), Drugs_1[].class);
        for (Drugs_1 result_drug : result_drugs) {
            OrderDrugs orderDrugs = new OrderDrugs();
            orderDrugs.setDrugId(result_drug.drug);
            orderDrugs.setType(OrderDrugs.DS_ORDER_DRUGS);
            orderDrugs.setCount(result_drug.num);
            orderDrugs.setOid(order.getId());
            orderDrugsDao.insert(orderDrugs);
        }
        return "done";
    }
}
