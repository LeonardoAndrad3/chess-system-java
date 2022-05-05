package model.chess.exception;

import model.boardgame.excetion.BoardException;

public class ChessException extends BoardException{
	private static final long serialVersionUID = 1L;
	
	public ChessException(String msg) {
		super(msg);
	}
	
}
