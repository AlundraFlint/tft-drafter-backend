package elna.torla.tft.service;

import elna.torla.tft.entities.Board;
import elna.torla.tft.entities.BoardSlot;
import elna.torla.tft.entities.Champion;
import elna.torla.tft.repository.BoardSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardSlotService {

    private BoardSlotRepository boardSlotRepository;
    private BoardService boardService;
    private ChampionService championService;
    private ItemService itemService;

    public BoardSlotService(BoardSlotRepository boardSlotRepository, BoardService boardService, ChampionService championService, ItemService itemService) {
        this.boardSlotRepository = boardSlotRepository;
        this.boardService = boardService;
        this.championService = championService;
        this.itemService = itemService;
    }

    public BoardSlot getBoardSlot(int boardId, int id){
        Optional<BoardSlot> boardSlot=this.boardSlotRepository.findById(id);
        return boardSlot.orElse(null);
    }

    public List<BoardSlot> getBoardSlots(int boardId){
        if (boardId>0) {
            Board board = this.boardService.getBoard(boardId);
            if(board != null)
            {
                return this.boardSlotRepository.findBoardSlotByBoardIdOrderByPosition(board);
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    public void createBoardSlot(int boardId, BoardSlot boardSlot){
        Board board = this.boardService.getBoard(boardId);
        if(board!=null) {
            boardSlot.setBoardId(board);
            if (boardSlot.getPosition() >= 1 && boardSlot.getPosition() <= 28) {
                BoardSlot boardSlotInDb = this.boardSlotRepository.findBoardSlotByBoardIdAndPosition(board,boardSlot.getPosition());
                if(boardSlotInDb == null){
                    if(boardSlot.getChampion()!=null){
                        Champion champion = this.championService.getChampion(boardSlot.getChampion().getId());
                        if (champion != null) {
                            boardSlot.setChampion(champion);

                            this.boardSlotRepository.save(boardSlot);
                        } else {
                            //Erreur à gérer
                        }
                    } else {
                        //Erreur à gérer
                    }
                } else {
                    //Erreur à gérer
                }
            } else {
                //Erreur à gérer
            }
        } else {
            //Erreur à gérer
        }
    }

    public void updateBoardSlot(int boardId, int id, BoardSlot boardSlot){
        Board board = this.boardService.getBoard(boardId);
        if(board!=null) {
            boardSlot.setBoardId(board);
            if (boardSlot.getPosition() >= 1 && boardSlot.getPosition() <= 28) {
                BoardSlot boardSlotInDb = this.boardSlotRepository.findBoardSlotByBoardIdAndPosition(board,boardSlot.getPosition());
                if(boardSlotInDb == null){
                    if(boardSlot.getChampion()!=null){
                        Champion champion = this.championService.getChampion(boardSlot.getChampion().getId());
                        if (champion != null) {
                            boardSlot.setChampion(champion);

                            this.boardSlotRepository.save(boardSlot);
                        } else {
                            deleteBoardSlot(boardId,id);
                        }
                    } else {
                        //Erreur à gérer
                    }
                } else {
                    //Erreur à gérer
                }
            } else {
                //Erreur à gérer
            }
        } else {
            //Erreur à gérer
        }
    }

    public void deleteBoardSlot(int boardId, int id){
        if(this.boardSlotRepository.existsById(id)){
            this.boardSlotRepository.deleteById(id);
        } else {
            //erreur à gérer
        }
    }

}
