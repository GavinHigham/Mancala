
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This class gives us version of the board game,
 * in which the numbers are displayed in the pits,
 * representing the number of stones.
 *
 * @author P.U.G.S.
 */
public class NumberGameView extends GameView {

    private JPanel playingPane;
    private ApplicationState model;
    JLabel playerLabel;
    private String player1TurnString = "Player one's turn";
    private String player2TurnString = "Player two's turn";

    public NumberGameView(ApplicationState model) {
        this.model = model;
        JButton undoButton = new JButton("Undo");
        undoButton.setBounds(800, 10, 75, 50); //Size and position of button
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add undo functionality
            }
        });
        this.add(undoButton);
       
        playerLabel = new JLabel(getPlayerTurnString());
        playerLabel.setBounds(100, 5, 200, 100);
        this.add(playerLabel);
        
        playingPane = new JPanel();
        playingPane.setLayout(new BorderLayout());
        playingPane.setBounds(50, 70, 800, 300);
        playingPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        this.add(playingPane);
        redraw();
    }

    public void paintComponent() {
        // TODO Auto-generated method stub
    }
    
    private String getPlayerTurnString() {
        if (model.getPlayer1Turn()) {
            return player1TurnString;
        } else {
            return player2TurnString;
        }
    }

    @Override
    public void redraw() {
    	playingPane.removeAll(); //We may need to change this to remove EVERYTHING.
        
        //Updating playerLabel
        playerLabel.setText(getPlayerTurnString());
        JPanel smallPits = new JPanel();

        //Could only get grid layout to work. Gridbag layout would be better.
        smallPits.setLayout(new GridLayout(2, 6));
        //Get current pits state.
        int[] pits = model.getPits();
        
        //creates a pit and places stones in each of them
        for (int i = 0; i < 12; i++) {
            JLabel pitLab = new JLabel();
            pitLab.setIcon(new PitIcon(90, 90, pits[i]));
            pitLab.setPreferredSize(new Dimension(100, 100));
            smallPits.add(pitLab);
        }

        //Creates the large pits (Mancalas) and places their stones in them.
        //Wrapping the Icons in JLabels.
        JLabel player1Pit = new JLabel();
        JLabel player2Pit = new JLabel();
        player2Pit.setIcon(new PitIcon(75, 200, model.getMancala1()));
        player1Pit.setIcon(new PitIcon(75, 200, model.getMancala2()));

        playingPane.add(player2Pit, BorderLayout.EAST);
        playingPane.add(player1Pit, BorderLayout.WEST);
        playingPane.add(smallPits, BorderLayout.CENTER);
        playingPane.validate();
        playingPane.repaint();
    }
}
