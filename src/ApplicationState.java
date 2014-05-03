
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Stack;

/**
 * This class keeps track of the state of the game and the board. It keeps track
 * of which views are being displayed.
 *
 * @author P.U.G.S
 */
public class ApplicationState {

    private ArrayList<ChangeListener> changeListeners; //Just views in this case.
    private Board board;
    private JFrame mainFrame;
    private Stack<int[]> prevStates;
    private Stack<Boolean> playerTurns;
    private int undos;
    //private GameView displayedView;

    /**
     * Constructor to create a state of the game.
     */
    public ApplicationState() {
        changeListeners = new ArrayList<>();
        board = new Board();
        prevStates = new Stack();
        playerTurns = new Stack();

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

        //This is ugly as hell. Can someone make something better.
        if (state != 0) {
            //Have to cast to call redraw otherwise first screen does not display stones
            GameView tmp = (GameView) style;
            tmp.redraw();
            style = (JPanel) tmp;
        }
        style.validate();
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

    public void newGame(int stonesPerPit) {
        board.setNewGame(stonesPerPit);
    }

    /*
     * Gets the board int array representation from the Board object.
     * @return the board int array representation.
     */
    public int[] getBoardState() {
        return board.getBoardState();
    }
    
    public Board getCurrentBoard() {
        return board;
    }
    
    public boolean undo() {
        if (prevStates.size() == 0 || undos <= 0) {
            return false;
        }
        board.setBoardState(prevStates.pop(), playerTurns.pop());
        undos--;
        updateChangeListeners();
        return true;
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
     * Plays a move in Row Major Order. Useful for access from mouse listeners.
     * Looks like this:
     * [ 0][ 1][ 2][ 3][ 4][ 5]
     * [ 6][ 7][ 8][ 9][10][11]
     * Basically, give the integer for which pit you want to try a move from.
     * @param pit the pit to attempt to play a move from.
     * @return Whether or not the move was valid.
     */
    public boolean playMoveRowMajorOrder(int pit) {
        int[] currentState = board.getBoardState();
        boolean currentTurn = board.getPlayer1Turn();
        boolean moveSuccessful = board.playMoveRowMajorOrder(pit);
        if (moveSuccessful) {
            //Adds current board to stack for undo function
            prevStates.push(currentState);
            playerTurns.push(currentTurn);
            undos = 3;
        }
        updateChangeListeners();
        return moveSuccessful;
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
