
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Constructs a panel that displays a board
 *
 * @author Dakota
 */
public class BoardPanel extends JPanel {

    private pitIcon[] pits;

    /**
     * initializes the board view in this panel
     *
     * @param stones the number of stones per pit
     */
    public BoardPanel(int[] stones) {
        pits = new pitIcon[13];
        int largePitIndexRight = 7;
        int largePitIndexLeft = 0;
        int pitIndex = 12;

        JPanel smallPits = new JPanel();
        GridLayout grid = new GridLayout(2, 6);
        grid.setHgap(10);
        grid.setVgap(10);
        //Could only get grid layout to work. Gridbag layout would be better.

        smallPits.setLayout(grid);

        //creates a pit and places stones in each of them
        for (int i = 0; i < 12; i++) {
            pitIcon pitIc = new pitIcon(90, 90, stones[pitIndex]);
            pits[pitIndex] = pitIc;

            JLabel pitLab = new JLabel();
            pitLab.setIcon(pitIc);
            pitLab.setPreferredSize(new Dimension(100, 100));
            smallPits.add(pitLab);

            pitIndex--;
            if (pitIndex == largePitIndexRight) {
                pitIndex--;
            }
        }

        this.setLayout(new BorderLayout());


        //Creates the large pit and places their stones in them
        pitIcon pitLarge = new pitIcon(75, 200, stones[largePitIndexRight]);
        pitIcon pitLargeR = new pitIcon(75, 200, stones[largePitIndexLeft]);

        pits[largePitIndexRight] = pitLarge;
        pits[largePitIndexLeft] = pitLargeR;

        //Need to convert Icon to JLabel because Java is dumb
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

    /**
     * changes the amount of stones in the pit
     *
     * @param stones
     */
    public void changeStones(int[] stones) {
        //TODO add method that repaints a given board
    }
}
