package com.mattu.superherosighting.Controller;


import com.alibaba.fastjson.JSONArray;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Model.Superherosight;
import com.mattu.superherosighting.Service.orgnizationService.OrgnizationService;
import com.mattu.superherosighting.Service.superheroService.SuperheroService;
import com.mattu.superherosighting.Service.superheromemberService.SuperheromemberService;
import com.mattu.superherosighting.Service.superherosightingService.SuperherosightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SuperheroController {

    @Autowired
    SuperherosightingService ssService;

    @Autowired
    SuperheromemberService smService;

    @Autowired
    SuperheroService service;

    @Autowired
    OrgnizationService oService;

    @GetMapping({"/","/index"})
    public String index(Model model){

        List<Superherosight> list = ssService.getRecentSightings().stream().sorted(Comparator.comparing(Superherosight::getSSid).reversed())
                .limit(10) //get the 10 first sightings
                .collect(Collectors.toList());        ;
        model.addAttribute("sightings", list);

//        JSONArray jArr = (JSONArray) JSONArray.toJSON(list);
//        model.addAttribute("sightingsJSON",jArr);
        return "index.html";
    }

//    @GetMapping("/getall")
//    public String getAll(Model model){
//        List<Superherosight> list = ssDao.getAllSuperherosight();
//        model.addAttribute("sightings", list);
//        return "index.html";
//    }

    @GetMapping("/getAllHeroes")
    public String getAllSuperheroes(Model model){
        List <Superhero> list = service.getAllHeroes();
        HashMap<Integer,String> isVillian = new HashMap<>();
        for(Superhero s:list){
            if(s.getSvillian()==0){
                isVillian.put(s.getSid(),"not villian");
            }else{
                isVillian.put(s.getSid(),"is villian");
            }
        }
        model.addAttribute("heroes",list);
        model.addAttribute("isVillian",isVillian);
        return "superherodetail.html";
    }

    @GetMapping("/addHero")
    public String getAddHero(Model model){
        Superhero hero = new Superhero();
        hero.setSname("Please put Superhero Name");
        hero.setSdescription("Please put Superhero description");
        hero.setSsp("Please put Superpower");
        hero.setSvillian(0);

        model.addAttribute("hero",hero);
        model.addAttribute("orgnizations",oService.getAllOrgnizations());
        return "superheroadd.html";
    }

    @PostMapping("/addHero")
    public String postAddHero(HttpServletRequest req,Model model){
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values[0]);
            }
        }


        // label name tag
        Superhero newSuperhero = new Superhero();
        newSuperhero.setSvillian(Integer.parseInt(map.get("Svillian")));
        newSuperhero.setSname(map.get("Sname"));
        newSuperhero.setSdescription(map.get("Sdescription"));
        newSuperhero.setSsp(map.get("Ssp"));

        service.addSuperhero(newSuperhero,Integer.parseInt(map.get("heroOrg")));

        return getAllSuperheroes(model);

    }

    @GetMapping({"/editHero"})
    public String getEditHero(Model model,
                              @RequestParam("id") int heroId){
        Superhero hero = service.getHeroById(heroId);
        model.addAttribute("hero",hero);

        model.addAttribute("orgnizations",oService.getAllOrgnizations());
        model.addAttribute("heroOrg",service.getHeroOrgnizationById(heroId));


        model.addAttribute("isVillianSelection", new String[]{"is Villian","not Villian"});



        return "superheroedit.html";
    }

    @PostMapping("/editHero")
    public String postEditHero(HttpServletRequest req,Model model){
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values[0]);
            }
        }


        // label name tag
        Superhero newSuperhero = new Superhero();
        newSuperhero.setSid(Integer.parseInt(map.get("Sid")));
        newSuperhero.setSvillian(Integer.parseInt(map.get("Svillian")));
        newSuperhero.setSname(map.get("Sname"));
        newSuperhero.setSdescription(map.get("Sdescription"));
        newSuperhero.setSsp(map.get("Ssp"));


        if(Integer.parseInt(map.get("heroOrg"))!=smService.getSuperheromemberBySid(newSuperhero.getSid()).getOrg().getOid()){
            smService.updateMemberBySid(newSuperhero.getSid(),Integer.parseInt(map.get("heroOrg")));
        }

        service.updateSuperhero(newSuperhero);

        return getAllSuperheroes(model);


    }

    @GetMapping("/deleteHero")
    public String deleteHero(@RequestParam("id") int heroId,Model model){
        service.deleteSuperheroById(heroId);

        return getAllSuperheroes(model);

    }

}
