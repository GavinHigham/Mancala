import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;

/**
 * This class creates a pit icon
 * 
 * @author P.U.G.S
 */
public class pitIcon implements Icon {
    private Ellipse2D pit;
    private int width;
    private int height;
    private int stones;
    
    /**
     * Initializes variables used in pitIcon
     * @param width
     * @param height
     * @param stones 
     */
    public PitIcon(int width, int height, int stones) {
        this.width = width;
        this.height = height;
        this.stones = stones;
    }

    /**
     * Paints the icon with the given stones
     * @param c
     * @param g
     * @param x
     * @param y 
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        
        Ellipse2D.Double p = new Ellipse2D.Double(0, 10, width, height);
        
        String strStones = "" + stones;
        int labelX = width / 2;
        int labelY = height / 2;
        
        
        g2.setColor(Color.BLACK);
        g2.draw(p);
        g2.drawString(strStones, labelX, labelY);
        //TODO Icon class only works for number view
    }

    /**
     * Returns the icon width
     * @return 
     */
    @Override
    public int getIconWidth() {
        return width;
    }

    /**
     * returns the icon height
     * @return 
     */
    @Override
    public int getIconHeight() {
        return height;
    }
    
}
