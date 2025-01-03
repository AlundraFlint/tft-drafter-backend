package elna.torla.tft.repository;

import elna.torla.tft.entities.BoardSlot;
import elna.torla.tft.entities.BoardSlotItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardSlotItemRepository extends JpaRepository<BoardSlotItem, Integer> {
    List<BoardSlotItem> findBoardSlotItemByBoardSlotIdOrderById(BoardSlot boardSlot);
}
