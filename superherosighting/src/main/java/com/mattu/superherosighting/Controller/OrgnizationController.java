package com.mattu.superherosighting.Controller;

import com.mattu.superherosighting.Model.Location;
import com.mattu.superherosighting.Model.Orgnization;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Service.locationService.LocationService;
import com.mattu.superherosighting.Service.orgnizationService.OrgnizationService;
import com.mattu.superherosighting.Service.superheroService.SuperheroService;
import com.mattu.superherosighting.Service.superheromemberService.SuperheromemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Controller
public class OrgnizationController {
    @Autowired
    SuperheromemberService smService;

    @Autowired
    SuperheroService sService;

    @Autowired
    OrgnizationService oService;

    @Autowired
    LocationService lService;

    @GetMapping("/getAllOrgs")
    public String getAllOrgs(Model model){
        List<Orgnization> list = oService.getAllOrgnizations();
        model.addAttribute("orgs",list);

        HashMap<Integer,String> map = new HashMap<>();
        HashMap<Integer,String> addressMap = new HashMap<>();
        for(Orgnization o : list){
            map.put(o.getOid(), smService.getHeroStringByOid(o.getOid()));
            addressMap.put(o.getOid(),lService.getAddressByOid(o));
        }

        model.addAttribute("orgHero",map);
        model.addAttribute("orgAdd",addressMap);
        return "orgnizationdetail.html";
    }

    @GetMapping("/addOrg")
    public String getAddOrg(Model model){
        Orgnization org = new Orgnization();
        org.setOname("Please put Orgnization name");
        org.setOdescription("Please put Orgnization description");
        org.setOcontact("Please put Orgnization contact");
        org.setLid(1);

        model.addAttribute("org",org);

        model.addAttribute("locations",lService.getAllLocations());

        model.addAttribute("heroes",sService.getAllHeroes());

        return "orgnizationadd.html";
    }

    @PostMapping("/addOrg")
    public String postAddOrg(HttpServletRequest req, Model model){
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String[]> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values);
            }
        }

        Orgnization org = new Orgnization();
        org.setOname(map.get("Oname")[0]);
        org.setOdescription(map.get("Odescription")[0]);
        org.setOcontact(map.get("Ocontact")[0]);
        org.setLid(Integer.parseInt(map.get("Lid")[0]));

        org=oService.addOrgnization(org);

        // update superheroMember
        List<Integer> list =new ArrayList<>();
        if(map.get("Superhero")!=null) {
            for (String s : map.get("Superhero")) {
                list.add(Integer.parseInt(s));
            }
        }
        smService.updateSuperheromember(org.getOid(),list);
        return getAllOrgs(model);
    }



    @GetMapping("/editOrg")
    public String getEditOrg(Model model,
                             @RequestParam("id") int oId){
        model.addAttribute("org",oService.getOrgnizationById(oId));

        model.addAttribute("locations",lService.getAllLocations());

        model.addAttribute("heroes",sService.getAllHeroes());

        List<Superhero> orgHero = smService.getSuperheromemberByOid(oId);

        List<Integer> orgSid = new ArrayList<>();
        for(Superhero h:orgHero){
            orgSid.add(h.getSid());
        }

        model.addAttribute("orgSid",orgSid);

        return "orgnizationedit.html";
    }

    @PostMapping("/editOrg")
    public String postEditOrgs(HttpServletRequest req, Model model){
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String[]> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values);
            }
        }

        Orgnization org = new Orgnization();
        org.setOid(Integer.parseInt(map.get("Oid")[0]));
        org.setOname(map.get("Oname")[0]);
        org.setOdescription(map.get("Odescription")[0]);
        org.setOcontact(map.get("Ocontact")[0]);
        org.setLid(Integer.parseInt(map.get("Lid")[0]));

        oService.updateOrgnization(org);

        // update superheroMember
        List<Integer> list =new ArrayList<>();
        if(map.get("Superhero")!=null) {
            for (String s : map.get("Superhero")) {
                list.add(Integer.parseInt(s));
            }
        }
        smService.updateSuperheromember(org.getOid(),list);
        return getAllOrgs(model);
    }

    @GetMapping("/deleteOrg")
    public String deleteOrg(@RequestParam("id") int id,Model model){
        oService.deleteOrgnizationById(id);
        return getAllOrgs(model);
    }
}
