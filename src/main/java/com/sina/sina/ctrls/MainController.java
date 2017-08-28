/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.ctrls;

import com.sina.sina.dao.CityDao;
import com.sina.sina.models.City;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mphj
 */
@RestController
public class MainController {
    
    @Autowired
    CityDao cityDao;
    
    @GetMapping("/core/city")
    public List<City> listCity(){
        return cityDao.findAll();
    }
}
