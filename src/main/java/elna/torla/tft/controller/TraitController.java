package elna.torla.tft.controller;

import elna.torla.tft.entities.Trait;
import elna.torla.tft.service.TraitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path="trait")
public class TraitController {

    private TraitService traitService;

    public TraitController(TraitService traitService) {
        this.traitService = traitService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createTrait(@RequestBody Trait trait) {
        this.traitService.createTrait(trait);
    }

    @GetMapping(path = "{id}",produces = APPLICATION_JSON_VALUE)
    public Trait getTrait(@PathVariable int id) {
        return this.traitService.getTrait(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Trait> getTraits(@RequestParam(required = false) String nom, @RequestParam(required = false) String nomAnglais) {
        return this.traitService.getTraits(nom,nomAnglais);
    }

    @PutMapping(path = "{id}",consumes = APPLICATION_JSON_VALUE)
    public void updateTrait(@PathVariable int id, @RequestBody Trait trait) {
        this.traitService.updateTrait(id, trait);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTrait(@PathVariable int id) {
        this.traitService.deleteTrait(id);
    }

}
