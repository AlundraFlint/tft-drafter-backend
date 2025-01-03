package elna.torla.tft.repository;

import elna.torla.tft.entities.Board;
import elna.torla.tft.entities.BoardSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardSlotRepository extends JpaRepository<BoardSlot,Integer> {
    List<BoardSlot> findBoardSlotByBoardIdOrderByPosition(Board board);
    BoardSlot findBoardSlotByBoardIdAndPosition(Board board, int position);
}
