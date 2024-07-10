package elna.torla.tft.controller;

import elna.torla.tft.entities.Board;
import elna.torla.tft.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "board")
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    @RequestMapping(path = "{id}")
    public Board getBoard(@PathVariable int id)
    {
        return this.boardService.getBoard(id);
    }
}
