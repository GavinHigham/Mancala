
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 *
 * @author Dakota
 */
public class pitIcon implements Icon {
    private Ellipse2D pit;
    private int width;
    private int height;
    private int posX;
    private int posY;
    
    public pitIcon(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        
        Ellipse2D.Double p = new Ellipse2D.Double(0, 10, width, height);
        
        g2.setColor(Color.BLACK);
        g2.draw(p);    
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
    
}
