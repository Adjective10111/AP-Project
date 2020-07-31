package game;
import java.util.*;

public class Cup {
    private int playercount;
    private int level = 1;
    private ArrayList<Boolean> players = new ArrayList<Boolean>();
    private boolean skip_level = true;
    private int levelcount = 0;
    public Cup(int playercount) {
         for (int i = 0 ; i < playercount ; i++){
             players.add(false);
         }
         for (int i = 1; playercount == 3 || playercount == 2 ; i++ ){
                playercount = playercount / 2 ;
                level++ ;
         }
    }
    public void cup(){
       for (int i = 0 ; i <= level ; i++){
           for (int k = 0 ; k < players.size() ; k++){
               players.set(k, false);
           }
           Collections.shuffle(players);
           if (players.size() % 2 != 0){
               players.set(0, true);
               for ( int j = 1; j < players.size() -1 ; j += 2){
                        //players.get(i) players.get(i+1)

                       }
                   }
           else{
               for ( int j = 0; j < players.size() ; j += 2){
                        //players.get(i) players.get(i+1)
               }
           }
           for ( int j = 0; j < players.size() ; j++){
               if ( players.get(j) == false)
                   players.remove(j);
           }
       }

    }

}
