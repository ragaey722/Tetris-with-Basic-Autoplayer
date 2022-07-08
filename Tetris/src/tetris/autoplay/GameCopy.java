package tetris.autoplay;

import javax.print.attribute.standard.RequestingUserName;

import tetris.game.Board;
import tetris.game.TetrisGameView;
import tetris.game.pieces.PieceFactoryImp;
import tetris.game.pieces.*;;

public class GameCopy {
    Board curboard;
    Piece curpiece;
    Piece nextpiece;
    int curcol, currow;
   public GameCopy(TetrisGameView game){

    this.curboard= game.getBoardCopy();
    this.curcol= game.getPieceColumn();
    this.currow=game.getPieceRow();
    this.curpiece=game.getCurrentPieceCopy();
    this.nextpiece=game.getNextPieceCopy();
   }

   public GameCopy(Board board, Piece curpiece,Piece nextpiece, int curcol,int currow){

    this.curboard= board;
    this.curcol= curcol;
    this.currow= currow ;
    this.curpiece= curpiece;
    this.nextpiece=nextpiece;
   }
   
   public boolean moveLeft() {
    curboard.removePiece(curpiece, currow, curcol);
    if(curboard.canAddPiece(curpiece, currow , curcol-1))
    { curboard.addPiece(curpiece, currow , curcol-1);
        curcol--;
        return true;
    }
    curboard.addPiece(curpiece, currow, curcol);
    return false;
}

public boolean moveRight() {
    curboard.removePiece(curpiece, currow, curcol);
    if(curboard.canAddPiece(curpiece, currow , curcol+1))
    { curboard.addPiece(curpiece, currow , curcol+1);
        curcol++;
        return true;
    }
    curboard.addPiece(curpiece, currow, curcol);
    return false;
}

public boolean moveDown() {
    curboard.removePiece(curpiece, currow, curcol);
    if(curboard.canAddPiece(curpiece, currow+1 , curcol))
    { curboard.addPiece(curpiece, currow+1 , curcol);
        currow++;
        return true;
    }
    curboard.addPiece(curpiece, currow, curcol);
    return false;
}

public boolean rotatePieceClockwise() {
    curboard.removePiece(curpiece, currow, curcol);
    if(curboard.canAddPiece(curpiece.getClockwiseRotation() , currow , curcol))
    { curboard.addPiece(curpiece.getClockwiseRotation() , currow , curcol);
        curpiece= curpiece.getClockwiseRotation();
        return true;
    }
    curboard.addPiece(curpiece, currow, curcol);
    return false;
}

public boolean rotatePieceCounterClockwise() {
    curboard.removePiece(curpiece, currow, curcol);
    if(curboard.canAddPiece(curpiece.getCounterClockwiseRotation() , currow , curcol))
    { curboard.addPiece(curpiece.getCounterClockwiseRotation() , currow , curcol);
        curpiece= curpiece.getCounterClockwiseRotation();
        return true;
    }
    curboard.addPiece(curpiece, currow, curcol);
    return false;
}
public GameCopy clone(){

    GameCopy result = new GameCopy(curboard.clone(), curpiece.clone(), nextpiece.clone(), curcol, currow);
    return result;
 } 
	


}
