package elna.torla.tft.controller;

import elna.torla.tft.entities.BoardSlotItem;
import elna.torla.tft.service.BoardSlotItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "board/{boardId}/slot/{boardSlotId}/item")
public class BoardSlotItemController {

    private BoardSlotItemService boardSlotItemService;

    public BoardSlotItemController(BoardSlotItemService boardSlotItemService) {
        this.boardSlotItemService = boardSlotItemService;
    }

    @GetMapping(path = "{boardSlotItemId}")
    public BoardSlotItem getBoardSlotItem(@PathVariable int boardId, @PathVariable int boardSlotId, @PathVariable int boardSlotItemId){
        return this.boardSlotItemService.getBoardSlotItem(boardId,boardSlotId,boardSlotItemId);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<BoardSlotItem> getBoardSlotItems(@PathVariable int boardId, @PathVariable int boardSlotId){
        return this.boardSlotItemService.getBoardSlotItems(boardId,boardSlotId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createBoardSlotItem(@PathVariable int boardId, @PathVariable int boardSlotId, @RequestBody BoardSlotItem boardSlotItem){
        this.boardSlotItemService.createBoardSlotItem(boardId,boardSlotId,boardSlotItem);

    }

    @DeleteMapping(path = "{boardSlotItemId}")
    public void deleteBoardSlotItem(@PathVariable int boardId, @PathVariable int boardSlotId, @PathVariable int boardSlotItemId){
        this.boardSlotItemService.deleteBoardSlotItem(boardId,boardSlotId,boardSlotItemId);
    }
}
