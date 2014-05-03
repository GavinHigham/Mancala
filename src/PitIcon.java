
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;

/**
 * This class creates a pit icon
 * @author Dakota
 */
public class PitIcon implements Icon {
	
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
        
        g2.setColor(Color.BLACK);
        g2.draw(p);
        
        int labelX = width / 2;
        int labelY = height / 2;
        if (stones > 0) {
        	int padding = 5;
	        int numCols = (int)Math.ceil(Math.sqrt(stones));
	        int diameter = width/Math.max(numCols, stones/numCols) - padding;
	        
	        for (int i = 0; i < stones; i++) {
	        	Ellipse2D.Double tempEllipse = new Ellipse2D.Double(
	        			(i%numCols)*(width/numCols)+padding,
	        			(i/numCols)*(height/numCols)+padding,
	        			diameter,
	        			diameter);
	        	g2.setColor(new Color(100, 100, 255));
	        	g2.fill(tempEllipse);
	        	g2.setColor(Color.BLACK);
	        	g2.draw(tempEllipse);
	        }
        }
        String strStones = "" + stones;
        g2.drawString(strStones, labelX, labelY);
        //TODO Icon class only works for number view
    }
    
    public void setStones(int stones) {
    	this.stones = stones;
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
