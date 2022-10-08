import javax.swing.*;
class Tile{
    public boolean isBomb = false;
    public int bombCount = 0;
    public boolean flag = false;
    public boolean flagLock = false;
    JButton b;
    public Tile(){
        b = new JButton();
    };
}