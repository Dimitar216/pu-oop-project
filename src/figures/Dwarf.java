package figures;

import java.awt.*;

public class Dwarf extends Figure{
    private final String title = "D";
    private int attackValue = 6;
    private int armorValue = 2;
    private int healthValue = 12;
    /**
     * Constructor for Dwarf
     *
     * @param row row position
     * @param col col position
     */
    public Dwarf(int row, int col,Color color) {
        super(row, col,color);
    }

    public void render(Graphics g) {
        Graphics2D lineDrawer = (Graphics2D) g;

        int widthOfTile = 100;
        int tileX = this.col * widthOfTile;
        int heightOfTile = 100;
        int tileY = this.row * heightOfTile;

        g.setColor(color);
        g.fillRect(tileX,tileY, widthOfTile, heightOfTile);
        outlineRenderer(g, lineDrawer, tileX, tileY);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        g.drawString(title,tileX+40,tileY+60);
    }
    public String getTitle() {
        return title;
    }

    public boolean isMoveValid(int moveRow, int moveCol) {
        int rowCoefficient =  Math.abs(moveRow-this.row);
        int colCoefficient =  Math.abs(moveCol - this.col);

        return rowCoefficient == 0 && colCoefficient == 2 || rowCoefficient == 2 && colCoefficient == 0;
    }

    public boolean isAttackValid(int moveRow, int moveCol) {
        int rowCoefficient =  Math.abs(moveRow-this.row);
        int colCoefficient =  Math.abs(moveCol - this.col);

        return rowCoefficient == 0 && colCoefficient == 2 || rowCoefficient == 2 && colCoefficient == 0;
    }
}
