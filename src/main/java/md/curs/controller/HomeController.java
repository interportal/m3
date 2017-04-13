package md.curs.controller;

import md.curs.model.User;
import md.curs.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MG
 */
@Controller
public class HomeController {

    MyService service;

    @Autowired
    public HomeController(MyService service) {
        this.service = service;
    }

    @RequestMapping(value = "user-list")
    public String userList(Model model) {
        Random r = new Random();
        int minorsCount = 0;

        // Generate 10 users with random ages
        List<User> users = new ArrayList<>();
        for (int i =0; i < 10; i++) {
            User u = new User("Name" + i, "Surname" + i, r.nextInt(50));
            if (u.getAge() < 18) {
                minorsCount++;
            }
            users.add(u);
        }

        model.addAttribute("users", users);
        model.addAttribute("minorsCount", minorsCount);


        return "user-list";
    }
}
