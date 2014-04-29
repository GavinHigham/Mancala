<<<<<<< HEAD

import java.awt.BasicStroke;
=======
>>>>>>> FETCH_HEAD
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;

/**
 * This class creates a pit icon
 * 
 * @author P.U.G.S
 */
public class PitIcon implements Icon {
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
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3));     
        
        Ellipse2D.Double p = new Ellipse2D.Double(2, 2, width - 4, height - 4);
        
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
