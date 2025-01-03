package elna.torla.tft.controller;

import elna.torla.tft.entities.BoardSlot;
import elna.torla.tft.service.BoardSlotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "board/{boardId}/slot")
public class BoardSlotController {
    private BoardSlotService boardSlotService;

    public BoardSlotController(BoardSlotService boardSlotService) {
        this.boardSlotService = boardSlotService;
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public BoardSlot getBoardSlot(@PathVariable int boardId, @PathVariable int id){
        return this.boardSlotService.getBoardSlot(boardId,id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<BoardSlot> getBoardSlots(@PathVariable int boardId){
        return this.boardSlotService.getBoardSlots(boardId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createBoardSlot(@PathVariable int boardId, @RequestBody BoardSlot boardSlot){
        this.boardSlotService.createBoardSlot(boardId,boardSlot);
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void updateBoardSlot(@PathVariable int boardId, @PathVariable int id, BoardSlot boardSlot){
        this.boardSlotService.updateBoardSlot(boardId,id,boardSlot);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBoardSlot(@PathVariable int boardId, @PathVariable int id){
        this.boardSlotService.deleteBoardSlot(boardId,id);
    }
}
