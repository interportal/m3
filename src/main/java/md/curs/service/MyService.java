package md.curs.service;

import md.curs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by MG
 */
@Service
public class MyService {

    @Autowired
    public MyService(@Qualifier("mockUser") User user) {
        System.out.println("Received user: " + user);
    }

    @PostConstruct
    public void init() {
        System.out.println();
    }
}
