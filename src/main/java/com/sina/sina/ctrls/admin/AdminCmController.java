/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.admin;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sina.sina.dao.CmDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Cm;
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
public class AdminCmController {

    @Autowired
    CmDao cmDao;

    @GetMapping("/admin/cm/{id}/disable")
    public Boolean disableCm(@PathVariable Integer id, HttpSession httpSession) {
        if (httpSession.getAttribute("admin") == null) {
            return null;
        }
        Cm cm = cmDao.findById(id);
        cm.setEnabled(false);
        cmDao.update(cm);
        return true;
    }

    @GetMapping("/admin/cm/{id}/enable")
    public Boolean enableCm(@PathVariable Integer id, HttpSession httpSession) {
        if (httpSession.getAttribute("admin") == null) {
            return null;
        }
        Cm cm = cmDao.findById(id);
        cm.setEnabled(true);
        cmDao.update(cm);
        return true;
    }

}
