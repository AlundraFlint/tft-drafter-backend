package elna.torla.tft.controller;

import elna.torla.tft.entities.Item;
import elna.torla.tft.entities.Trait;
import elna.torla.tft.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createItem(@RequestBody Item item) {
        this.itemService.createItem(item);
    }

    @GetMapping(path = "{id}",produces = APPLICATION_JSON_VALUE)
    public Item getItem(@PathVariable int id) {
        return this.itemService.getItem(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Item> getItems(@RequestParam(required = false) String nom, @RequestParam(required = false) String nomAnglais) {
        return this.itemService.getItems(nom,nomAnglais);
    }

    @PutMapping(path = "{id}",consumes = APPLICATION_JSON_VALUE)
    public void updateItem(@PathVariable int id, @RequestBody Item item) {
        this.itemService.updateItem(id, item);
    }

    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable int id) {
        this.itemService.deleteItem(id);
    }
}
