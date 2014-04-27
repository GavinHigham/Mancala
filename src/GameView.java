
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
    private JPanel playingPane;

    public GameView() {

        setLayout(null); //Layouts failed me
        playingPane = new BoardPanel(); //Pane where we can draw the board
        playingPane.setBounds(50, 70, 800, 300);
        playingPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        JButton undoButton = new JButton("Undo");
        undoButton.setBounds(800, 10, 75, 50); //Size and position of button

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add undo functionality
            }
        });
        
        
        this.add(undoButton);
        this.add(playingPane);
    }

    public abstract void redraw();

    public void stateChanged(ChangeEvent e) {
        ApplicationState model = (ApplicationState) e.getSource();
        board = model.getBoardState();
        player1Turn = model.getPlayer1Turn();
        redraw();
    }
}
