import dto.Position;
import helper.BoardHelper;
import model.Board;
import service.ChessGameService;
import service.ChessGameServiceImpl;
import service.ChessGamePieceColor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Board board = BoardHelper.buildNewBoard();
        ChessGameService chessGameService = new ChessGameServiceImpl(board);

        chessGameService.printBoard();

        System.out.println(chessGameService.nextMoves(ChessGamePieceColor.WHITE.ordinal(), new Position(6, 0)));
        System.out.println(chessGameService.move(ChessGamePieceColor.WHITE.ordinal(), new Position(6, 0),
                new Position(5, 0)));

        chessGameService.printBoard();
    }
}