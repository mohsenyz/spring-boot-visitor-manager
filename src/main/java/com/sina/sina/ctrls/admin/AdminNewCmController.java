/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.admin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.sina.dao.CmCityDao;
import com.sina.sina.dao.CmDao;
import com.sina.sina.dao.DocsDao;
import com.sina.sina.dao.VisitorCityDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Cm;
import com.sina.sina.models.CmCity;
import com.sina.sina.models.Docs;
import com.sina.sina.models.Visitor;
import com.sina.sina.models.VisitorCity;
import com.sina.sina.pojo.Docs_1;
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
        if (httpSession.getAttribute("cm") == null) {
            return "403";
        }
        Cm currVisitor = (Cm) httpSession.getAttribute("cm");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonBody);
        Cm cm = new Cm();
        String name = jsonNode.get("name").asText();
        String a_fname = jsonNode.get("a_fname").asText();
        String a_lname = jsonNode.get("a_lname").asText();
        String a_birthday = jsonNode.get("a_birthday").asText();
        String a_code = jsonNode.get("a_code").asText();
        String fixed_phone = jsonNode.get("fixed_phone").asText();
        String mobile = jsonNode.get("mobile").asText();
        String uname = jsonNode.get("uname").asText();
        String password = jsonNode.get("password").asText();
        String address = jsonNode.get("address").asText();
        String desc = jsonNode.get("desc").asText();
        String city = jsonNode.get("city").asText();
        cm.setName(name);
        cm.setaFname(a_fname);
        cm.setaLname(a_lname);
        cm.setaBirthday(a_birthday);
        cm.setaCode(a_code);
        cm.setFixedPhone(fixed_phone);
        cm.setMobile(mobile);
        cm.setUsername(uname);
        cm.setPassword(password);
        cm.setCreatedAt(TimeHelper.getCurrentTimestamp());
        // @TODO add desc
        // @TODO add address
        cmDao.insert(cm);

        CmCity cmCity = new CmCity();
        cmCity.setCid(Integer.parseInt(city));
        cmCity.setCmid(cm.getId());
        cmCityDao.insert(cmCity);

        Docs_1 doc = objectMapper.treeToValue(jsonNode.get("doc1"), Docs_1.class);
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
        doc1.setCid(currVisitor.getId());
        doc1.setType(Docs.CM_DOC);
        docsDao.insert(doc1);
        return "done";
    }
}