package com.mattu.superherosighting.Controller;

import com.mattu.superherosighting.Model.Location;
import com.mattu.superherosighting.Model.Superhero;
import com.mattu.superherosighting.Service.locationService.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Enumeration;
import java.util.HashMap;

@Controller
public class LocationController {
    @Autowired
    LocationService lService;

    @GetMapping("/getAllLocations")
    public String getAllLocations(Model model){
        model.addAttribute("locations",lService.getAllLocations());
        return "locationdetail.html";
    }

    @GetMapping("/addLocation")
    public String getAddLocation(Model model){
        Location location = new Location();
        location.setLname("Please put location name");
        location.setLdescription("Please put Location description");
        location.setLaddress("Please put location address");
        location.setLlongtitude(new BigDecimal(1).setScale(5,RoundingMode.FLOOR));
        location.setLlatitude(new BigDecimal(1).setScale(5,RoundingMode.FLOOR));

        model.addAttribute("location",location);
        return "locationadd.html";
    }

    @PostMapping("/addLocation")
    public String postAddLocation(HttpServletRequest req, Model model){
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values[0]);
            }
        }

        Location location = new Location();
        location.setLdescription(map.get("Ldescription"));
        location.setLaddress(map.get("Laddress"));
        location.setLname(map.get("Lname"));
        location.setLlatitude(new BigDecimal(map.get("Llatitude")).setScale(5, RoundingMode.FLOOR));
        location.setLlongtitude(new BigDecimal(map.get("Llongtitude")).setScale(5, RoundingMode.FLOOR));

        lService.addLocation(location);

        return getAllLocations(model);
    }

    @GetMapping("/editLocation")
    public String getEditLocation(Model model,
                              @RequestParam("id") int lId){
        Location location = lService.getLocationById(lId);
        model.addAttribute("location",location);

        return "locationedit.html";
    }

    @PostMapping("/editLocation")
    public String postEditLocation(HttpServletRequest req, Model model){
        Enumeration<String> names = req.getParameterNames();
        HashMap<String,String> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = req.getParameterValues(name);
            if(values!=null){
                map.put(name, values[0]);
            }
        }

        Location location = new Location();
        location.setLid(Integer.parseInt(map.get("Lid")));
        location.setLdescription(map.get("Ldescription"));
        location.setLaddress(map.get("Laddress"));
        location.setLname(map.get("Lname"));
        location.setLlatitude(new BigDecimal(map.get("Llatitude")).setScale(5, RoundingMode.FLOOR));
        location.setLlongtitude(new BigDecimal(map.get("Llongtitude")).setScale(5, RoundingMode.FLOOR));

        lService.updateLocation(location);

        return getAllLocations(model);
    }

    @GetMapping("/deleteLocation")
    public String deleteLocation(@RequestParam("id") int id,Model model){
        lService.deleteLocationById(id);
        return getAllLocations(model);
    }
}
