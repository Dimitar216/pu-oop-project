package figures;

import java.awt.*;

public class Knight extends Figure{
    /**
     * Constructor for Knight
     *
     * @param row row position
     * @param col col position
     */
    public Knight(double row, double col) {
        super(row, col);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D lineDrawer = (Graphics2D) g;

        int widthOfTile = 100;
        int tileX = (int) (this.col * widthOfTile);
        int heightOfTile = 100;
        int tileY = (int) (this.row * heightOfTile);

        g.setColor(Color.WHITE);
        g.fillRect(tileX,tileY, widthOfTile, heightOfTile);
        outlineRenderer(g, lineDrawer, tileX, tileY);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        g.drawString("K",tileX+40,tileY+60);
    }
}
