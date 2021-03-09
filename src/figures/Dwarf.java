package figures;

import java.awt.*;

public class Dwarf extends Figure{
    private final String title = "D";
    /**
     * Constructor for Dwarf
     *
     * @param row row position
     * @param col col position
     */
    public Dwarf(double row, double col,Color color) {
        super(row, col,color);
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
        g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        g.drawString(title,tileX+40,tileY+60);
    }
    public String getTitle() {
        return title;
    }
}
