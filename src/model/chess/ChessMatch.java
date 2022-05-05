package model.chess;

import model.boardgame.Board;
import model.boardgame.Piece;
import model.boardgame.Position;
import model.chess.exception.ChessException;
import model.chess.pieces.King;
import model.chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i=0;i<board.getRows();i++) {
			for(int j=0;j<board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);
			}
		}
		return mat;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPositon();
		Position target = targetPosition.toPositon();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on souce position");
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece,  new ChessPosition(column, row).toPositon());
	}
	
	private void initialSetup() {
		placeNewPiece('c',1,new Rook(board, Color.BLACK));
		placeNewPiece('c',2,new Rook(board, Color.BLACK));
		placeNewPiece('d',2,new Rook(board, Color.BLACK));
		placeNewPiece('e',2,new Rook(board, Color.BLACK));
		placeNewPiece('e',1,new Rook(board, Color.BLACK));
		placeNewPiece('d',1,new King(board, Color.BLACK));
		
		placeNewPiece('c',7,new Rook(board, Color.WHITE));
		placeNewPiece('c',8,new Rook(board, Color.WHITE));
		placeNewPiece('d',7,new Rook(board, Color.WHITE));
		placeNewPiece('e',7,new Rook(board, Color.WHITE));
		placeNewPiece('e',8,new Rook(board, Color.WHITE));
		placeNewPiece('d',8,new King(board, Color.WHITE));
	}
	
}