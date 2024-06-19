package elna.torla.tft.controller;

import elna.torla.tft.entities.TraitLevel;
import elna.torla.tft.service.TraitLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "level")
public class TraitLevelController {

    private TraitLevelService traitLevelService;

    public TraitLevelController(TraitLevelService traitLevelService) {
        this.traitLevelService = traitLevelService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createTraitLevel(@RequestBody TraitLevel traitLevel){
        this.traitLevelService.createTraitLevel(traitLevel);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<TraitLevel> getTraitLevels (@RequestParam(required = false) Integer traitId){
        return this.traitLevelService.getTraitLevels(traitId);
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public TraitLevel getTraitLevel (@PathVariable int id){
        return this.traitLevelService.getTraitLevel(id);
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void updateTraitLevel(@PathVariable int id, @RequestBody TraitLevel traitLevel) {
        this.traitLevelService.updateTraitLevel(id, traitLevel);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTraitLevel(@PathVariable int id) {
        this.traitLevelService.deleteTraitLevel(id);
    }
}
