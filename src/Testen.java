import info.kwarc.teaching.AI.Kalah.Game;
import info.kwarc.teaching.AI.Kalah.RandomPlayer;

public class Testen {

	public static void main(String[] args) {
		Game g = new Game(new DerDude(), new RandomPlayer("p2"), 6, 3);
		System.out.println(g.play(true));
	}

}
