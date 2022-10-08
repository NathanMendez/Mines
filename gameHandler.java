import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gameHandler {
    final int SCREEN_WIDTH = 515;
    final int SCREEN_LENGTH = 639;
    final int bombCount = 10;
    long startTime = System.currentTimeMillis();
    long endTime;
    int score = 0;
    int emptyCount = 0;
    Font font = new Font("Arial",Font.PLAIN,30);
    Tile curr;
    boolean firstPress = true;
    Tile[][] tiles = new Tile[bombCount][bombCount];

    public gameHandler(){
        JFrame f = new JFrame("Mines");
        JPanel p = new JPanel();
        p.setBackground(new Color(255,176,176));
        p.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField scorel = new JTextField();
        scorel.setSize(150,50);
        scorel.setLocation(50,30);
        scorel.setEditable(false);
        scorel.setFont(font);
        scorel.setForeground(Color.white);
        scorel.setText("Score: "+score);
        scorel.setMargin(new Insets(0,0,0,0));
        scorel.setBackground(new Color(255,195,195));

        JTextField time = new JTextField();
        time.setSize(150,50);
        time.setLocation(300,30);
        time.setEditable(false);
        time.setFont(font);
        time.setMargin(new Insets(0,0,0,0));
        time.setBackground(new Color(255,195,195));
        time.setForeground(Color.WHITE);
        time.setText("Time: ---");

        JButton status = new JButton(":)");
        status.setForeground(Color.white);
        status.setSize(50,50);
        status.setLocation(225,30);
        status.setFont(font);
        status.setMargin(new Insets(0,0,0,0));
        status.setBackground(new Color(255,195,195));
        status.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
                new gameHandler();
        }});

        p.add(time);
        p.add(status);

        p.add(scorel);
        tileFunctions functionObj = new tileFunctions();
        functionObj.tileStarter(tiles);

        int colCount = 0;
        int rowCount = 100;
        for (int i = 0; i < bombCount; i++) {
            for (int j = 0; j < bombCount; j++) {
                int tileX = i;
                int tileY = j;
                curr = tiles[i][j];
                tiles[i][j].b.setLocation(colCount, rowCount);
                tiles[i][j].b.setBackground(new Color(255,195,195));
                tiles[i][j].b.setSize(50,50);
                tiles[i][j].b.setFont(font);
                tiles[i][j].b.setMargin(new Insets(0,0,0,0));
                tiles[i][j].b.setForeground(Color.white);
                tiles[i][j].b.addMouseListener(new MouseAdapter(){
                    public void mouseReleased(MouseEvent event){
                        if(SwingUtilities.isRightMouseButton(event)){
                            tiles[tileX][tileY].flag = !tiles[tileX][tileY].flag;
                            if(tiles[tileX][tileY].flagLock == false){
                                if(tiles[tileX][tileY].flag){
                                    tiles[tileX][tileY].b.setText("F");
                                 }else{
                                     tiles[tileX][tileY].b.setText("");
                                 }
                            }
                        }
                    }
                });
                tiles[i][j].b.addActionListener(new ActionListener(){
                    
                    public void actionPerformed(ActionEvent e){
                       if(firstPress){
                            functionObj.prepTires(tiles, bombCount, tileX, tileY);
                            emptyCount = functionObj.getEmptyCount(tiles);
                            firstPress = false;
                       }else{
                                if(tiles[tileX][tileY].flagLock==false){
                                    if(tiles[tileX][tileY].isBomb){
                                        tiles[tileX][tileY].b.setForeground(Color.red);
                                        tiles[tileX][tileY].b.setText("*");
                                        functionObj.triggerBombs(tiles);
                                        tiles[tileX][tileY].flagLock = true;
                                        status.setText(":(");
                                        endTime = System.currentTimeMillis()-startTime;
                                        long end = endTime/1000;
                                        time.setText("Time: "+(double)end);
                                        functionObj.disableAllTIles(tiles);
                                    }else{
                                        int count = tiles[tileX][tileY].bombCount;
                                        if(count != 0){
                                            functionObj.getMineColor(count,tiles[tileX][tileY].b);
                                            tiles[tileX][tileY].b.setText(""+tiles[tileX][tileY].bombCount);
                                            score++;
                                            scorel.setText("Score: "+score);
                                            if(score == 100-(bombCount)-emptyCount){
                                                endTime = System.currentTimeMillis()-startTime;
                                        long end = endTime/1000;
                                        time.setText("Time: "+(double)end);
                                        functionObj.disableAllTIles(tiles);
                                            }
                                            tiles[tileX][tileY].flagLock = true;
                                        }else{
                                            functionObj.getAllNeighbors1(tiles,tileX,tileY);
                                            functionObj.getAllNeighbors2(tiles,tileX,tileY);
                                            
                                        }
                                }
                            }
                       }
                    }
                });
                p.add(tiles[i][j].b);
                colCount +=50;
                if(colCount >= SCREEN_WIDTH -15){
                    colCount = 0;
                    rowCount +=50;
                }
            }
        }


        f.add(p);

        f.setSize(SCREEN_WIDTH,SCREEN_LENGTH);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
