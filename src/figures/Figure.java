package figures;

import java.awt.*;
import java.awt.geom.Line2D;

public class Figure {
    protected int row;
    protected int col;
    protected Color color;
    private final String title="";
    private int attackValue = 0;
    private int armorValue = 0;
    private int healthValue = 0;

    /**
     * Constructor for Figure abstract class
     * @param row row position
     * @param col col position
     */
    public Figure(int row, int col,Color color){
        this.row          = row;
        this.col          = col;
        this.color        = color;
    }


    protected static void outlineRenderer(Graphics g, Graphics2D lineDrawer, int tileX, int tileY) {
        g.setColor(Color.ORANGE);
        Line2D line0 = new Line2D.Float(tileX, tileY, tileX, tileY +99);
        Line2D line1 = new Line2D.Float(tileX +99, tileY, tileX +99, tileY +99);
        Line2D line2 = new Line2D.Float(tileX, tileY, tileX +99, tileY);
        Line2D line3 = new Line2D.Float(tileX, tileY +99, tileX +99, tileY +99);
        //left
        lineDrawer.draw(line0);
        //right
        lineDrawer.draw(line1);
        //top
        lineDrawer.draw(line2);
        //bottom
        lineDrawer.draw(line3);
    }

    public String getTitle() {
        return title;
    }

    /**
     * Method which changes the row and col of the figure.
     * @param row new row of the figure.
     * @param col new col of the figure.
     */
    public void move(int row,int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return (int) row;
    }

    public int getCol() {
        return (int) col;
    }

    public Color getColor() {
        return color;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
    }

    public int getHealthValue() {
        return healthValue;
    }

    public void setHealthValue(int healthValue) {
        this.healthValue = healthValue;
    }

    public boolean checkIfHpIsZero(){
        return healthValue == 0;
    }
}
