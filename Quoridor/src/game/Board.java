package game;

public class Board {
  private Boolean[][] board=new Boolean[9][9];
        public Board(){
            board[0][4]=true;
            board[8][4]=true;
        }
        public void move(String playermove){
            if(playermove.equalsIgnoreCase("Up")) {
            }

            if(playermove.equalsIgnoreCase("Down")){

            }
            if (playermove.equalsIgnoreCase("Right")){

            }
            if (playermove.equalsIgnoreCase("Left")){

            }
        }
}
