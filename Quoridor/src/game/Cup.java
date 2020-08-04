package game;
import java.util.*;

public class Cup {
    private int playercount;
    private int level = 1;
    private ArrayList<Player> players = new ArrayList<Player>();
    private boolean skip_level = true;
    private int levelcount = 0;
    public Cup(int playercount, ArrayList players) {
        for (int i = 1; playercount != 3 || playercount != 2 ; i++ ){
            playercount = playercount / 2 ;
            level++ ;
        }
    }
    public ArrayList<Player> play(ArrayList<Player> players){
        Collections.shuffle(players);
        if (players.size() % 2 != 0)
            players.get(0).skip_level = true;
        return players;
    }
    public void cup(){
        for (int i = 0 ; i <= level ; i++){

            if (players.size() % 2 != 0){
                for ( int j = 1; j < players.size() -1 ; j += 2){
                    //players.get(i) players.get(i+1)

                }
            }
            else{
                for ( int j = 0; j < players.size() ; j += 2){
                    //players.get(i) players.get(i+1)
                }
            }
        }

    }

}
