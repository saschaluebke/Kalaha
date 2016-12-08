import java.util.ArrayList;

/**
 * Created by Noah on 09/12/2016.
 */
public class Minimax {

    int naechsterZug=0;
    int maxDepth;
    DerDude.DudeBoard currentDudeBoard;
    public Minimax(DerDude.DudeBoard d, int maxDepth){

        currentDudeBoard = d;
        this.maxDepth = maxDepth;
    }

    public int calculateMove(ArrayList<Integer> state){
        max(state,1,maxDepth);
        return naechsterZug;

    }

    private int evaluate(ArrayList<Integer> state){
        return state.get(currentDudeBoard.houses);
    }

    //FIXME Moeglich, dass Zug ungueltig ist!!! Wenn leer, also checken!

    public int max(ArrayList<Integer> state, int player, int maxDepth){
        //FIXME oder gewonnen oder son scheis
        if(maxDepth==0){
            //Heuristik
            return evaluate(state);
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<currentDudeBoard.houses;i++){
            ArrayList<Integer> copy = new ArrayList<>(state);
            currentDudeBoard.makeMove(state,i,player);
            int wert = min(state, -player, maxDepth-1 );
            state = copy;
            if(wert > max){
                max = wert;
                if(maxDepth==maxDepth){
                    naechsterZug = i;
                }
            }



        }
        return max;
    }
    public int min(ArrayList<Integer> state, int player, int maxDepth){
        //FIXME oder gewonnen oder son scheis
        if(maxDepth==0){
            //Heuristik
            return evaluate(state);
        }
        int min = Integer.MAX_VALUE;
        for(int i=currentDudeBoard.houses+1;i<currentDudeBoard.houses*2+1;i++){
            ArrayList<Integer> copy = new ArrayList<>(state);
            currentDudeBoard.makeMove(state,i,player);
            int wert = max(state, -player, maxDepth-1 );
            state = copy;
            if(wert < min){
                min = wert;
            }


        }
        return min;
    }

    public static void main (String args[]){
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=0; i<15;i++){
            a.add(2);

        }
        DerDude.DudeBoard d = new DerDude().new DudeBoard(6,6);
        Minimax m = new Minimax(d,5);

        System.out.println(m.calculateMove(a));
    }
}
