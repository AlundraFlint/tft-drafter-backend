package elna.torla.tft.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "BOARD")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardSlot> boardSlots;

    public Board() {
    }

    public Board(int id, String name, User user, List<BoardSlot> boardSlots) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.boardSlots = boardSlots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BoardSlot> getBoardSlots() {
        return boardSlots;
    }

    public void setBoardSlots(List<BoardSlot> boardSlots) {
        this.boardSlots = boardSlots;
    }
}
