/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.visitor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.sina.dao.CmDao;
import com.sina.sina.dao.DocsDao;
import com.sina.sina.dao.DrDao;
import com.sina.sina.dao.DsDao;
import com.sina.sina.dao.OrderDao;
import com.sina.sina.dao.OrderDrugsDao;
import com.sina.sina.dao.VisitorCityDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Cm;
import com.sina.sina.models.Docs;
import com.sina.sina.models.Dr;
import com.sina.sina.models.Ds;
import com.sina.sina.models.Order;
import com.sina.sina.models.OrderDrugs;
import com.sina.sina.models.Visitor;
import com.sina.sina.pojo.Docs_1;
import com.sina.sina.pojo.Drugs_1;
import com.sina.sina.pojo.Drugs_2;
import com.sina.sina.utils.IntegerHelper;
import com.utils.http.Uploader;
import com.utils.time.TimeHelper;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mphj
 */
@RestController
public class NewVisitController {

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

    @Autowired
    VisitorCityDao visitorCityDao;

    @Autowired
    VisitorDao visitorDao;

    @Autowired
    CmDao cmDao;

    @Value("${mphj.filesystem.uploadpath}")
    String uploadPath;

    @PostMapping("/visitor/visit/new")
    public String newVisit(@RequestParam(value = "json", required = false) String jsonBody,
            HttpServletRequest httpServletRequest,
            HttpSession httpSession) throws IOException {
        if (httpSession.getAttribute("visitor") == null) {
            return "403";
        }
        Visitor currVisitor = (Visitor) httpSession.getAttribute("visitor");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Order order = new Order();
        JsonNode jsonNode = objectMapper.readTree(jsonBody);
        String visitor = "", doctor = "", drugstore = "", next_session_date = "", visit_time = "", visit_date = "";
        try{
             visitor = jsonNode.get("visitor").asText();
        }catch (Exception e){}
        try{
             doctor = jsonNode.get("doctor").asText();
        }catch (Exception e){}
        try{
             drugstore = jsonNode.get("drugstore").asText();
        }catch (Exception e){}
        try{
             next_session_date = jsonNode.get("next_session_date").asText();
        }catch (Exception e){}
        try{
             visit_time = jsonNode.get("visit_time").asText();
        }catch (Exception e){}
        try{
             visit_date = jsonNode.get("visit_date").asText();
        }catch (Exception e){}
        String urgency = "", content = "", desc = "";
        try{
             urgency = jsonNode.get("urgency").asText();
        }catch (Exception e){}
        try{
             content = jsonNode.get("content").asText();
        }catch (Exception e){}
        try{
             desc = jsonNode.get("desc").asText();
        }catch (Exception e){}
        order.setVid(currVisitor.getId());
        try {
            order.setForwardToVid(IntegerHelper.parseInt(visitor));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            order.setNextSession(TimeHelper.parseTimestamp(next_session_date, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (visit_time.contains("am")) {
            order.setCreatedAtAp(1);
        } else {
            order.setCreatedAtAp(2);
        }
        order.setCreatedAt(TimeHelper.parseTimestamp(visit_date, null));
        order.setUrgency(IntegerHelper.parseInt(urgency));
        order.setContent(content);
        order.setDesc(desc);
        int cid = visitorCityDao.findByVid(currVisitor.getId()).get(0).getCid();

        /**
         * *
         *
         */
        String visit_place = "", visit_place_name = "", dr_visit_suggestion = "", ds_visited_name = "";
        try{
             visit_place = jsonNode.get("visit_place").asText();
        }catch (Exception e){}
        try{
             visit_place_name = jsonNode.get("visit_place_name").asText();
        }catch (Exception e){}
        try{
             dr_visit_suggestion = jsonNode.get("dr_visit_suggestion").asText();
        }catch (Exception e){}
        try{
             ds_visited_name = jsonNode.get("ds_visited_name").asText();
        }catch (Exception e){}
        String ds_visited_exp = "", ds_visited_phone = "", ds_idea = "", ds_pop_ds_name = "", ds_opponent = "";
        try {
             ds_visited_exp = jsonNode.get("ds_visited_exp").asText();
        }catch (Exception e){}
        try{
             ds_visited_phone = jsonNode.get("ds_visited_phone").asText();
        }catch (Exception e){}
        try{
             ds_idea = jsonNode.get("ds_idea").asText();
        }catch (Exception e){}
        try{
             ds_pop_ds_name = jsonNode.get("ds_pop_ds_name").asText();
        }catch (Exception e){}
        try{
             ds_opponent = jsonNode.get("ds_opponent").asText();
        }catch (Exception e){}
        String ds_dr_index = "", cm = "", given_etc = "", given = "", needed = "";
        try{
             ds_dr_index = jsonNode.get("ds_dr_index").asText();
        }catch (Exception e){
        }
        try{
             cm = jsonNode.get("cm").asText();
        }catch (Exception e){
        }
        try {
             given = jsonNode.get("given").asText();
        }catch (Exception e){}
        try{
             given_etc = jsonNode.get("given_etc").asText();
        }catch (Exception e){ }
        try{
             needed = jsonNode.get("needed").asText();
        }catch (Exception r){}
        String result = "";
        try{
             result = jsonNode.get("result").asText();
        }catch (Exception e){}

        /**
         * *
         *
         */
        if (drugstore != null && !drugstore.equals("none")) {
            if (drugstore.equals("make_new")) {
                JsonNode doctorDetails = jsonNode.get("ds");
                String ds_name = "", ds_fanni_name = "", ds_phone_number = "", ds_address = "", ds_knowledge = "",
                        ds_best_visit_time = "", ds_type = "", ds_company_name_ack = "",
                        ds_username = "", ds_password = "";
                try{
                     ds_name = doctorDetails.get("ds_name").asText();
                }catch (Exception e){}
                try{
                     ds_fanni_name = doctorDetails.get("ds_fanni_name").asText();
                }catch (Exception e){}
                try{
                     ds_phone_number = doctorDetails.get("phone_number").asText();
                }catch (Exception e){}
                try{
                     ds_address = doctorDetails.get("address").asText();
                }catch (Exception e){}
                try{
                     ds_knowledge = doctorDetails.get("knowledge").asText();
                }catch (Exception e){}
                try {
                     ds_best_visit_time = doctorDetails.get("best_visit_time").asText();
                }catch (Exception e){}
                try{
                     ds_type = doctorDetails.get("type").asText();
                }catch (Exception e){}
                try{
                     ds_company_name_ack = doctorDetails.get("company_name_ack").asText();
                }catch (Exception e){}
                try{
                    ds_username = doctorDetails.get("ds_username").asText();
                }catch (Exception e){}
                try{
                    ds_password = doctorDetails.get("ds_password").asText();
                }catch (Exception e){}

                Ds ds = new Ds();
                ds.setName(ds_name);
                ds.setClerkName(ds_fanni_name);
                ds.setPhone(ds_phone_number);
                ds.setCity(cid);
                ds.setAddress(ds_address);
                ds.setUsername(ds_username);
                ds.setPassword(ds_password);
                ds.setCompanyProductsAck(ds_knowledge);
                ds.setCreatedAt(order.getCreatedAt());
                ds.setBestTime(IntegerHelper.parseInt(ds_best_visit_time));
                ds.setType(IntegerHelper.parseInt(ds_type));
                ds.setCompanyNameAckReason(IntegerHelper.parseInt(ds_company_name_ack));
                dsDao.insert(ds);
                order.setDsid(ds.getId());
            } else {
                try {
                    order.setDsid(IntegerHelper.parseInt(drugstore));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                order.setDsVisitedName(ds_visited_name);
                order.setDsVisitedJob(IntegerHelper.parseInt(ds_visited_exp));
                order.setDsVisitedPhone(ds_visited_phone);
                order.setDsIdea(ds_idea);
                order.setDsPopCm(ds_pop_ds_name);
                order.setDsRival(ds_opponent);
                order.setDsIndexDr(ds_dr_index);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (doctor != null && !doctor.equals("none")) {
            if (doctor.equals("make_new")) {
                JsonNode doctorDetails = jsonNode.get("dr");
                String  dr_name = "", dr_expertise = "", dr_best_visit_time = "", dr_fixed_phone = "", dr_mobile = "", dr_place = "",
                        dr_address = "", dr_email = "", dr_pezeshk = "", dr_consent = "", dr_company_product_ack = "";
                try{
                     dr_name = doctorDetails.get("name").asText();
                }catch (Exception e){}
                try{
                     dr_expertise = doctorDetails.get("expertise").asText();
                }catch (Exception e){}
                try{
                     dr_best_visit_time = doctorDetails.get("best_visit_time").asText();
                }catch (Exception e){}
                try{
                     dr_fixed_phone = doctorDetails.get("fixed_phone").asText();
                }catch (Exception e){}
                try{
                     dr_mobile = doctorDetails.get("mobile").asText();
                }catch (Exception e){}
                try{
                     dr_place = doctorDetails.get("place").asText();
                }catch (Exception e){}
                try{
                     dr_address = doctorDetails.get("address").asText();
                }catch (Exception e){}
                try{
                     dr_email = doctorDetails.get("email").asText();
                }catch (Exception e){}
                try{
                     dr_pezeshk = doctorDetails.get("pezeshk").asText();
                }catch (Exception e){}
                try{
                     dr_consent = doctorDetails.get("consent").asText();
                }catch (Exception e){}
                try{
                     dr_company_product_ack = doctorDetails.get("company_products_ack").asText();
                }catch (Exception e){}

                Dr dr = new Dr();
                dr.setName(dr_name);
                dr.setExpert(dr_expertise);
                dr.setBestVisitTime1(IntegerHelper.parseInt(dr_best_visit_time));
                dr.setFixedPhone(dr_fixed_phone);
                dr.setMobile(dr_mobile);
                dr.setPlace(dr_place);
                dr.setAddress(dr_address);
                dr.setEmail(dr_email);
                dr.setPezeshk(dr_pezeshk);
                dr.setCompanyProductsPop(dr_consent);
                dr.setCompanyProductsAck(dr_company_product_ack);
                drDao.insert(dr);
                order.setDrid(dr.getId());
            } else {
                try {
                    order.setDrid(IntegerHelper.parseInt(doctor));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                order.setDrVisitPlace(IntegerHelper.parseInt(visit_place));
                order.setDrVisitPlaceName(visit_place_name);
                order.setDrSuggestion(dr_visit_suggestion);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String cmName = "", cmPhone = "";
        try{
            cmName = jsonNode.get("cm_name").asText();
        }catch (Exception e){
        }
        try{
            cmPhone = jsonNode.get("cm_phone").asText();
        }catch (Exception e){
        }

        // @TODO set given
        order.setGivenDocument(given_etc);
        order.setNeededDocument(needed);
        order.setResult(result);
        order.setCmName(cmName);
        order.setCmPhone(cmPhone);
        orderDao.insert(order);
        Drugs_1[] result_drugs = objectMapper.treeToValue(jsonNode.get("result_drugs"), Drugs_1[].class);
        for (Drugs_1 result_drug : result_drugs) {
            OrderDrugs orderDrugs = new OrderDrugs();
            orderDrugs.setDrugId(result_drug.drug);
            orderDrugs.setType(OrderDrugs.RESULT_DRUGS);
            orderDrugs.setCount(result_drug.num);
            orderDrugs.setVisitDesc(result_drug.visit);
            orderDrugs.setOid(order.getId());
            orderDrugsDao.insert(orderDrugs);
        }
        Drugs_1[] content_drugs = objectMapper.treeToValue(jsonNode.get("content_drugs"), Drugs_1[].class);
        for (Drugs_1 content_drug : content_drugs) {
            OrderDrugs orderDrugs = new OrderDrugs();
            orderDrugs.setDrugId(content_drug.drug);
            orderDrugs.setType(OrderDrugs.CONTENT_DRUGS);
            orderDrugs.setCount(content_drug.num);
            orderDrugs.setVisitDesc(content_drug.visit);
            orderDrugs.setOid(order.getId());
            orderDrugsDao.insert(orderDrugs);
        }
        Docs_1[] docs = objectMapper.treeToValue(jsonNode.get("docs"), Docs_1[].class);
        for (Docs_1 doc : docs) {
            Docs doc1 = new Docs();
            doc1.setDesc(doc.desc);
            doc1.setName(UUID.randomUUID().toString() + "_" + doc.file);
            try {
                Uploader.from(httpServletRequest.getPart(doc.file))
                        .withName(doc1.getName())
                        .saveInto(uploadPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            doc1.setOid(order.getId());
            doc1.setType(Docs.ORDER_DOCS);
            docsDao.insert(doc1);
        }
        Drugs_2[] noskhe = objectMapper.treeToValue(jsonNode.get("noskhe"), Drugs_2[].class);
        for (Drugs_2 noskh : noskhe) {
            OrderDrugs orderDrugs = new OrderDrugs();
            orderDrugs.setType(OrderDrugs.NOSKHE);
            orderDrugs.setDrugId(noskh.drug);
            orderDrugs.setVisitDesc(noskh.desc);
            orderDrugs.setReason(noskh.reason);
            orderDrugs.setOid(order.getId());
            orderDrugsDao.insert(orderDrugs);
        }
        Drugs_2[] not_noskhe = objectMapper.treeToValue(jsonNode.get("not_noskhe"), Drugs_2[].class);
        for (Drugs_2 noskh : not_noskhe) {
            OrderDrugs orderDrugs = new OrderDrugs();
            orderDrugs.setType(OrderDrugs.NOT_NOSKHE);
            orderDrugs.setDrugId(noskh.drug);
            orderDrugs.setVisitDesc(noskh.desc);
            orderDrugs.setReason(noskh.reason);
            orderDrugs.setOid(order.getId());
            orderDrugsDao.insert(orderDrugs);
        }
        Drugs_2[] known_drugs = objectMapper.treeToValue(jsonNode.get("known_drugs"), Drugs_2[].class);
        for (Drugs_2 noskh : known_drugs) {
            OrderDrugs orderDrugs = new OrderDrugs();
            orderDrugs.setType(OrderDrugs.KNOWN_DRUGS);
            orderDrugs.setDrugId(noskh.drug);
            orderDrugs.setVisitDesc(noskh.desc);
            orderDrugs.setReason(noskh.reason);
            orderDrugs.setOid(order.getId());
            orderDrugsDao.insert(orderDrugs);
        }
        Drugs_2[] exists_drugs = objectMapper.treeToValue(jsonNode.get("exists_drugs"), Drugs_2[].class);
        for (Drugs_2 noskh : exists_drugs) {
            OrderDrugs orderDrugs = new OrderDrugs();
            orderDrugs.setType(OrderDrugs.EXISTS_DRUGS);
            orderDrugs.setDrugId(noskh.drug);
            orderDrugs.setVisitDesc(noskh.desc);
            orderDrugs.setReason(noskh.reason);
            orderDrugs.setOid(order.getId());
            orderDrugsDao.insert(orderDrugs);
        }
        Drugs_1[] same_drugs = objectMapper.treeToValue(jsonNode.get("same_drugs"), Drugs_1[].class);
        for (Drugs_1 noskh : same_drugs) {
            OrderDrugs orderDrugs = new OrderDrugs();
            orderDrugs.setType(OrderDrugs.SAME_DRUGS);
            orderDrugs.setDrugId(noskh.drug);
            orderDrugs.setCount(noskh.num);
            orderDrugs.setOid(order.getId());
            orderDrugsDao.insert(orderDrugs);
        }
        // @TODO insert esales drugs
        return "done";
    }

    @GetMapping("/visitor/ds")
    public List<Ds> getDsList(HttpSession httpSession) {
        if (httpSession.getAttribute("visitor") == null) {
            throw new SecurityException("403");
        }
        return dsDao.listAll();
    }

    @GetMapping("/visitor/dr")
    public List<Dr> getDrList(HttpSession httpSession) {
        if (httpSession.getAttribute("visitor") == null) {
            throw new SecurityException("403");
        }
        return drDao.listAll();
    }

    @GetMapping("/visitor/visitor")
    public List<Visitor> getVisitorList(HttpSession httpSession) {
        if (httpSession.getAttribute("visitor") == null) {
            throw new SecurityException("403");
        }
        return visitorDao.listAll();
    }

    @GetMapping("/visitor/cm")
    public List<Cm> getCmList(HttpSession httpSession) {
        if (httpSession.getAttribute("visitor") == null) {
            throw new SecurityException("403");
        }
        return cmDao.listAll();
    }

}
