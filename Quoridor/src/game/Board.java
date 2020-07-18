package game;

public class Board{
        private Boolean[][] board=new Boolean[9][9];
        Player player1=new Player();
        Player player2=new Player();
        Player turn = player1;
    public Board(){
        board[0][4]=true;
        board[8][4]=true;
        player1.bead.setY(0);
        player1.bead.setX(4);
        player2.bead.setY(8);
        player2.bead.setX(4);
    }
    public void turn() {
        if (this.turn == player1)
            turn = player2;
        else
            turn = player1;
    }
        public void move(String playermove){
            if(playermove.equalsIgnoreCase("Up")) {
                board[(turn.bead).getY()][(turn.bead).getX()]=false;
                (turn.bead).setY((turn.bead).getY()-1);
                board[(turn.bead).getY()][(turn.bead).getX()]=true;
            }

            if(playermove.equalsIgnoreCase("Down")){
                board[(turn.bead).getY()][(turn.bead).getX()]=false;
                (turn.bead).setY((turn.bead).getY()+1);
                board[(turn.bead).getY()][(turn.bead).getX()]=true;
            }
            if (playermove.equalsIgnoreCase("Right")){
                board[(turn.bead).getY()][(turn.bead).getX()]=false;
                (turn.bead).setX((turn.bead).getX()+1);
                board[(turn.bead).getY()][(turn.bead).getX()]=true;
            }
            if (playermove.equalsIgnoreCase("Left")){
                board[(turn.bead).getY()][(turn.bead).getX()]=false;
                (turn.bead).setX((turn.bead).getX()-1);
                board[(turn.bead).getY()][(turn.bead).getX()]=true;
            }
            turn();
        }

    }


