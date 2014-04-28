import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class keeps track of the state of the game and the board. It keeps track
 * of which views are being displayed.
 *
 * @author
 * @P.U.G.S.
 */
public class ApplicationState {

    private ArrayList<ChangeListener> changeListeners; //Just views in this case.
    private Board board;
    private JFrame mainFrame;
    //private GameView displayedView;

/**
 * Constructor to create a state of the game.
 */
    public ApplicationState() {
        changeListeners = new ArrayList<>();
        board = new Board();
        board.setNewGame();
        mainFrame = new JFrame();
        mainFrame.setMinimumSize(new Dimension(600, 200));
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

   /*
   * Gets the current state of the Mancala game.
   @param state The state of the game.
   */
    public void setState(int state) {
        mainFrame.getContentPane().removeAll();
        if (state > 0) {
            mainFrame.setSize(900, 500);
        }
        mainFrame.validate();
        mainFrame.repaint();
        JPanel style = (JPanel) changeListeners.get(state);
        style.repaint();
        mainFrame.add(style, BorderLayout.CENTER);
        mainFrame.validate();
        mainFrame.repaint();

    }

    /**
     * Updates the state of the application.
     */
    public void updateState() {
        //
    }

    /**
     * Updates the game mode.
     */
    public void updateGameMode() {
    }

    /*
     * Gets the board int array representation from the Board object.
     * @return the board int array representation.
     */
    public int[] getBoardState() {
        return board.getBoardState();
    }
    
	/*
	 * (Wrapper for Board method)
	 * Returns a copy of the current board pits, not including Mancalas.
	 * Like getBoardStata, but it looks like this:
	 * [13][12][11][10][ 9][ 8]
	 * [ 1][ 2][ 3][ 4][ 5][ 6]
	 * For when these need to be accessed in left-to-right, top-to-bottom order.
	 * @return a copy of the current board pits.
	 */
	public int[] getPits() {
		return board.getPits();
	}
	
	/*
	 * (Wrapper for Board method)
	 * Gives the number of stones in the Mancala of player 1.
	 * @return the number of stones in the Mancala of player 1.
	 */
	public int getMancala1() {
		return board.getMancala1(); //board[7] is, as shown in the diagram, player 1's Mancala.
	}
	
	/*
	 * (Wrapper for Board method)
	 * Gives the number of stones in the Mancala of player 2.
	 * @return the number of stones in the Mancala of player 2.
	 */
	public int getMancala2() {
		return board.getMancala2();
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
        for (ChangeListener c : changeListeners) {
            c.stateChanged(new ChangeEvent(this));
        }
    }
}
