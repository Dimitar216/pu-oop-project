package gameboard;

import tiles.BattlefieldTile;
import tiles.PlayerTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard extends JFrame implements MouseListener {
    //PlayerA
    PlayerTile playerATile1 = new PlayerTile(0,0, Color.GRAY);
    PlayerTile playerATile2 = new PlayerTile(0,1, Color.BLACK);
    PlayerTile playerATile3 = new PlayerTile(0,2, Color.GRAY);
    PlayerTile playerATile4 = new PlayerTile(0,3, Color.BLACK);
    PlayerTile playerATile5 = new PlayerTile(0,4, Color.GRAY);
    PlayerTile playerATile6 = new PlayerTile(0,5, Color.BLACK);
    PlayerTile playerATile7 = new PlayerTile(0,6, Color.GRAY);
    PlayerTile playerATile8 = new PlayerTile(0,7, Color.BLACK);
    PlayerTile playerATile9 = new PlayerTile(0,8, Color.GRAY);
    PlayerTile playerATile10 = new PlayerTile(1,0, Color.BLACK);
    PlayerTile playerATile11 = new PlayerTile(1,1, Color.GRAY);
    PlayerTile playerATile12 = new PlayerTile(1,2, Color.BLACK);
    PlayerTile playerATile13 = new PlayerTile(1,3, Color.GRAY);
    PlayerTile playerATile14 = new PlayerTile(1,4, Color.BLACK);
    PlayerTile playerATile15 = new PlayerTile(1,5, Color.GRAY);
    PlayerTile playerATile16 = new PlayerTile(1,6, Color.BLACK);
    PlayerTile playerATile17 = new PlayerTile(1,7, Color.GRAY);
    PlayerTile playerATile18 = new PlayerTile(1,8, Color.BLACK);
    //PlayerB
    PlayerTile playerBTile1 = new PlayerTile(6,0, Color.GRAY);
    PlayerTile playerBTile2 = new PlayerTile(6,1, Color.BLACK);
    PlayerTile playerBTile3 = new PlayerTile(6,2, Color.GRAY);
    PlayerTile playerBTile4 = new PlayerTile(6,3, Color.BLACK);
    PlayerTile playerBTile5 = new PlayerTile(6,4, Color.GRAY);
    PlayerTile playerBTile6 = new PlayerTile(6,5, Color.BLACK);
    PlayerTile playerBTile7 = new PlayerTile(6,6, Color.GRAY);
    PlayerTile playerBTile8 = new PlayerTile(6,7, Color.BLACK);
    PlayerTile playerBTile9 = new PlayerTile(6,8, Color.GRAY);
    PlayerTile playerBTile10 = new PlayerTile(5,0, Color.BLACK);
    PlayerTile playerBTile11 = new PlayerTile(5,1, Color.GRAY);
    PlayerTile playerBTile12 = new PlayerTile(5,2, Color.BLACK);
    PlayerTile playerBTile13 = new PlayerTile(5,3, Color.GRAY);
    PlayerTile playerBTile14 = new PlayerTile(5,4, Color.BLACK);
    PlayerTile playerBTile15 = new PlayerTile(5,5, Color.GRAY);
    PlayerTile playerBTile16 = new PlayerTile(5,6, Color.BLACK);
    PlayerTile playerBTile17 = new PlayerTile(5,7, Color.GRAY);
    PlayerTile playerBTile18 = new PlayerTile(5,8, Color.BLACK);

    public GameBoard(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,1000);
        this.setVisible(true);
    }

    /**
     * Method which calls the methods where all the figures/lines/tiles are rendered.
     * @param g graphics component
     */
    @Override
    public void paint(Graphics g){
        playerASideOfGameBoardRender(g);
        playerBSideOfGameBoardRender(g);
        battleFieldRender(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private void playerASideOfGameBoardRender(Graphics g) {
        playerATile1.render(g);
        playerATile2.render(g);
        playerATile3.render(g);
        playerATile4.render(g);
        playerATile5.render(g);
        playerATile6.render(g);
        playerATile7.render(g);
        playerATile8.render(g);
        playerATile9.render(g);
        playerATile10.render(g);
        playerATile11.render(g);
        playerATile12.render(g);
        playerATile13.render(g);
        playerATile14.render(g);
        playerATile15.render(g);
        playerATile16.render(g);
        playerATile17.render(g);
        playerATile18.render(g);
    }
    private void playerBSideOfGameBoardRender(Graphics g) {
        playerBTile1.render(g);
        playerBTile2.render(g);
        playerBTile3.render(g);
        playerBTile4.render(g);
        playerBTile5.render(g);
        playerBTile6.render(g);
        playerBTile7.render(g);
        playerBTile8.render(g);
        playerBTile9.render(g);
        playerBTile10.render(g);
        playerBTile11.render(g);
        playerBTile12.render(g);
        playerBTile13.render(g);
        playerBTile14.render(g);
        playerBTile15.render(g);
        playerBTile16.render(g);
        playerBTile17.render(g);
        playerBTile18.render(g);
    }
    private void battleFieldRender(Graphics g) {
        for(int row = 2; row<=4;row++){
            for(int col = 0 ; col<9;col++){
                BattlefieldTile battlefieldTile = new BattlefieldTile(row,col,Color.LIGHT_GRAY);
                battlefieldTile.render(g);
            }
        }
    }
}
