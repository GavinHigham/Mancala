
import java.awt.BorderLayout;
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

    private PitIcon[] pits;

    /**
     * initializes the board view in this panel
     *
     * @param stones the number of stones per pit
     */
    public BoardPanel(int[] stones, int player1Mancala, int player2Mancala) {
        int largePitIndexRight = 7;
        int largePitIndexLeft = 0;
        int pitIndex = 12;

        JPanel smallPits = new JPanel();
        GridLayout grid = new GridLayout(2, 6);
        //Could only get grid layout to work. Gridbag layout would be better.

        smallPits.setLayout(grid);

        //creates a pit and places stones in each of them
        for (int i = 0; i < 12; i++) {
            JLabel pitLab = new JLabel();
            pitLab.setIcon(new PitIcon(90, 90, stones[i]));
            pitLab.setPreferredSize(new Dimension(100, 100));
            smallPits.add(pitLab);
        }

        this.setLayout(new BorderLayout());


        //Creates the large pit and places their stones in them
        PitIcon pitLarge = new PitIcon(75, 200, player1Mancala);
        PitIcon pitLargeR = new PitIcon(75, 200, player2Mancala);

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
