
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

/**
 * This class creates a pit icon
 * 
 * @author Dakota
 */
public class PitIcon implements Icon {
	
    private int width;
    private int height;
    private int stones;
    private Color pitColor;
    private Color stoneColor;
    private boolean rectangular; //Are the pits rectangular?
    
    /**
     * Initializes variables used in pitIcon
     * 
     * @param width The width of the icon.
     * @param height The height of the icon.
     * @param stones The stones in the pits.
     */
    public PitIcon(int width, int height, int stones) {
        this.width = width;
        this.height = height;
        this.stones = stones;
        stoneColor = new Color(100, 100, 255);
        pitColor = new Color(255, 255, 255);
    }

    /**
     * Paints the icon with the given stones
     * 
     * @param c Component that could be used instead of current icon. Unused
     * @param g The graphics context
     * @param x The x positioning. Unused
     * @param y The y positioning. Unused
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3));     
        
        Shape p;
        if (rectangular) {
        	p = new Rectangle2D.Double(2, 2, width - 4, height - 4);
        }
        else {
        	p = new Ellipse2D.Double(2, 2, width - 4, height - 4);
        }
        
        g2.setColor(pitColor);
        g2.fill(p);
        g2.setColor(Color.BLACK);
        g2.draw(p);
        
        int labelX = width / 2;
        int labelY = height / 2;
        if (stones > 0) {
        	int padding = 5;
	        int numCols = (int)Math.ceil(Math.sqrt(stones));
	        int diameter = stones>1?width/numCols - padding:width/2-padding;
	        
	        for (int i = 0; i < stones; i++) {
	        	Ellipse2D.Double tempEllipse = new Ellipse2D.Double(
	        			(i%numCols)*(width/numCols)+padding,
	        			(i/numCols)*(height/numCols)+padding,
	        			diameter,
	        			diameter);
	        	g2.setColor(stoneColor);
	        	g2.fill(tempEllipse);
	        	g2.setColor(Color.BLACK);
	        	g2.draw(tempEllipse);
	        }
        }
        String strStones = "" + stones;
        g2.drawString(strStones, labelX, labelY);
    }

    /**
     * Sets the number of stones to be displayed in the pit
     * 
     * @param stones the number of stones
     */
    public void setStones(int stones) {
    	this.stones = stones;
    }
    
    /**
     * Sets the color of both the pit and stones within that pit
     * 
     * @param pitColor The color of the pit
     * @param stoneColor The color of the stones in this pit
     */
    public void setColors(Color pitColor, Color stoneColor) {
    	this.pitColor = pitColor;
    	this.stoneColor = stoneColor;
    }
    
    /**
     * Sets the pit to be rectangular
     * 
     * @param rectangular True if the pit is displayed as a rectangle
     */
    public void setRectangular(boolean rectangular)
    {
    	this.rectangular = rectangular;
    }

    /**
     * Gets the icon width
     * 
     * @return The width of the icon.
     */
    @Override
    public int getIconWidth() {
        return width;
    }

    /**
     * Gets the icon height
     * 
     * @return The height of the icon.
     */
    @Override
    public int getIconHeight() {
        return height;
    }
    
}
