package gameboard;

import figures.Dwarf;
import figures.Elf;
import figures.Figure;
import figures.Knight;
import tiles.BattlefieldTile;
import tiles.BlockingTile;
import tiles.MenuTile;
import tiles.PlayerTile;
import ui.Modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard extends JFrame implements MouseListener {
    private int playerTurn = 0;
    private int figuresPlaced = 0;
    private int figuresOfPlayerA = 3;
    private int figuresOfPlayerB = 3;
    private int pointsPlayerA = 0;
    private int pointsPlayerB = 0;
    private boolean isButtonClicked = false;
    private boolean isAttackInitiated = false;
    private boolean isMovementButtonClicked = false;
    private boolean isHealButtonClicked = false;
    Figure[] figuresSelection = new Figure[3];
    Figure[][] figureCollection = new Figure[7][9];
    Figure[] figuresDestroyedPlayerA = new Figure[3];
    Figure[] figuresDestroyedPlayerB = new Figure[3];
    private int indexForPlayerADestroyedFigures = 0;
    private int indexForPlayerBDestroyedFigures = 0;
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

    /**
     * Game board constructor
     */
    public GameBoard(){
        blockingTilesSetUp();
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
        blockingTileRenderer(g);
        playerTurnRenderer(g);
        figureBoardRenderer(g);
        playerAPlacementBlockingTilesRenderer(g);
        playerBPlacementBlockingTilesRenderer(g);
        actionMenuRenderer(g);
    }

    /**
     * Method which awaits mouse input and the game mechanics is implemented through
     * @param e Mouse listener.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardCoordinates(e.getY());
        int col = this.getBoardCoordinates(e.getX());

        figurePlacementPhase(row, col);

        if(figuresPlaced>=6) {
            if (battlePhase(row, col)) return;
            figureSelectionForBattlePhase(row, col);
        }
        endGameChecker();
    }

    private boolean battlePhase(int row, int col) {
        if (this.selectedFigure != null) {
            buttonSelection(row, col);
            if(row <=6&& col <=8) {
                if (isButtonClicked) {
                    Figure figure = this.selectedFigure;
                    String str = figure.getTitle();
                    switch (str) {
                        case "D":
                            return dwarfSelector(row, col);
                        case "E":
                            return elfSelector(row, col);
                        case "K":
                            return knightSelector(row, col);
                    }
                }
            }
        }
        return false;
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

    /**
     * Method which renders playerA's side of the field.
     * @param g graphics component
     */
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

    /**
     * Method which renders playerB's side of the field.
     * @param g Graphics component.
     */
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

    /**
     * Method which renders the tiles between the player's fields.
     * @param g graphics component
     */
    private void battleFieldRender(Graphics g) {
        for(int row = 2; row<=4;row++){
            for(int col = 0 ; col<9;col++){
                BattlefieldTile battlefieldTile = new BattlefieldTile(row,col,Color.LIGHT_GRAY);
                battlefieldTile.render(g);
            }
        }
    }

    /**
     * Method which selects the initial components of the selection array
     */
    private void initialFigureSelection(){
        int arrayIndexCounter = 0;
        for (int i = 10;i<13;i++){
            int randomNumber = ThreadLocalRandom.current().nextInt(1,4);
            if(randomNumber == 1){
                Dwarf dwarf = new Dwarf(1,i,Color.WHITE);
                figuresSelection[arrayIndexCounter++] = dwarf;
            } else if(randomNumber == 2){
                Elf elf = new Elf(1,i,Color.WHITE);
                figuresSelection[arrayIndexCounter++] = elf;
            } else if(randomNumber == 3){
                Knight knight = new Knight(1,i,Color.WHITE);
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
        if(figuresPlaced<6) {
            for (int i = 0; i < 3; i++) {
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
    }

    /**
     * Method which checks if the figure exists.
     * @param row row of the figure.
     * @param col col of the figure.
     * @return true if it exists,false if not.
     */
    private boolean hasBoardFigure(int row,int col){
        return this.getBoardFigure(row,col) != null;
    }

    /**
     * Gets figure from array with inputted coordinates
     * @param row row of the searched figure.
     * @param col col of the searched figure.
     * @return figure
     */
    private Figure getBoardFigure(int row, int col){
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
                    Figure figure = getBoardFigure(row,col);
                    String str = figure.getTitle();
                    switch (str) {
                        case "D":
                            Dwarf dwarf = (Dwarf) getBoardFigure(row,col);
                            dwarf.render(g);
                            break;
                        case "E":
                            Elf elf = (Elf) getBoardFigure(row,col);
                            elf.render(g);
                            break;
                        case "K":
                            Knight knight = (Knight) getBoardFigure(row,col);
                            knight.render(g);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Method where it is decided what will the figure do attack/heal/move
     * @param row row of the action being taken
     * @param col col of the action being taken
     * @return
     */
    private boolean knightSelector(int row, int col) {
        Knight knight = (Knight) this.selectedFigure;
        knightAttack(row, col,knight);
        knightMovement(row, col, knight);
        knightHeal(knight);
        playerTurn++;
        return true;
    }

    /**
     * Method where it is decided what will the figure do attack/heal/move
     * @param row row of the action being taken
     * @param col col of the action being taken
     * @return
     */
    private boolean elfSelector (int row, int col) {
        Elf elf = (Elf) this.selectedFigure;
        elfAttack(row, col,elf);
        elfMovement(row, col,elf);
        elfHeal(elf);
        playerTurn++;
        return true;
    }

    /**
     * Method where it is decided what will the figure do attack/heal/move
     * @param row row of the action being taken
     * @param col col of the action being taken
     * @return
     */
    private boolean dwarfSelector(int row, int col) {
        Dwarf dwarf = (Dwarf) this.selectedFigure;
        dwarfAttack(row, col,dwarf);
        dwarfMovement(row, col,dwarf);
        dwarfHeal(dwarf);
        playerTurn++;
        return true;
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
    private void figureSelector(int row,int col) {
        if(row == 1 && col > 9 && col < 13){
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
        } else {
            Modal.render(this,"Warning!","Out of bounds!");
        }
    }

    /**
     * Method which refreshes the components of the selection array
     */
    private void figureSelectionRefresher(){
        Color color;
        if(playerTurn%2 == 1){
            color = Color.WHITE;
        } else {
            color = Color.GREEN;
        }
        int arrayIndexCounter = 0;
        for (int i = 10;i<13;i++){
            int randomNumber = ThreadLocalRandom.current().nextInt(1,4);
            if(randomNumber == 1){
                Dwarf dwarf = new Dwarf(1,i,color);
                figuresSelection[arrayIndexCounter++] = dwarf;
            } else if(randomNumber == 2){
                Elf elf = new Elf(1,i,color);
                figuresSelection[arrayIndexCounter++] = elf;
            } else if(randomNumber == 3){
                Knight knight = new Knight(1,i,color);
                figuresSelection[arrayIndexCounter++] = knight;
            }
        }
    }

    /**
     * Method where the placement of the figure is implemented
     * @param row row of the figure
     * @param col col of the figure
     */
    private void figurePlacement(int row, int col) {
        if(playerPlacementChecker(row)) {
            Figure figure = this.selectedFigure;
            figure.move(row, col);
            this.figureCollection[figure.getRow()][figure.getCol()] = selectedFigure;
            this.selectedFigure = null;
            figureSelectionRefresher();
            playerTurn++;
            figuresPlaced++;
            this.repaint();
        } else {
            Modal.render(this,"Warning!","Invalid placement");
        }
    }

    /**
     * Method which renders red squares everywhere besides playerA's field
     * @param g graphics component
     */
    private void playerAPlacementBlockingTilesRenderer(Graphics g) {
        if (figuresPlaced < 6) {
            if (playerTurn % 2 == 0) {
                for (int i = 2; i < 7; i++) {
                    for (int j = 0; j < 9; j++) {
                        BlockingTile blockingTile = new BlockingTile(i, j, Color.RED);
                        blockingTile.render(g);
                    }
                }
            }
        }
    }

    /**
     * Method which renders red squares everywhere besides playerB's field
     * @param g graphics component
     */
    private void playerBPlacementBlockingTilesRenderer(Graphics g) {
        if (figuresPlaced < 6) {
            if (playerTurn % 2 == 1) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 9; j++) {
                        BlockingTile blockingTile = new BlockingTile(i, j, Color.RED);
                        blockingTile.render(g);
                    }
                }
            }
        }
    }

    /**
     * Method which checks which row the figure is placed depending on the player turn.
     * @param row row of the figure being placed.
     * @return true if the placement is valid false if not.
     */
    private boolean playerPlacementChecker(int row){
        if(playerTurn%2==0&&row<2){
            return true;
        }
        if(playerTurn%2==1&&row>4){
            return true;
        }
        return false;
    }

    /**
     * Method where the placement phase is implemented
     * @param row row of the figure being placed.
     * @param col col of the figure being placed.
     */
    private void figurePlacementPhase(int row, int col) {
        if(figuresPlaced<6) {
            if (this.selectedFigure != null) {
                if (row < 7 && col < 9) {
                    figurePlacement(row, col);
                    return;
                } else {
                    Modal.render(this, "Warning!", "Out of bounds!");
                }
            }

            figureSelector(row, col);
        }
    }

    /**
     * renders menu after figure placement is done
     * @param g graphics component
     */
    private void actionMenuRenderer(Graphics g){
        if(figuresPlaced >= 6) {
            MenuTile attack = new MenuTile(5, 10, Color.WHITE, "Attack");
            attack.render(g);
            MenuTile move = new MenuTile(5, 11, Color.WHITE, "Move");
            move.render(g);
            MenuTile heal = new MenuTile(5, 12, Color.WHITE, "Heal");
            heal.render(g);
       }
    }

    /**
     * Method which sets up blocking tiles into the array
     */
    private void blockingTilesSetUp(){
        int random = ThreadLocalRandom.current().nextInt(1,6);
        for(int i =0; i<random;i++){
            int randomRow = ThreadLocalRandom.current().nextInt(2, 5);
            int randomCol = ThreadLocalRandom.current().nextInt(0, 9);
            BlockingTile tile = new BlockingTile(randomRow,randomCol,Color.BLACK);
            this.figureCollection[randomRow][randomCol] = tile;
        }
    }

    /**
     * Method which renders a blocking tile if there is one there
     * @param g graphics component
     */
    private void blockingTileRenderer(Graphics g){
        for(int i = 2;i<5;i++){
            for(int j = 0 ; j<9;j++){
                if(hasBoardFigure(i,j)){
                    Figure figure = getBoardFigure(i,j);
                    if(figure.getColor().equals(Color.BLACK)){
                        BlockingTile tile = (BlockingTile) figure;
                        tile.render(g);
                    }
                }
            }
        }
    }

    /**
     * Method which selects the figure during the battling phase
     * @param row row of selected figure
     * @param col col of selected figure
     */
    private void figureSelectionForBattlePhase(int row, int col) {
        if(row<=6&&col<=8) {
            if (this.selectedFigure == null) {
                if (this.hasBoardFigure(row, col)) {
                    this.selectedFigure = this.getBoardFigure(row, col);
                    if ((playerTurn % 2 == 0 && selectedFigure.getColor().equals(Color.WHITE)) || (playerTurn % 2 == 1 && selectedFigure.getColor().equals(Color.GREEN))) {
                        System.out.println("Successfully selected!");
                    } else {
                        this.selectedFigure = null;
                        Modal.render(this, "Warning!", "You can select only your figures!");
                    }
                }
            }
        }
    }

    /**
     * Method where the knight movement is realized
     * @param row row of knight being moved to
     * @param col col of knight being moved to
     * @param knight knight object
     */
    private void knightMovement(int row, int col,Knight knight) {
        if (isMovementButtonClicked) {
            if (knight.isMoveValid(row, col)) {
                if (this.figureCollection[row][col] == null) {
                    int initialRow = knight.getRow();
                    int initialCol = knight.getCol();

                    knight.move(row, col);
                    this.figureCollection[knight.getRow()][knight.getCol()] = this.selectedFigure;
                    this.figureCollection[initialRow][initialCol] = null;
                    this.selectedFigure = null;
                    this.repaint();
                    this.isMovementButtonClicked = false;
                    this.isButtonClicked = false;
                }
            }
        }
    }

    /**
     * Method which activates the button clicked
     * @param row row of the button
     * @param col col of the button
     */
    private void buttonSelection(int row,int col) {
        if(row == 5 && col == 10){
            this.isAttackInitiated = true;
            this.isButtonClicked = true;
            System.out.println("Attack initiated!");
        }
        if(row == 5 && col == 11){
            this.isMovementButtonClicked = true;
            this.isButtonClicked = true;
            System.out.println("Movement initiated!");
        }
        if(row == 5 && col == 12){
            this.isHealButtonClicked = true;
            this.isButtonClicked = true;
            System.out.println("Heal initiated!");
        }
    }

    /**
     * Method which removes the blocking tile
     * @param row row of tile
     * @param col col of tile
     */
    private void blockingTileAttack(int row, int col) {
        this.figureCollection[row][col] = null;
        this.selectedFigure = null;
        this.repaint();
        this.isAttackInitiated = false;
        this.isButtonClicked = false;
    }

    /**
     * Calculates the damage done to the figure
     * @param knight attacking knight
     * @param figure defending figure
     * @param row row of attacked figure
     * @param col col of attacked figure
     */
    private void knightAttackingAnotherFigure(Knight knight, Figure figure,int row,int col) {
        int diceOne = ThreadLocalRandom.current().nextInt(1, 11);
        int diceTwo = ThreadLocalRandom.current().nextInt(1, 11);
        int diceThree = ThreadLocalRandom.current().nextInt(1, 11);
        int sum = diceOne + diceTwo + diceThree;
        if (sum == figure.getHealthValue()) {
            System.out.println("Miss");
        } else if (sum == 3) {
            figure.setHealthValue(figure.getHealthValue() - (knight.getAttackValue() / 2));
        } else {
            figure.setHealthValue(figure.getHealthValue() - knight.getAttackValue());
        }
        if(figure.checkIfHpIsZero()){
            if(playerTurn%2==0){
                figuresDestroyedPlayerB[indexForPlayerBDestroyedFigures++] = figure;
            } else {
                figuresDestroyedPlayerA[indexForPlayerADestroyedFigures++] = figure;
            }
            this.figureCollection[row][col] = null;
            figureSubtraction();
        }
    }

    /**
     * Method which heals the dwarf
     * @param dwarf dwarf being healed
     */
    private void dwarfHeal(Dwarf dwarf) {
        if(isHealButtonClicked) {
            int diceRoll = ThreadLocalRandom.current().nextInt(1, 7);
            dwarf.setHealthValue(dwarf.getHealthValue()+diceRoll);
            int turnDiceRoll = ThreadLocalRandom.current().nextInt(1,11);
            if(turnDiceRoll%2==1){
                playerTurn--;
            }
            isHealButtonClicked = false;
        }
    }

    /**
     * Method which heals the elf
     * @param elf elf being healed
     */
    private void elfHeal(Elf elf) {
        if(isHealButtonClicked) {
            int diceRoll = ThreadLocalRandom.current().nextInt(1, 7);
            elf.setHealthValue(elf.getHealthValue()+diceRoll);
            int turnDiceRoll = ThreadLocalRandom.current().nextInt(1,11);
            if(turnDiceRoll%2==1){
                playerTurn--;
            }
            isHealButtonClicked = false;
        }
    }

    /**
     * Method which heals the knight
     * @param knight knight being healed
     */
    private void knightHeal(Knight knight) {
        if(isHealButtonClicked) {
            int diceRoll = ThreadLocalRandom.current().nextInt(1, 7);
            knight.setHealthValue(knight.getHealthValue()+diceRoll);
            int turnDiceRoll = ThreadLocalRandom.current().nextInt(1,11);
            if(turnDiceRoll%2==1){
                playerTurn--;
            }
            isHealButtonClicked = false;
        }
    }

    /**
     * Method which implements the attack of the knight
     * @param row row being attacked
     * @param col col being attacked
     * @param knight knight figure
     */
    private void knightAttack(int row,int col,Knight knight) {
        if(isAttackInitiated){
            if(knight.isAttackValid(row,col)){
                if(this.figureCollection[row][col] != null){
                    Figure figure = getBoardFigure(row,col);
                    if(figure.getColor().equals(Color.BLACK)){
                        blockingTileAttack(row, col);
                    } else if(playerTurn%2 == 0 && figure.getColor().equals(Color.GREEN)){
                        knightAttackingAnotherFigure(knight, figure,row,col);
                    } else if(playerTurn%2 == 1 && figure.getColor().equals(Color.WHITE)){
                        knightAttackingAnotherFigure(knight, figure,row,col);
                    } else Modal.render(this,"Warning","Invalid attack");
                } else Modal.render(this,"Warning!","You cannot attack empty space");
            } else Modal.render(this,"Warning!","Illegal attack");
            isAttackInitiated = false;
        }
    }

    /**
     * Method which implements the attack of the dwarf
     * @param row row being attacked
     * @param col col being attacked
     * @param dwarf dwarf figure
     */
    private void dwarfAttack(int row,int col,Dwarf dwarf) {
        if(isAttackInitiated){
            if(dwarf.isAttackValid(row,col)){
                if(this.figureCollection[row][col] != null){
                    Figure figure = getBoardFigure(row,col);
                    if(figure.getColor().equals(Color.BLACK)){
                        blockingTileAttack(row, col);
                    } else if(playerTurn%2 == 0 && figure.getColor().equals(Color.GREEN)){
                        dwarfAttackingAnotherFigure(dwarf, figure,row,col);
                    } else if(playerTurn%2 == 1 && figure.getColor().equals(Color.WHITE)){
                        dwarfAttackingAnotherFigure(dwarf, figure,row,col);
                    } else Modal.render(this,"Warning","Invalid attack");
                } else Modal.render(this,"Warning!","You cannot attack empty space");
            } else Modal.render(this,"Warning!","Illegal attack");
            isAttackInitiated = false;
        }
    }

    /**
     * Calculates the damage done to the figure
     * @param dwarf attacking dwarf
     * @param figure defending figure
     * @param row row of attacked figure
     * @param col col of attacked figure
     */
    private void dwarfAttackingAnotherFigure(Dwarf dwarf, Figure figure,int row,int col) {
        int diceOne = ThreadLocalRandom.current().nextInt(1, 11);
        int diceTwo = ThreadLocalRandom.current().nextInt(1, 11);
        int diceThree = ThreadLocalRandom.current().nextInt(1, 11);
        int sum = diceOne + diceTwo + diceThree;
        if (sum == figure.getHealthValue()) {
            System.out.println("Miss");
        } else if (sum == 3) {
            figure.setHealthValue(figure.getHealthValue() - (dwarf.getAttackValue() / 2));
        } else {
            figure.setHealthValue(figure.getHealthValue() - dwarf.getAttackValue());
        }
        if(figure.checkIfHpIsZero()){
            if(playerTurn%2==0){
                figuresDestroyedPlayerB[indexForPlayerBDestroyedFigures++] = figure;
            } else {
                figuresDestroyedPlayerA[indexForPlayerADestroyedFigures++] = figure;
            }
            this.figureCollection[row][col] = null;
            figureSubtraction();
        }
    }

    /**
     * Method which calculates the remaining figures of each player based on turn.
     */
    private void figureSubtraction(){
        if(playerTurn%2==0){
            figuresOfPlayerB--;
        } else {
            figuresOfPlayerA--;
        }
    }

    /**
     * Method where the dwarf movement is realized
     * @param row row of dwarf being moved to
     * @param col col of dwarf being moved to
     * @param dwarf dwarf figure
     */
    private void dwarfMovement(int row, int col,Dwarf dwarf) {
        if (isMovementButtonClicked) {
            if (dwarf.isMoveValid(row, col)) {
                if (this.figureCollection[row][col] == null) {
                    int initialRow = dwarf.getRow();
                    int initialCol = dwarf.getCol();

                    dwarf.move(row, col);
                    this.figureCollection[dwarf.getRow()][dwarf.getCol()] = this.selectedFigure;
                    this.figureCollection[initialRow][initialCol] = null;
                    this.selectedFigure = null;
                    this.repaint();
                    this.isMovementButtonClicked = false;
                    this.isButtonClicked = false;
                }
            }
        }
    }

    /**
     * Method where the elf movement is realized
     * @param row row of elf being moved to
     * @param col col of elf being moved to
     * @param elf elf figure
     */
    private void elfMovement(int row, int col,Elf elf) {
        if (isMovementButtonClicked) {
            if (elf.isMoveValid(row, col)) {
                if (this.figureCollection[row][col] == null) {
                    int initialRow = elf.getRow();
                    int initialCol = elf.getCol();

                    elf.move(row, col);
                    this.figureCollection[elf.getRow()][elf.getCol()] = this.selectedFigure;
                    this.figureCollection[initialRow][initialCol] = null;
                    this.selectedFigure = null;
                    this.repaint();
                    this.isMovementButtonClicked = false;
                    this.isButtonClicked = false;
                }
            }
        }
    }

    /**
     * Method which implements the attack of the elf
     * @param row row being attacked
     * @param col col being attacked
     * @param elf elf figure
     */
    private void elfAttack(int row,int col,Elf elf) {
        if(isAttackInitiated){
            if(elf.isAttackValid(row,col)){
                if(this.figureCollection[row][col] != null){
                    Figure figure = getBoardFigure(row,col);
                    if(figure.getColor().equals(Color.BLACK)){
                        blockingTileAttack(row, col);
                    } else if(playerTurn%2 == 0 && figure.getColor().equals(Color.GREEN)){
                        elfAttackingAnotherFigure(elf, figure,row,col);
                    } else if(playerTurn%2 == 1 && figure.getColor().equals(Color.WHITE)){
                        elfAttackingAnotherFigure(elf, figure,row,col);
                    } else Modal.render(this,"Warning","Invalid attack");
                } else Modal.render(this,"Warning!","You cannot attack empty space");
            } else Modal.render(this,"Warning!","Illegal attack");
            isAttackInitiated = false;
        }
    }

    /**
     * Calculates the damage done to the figure
     * @param elf attacking elf
     * @param figure defending figure
     * @param row row of attacked figure
     * @param col col of attacked figure
     */
    private void elfAttackingAnotherFigure(Elf elf, Figure figure,int row,int col) {
        int diceOne = ThreadLocalRandom.current().nextInt(1, 11);
        int diceTwo = ThreadLocalRandom.current().nextInt(1, 11);
        int diceThree = ThreadLocalRandom.current().nextInt(1, 11);
        int sum = diceOne + diceTwo + diceThree;
        if (sum == figure.getHealthValue()) {
            System.out.println("Miss");
        } else if (sum == 3) {
            figure.setHealthValue(figure.getHealthValue() - (elf.getAttackValue() / 2));
        } else {
            figure.setHealthValue(figure.getHealthValue() - elf.getAttackValue());
        }
        if(figure.checkIfHpIsZero()){
            if(playerTurn%2==0){
                figuresDestroyedPlayerB[indexForPlayerBDestroyedFigures++] = figure;
            } else {
                figuresDestroyedPlayerA[indexForPlayerADestroyedFigures++] = figure;
            }
            this.figureCollection[row][col] = null;
            figureSubtraction();
        }
    }

    /**
     * Checks if one of the players has ran out of figures and displays scores.
     */
    private void endGameChecker(){
        if (figuresOfPlayerA == 0){
            System.out.println(pointsPlayerA);
            System.out.println(playerTurn);
            figuresToString(figuresDestroyedPlayerB);
            figuresToString(figuresDestroyedPlayerA);
            System.exit(1);
        }
        if(figuresOfPlayerB == 0){
            System.out.println(pointsPlayerB);
            System.out.println(playerTurn);
            figuresToString(figuresDestroyedPlayerA);
            figuresToString(figuresDestroyedPlayerB);
            System.exit(1);
        }
    }

    /**
     * Renders strings of figures in the array
     * @param arr array which you want to render the strings of figures
     */
    private void figuresToString(Figure[] arr){
        for(Figure i : arr){
            System.out.println(i.getTitle());
        }
    }
}