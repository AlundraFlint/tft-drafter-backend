package elna.torla.tft.controller;

import elna.torla.tft.service.InitDbService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "init")
public class InitDbController {
    private InitDbService initDbService;

    public InitDbController(InitDbService initDbService) {
        this.initDbService = initDbService;
    }

    @PostMapping(path = "database")
    public void initDatabase() {
        this.initDbService.initDatabase();
    }
}
