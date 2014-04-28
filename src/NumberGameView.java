
import java.awt.Color;
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
    private BoardPanel boardView;
    private ApplicationState model;

    public NumberGameView(ApplicationState model) {
        setLayout(null); //Layouts failed me
        JButton undoButton = new JButton("Undo");
        undoButton.setBounds(800, 10, 75, 50); //Size and position of button

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add undo functionality
            }
        });

        String playerTurn;
        if (model.getPlayer1Turn()) {
            playerTurn = "Player one's turn";
        } else {
            playerTurn = "Player two's turn";
        }

        JLabel playerLabel = new JLabel(playerTurn);
        playerLabel.setBounds(100, 5, 200, 100);
        this.add(playerLabel);
        
        
        this.add(undoButton);
        this.model = model;
        boardView = new BoardPanel(model.getPits(), model.getMancala1(), model.getMancala2());
        playingPane = boardView; //Pane where we can draw the board
        playingPane.setBounds(50, 70, 800, 300);
        playingPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        this.add(playingPane);
    }

    public void paintComponent() {
        // TODO Auto-generated method stub
    }

    @Override
    public void redraw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
