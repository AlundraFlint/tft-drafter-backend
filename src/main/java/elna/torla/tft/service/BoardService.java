package elna.torla.tft.service;

import elna.torla.tft.entities.Board;
import elna.torla.tft.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    public Board getBoard(int id) {
        Optional<Board> board = boardRepository.findById(id);
        return board.orElse(null);
    }
}
