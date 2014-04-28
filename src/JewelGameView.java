
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * This provides a second type of game board,
 * in which the stones in the pits are visually displayed as circles.
 *
 * @author P.U.G.S.
 */
public class JewelGameView extends GameView {

    private JPanel playingPane;
    private BoardPanel boardView;
    private ApplicationState model;

    JewelGameView(ApplicationState model) {
        this.model = model;
        setLayout(null); //Layouts failed me
        JButton undoButton = new JButton("Undo");
        undoButton.setBounds(800, 10, 75, 50); //Size and position of button

        String playerTurn;
        if (model.getPlayer1Turn()) {
            playerTurn = "Player one's turn";
        } else {
            playerTurn = "Player two's turn";
        }

        JLabel playerLabel = new JLabel(playerTurn);
        playerLabel.setBounds(100, 5, 100, 100);
        this.add(playerLabel);

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add undo functionality
            }
        });


        this.add(undoButton);
        this.model = model;
        boardView = new BoardPanel(model.getPits(), model.getMancala1(), model.getMancala2());
        playingPane = boardView; //Pane where we can draw the board
        playingPane.setBounds(50, 70, 800, 300);
        playingPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        this.add(playingPane);
    }

    @Override
    public void redraw() {
        // TODO Auto-generated method stub
    }
}
