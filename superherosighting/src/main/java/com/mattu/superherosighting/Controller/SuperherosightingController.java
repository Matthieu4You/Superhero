package com.mattu.superherosighting.Controller;

import com.mattu.superherosighting.Model.Location;
import com.mattu.superherosighting.Model.Orgnization;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superherosight;
import com.mattu.superherosighting.Service.locationService.LocationService;
import com.mattu.superherosighting.Service.superheroService.SuperheroService;
import com.mattu.superherosighting.Service.superherosightingService.SuperherosightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Controller
public class SuperherosightingController {

    @Autowired
    SuperherosightingService ssService;

    @Autowired
    LocationService lService;

    @Autowired
    SuperheroService sService;

    @GetMapping("/getAllSightings")
    public String getAllSighting(Model model){
        List<Superherosight> list = ssService.getAllSightings();
        model.addAttribute("sightings",list);


        return "sightingdetail.html";
    }

    @GetMapping("/addSighting")
    public String getAddSighting(Model model){
        Superherosight ss = new Superherosight();
        ss.setSSdate("please put sighting date");
        model.addAttribute("sight",ss);
        model.addAttribute("locations",lService.getAllLocations());
        model.addAttribute("heroes",sService.getAllHeroes());
        return "sightingadd.html";
    }

    @PostMapping("/addSighting")
    public String postAddSighting(HttpServletRequest req, Model model){
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String[]> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values);
            }
        }
        Superherosight ss = new Superherosight();

        ss.setSSdate(map.get("SSdate")[0]);

        Superhero sTemp =new Superhero();
        sTemp.setSid(Integer.parseInt(map.get("Sid")[0]));
        ss.setSuperhero(sTemp);

        Location lTemp = new Location();
        lTemp.setLid(Integer.parseInt(map.get("Lid")[0]));
        ss.setLocation(lTemp);

        ssService.addSuperherosight(ss);

        return getAllSighting(model);
    }

    @GetMapping("/editSighting")
    public String getEditSighting(@RequestParam("id") int id,Model model) {
        model.addAttribute("sight",ssService.getSuperherosightById(id));
        model.addAttribute("locations",lService.getAllLocations());
        model.addAttribute("heroes",sService.getAllHeroes());
        return "sightingedit.html";
    }

    @PostMapping("/editSighting")
    public String postEditSighting(HttpServletRequest req, Model model) {
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String[]> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values);
            }
        }

        Superherosight ss = new Superherosight();
        ss.setSSid(Integer.parseInt(map.get("SSid")[0]));
        ss.setSSdate(map.get("SSdate")[0]);

        Superhero sTemp =new Superhero();
        sTemp.setSid(Integer.parseInt(map.get("Sid")[0]));
        ss.setSuperhero(sTemp);

        Location lTemp = new Location();
        lTemp.setLid(Integer.parseInt(map.get("Lid")[0]));
        ss.setLocation(lTemp);

        ssService.updateSuperherosight(ss);

        return getAllSighting(model);
    }

    @GetMapping("/deleteSighting")
    public String deleteSighting(@RequestParam("id") int id,Model model) {
        ssService.deleteSuperherosight(id);
        return getAllSighting(model);
    }
}
