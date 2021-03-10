package tiles;

import figures.Figure;

import java.awt.*;

public class MenuTile extends Figure {
    private final String title;
    /**
     * Constructor for Dwarf
     *
     * @param row row position
     * @param col col position
     */
    public MenuTile(double row, double col, Color color,String title) {
        super(row, col,color);
        this.title = title;
    }

    public void render(Graphics g) {
        Graphics2D lineDrawer = (Graphics2D) g;

        int widthOfTile = 100;
        int tileX = (int) (this.col * widthOfTile);
        int heightOfTile = 100;
        int tileY = (int) (this.row * heightOfTile);

        g.setColor(color);
        g.fillRect(tileX,tileY, widthOfTile, heightOfTile);
        outlineRenderer(g, lineDrawer, tileX, tileY);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString(title,tileX+10,tileY+60);
    }

}
