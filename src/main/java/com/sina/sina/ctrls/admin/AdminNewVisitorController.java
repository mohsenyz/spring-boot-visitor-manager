/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.admin;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.sina.dao.DocsDao;
import com.sina.sina.dao.VisitorCityDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Docs;
import com.sina.sina.models.Visitor;
import com.sina.sina.models.VisitorCity;
import com.sina.sina.pojo.Docs_1;
import com.sina.sina.utils.IntegerHelper;
import com.utils.http.Uploader;
import com.utils.time.TimeHelper;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mphj
 */
@RestController
public class AdminNewVisitorController {

    @Autowired
    DocsDao docsDao;

    @Autowired
    VisitorDao visitorDao;

    @Autowired
    VisitorCityDao visitorCityDao;
    
    @Value("${mphj.filesystem.uploadpath}")
    String uploadPath;

    @PostMapping("/admin/visitor/new")
    public String newVisitorAdmin(@RequestParam("json") String jsonBody,
            HttpSession httpSession,
            HttpServletRequest httpServletRequest) throws IOException {
        if (httpSession.getAttribute("admin") == null) {
            return "403";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(jsonBody);
        Visitor visitor = new Visitor();
        String fname = "", lname = "", birthday = "", code = "";
        try{
             fname = jsonNode.get("fname").asText();
        }catch (Exception e){}
        try{
             lname = jsonNode.get("lname").asText();
        }catch (Exception e){}
        try {
             birthday = jsonNode.get("birthday").asText();
        } catch (Exception e){}
        try{
             code = jsonNode.get("code").asText();
        }catch (Exception e){}
        String fixed_phone = "", mobile = "", ack_type = "", grade = "", uname = "";
        try{
             fixed_phone = jsonNode.get("fixed_phone").asText();
        }catch (Exception e){}
        try{
             mobile = jsonNode.get("mobile").asText();
        }catch (Exception e){ }
        try{
             ack_type = jsonNode.get("type_ack").asText();
        }catch (Exception e){}
        try{
             grade = jsonNode.get("grade").asText();
        }catch (Exception e){}
        try{
             uname = jsonNode.get("uname").asText();
        }catch (Exception e){ }
        String password = "", address = "", grade_exp = "", work_exp = "";
        try {
             password = jsonNode.get("password").asText();
        }catch (Exception e){}
        try{
             address = jsonNode.get("address").asText();
        }catch (Exception e){}
        try{
             grade_exp = jsonNode.get("grade_exp").asText();
        }catch (Exception e){}
        try{
             work_exp = jsonNode.get("work_exp").asText();
        }catch (Exception e){}
        String desc = "", city = "";
        try{
             desc = jsonNode.get("desc").asText();
        }catch (Exception e){}
        try{
             city = jsonNode.get("city").asText();
        }catch (Exception e){}
        visitor.setFname(fname);
        visitor.setLname(lname);
        visitor.setBirthday(birthday);
        visitor.setCode(code);
        visitor.setEnabled(true);
        visitor.setFixedPhone(fixed_phone);
        visitor.setMobile(mobile);
        visitor.setAck(ack_type);
        visitor.setGrade(grade);
        visitor.setAddress(address);
        visitor.setUsername(uname);
        visitor.setPassword(password);
        visitor.setGradeExp(grade_exp);
        visitor.setWorkExp(work_exp);
        visitor.setDesc(desc);
        visitor.setCreatedAt(TimeHelper.getCurrentTimestamp());
        visitorDao.insert(visitor);

        VisitorCity visitorCity = new VisitorCity();
        visitorCity.setCid(IntegerHelper.parseInt(city));
        visitorCity.setVid(visitor.getId());
        visitorCityDao.insert(visitorCity);

        Docs_1 doc = objectMapper.treeToValue(jsonNode.get("pic1"), Docs_1.class);
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
        doc1.setVid(visitor.getId());
        doc1.setType(Docs.VISITOR_DOC1);
        docsDao.insert(doc1);

        doc = objectMapper.treeToValue(jsonNode.get("pic2"), Docs_1.class);
        Docs doc2 = new Docs();
        doc2.setDesc(doc.desc);
        doc2.setVid(visitor.getId());
        doc2.setName(UUID.randomUUID().toString() + "_" + doc.file);
        try {
            Uploader.from(httpServletRequest.getPart(doc.file))
                    .withName(doc2.getName())
                    .saveInto(uploadPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doc2.setType(Docs.VISITOR_DOC2);
        docsDao.insert(doc2);

        Docs_1[] docs = objectMapper.treeToValue(jsonNode.get("docs"), Docs_1[].class);
        for (Docs_1 doc22 : docs) {
            Docs doc11 = new Docs();
            doc11.setDesc(doc22.desc);
            doc11.setName(UUID.randomUUID().toString() + "_" + doc22.file);
            try {
                Uploader.from(httpServletRequest.getPart(doc22.file))
                        .withName(doc11.getName())
                        .saveInto(uploadPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            doc11.setType(Docs.VISITOR_DOCS);
            doc11.setVid(visitor.getId());
            docsDao.insert(doc1);
        }
        return "done";
    }
}
