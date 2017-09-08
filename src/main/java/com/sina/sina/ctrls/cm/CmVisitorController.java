/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.cm;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Visitor;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mphj
 */
@RestController
public class CmVisitorController {

    @Autowired
    VisitorDao visitorDao;

    @GetMapping("/cm/visitor/{id}/disable")
    public Boolean disableVisitor(@PathVariable Integer id, HttpSession httpSession) {
        if (httpSession.getAttribute("cm") == null) {
            return null;
        }
        Visitor visitor = visitorDao.findById(id);
        visitor.setEnabled(false);
        visitorDao.update(visitor);
        return true;
    }

    @GetMapping("/cm/visitor/{id}/enable")
    public Boolean enableVisitor(@PathVariable Integer id, HttpSession httpSession) {
        if (httpSession.getAttribute("cm") == null) {
            return null;
        }
        Visitor visitor = visitorDao.findById(id);
        visitor.setEnabled(true);
        visitorDao.update(visitor);
        return true;
    }

}
