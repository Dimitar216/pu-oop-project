package tiles;

import java.awt.*;

public class PlayerTile {

    private final double row;
    private final double col;
    private final Color color;

    /**
     * Constructor for PlayerTile
     * @param row row position
     * @param col col position
     * @param color color of the tile
     */
    public PlayerTile(double row, double col,Color color){
        this.row          = row;
        this.col          = col;
        this.color        = color;
    }

    /**
     * renders tile on the board based on row/col and width/height.
     * @param g graphics component
     */
    public void render(Graphics g){
        int widthOfTile = 100;
        int tileX = (int) (this.col * widthOfTile);
        int heightOfTile = 100;
        int tileY = (int) (this.row * heightOfTile);

        g.setColor(this.color);
        g.fillRect(tileX,tileY, widthOfTile, heightOfTile);
    }
}
