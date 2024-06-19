package elna.torla.tft.controller;

import elna.torla.tft.entities.Champion;
import elna.torla.tft.service.ChampionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "champion")
public class ChampionController {

    private ChampionService championService;

    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createChampion(@RequestBody Champion champion) {
        this.championService.createChampion(champion);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Champion> getChampions(@RequestParam(required = false) String nom,@RequestParam(required = false) Integer cout,@RequestParam(required = false) String trait) {
        return this.championService.getChampions(nom, cout, trait);
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Champion getChampion(@PathVariable int id) {
        return this.championService.getChampion(id);
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void updateChampion(@PathVariable int id, @RequestBody Champion champion) {
        this.championService.updateChampion(id, champion);
    }

    @DeleteMapping(path = "{id}")
    public void deleteChampion(@PathVariable int id) {
        this.championService.deleteChampion(id);
    }
}
