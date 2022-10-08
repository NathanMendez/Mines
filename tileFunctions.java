import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;

public class tileFunctions {
    public tileFunctions(){};
    public void tileStarter(Tile[][] tiles){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[i][j] = new Tile();
            }
        }
    }
    private void setBombs(Tile[][] tiles, int bombCount,int exepX,int exepY){
        for (int i = 0; i < bombCount+5; i++) {    
            int rand = ThreadLocalRandom.current().nextInt(0,bombCount);
            int rand2 = ThreadLocalRandom.current().nextInt(0,bombCount);
            if(tiles[rand][rand2].isBomb||(rand==exepX&&exepY==rand2)){
                i = i-1;
                continue;
            }else{
                tiles[rand][rand2].isBomb = true;
            }
            
        }
    }
    private void setCount(Tile[][] tiles){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {//l
                    if(tiles[i-1][j].isBomb){
                        tiles[i][j].bombCount++;
                    }
                } catch (Exception e) {
                    ;
                }
                try {//r
                    if(tiles[i+1][j].isBomb){
                        tiles[i][j].bombCount++;
                    }
                } catch (Exception e) {
                    ;
                }
                
                try {//u
                    if(tiles[i][j+1].isBomb){
                        tiles[i][j].bombCount++;
                    }
                } catch (Exception e) {
                    ;
                }
                try {//d
                    if(tiles[i][j-1].isBomb){
                        tiles[i][j].bombCount++;
                    }
                } catch (Exception e) {
                    ;
                }

                try {//ul
                    if(tiles[i-1][j+1].isBomb){
                        tiles[i][j].bombCount++;
                    }
                } catch (Exception e) {
                    ;
                }
                try {//ll
                    if(tiles[i-1][j-1].isBomb){
                        tiles[i][j].bombCount++;
                    }
                } catch (Exception e) {
                    ;
                }

                try {//ur
                    if(tiles[i+1][j+1].isBomb){
                        tiles[i][j].bombCount++;
                    }
                } catch (Exception e) {
                    ;
                }
                try {//lr
                    if(tiles[i+1][j-1].isBomb){
                        tiles[i][j].bombCount++;
                    }
                } catch (Exception e) {
                    ;
                }
            }
        }

    }
    public void getMineColor(int count,JButton button){
        switch (count) {
            case 1:
                button.setForeground(Color.BLUE);
                break;
            case 2:
                button.setForeground(Color.MAGENTA);
                break;
            case 3:
                button.setForeground(Color.YELLOW);
                break;
            case 4:
                button.setForeground(Color.ORANGE);
                break;
            case 5:
                button.setForeground(Color.PINK);
                break;
            case 6:
                button.setForeground(Color.WHITE);
                break;
            case 7:
                button.setForeground(Color.CYAN);
                break;

            default:
                break;
        }
    }

    public void triggerBombs(Tile[][] tiles){
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if(tiles[i][j].isBomb){
                    tiles[i][j].b.setText("*");
                    tiles[i][j].b.setForeground(Color.RED);
                    tiles[i][j].flagLock = true;
                }
            }
        }
    }
    public void getAllNeighbors1(Tile[][] tiles,int xPos,int yPos){
        try{
            if(tiles[xPos][yPos].bombCount == 0){
                tiles[xPos][yPos].b.setBackground(new Color(255,216,216));
                tiles[xPos][yPos].flagLock = true;
                getAllNeighbors1(tiles,xPos+1,yPos);
                getAllNeighbors1(tiles,xPos,yPos+1);
                return;
            }
        }catch(Exception e){
           ;
        }
    }

    public void getAllNeighbors2(Tile[][] tiles,int xPos,int yPos){
        try{
            if(tiles[xPos][yPos].bombCount == 0){
                tiles[xPos][yPos].b.setBackground(new Color(255,216,216));
                getAllNeighbors2(tiles,xPos-1,yPos);
                getAllNeighbors2(tiles,xPos,yPos-1);
                return;
            }
        }catch(Exception e){
            ;
        }
    }

    public void prepTires(Tile[][] tiles,int bombCount,int excepX,int excepY){
        setBombs(tiles, bombCount,excepX,excepY);
        setCount(tiles);
    }

    public int getEmptyCount(Tile[][]tiles){
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if(tiles[i][j].bombCount==0){
                    count++;
                }
            }
        }return count;
    }

    public void disableAllTIles(Tile[][] tiles){
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if(tiles[i][j].bombCount==0){
                    tiles[i][j].flagLock = true;
                }
            }
        }
    }
}
