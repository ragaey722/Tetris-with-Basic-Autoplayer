package tetris.game;

import tetris.game.pieces.Piece;
import tetris.game.pieces.PieceImp;
import java.util.*;

import java.util.Random;
import java.util.Set;

import tetris.autoplay.AutoPlayer;
import tetris.game.pieces.PieceFactory;
import tetris.game.pieces.PieceFactoryImp;

public class GameImp implements TetrisGame {
	
	Piece curpiece;
	Piece nextpiece;
	Board curboard;
	PieceFactory pf;
	int curcol,currow;
	int points;
	int completed;
	boolean gameover;
	Set<GameObserver> observers;

	public GameImp(Random r, int rows , int columns){
		this.pf= new PieceFactoryImp(r);
		this.curboard= new BoardImp(rows, columns);
		this.curpiece=null;
		this.nextpiece=pf.getNextRandomPiece();
		curcol=0;
		currow=0;
		points=0;
		completed=0;
		gameover=false;
		observers = new HashSet<GameObserver>();
	}

	@Override
	public void addObserver(GameObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(GameObserver observer) {
		observers.remove(observer);

	}

	@Override
	public Piece getCurrentPiece() {
		
		return this.curpiece;
	}

	@Override
	public Board getBoard() {

		return this.curboard;
	}

	@Override
	public Piece getNextPiece() {
	return this.nextpiece;
	}

	@Override
	public int getNumberOfCompletedRows() {
		return this.completed;
	}

	@Override
	public int getPieceColumn() {
		return curcol ;
	}

	@Override
	public int getPieceRow() {
		return currow;
	}

	@Override
	public long getPoints() {
		return points;
	}

	@Override
	public boolean isGameOver() {
		return this.gameover;
	}

	@Override
	public boolean moveDown() {
		curboard.removePiece(curpiece, currow, curcol);
		if(curboard.canAddPiece(curpiece, currow+1 , curcol))
		{ curboard.addPiece(curpiece, currow+1 , curcol);
			currow++;
			for(GameObserver observer: observers)
			{ observer.piecePositionChanged();
			}
			return true;
		}
		curboard.addPiece(curpiece, currow, curcol);
		return false;
	}

	@Override
	public boolean moveLeft() {
		curboard.removePiece(curpiece, currow, curcol);
		if(curboard.canAddPiece(curpiece, currow , curcol-1))
		{ curboard.addPiece(curpiece, currow , curcol-1);
			curcol--;
			for(GameObserver observer: observers)
			{ observer.piecePositionChanged();
			}
			return true;
		}
		curboard.addPiece(curpiece, currow, curcol);
		return false;
	}

	@Override
	public boolean moveRight() {
		curboard.removePiece(curpiece, currow, curcol);
		if(curboard.canAddPiece(curpiece, currow , curcol+1))
		{ curboard.addPiece(curpiece, currow , curcol+1);
			curcol++;
			for(GameObserver observer: observers)
			{ observer.piecePositionChanged();
			}
			return true;
		}
		curboard.addPiece(curpiece, currow, curcol);
		return false;
	}

	@Override
	public boolean newPiece() {
		Piece npiece = pf.getNextRandomPiece();
		int score= curboard.deleteCompleteRows();
		completed+=score;
		if(score>0)
		{ for(GameObserver observer: observers)
			{ observer.rowsCompleted();;
			}
		} 
		switch (score){
			case 1: points+= 100 ;
			break;
			case 2: points+= 300 ;
			break;
			case 3: points+= 500 ;
			break;
			case 4: points+= 1000;
			break;
			default:points+= 0;
			break;
		}
		
		if(curboard.canAddPiece(nextpiece, 2 ,curboard.getNumberOfColumns()/2))
		{  curboard.addPiece(nextpiece, 2 , curboard.getNumberOfColumns()/2);
			curpiece=nextpiece;
			nextpiece=npiece;
			currow=2;
			curcol=curboard.getNumberOfColumns()/2;
			return true;
		}
		setGameOver(); 
		return false;
	}

	@Override
	public boolean rotatePieceClockwise() {
		curboard.removePiece(curpiece, currow, curcol);
		if(curboard.canAddPiece(curpiece.getClockwiseRotation() , currow , curcol))
		{ curboard.addPiece(curpiece.getClockwiseRotation() , currow , curcol);
			curpiece= curpiece.getClockwiseRotation();
			for(GameObserver observer: observers)
			{ observer.piecePositionChanged();
			}
			return true;
		}
		curboard.addPiece(curpiece, currow, curcol);
		return false;
	}

	@Override
	public boolean rotatePieceCounterClockwise() {
		curboard.removePiece(curpiece, currow, curcol);
		if(curboard.canAddPiece(curpiece.getCounterClockwiseRotation() , currow , curcol))
		{ curboard.addPiece(curpiece.getCounterClockwiseRotation() , currow , curcol);
			curpiece= curpiece.getCounterClockwiseRotation();
			for(GameObserver observer: observers)
			{ observer.piecePositionChanged();
			}
			return true;
		}
		curboard.addPiece(curpiece, currow, curcol);
		return false;
	}

	@Override
	public void setGameOver() {
		for(GameObserver observer: observers)
			{ observer.gameOver();
			}
		gameover= true;
	}

	@Override
	public void step() {
		if(gameover)
		return;
		boolean flag;
		if(curpiece==null)
		flag = newPiece();
		else
		{flag= moveDown();
			if (!flag)
			{ 	
				for(GameObserver observer: observers)
				{
					observer.pieceLanded();
				}
				flag=newPiece();}
		}



	}

}
