/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls.visitor;

import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.Visitor;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mphj
 */
@RestController
public class VisitorController {
    
    @Autowired
    VisitorDao visitorDao;
    
    @PostMapping("/visitor/login")
    public boolean login(@RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession httpSession){
        Visitor visitor = visitorDao.findByUsername(username.trim());
        if (visitor == null || !visitor.getPassword().equals(password.trim())){
            return false;
        }
        httpSession.setAttribute("visitor", visitor);
        return true;
    }
}
