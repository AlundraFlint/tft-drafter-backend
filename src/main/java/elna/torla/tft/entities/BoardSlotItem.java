package elna.torla.tft.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "BOARD_SLOT_ITEMS")
public class BoardSlotItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "BOARD_SLOT_ID")
    private BoardSlot boardSlotId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public BoardSlotItem() {
    }

    public BoardSlotItem(int id, BoardSlot boardSlotId, Item item) {
        this.id = id;
        this.boardSlotId = boardSlotId;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public BoardSlot getBoardSlotId() {
        return boardSlotId;
    }

    public void setBoardSlotId(BoardSlot boardSlotId) {
        this.boardSlotId = boardSlotId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
