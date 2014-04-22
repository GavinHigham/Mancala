import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This is an interface which will be implemented by the other view classes. 
 * 
 * @author P.U.G.S.
 */
abstract class View extends JPanel implements ChangeListener
{
	int[] board; //For display purposes. Local copy of boards state, may be stale if not updated.
	boolean player1Turn; //Same ^
	
    public abstract void paintComponent();
    public abstract void redraw();
    
    public void stateChanged(ChangeEvent e) {
    	ApplicationState model = (ApplicationState)e.getSource();
    	board = model.getBoardState();
    	player1Turn = model.getPlayer1Turn();
    	redraw();
    }
}
