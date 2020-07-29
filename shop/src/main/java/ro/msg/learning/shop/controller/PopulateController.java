package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.PopulateService;

@ActiveProfiles("test")
@RestController
public class PopulateController {

    @Autowired
    private PopulateService populateService;

    @PostMapping("/data")
    void populatingDatabase() {
        populateService.populateDatabase();
    }

    @DeleteMapping("/clear")
    void deleteData(){
        populateService.deleteData();
    }
}
