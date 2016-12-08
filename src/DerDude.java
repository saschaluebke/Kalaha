import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import info.kwarc.teaching.AI.Kalah.Board;

public class DerDude extends info.kwarc.teaching.AI.Kalah.Agent{

	private DudeBoard currentDudeBoard;
	static private Board currentBoard; 		//muessen beide gleich sein.
	@Override
	public void init(Board board, boolean playerOne) {
		List<Integer> houses = (List<Integer>)board.getState().iterator().next();
		int seed = houses.get(0);
		currentDudeBoard = new DudeBoard(houses.size(),seed);
		currentBoard = board;
		
		//Debug
		System.out.println("DerDudeDebug: "+currentDudeBoard.houses+"/"+currentDudeBoard.seeds);
		System.out.println(currentDudeBoard.boardState);
		currentDudeBoard.makeMove(currentDudeBoard.boardState, 4, 1);
		System.out.println(currentDudeBoard.boardState);
	}

	@Override
	public int move() {
		currentDudeBoard.updateBoard(currentBoard);
		System.out.println(currentDudeBoard.boardState);
		return 1;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> students() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class DudeBoard {

	     int houses, seeds;
	     ArrayList<Integer> boardState = new ArrayList<>();
	    public DudeBoard(int houses, int seeds){
	        this.houses = houses;
	        this.seeds = seeds;
	        for (int i=0; i<houses;i++){
	        	boardState.add(seeds);
			}
	        boardState.add(0);//Mulde
	        for (int i=0; i<houses;i++){
	        	boardState.add(seeds);
			}
	        boardState.add(0);//Mulde
	        boardState.add(0); //für Flag

	    }

	    public void updateBoard(Board board){
	    	
	    	ArrayList<Integer> tmpState = new ArrayList<>();
	    	int count=0;
	    	
	    	List<Integer> houses1= (List<Integer>)board.getState().iterator().next();
	    	List<Integer> houses2= (List<Integer>)board.getState().iterator().next();
	    	System.out.println(houses1);
	    	
	    	for (int i = 0; i<houses1.size(); i++){
				tmpState.add(houses1.get(i));
			}
			tmpState.add(0);
			for (int i = 0; i<houses2.size(); i++){
				tmpState.add(houses2.get(i));
			}
			int score1 = board.getScore(1);
			tmpState.set(houses1.size(), score1);
			int score2 = board.getScore(2);
			
			tmpState.add(score2);
			tmpState.add(0);//Flag
			
			boardState = tmpState;
	    }


	    //STATE muss folgendermassen aussehen:

	    //Liste von Spieler 1 houses + mulde + Spieler2 houses + Mulde + nochmalZiehenFLAG

	    //int player gibt spieler an

	    //FIXME ES MUSS EINE LISTE MIT PLATZ FUER NOCHMALZIEHEN FLAG ANGEGEBEN WERDEN

	   public  ArrayList<Integer> makeMove(ArrayList<Integer> state, int house, int player){
	        //Check if house is empty
	       if(state.get(house) == 0){
	           return null;
	       }
	       //Get stones in house
	       int steps = state.get(house);
	       state.set(house, 0);

	       if(player==1) {

	           for (int i = 0; i < steps; i++) {
	               //Add stones in houses
	               if (houses * 2 + 1 == (house+i) % (houses*2+2)){
	                   continue;
	               }
	               state.set((house + i+1) % (houses * 2 + 2), state.get((house + i+1) % (houses * 2 + 2)) + 1);
	           }

	           //Sonderfaelle. Letzter Stein in Mulde, dann nochmal neu ziehen
	           if (house + steps == houses) {

	               state.set(houses*2+2, Integer.MAX_VALUE);
	           }

	           else if (house + steps < houses) {

	               //Shallow List
	               ArrayList<Integer> newList = new ArrayList<>(state);
	               Collections.reverse(newList);
	               //-1 weil wir oben schon einen wenn dann reingelegt haben
	               if (state.get(house + steps)-1 == 0) {

	                   state.set(houses, state.get(houses) + newList.get(house + steps + 1));
	               }

	           }
	       }
	       //Player 2
	       else {

	           for (int i = 0; i < steps; i++) {
	               //Add stones in houses, fuer Spieler 2. Wenn in Gewinnmulde
	               if (houses == (house+i) % (houses*2+2)){
	                   continue;
	               }
	               state.set((house + i+1) % (houses * 2 + 2), state.get((house + i+1) % (houses * 2 + 2)) + 1);
	           }

	           //Sonderfaelle. Letzter Stein in Mulde, dann nochmal neu ziehen
	           if (house + steps == houses*2+1) {

	               state.set(houses*2+2,Integer.MAX_VALUE);
	           }
	           else if (house + steps < houses*2+1 && house+steps > houses) {

	               //Shallow List
	               //ArrayList<Integer> newList = new ArrayList<>(state);
	               //Collections.reverse(newList);
	               //-1 weil wir oben schon einen wenn dann reingelegt haben
	               if (state.get(house + steps)-1 == 0) {

	                   state.set(houses*2+1, state.get(houses*2+1) + state.get(houses*2-house+steps));
	               }

	           }
	       }

	       return state;
	   }

	   

	}

}
