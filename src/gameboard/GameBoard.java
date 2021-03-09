package gameboard;

import figures.Dwarf;
import figures.Elf;
import figures.Figure;
import figures.Knight;
import tiles.BattlefieldTile;
import tiles.PlayerTile;
import ui.Modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard extends JFrame implements MouseListener {
    private int playerTurn = 0;
    Figure[] figuresSelection = new Figure[3];
    Figure[][] figureCollection = new Figure[7][9];
    private Figure selectedFigure;

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
        initialFigureSelection();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1400,1000);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    /**
     * Method which calls the methods where all the figures/lines/tiles are rendered.
     * @param g graphics component
     */
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        playerASideOfGameBoardRender(g);
        playerBSideOfGameBoardRender(g);
        battleFieldRender(g);
        figureSelectorRenderer(g);
        playerTurnRenderer(g);
        figureBoardRenderer(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardCoordinates(e.getY());
        int col = this.getBoardCoordinates(e.getX());

        if(this.selectedFigure != null){
            figurePlacement(row, col);
            return;
        }

        if(row == 1 && col > 9 && col < 13){
            figureSelector(col);
        } else {
            Modal.render(this,"Warning!","During selection you can select only from the right hand of the screen.");
        }
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

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    /**
     * Method which selects the initial components of the selection array
     */
    private void initialFigureSelection(){
        int arrayIndexCounter = 0;
        for (int i = 10;i<13;i++){
            int randomNumber = ThreadLocalRandom.current().nextInt(1,4);
            if(randomNumber == 1){
                Dwarf dwarf = new Dwarf(1,i);
                figuresSelection[arrayIndexCounter++] = dwarf;
            } else if(randomNumber == 2){
                Elf elf = new Elf(1,i);
                figuresSelection[arrayIndexCounter++] = elf;
            } else if(randomNumber == 3){
                Knight knight = new Knight(1,i);
                figuresSelection[arrayIndexCounter++] = knight;
            }
        }
    }
    /**
     * Gets tile from array with inputted coordinates
     * @param index index of the searched element.
     * @return tile
     */
    private Figure getBoardSelectionTile(int index){
        return this.figuresSelection[index];
    }

    /**
     * Method which selects which figure to render
     * @param g graphics component
     */
    private void figureSelectorRenderer(Graphics g) {
        for(int i = 0; i<3; i++){
            Figure figure = getBoardSelectionTile(i);
            String str = figure.getTitle();
            switch (str) {
                case "D":
                    Dwarf dwarf = (Dwarf) getBoardSelectionTile(i);
                    dwarf.render(g);
                    break;
                case "E":
                    Elf elf = (Elf) getBoardSelectionTile(i);
                    elf.render(g);
                    break;
                case "K":
                    Knight knight = (Knight) getBoardSelectionTile(i);
                    knight.render(g);
                    break;
            }
        }
    }

    private boolean hasBoardFigure(int row,int col){
        return this.getBoardTile(row,col) != null;
    }

    private Figure getBoardTile(int row, int col){
        return this.figureCollection[row][col];
    }

    /**
     * Method which shows which player's turn it is
     * @param g graphics component
     */
    private void playerTurnRenderer(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        if(playerTurn%2 == 0){
            g.drawString("Player A Turn",1050,90);
        } else if(playerTurn%2 == 1){
            g.drawString("Player B Turn",1050,90);
        }
    }

    /**
     * Method which searches for figures on the board and renders them
     * @param g graphics component
     */
    private void figureBoardRenderer(Graphics g){
        for(int row = 0; row<7;row++){
            for(int col = 0; col < 9; col++ ){
                if(this.hasBoardFigure(row,col)){
                    Figure figure = getBoardTile(row,col);
                    String str = figure.getTitle();
                    switch (str) {
                        case "D":
                            Dwarf dwarf = (Dwarf) getBoardTile(row,col);
                            dwarf.render(g);
                            break;
                        case "E":
                            Elf elf = (Elf) getBoardTile(row,col);
                            elf.render(g);
                            break;
                        case "K":
                            Knight knight = (Knight) getBoardTile(row,col);
                            knight.render(g);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Method which gets coordinates based on inputted X/Y and returns row/col
     * @param coordinates X or Y coordinate.
     * @return row or col coordinate.
     */
    private int getBoardCoordinates(int coordinates){
        return  coordinates/100;
    }

    /**
     * Method which transforms selectedFigure to one of the 3 figures
     * @param index index in the array
     */
    private void selectedFigureSelector(int index) {
            Figure figure = getBoardSelectionTile(index);
            String str = figure.getTitle();
            switch (str) {
                case "D":
                    Dwarf dwarf = (Dwarf) getBoardSelectionTile(index);
                    this.selectedFigure = dwarf;
                    break;
                case "E":
                    Elf elf = (Elf) getBoardSelectionTile(index);
                    this.selectedFigure = elf;
                    break;
                case "K":
                    Knight knight = (Knight) getBoardSelectionTile(index);
                    this.selectedFigure = knight;
                    break;
            }
        }

    /**
     * Method that when the player clicks on the screen the appropriate figure is selected
     * @param col col of the figure
     */
    private void figureSelector(int col) {
        if(col == 10){
            selectedFigure = getBoardSelectionTile(0);
            selectedFigureSelector(0);
        } else if(col == 11){
            selectedFigure = getBoardSelectionTile(1);
            selectedFigureSelector(1);
        } else {
            selectedFigure = getBoardSelectionTile(2);
            selectedFigureSelector(2);
        }
    }

    private void figureSelectionRefresher(){
        int arrayIndexCounter = 0;
        for(int i = 0;i<3;i++){
            figuresSelection[i] = null;
        }
        for (int i = 10;i<13;i++){
            int randomNumber = ThreadLocalRandom.current().nextInt(1,4);
            if(randomNumber == 1){
                Dwarf dwarf = new Dwarf(1,i);
                figuresSelection[arrayIndexCounter++] = dwarf;
            } else if(randomNumber == 2){
                Elf elf = new Elf(1,i);
                figuresSelection[arrayIndexCounter++] = elf;
            } else if(randomNumber == 3){
                Knight knight = new Knight(1,i);
                figuresSelection[arrayIndexCounter++] = knight;
            }
        }
    }
    private void figurePlacement(int row, int col) {
        Figure figure = this.selectedFigure;
        figure.move(row, col);
        this.figureCollection[figure.getRow()][figure.getCol()] = selectedFigure;
        this.selectedFigure = null;
        figureSelectionRefresher();
        playerTurn++;
        this.repaint();
        return;
    }
}