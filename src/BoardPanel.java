import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Constructs a panel that displays a board
 *
 * @author P.U.G.S
 */
public class BoardPanel extends JPanel {
    private PitIcon[] pits;

    /**
     * initializes the board view in this panel
     *
     * @param stones the number of stones per pit
     */
    public BoardPanel(int[] stones, int player1Mancala, int player2Mancala) {

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

        //Creates the large pits (Mancalas) and places their stones in them.
        //Wrapping the Icons in JLabels.
        JLabel player1Pit = new JLabel();
        JLabel player2Pit = new JLabel();
        player2Pit.setIcon(new PitIcon(75, 200, player1Mancala));
        player1Pit.setIcon(new PitIcon(75, 200, player2Mancala));

        // inserts the large Mancala pits and small pits onto the game board
        this.add(player2Pit, BorderLayout.EAST);
        this.add(player1Pit, BorderLayout.WEST);
        this.add(smallPits, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }
}
