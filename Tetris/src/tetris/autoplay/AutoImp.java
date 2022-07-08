package tetris.autoplay;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import tetris.game.TetrisGameView;
import tetris.game.*;

public class AutoImp implements AutoPlayer {

	TetrisGameView game;
	boolean gameover,rowsCompleted;
	boolean land,piecepos;
	Queue<Move> mq;
	Stack<Move> curs;
	double bestscore;


	public AutoImp(TetrisGameView game){
		this.game=game;
		this.game.addObserver(this);
		gameover=false;
		land = false;
		piecepos=false;
		rowsCompleted=false;
		bestscore=Integer.MIN_VALUE;
		mq=new LinkedList<Move>();
		curs=new Stack<Move>();
	}

	@Override
	public void rowsCompleted() {
		this.rowsCompleted=true;
	}

	@Override
	public void piecePositionChanged() {

		this.piecepos=true;
	}

	@Override
	public void pieceLanded() {
		
		this.land = true;
	}

	@Override
	public void gameOver() {
		
		this.gameover=true;
	}

	@Override
	public Move getMove() {
		if(gameover)
			return null;
		
		if(mq.size()>0 && !land)	 
			return mq.poll();
			
			
		else if(!land)
			return Move.DOWN ;


		land = false;
		bestscore=Integer.MIN_VALUE;
		curs.clear();
		mq.clear();
		check();

		return mq.poll();
		
	}

	private void check(){
		
	 	GameCopy copy= new GameCopy(game);
		left(copy.clone());
		right(copy.clone());
		cw(copy.clone());
		cc(copy.clone());
		cw2(copy.clone());
		cc2(copy.clone());
	while(copy.moveDown());
	curs.add(Move.DOWN);
	scorcalc(copy.curboard);
		curs.pop();
	

	}

	private void left(GameCopy copy){
		if(!copy.moveLeft())
		return;
		curs.add(Move.LEFT);
		left(copy.clone());
		cw(copy.clone());
		cc(copy.clone());
		cw2(copy.clone());
		cc2(copy.clone());
	while(copy.moveDown());
	scorcalc(copy.curboard);
	curs.pop();
	
	}

	private void right(GameCopy copy){
		if(!copy.moveRight())
		return;
		curs.add(Move.RIGHT);
		right(copy.clone());
		cw(copy.clone());
		cc(copy.clone());
		cw2(copy.clone());
		cc2(copy.clone());
	while(copy.moveDown());
		scorcalc(copy.curboard);
	curs.pop();

	}

	private void cw(GameCopy copy){
		if(!copy.rotatePieceClockwise())
		return;
		curs.add(Move.ROTATE_CW);
		while(copy.moveDown());
		scorcalc(copy.curboard);
		curs.pop();

	}

	private void cc(GameCopy copy){
		if(!copy.rotatePieceCounterClockwise())
		return;
		curs.add(Move.ROTATE_CCW);
		while(copy.moveDown());
		scorcalc(copy.curboard);
		curs.pop();
	}
	private void cw2(GameCopy copy){
		if(!copy.rotatePieceClockwise() || !copy.rotatePieceClockwise())
		return;
		curs.add(Move.ROTATE_CW);
		curs.add(Move.ROTATE_CW);
		while(copy.moveDown());
		scorcalc(copy.curboard);
		curs.pop();
		curs.pop();

	}

	private void cc2(GameCopy copy){
		if(!copy.rotatePieceCounterClockwise() || !copy.rotatePieceCounterClockwise())
		return;
		curs.add(Move.ROTATE_CCW);
		curs.add(Move.ROTATE_CCW);
		while(copy.moveDown());
		scorcalc(copy.curboard);
		curs.pop();
		curs.pop();

	}





	private void scorcalc(Board board){
		double res=0;
		res = -0.51 * agHeight(board) +
			   0.76 *completerows(board) +
			  -0.356 * holes(board) +
			  -0.184 *bumpiness(board);
		
	if(res>bestscore)
	{ bestscore = res;
		assign();
	} 	
	}


	private int completerows(Board board){
		return board.deleteCompleteRows();
	}

	private int agHeight(Board board){
		int height=0;

		for (int i =0;i<board.getNumberOfColumns();i++)

		 	for (int q=0;q<board.getNumberOfRows();q++){
				if( board.getBoard()[q][i]!=null)
					{height+= board.getNumberOfRows()-q;
					continue;}
			}
		return height;
	}
	private int holes(Board board){
		int holes =0;
		for (int q=0;q<board.getNumberOfRows()-1;q++){
			for (int i =0;i<board.getNumberOfColumns();i++)
			{ if(board.getBoard()[q][i]!=null && board.getBoard()[q+1][i]==null)
				holes++;

			}
		}
		return holes;
	}
	private int bumpiness(Board board){
		int bumpy=0;
		int preh = colheight(board, 0);
		int curh =0 ;
		for (int i=1;i<board.getNumberOfColumns();i++)
		{	curh= colheight(board, i);
			if(curh-preh >=0)
			bumpy+=curh-preh;
			else
			bumpy+=preh-curh;

			preh=curh;
		}
		return bumpy;
	}
	private int colheight(Board board, int col){
		int h=0;
		for (int q=0;q<board.getNumberOfRows();q++)
		{ if(board.getBoard()[q][col]!=null)
			{h= board.getNumberOfRows()-q; break;}

		}
		return h;
	}

	private void assign(){
		mq.clear();
		for(Move m : curs)
		mq.add(m);
	}

	

}
