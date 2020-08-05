package game;
import java.util.*;

public class Cup {
    private ArrayList<Player> players = new ArrayList<Player>();
    private int index = -2;

    public Cup(ArrayList players) {
        // in kojas? chera khalie?:\
    }

    public Player[] play(ArrayList<Player> players) {
        if (players.size() % 2 != 0) {
            index += 2;
            return new Player[] {players.get(index + 1), players.get(index + 2)};
        }
        else {
            index += 2;
            return new Player[] {players.get(index), players.get(index + 1)};
        }
    }

    public void won(int winnerNumber) {
        if (players.size() % 2 != 0) {
            if (winnerNumber == 1)
                players.remove(index + 2);
            else players.remove(index + 1);
        }
        else {
            if (winnerNumber == 1)
                players.remove(index + 1);
            else players.remove(index);
        }
    }

    public void nextLevel() {
        Collections.shuffle(players);
        if (players.size() % 2 != 0)
            players.get(0).skip_level = true;
        index = -2;
    }

    public boolean levelFinished() {
        if (index >= players.size()) {
            return true;
        }
        else return false;
    }

    public Player finished() {
        if (players.size() == 1)
            return players.get(0);
        else return null;
    }
}
