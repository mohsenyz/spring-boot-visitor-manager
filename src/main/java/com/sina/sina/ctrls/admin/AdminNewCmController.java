/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.admin;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.sina.dao.CmCityDao;
import com.sina.sina.dao.CmDao;
import com.sina.sina.dao.DocsDao;
import com.sina.sina.models.Cm;
import com.sina.sina.models.CmCity;
import com.sina.sina.models.Docs;
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
public class AdminNewCmController {

    @Autowired
    DocsDao docsDao;

    @Autowired
    CmDao cmDao;

    @Autowired
    CmCityDao cmCityDao;
    
    @Value("${mphj.filesystem.uploadpath}")
    String uploadPath;

    @PostMapping("/admin/cm/new")
    public String newVisitorAdmin(@RequestParam("json") String jsonBody,
            HttpSession httpSession,
            HttpServletRequest httpServletRequest) throws IOException {
        if (httpSession.getAttribute("admin") == null) {
            return "403";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(jsonBody);
        Cm cm = new Cm();
        String name = "", a_fname = "", a_lname = "", a_birthday = "",
                a_code = "", fixed_phone = "", mobile = "", uname = "",
                password = "", address = "", desc = "", city = "";
        try{
            name = jsonNode.get("name").asText();
        }catch (Exception e){}
        try{
             a_fname = jsonNode.get("a_fname").asText();
        }catch (Exception e){}
        try{
             a_lname = jsonNode.get("a_lname").asText();
        }catch (Exception e){}
        try{
             a_birthday = jsonNode.get("a_birthday").asText();
        }catch (Exception e){}
        try{
             a_code = jsonNode.get("a_code").asText();
        }catch (Exception e){}
        try{
             fixed_phone = jsonNode.get("fixed_phone").asText();
        }catch (Exception e){}
        try{
             mobile = jsonNode.get("mobile").asText();
        }catch (Exception e){}
        try{
             uname = jsonNode.get("uname").asText();
        }catch (Exception e){}
        try{
             password = jsonNode.get("password").asText();
        }catch (Exception e){}
        try{
             address = jsonNode.get("address").asText();
        }catch (Exception e){}
        try{
             desc = jsonNode.get("desc").asText();
        }catch (Exception e){}
        try{
             city = jsonNode.get("city").asText();
        }catch (Exception e){}
        cm.setName(name);
        cm.setaFname(a_fname);
        cm.setaLname(a_lname);
        cm.setaBirthday(a_birthday);
        cm.setaCode(a_code);
        cm.setFixedPhone(fixed_phone);
        cm.setMobile(mobile);
        cm.setUsername(uname);
        cm.setEnabled(true);
        cm.setPassword(password);
        cm.setCreatedAt(TimeHelper.getCurrentTimestamp());
        // @TODO add desc
        // @TODO add address
        cmDao.insert(cm);

        CmCity cmCity = new CmCity();
        cmCity.setCid(IntegerHelper.parseInt(city));
        cmCity.setCmid(cm.getId());
        cmCityDao.insert(cmCity);

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
        doc1.setCid(cm.getId());
        doc1.setType(Docs.CM_DOC1);
        docsDao.insert(doc1);
        
        
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
            doc11.setType(Docs.CM_DOC);
            doc11.setCid(cm.getId());
            docsDao.insert(doc1);
        }
        return "done";
    }
}