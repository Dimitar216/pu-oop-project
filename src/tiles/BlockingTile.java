package tiles;

import figures.Figure;

import java.awt.*;
import java.awt.geom.Line2D;

public class BlockingTile extends Figure {
    private final String title = "B";

    /**
     * Constructor for BlockingTile
     * @param row row position
     * @param col col position
     * @param color color of the tile
     */
    public BlockingTile(int row, int col,Color color){
        super(row,col,color);
    }

    /**
     * renders tile on the board based on row/col and width/height.
     * @param g
     */
    public void render(Graphics g){
        Graphics2D lineDrawer = (Graphics2D) g;

        int widthOfTile = 100;
        int tileX = this.col * widthOfTile;
        int heightOfTile = 100;
        int tileY = this.row * heightOfTile;

        g.setColor(this.color);
        g.fillRect(tileX,tileY, widthOfTile, heightOfTile);
        outlineRenderer(g, lineDrawer, tileX, tileY);
    }

    /**
     * renders outlines for the tile
     * @param g graphics component
     * @param lineDrawer graphics 2d drawer
     * @param tileX x coordinate for line
     * @param tileY y coordinate for line
     */
    public static void outlineRenderer(Graphics g, Graphics2D lineDrawer, int tileX, int tileY) {
        g.setColor(Color.BLACK);
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
