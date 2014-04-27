
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This is an interface which will be implemented by the other view classes.
 *
 * @author P.U.G.S.
 */
abstract class GameView extends JPanel implements ChangeListener {

    int[] board; //For display purposes. Local copy of boards state, may be stale if not updated.
    boolean player1Turn; //Same ^
    ApplicationState model;

    public GameView() {

        setLayout(null); //Tried to use other layouts but could not get it to work
        JPanel playingPane = new JPanel(); //Pane where we can draw the board
        
        JButton undoButton = new JButton("Undo");
        undoButton.setBounds(450, 10, 75, 50); //Size and position of button. Won't resize if expanded

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add undo functionality
            }
        });

        this.add(undoButton);

    }

    public abstract void redraw();

    public void stateChanged(ChangeEvent e) {
        ApplicationState model = (ApplicationState) e.getSource();
        board = model.getBoardState();
        player1Turn = model.getPlayer1Turn();
        redraw();
    }
}
