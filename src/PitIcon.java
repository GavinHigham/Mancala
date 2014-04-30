
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
     * @param width The width of the pit
     * @param height the height of the pit
     * @param stones the number of stones in this pit
     */
    public PitIcon(int width, int height, int stones) {
        this.width = width;
        this.height = height;
        this.stones = stones;
    }

    /**
     * Paints the icon with the given stones
     * @param c Component that can be passed to this method
     * @param g The graphics content
     * @param x The x-axis positioning 
     * @param y The y-axis positioning
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3));     
        
        Ellipse2D.Double p = new Ellipse2D.Double(2, 2, width - 4, height - 4);
        
        g2.setColor(Color.BLACK);
        g2.draw(p);
        
        if (stones > 0) {
        	int padding = 5;
	        int numCols = (int)Math.round(Math.sqrt(stones));
	        int diameter = width/numCols - padding;
                //We could rework the diameter or make the padding larger to make it so the stones fit into the pit
	        
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
