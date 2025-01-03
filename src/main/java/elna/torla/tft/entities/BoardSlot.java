package elna.torla.tft.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "BOARD_SLOT")
public class BoardSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "BOARD_ID")
    private Board boardId;

    private int position;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "CHAMPION_ID")
    private Champion champion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boardSlotId", cascade = CascadeType.ALL)
    private List<BoardSlotItem> boardSlotsItems;

    @Column(name = "IS_MAX")
    private int isMax;

    public BoardSlot() {
    }

    public BoardSlot(int id, Board boardId, int position, Champion champion, List<BoardSlotItem> boardSlotsItems, int isMax) {
        this.id = id;
        this.boardId = boardId;
        this.position = position;
        this.champion = champion;
        this.boardSlotsItems = boardSlotsItems;
        this.isMax = isMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public Board getBoardId() {
        return boardId;
    }

    public void setBoardId(Board boardId) {
        this.boardId = boardId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public List<BoardSlotItem> getBoardSlotsItems() {
        return boardSlotsItems;
    }

    public void setBoardSlotsItems(List<BoardSlotItem> boardSlotsItems) {
        this.boardSlotsItems = boardSlotsItems;
    }

    public int getIsMax() {
        return isMax;
    }

    public void setIsMax(int isMax) {
        this.isMax = isMax;
    }
}
