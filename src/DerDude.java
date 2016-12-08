import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import info.kwarc.teaching.AI.Kalah.Board;

public class DerDude extends info.kwarc.teaching.AI.Kalah.Agent{

	
	public class DudeBoard {

	     int houses, seeds;
	    public DudeBoard(int houses, int seeds){
	        this.houses = houses;
	        this.seeds = seeds;

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

	               state.set(14, Integer.MAX_VALUE);
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

	               state.set(14,Integer.MAX_VALUE);
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
	
	@Override
	public void init(Board board, boolean playerOne) {
		List<Integer> houses = (List<Integer>)board.getState().iterator().next();
		int seed = houses.get(0);
		DudeBoard db = new DudeBoard(houses.size(),seed);
		System.out.println("DerDudeDebug: "+db.houses+"/"+db.seeds);
		ArrayList<Integer> state = new ArrayList<Integer>();
	       for(int i=0;i < 15; i++){
	           state.add(4);
	       }
	      System.out.print(Arrays.toString(state.toArray()));
	      db.makeMove(state, 12, 2);
	      System.out.print(Arrays.toString(state.toArray()));

	}

	@Override
	public int move() {
		// TODO Auto-generated method stub
		return 0;
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

}
