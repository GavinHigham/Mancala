import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * This class keeps track of the state of the game and the board.
 * It keeps track of which views are being displayed. 
 * 
 * @author @P.U.G.S.
 */
public class ApplicationState
{
    private ArrayList<ChangeListener> changeListeners; //Just views in this case.
    private Board board;
    private int state;
    private Board currentBoard;
    private View displyedView;
   // private View[] theviews;
    
    public ApplicationState()
    {
        changeListeners = new ArrayList<>();
        board = new Board();
    } 

    /**
     * Updates the state of the application.
     */
    public void updateState()
    {
        //
    }
    
    /**
     * Updates 
     */
    public void updateGameMode()
    {
        
    }
    
    /*
     * Gets the board int array representation from the Board object.
     * @return the board int array representation.
     */
    public int[] getBoardState() {
    	return board.getBoardState();
    }
    
    /*
     * Gets the board active player boolean.
     * @return true if it is Player 1's turn.
     */
    public boolean getPlayer1Turn() {
    	return board.getPlayer1Turn();
    }
    
	/*
	 * Registers a new ChangeListener.
	 * @param c The new ChangeListener to register.
	 */
	public void addChangeListener(ChangeListener c) {
		changeListeners.add(c);
	}
	
	/*
	 * Updates all registered views. Typically called after a state change of some sort.
	 */
	public void updateChangeListeners() {
		for (ChangeListener c : changeListeners)
			c.stateChanged(new ChangeEvent(this));
	}
}
