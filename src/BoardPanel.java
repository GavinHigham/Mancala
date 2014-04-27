
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dakota
 */
public class BoardPanel extends JPanel {

    public BoardPanel() {
        JPanel smallPits = new JPanel();
        GridLayout grid = new GridLayout(2, 6);
        grid.setHgap(10);
        grid.setVgap(10);
        smallPits.setLayout(grid);
        for (int i = 0; i < 12; i++) {
            pitIcon pitIc = new pitIcon(90, 90);
            JLabel pitLab = new JLabel();
            pitLab.setIcon(pitIc);
            pitLab.setPreferredSize(new Dimension(100, 100));
            smallPits.add(pitLab);
        }

        this.setLayout(new BorderLayout());

        pitIcon pitLarge = new pitIcon(75, 200);
        pitIcon pitLargeR = new pitIcon(75, 200);
        
        JLabel pitLabLargeR = new JLabel();
        JLabel pitLabLarge = new JLabel();
        pitLabLarge.setIcon(pitLarge);
        pitLabLargeR.setIcon(pitLargeR);
        
        this.add(pitLabLarge, BorderLayout.EAST);
        this.add(pitLabLargeR, BorderLayout.WEST);
        this.add(smallPits, BorderLayout.CENTER);
        this.validate();
        this.repaint();
        


    }
}
