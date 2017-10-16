package com.sina.sina.ctrls.admin;

import com.sina.sina.dao.CityDao;
import com.sina.sina.models.City;
import com.sina.sina.models.Cm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class AdminCityController {

    @Autowired
    CityDao cityDao;


    @PostMapping("/admin/city/new")
    public Boolean disableCm(@RequestParam("name") String name, HttpSession httpSession) {
        if (httpSession.getAttribute("admin") == null) {
            return null;
        }
        City city = new City();
        city.setName(name);
        cityDao.insert(city);
        return true;
    }

}
