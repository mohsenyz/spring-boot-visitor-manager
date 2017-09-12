/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls;

import com.sina.sina.dao.CityDao;
import com.sina.sina.dao.CmDao;
import com.sina.sina.dao.DrDao;
import com.sina.sina.dao.DrugsDao;
import com.sina.sina.dao.DsDao;
import com.sina.sina.dao.VisitorDao;
import com.sina.sina.models.City;
import com.sina.sina.models.Cm;
import com.sina.sina.models.Drugs;
import com.sina.sina.models.Ds;
import com.sina.sina.models.Visitor;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
public class MainController {
    
    @Autowired
    CityDao cityDao;
    
    @Autowired
    DrugsDao drugsDao;
    
    @Autowired
    VisitorDao visitorDao;
    
    @Autowired
    DsDao dsDao;
    
    @Autowired
    DrDao drDao;
    
    @Autowired
    CmDao cmDao;
    
    @GetMapping("/core/city")
    public List<City> listCity(){
        return cityDao.findAll();
    }
    
    
    @GetMapping("/")
    public void redirectToIndex(HttpServletResponse httpServletResponse) throws IOException{
        httpServletResponse.sendRedirect("/index.html");
    }
    
    
    
    @GetMapping("/login")
    public void loginAndRedirect(@RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession httpSession,
            HttpServletResponse httpServletResponse) throws IOException{
        username = username.trim();
        password = password.trim();
        // First check for admin
        if (username.equals("admin") && password.equals("admin*@")){
            httpSession.setAttribute("admin", true);
            httpServletResponse.sendRedirect("/admin.html");
            return;
        }
        // Check visitor
        Visitor visitor = visitorDao.findByUsername(username);
        if (visitor != null && visitor.getPassword().equals(password)){
            httpSession.setAttribute("visitor", visitor);
            httpServletResponse.sendRedirect("/visitor.html");
            return;
        }
        
        // Check cm
        Cm cm = cmDao.findByUsername(username);
        if (cm != null && cm.getPassword().equals(password)){
            httpSession.setAttribute("cm", cm);
            httpServletResponse.sendRedirect("/cm.html");
            return;
        }
        
        // Check drugstore
        Ds ds = dsDao.findByUsername(username);
        if (ds != null && ds.getPassword().equals(password)){
            httpSession.setAttribute("ds", ds);
            httpServletResponse.sendRedirect("/drugstore.html");
            return;
        }
        
        httpServletResponse.sendRedirect("/index.html");   
    }
    
    @GetMapping("/core/drugs")
    public List<Drugs> listDrugs(){
        return drugsDao.listAll();
    }
    
    @GetMapping("/logout")
    public boolean logout(HttpSession httpSession){
        httpSession.invalidate();
        return true;
    }
}
