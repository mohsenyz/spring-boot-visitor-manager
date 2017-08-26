/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.visitor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.sina.sina.dao.CityDao;
import com.sina.sina.dao.DrDao;
import com.sina.sina.dao.DsDao;
import com.sina.sina.dao.OrderDao;
import com.sina.sina.models.City;
import com.sina.sina.models.Cm;
import com.sina.sina.models.Dr;
import com.sina.sina.models.Ds;
import com.sina.sina.models.Order;
import com.sina.sina.pojo.Docs_1;
import com.sina.sina.pojo.Drugs_1;
import com.sina.sina.pojo.Drugs_2;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

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
    
    

    @GetMapping(value = "/visitor/visit")
    public String newVisit(@RequestParam(value = "json", required = false) String jsonBody) throws IOException {
        jsonBody = "{\"result_drugs\" : [{\"drug\" : 1},{\"drug\" : 2}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonBody);
        Drugs_1[] result_drugs = objectMapper.treeToValue(jsonNode.get("result_drugs"), Drugs_1[].class);
        Drugs_1[] content_drugs = objectMapper.treeToValue(jsonNode.get("content_drugs"), Drugs_1[].class);
        Docs_1[] docs = objectMapper.treeToValue(jsonNode.get("docs"), Docs_1[].class);
        String visitor = jsonNode.get("visitor").asText();
        String doctor = jsonNode.get("doctor").asText();
        String drugstore = jsonNode.get("drugstore").asText();
        Drugs_2[] noskhe = objectMapper.treeToValue(jsonNode.get("noskhe"), Drugs_2[].class);
        Drugs_2[] not_noskhe = objectMapper.treeToValue(jsonNode.get("not_noskhe"), Drugs_2[].class);
        Drugs_2[] known_drugs = objectMapper.treeToValue(jsonNode.get("known_drugs"), Drugs_2[].class);
        Drugs_2[] exists_drugs = objectMapper.treeToValue(jsonNode.get("exists_drugs"), Drugs_2[].class);
        Drugs_2[] same_drugs = objectMapper.treeToValue(jsonNode.get("exists_drugs"), Drugs_2[].class);
        String next_session_date = jsonNode.get("next_session_date").asText();
        String visit_time = jsonNode.get("visit_time").asText();
        String visit_date = jsonNode.get("visit_date").asText();
        String urgency = jsonNode.get("urgency").asText();
        if (drugstore.equals("make_new")){
            JsonNode doctorDetails = jsonNode.get("ds");
            String ds_name = doctorDetails.get("ds_name").asText();
            String ds_fanni_name = doctorDetails.get("ds_fanni_name").asText();
            String ds_phone_number = doctorDetails.get("phone_number").asText();
            String ds_address = doctorDetails.get("address").asText();
            String ds_knowledge = doctorDetails.get("knowledge").asText();
            String ds_best_visit_time = doctorDetails.get("best_visit_time").asText();
            String ds_type = doctorDetails.get("type").asText();
            String ds_company_name_ack = doctorDetails.get("company_name_ack").asText();
            Ds ds = new Ds();
            ds.setName(ds_name);
            ds.setClerkName(ds_fanni_name);
            ds.setPhone(ds_phone_number);
            ds.setAddress(ds_address);
            ds.setCompanyProductsAck(ds_knowledge);
            ds.setBestTime(Integer.parseInt(ds_best_visit_time));
            ds.setType(Integer.parseInt(ds_type));
            ds.setCompanyNameAckReason(Integer.parseInt(ds_company_name_ack));
            dsDao.insert(ds);
        }
        if (doctor.equals("make_new")){
            JsonNode doctorDetails = jsonNode.get("dr");
            String dr_name = doctorDetails.get("name").asText();
            String dr_expertise = doctorDetails.get("expertise").asText();
            String dr_best_visit_time = doctorDetails.get("best_visit_time").asText();
            String dr_fixed_phone = doctorDetails.get("fixed_phone").asText();
            String dr_mobile = doctorDetails.get("mobile").asText();
            String dr_place = doctorDetails.get("place").asText();
            String dr_address = doctorDetails.get("address").asText();
            String dr_email = doctorDetails.get("email").asText();
            String dr_pezeshk = doctorDetails.get("pezeshk").asText();
            String dr_consent = doctorDetails.get("dr_consent").asText();
            String dr_company_product_ack = doctorDetails.get("company_products_ack").asText();
            Dr dr = new Dr();
            dr.setName(dr_name);
            dr.setExpert(dr_expertise);
            dr.setBestVisitTime1(Integer.parseInt(dr_best_visit_time));
            dr.setFixedPhone(dr_fixed_phone);
            dr.setMobile(dr_mobile);
            dr.setPlace(dr_place);
            dr.setAddress(dr_address);
            dr.setEmail(dr_email);
            dr.setPezeshk(dr_pezeshk);
            dr.setCompanyProductsPop(dr_consent);
            dr.setCompanyProductsAck(dr_company_product_ack);
            drDao.insert(dr);
        }
        String visit_place = jsonNode.get("visit_place").asText();
        String visit_place_name = jsonNode.get("visit_place_name").asText();
        String dr_visit_suggestion = jsonNode.get("dr_visit_suggestion").asText();
        String ds_visited_name = jsonNode.get("ds_visited_name").asText();
        String ds_visited_exp = jsonNode.get("ds_visited_exp").asText();
        String ds_visited_phone = jsonNode.get("ds_visited_phone").asText();
        String ds_idea = jsonNode.get("ds_idea").asText();
        String ds_pop_ds_name = jsonNode.get("ds_pop_ds_name").asText();
        String ds_opponent = jsonNode.get("ds_opponent").asText();
        String ds_dr_index = jsonNode.get("ds_dr_index").asText();
        String cm = jsonNode.get("cm").asText();
        String given = jsonNode.get("given").asText();
        String given_etc = jsonNode.get("given_etc").asText();
        String needed = jsonNode.get("needed").asText();
        String result = jsonNode.get("result").asText();
        return null;
    }
    
    /*
    $scope.visit_place = null;
    $scope.visit_place_name = null;
    $scope.dr_visit_suggestion = null;
    $scope.ds_visited_name = null;
    $scope.ds_visited_exp = null;
    $scope.ds_visited_phone = null;
    $scope.ds_idea = null;
    $scope.ds_pop_ds_name = null;
    $scope.ds_opponent = null;
    $scope.ds_dr_index = null;
    $scope.cm = null;
    $scope.given = null;
    $scope.given_etc = null;
    $scope.needed = null;
    $scope.result = null;
    */
}
