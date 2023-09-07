import dto.Position;
import service.ChessGame;
import service.ChessGamePieceColor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ChessGame chessGame = new ChessGame();

        chessGame.printBoard();

        System.out.println(chessGame.nextMoves(ChessGamePieceColor.WHITE.ordinal(), new Position(6, 0)));
        System.out.println(chessGame.move(ChessGamePieceColor.WHITE.ordinal(), new Position(6, 0),
                new Position(5, 0)));

        chessGame.printBoard();
    }
}