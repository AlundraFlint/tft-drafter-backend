package elna.torla.tft.service;

import elna.torla.tft.entities.Board;
import elna.torla.tft.entities.BoardSlot;
import elna.torla.tft.entities.BoardSlotItem;
import elna.torla.tft.entities.Item;
import elna.torla.tft.repository.BoardSlotItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardSlotItemService {
    private BoardSlotItemRepository boardSlotItemRepository;
    private BoardSlotService boardSlotService;
    private BoardService boardService;
    private ItemService itemService;

    public BoardSlotItemService(BoardSlotItemRepository boardSlotItemRepository, BoardSlotService boardSlotService, BoardService boardService, ItemService itemService) {
        this.boardSlotItemRepository = boardSlotItemRepository;
        this.boardSlotService = boardSlotService;
        this.boardService = boardService;
        this.itemService = itemService;
    }

    public BoardSlotItem getBoardSlotItem(int boardId, int boardSlotId, int boardSlotItemId){
        Optional<BoardSlotItem> boardSlotItem=this.boardSlotItemRepository.findById(boardSlotItemId);
        return boardSlotItem.orElse(null);
    }

    public List<BoardSlotItem> getBoardSlotItems(int boardId, int boardSlotId) {
        if (boardId>0 && boardSlotId > 0){
            Board board = this.boardService.getBoard(boardId);
            if (board!=null)
            {
                BoardSlot boardSlot = this.boardSlotService.getBoardSlot(boardId,boardSlotId);
                if (boardSlot!=null && boardSlot.getBoardId() == board)
                {
                    return this.boardSlotItemRepository.findBoardSlotItemByBoardSlotIdOrderById(boardSlot);

                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void createBoardSlotItem (int boardId, int boardSlotId, BoardSlotItem boardSlotItem){
        Board board = this.boardService.getBoard(boardId);
        if (board!=null){
            BoardSlot boardSlot = this.boardSlotService.getBoardSlot(boardId,boardSlotId);
            if (boardSlot!=null && boardSlot.getBoardId()==board){
                Item item = this.itemService.getItem(boardSlotItem.getItem().getId());
                if (item!=null){
                    boardSlotItem.setItem(item);
                    this.boardSlotItemRepository.save(boardSlotItem);
                }
            } else {
                //Erreur à gérer
            }
        } else {
            //Erreur à gérer
        }


    }

    public void deleteBoardSlotItem (int boardId, int boardSlotId, int boardSlotItemId){

        if (boardId>0 && boardSlotId > 0 && boardSlotItemId>0){
            Board board = this.boardService.getBoard(boardId);
            if (board!=null)
            {
                BoardSlot boardSlot = this.boardSlotService.getBoardSlot(boardId,boardSlotId);
                if (boardSlot!=null && boardSlot.getBoardId() == board)
                {
                    BoardSlotItem boardSlotItem = getBoardSlotItem(boardId,boardSlotId,boardSlotItemId);
                    if (boardSlotItem.getBoardSlotId() == boardSlot)
                    {
                        this.boardSlotItemRepository.deleteById(boardSlotItemId);
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
}
