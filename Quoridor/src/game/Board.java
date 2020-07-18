package game;

public class Board{
        private Boolean[][] board=new Boolean[9][9];
        Bead bead1=new Bead();
        Bead bead2=new Bead();
    public Board(){
        board[0][4]=true;
        board[8][4]=true;
        bead1.setX(0);
        bead1.setY(4);
        bead2.setX(8);
        bead2.setY(4);
    }
        public void move(String playermove){
            if(playermove.equalsIgnoreCase("Up")) {
                board[bead1.getX()][bead1.getY()]=false;
                bead1.setX(bead1.getX()-1);
                board[bead1.getX()][bead1.getY()]=true;
            }

            if(playermove.equalsIgnoreCase("Down")){
                board[bead1.getX()][bead1.getY()]=false;
                bead1.setX(bead1.getX()+1);
                board[bead1.getX()][bead1.getY()]=true;
            }
            if (playermove.equalsIgnoreCase("Right")){
                board[bead1.getX()][bead1.getY()]=false;
                bead1.setY(bead1.getY()+1);
                board[bead1.getX()][bead1.getY()]=true;
            }
            if (playermove.equalsIgnoreCase("Left")){
                board[bead1.getX()][bead1.getY()]=false;
                bead1.setY(bead1.getY()-1);
                board[bead1.getX()][bead1.getY()]=true;
            }
        }

    }


