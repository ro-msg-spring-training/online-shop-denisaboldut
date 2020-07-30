package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.PopulateService;

@Profile("test")
@RestController
@RequiredArgsConstructor
public class PopulateController {

    private final PopulateService populateService;

    @PostMapping("/data")
    void populatingDatabase() {
        populateService.populateDatabase();
    }

    @DeleteMapping("/clear")
    void deleteData(){
        populateService.deleteData();
    }
}
