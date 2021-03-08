package figures;

import java.awt.*;
import java.awt.geom.Line2D;

public abstract class Figure {
    protected final double row;
    protected final double col;

    /**
     * Constructor for Figure abstract class
     * @param row row position
     * @param col col position
     */
    public Figure(double row, double col){
        this.row          = row;
        this.col          = col;
    }

    /**
     * renders figure on the board based on row/col and width/height.
     * @param g graphics component
     */
    public abstract void render(Graphics g);

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
}
