
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Stack;

/**
 * This class keeps track of the state of the game and the board.
 * It also keeps track of which views are being displayed.
 *
 * @author P.U.G.S
 */
public class ApplicationState {

    private ArrayList<ChangeListener> changeListeners; //Just views in this case.
    private JFrame mainFrame;
    private Stack<Board> moves;
    private int playerOneUndos;
    private int playerTwoUndos;
    private boolean p1PlayedLast;
    private boolean canUndo;

    /**
     * Constructor to create a state of the game.
     */
    public ApplicationState() {
        changeListeners = new ArrayList<>();
        newGame(3);

        mainFrame = new JFrame();
        mainFrame.setMinimumSize(new Dimension(600, 200));
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    /**
     * Gets the current state of the Mancala game.
     * @param state The state of the game.
     */
    public void setState(int state) {
        mainFrame.getContentPane().removeAll();
        if (state > 0) {
            mainFrame.setSize(900, 300);
        }
        mainFrame.validate();
        mainFrame.repaint();
        JPanel style = (JPanel) changeListeners.get(state);

        if (state != 0) {
            //Have to cast to call redraw stones in first screen
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
     * Initializes or sets up a new game.
     * 
     * @param stonesPerPit the number of stones to have in each pit.
     */
    public void newGame(int stonesPerPit) {
    	moves = new Stack<Board>();
        Board newGame = new Board(stonesPerPit);
        moves.push(newGame);
        playerOneUndos = 3;
        playerTwoUndos = 3;
        p1PlayedLast = true;
    }

    /**
     * Undoes the previous move if the player has enough undos and
     * the undo action did not immediately follow a previous one.
     *
     * @return true if the undo was successful.
     */
    public boolean undo() {
    	boolean undoSuccessful = false;
        if (moves.size() == 1 || !canUndo) {
            return false;
        }

        if (p1PlayedLast && playerOneUndos > 0) {
            playerOneUndos--;
            moves.pop();
            undoSuccessful = true;
            canUndo = false;
        }
        if (!p1PlayedLast && playerTwoUndos > 0) {
            playerTwoUndos--;
            moves.pop();
            undoSuccessful = true;
            canUndo = false;
        }
        
        updateChangeListeners();
        return undoSuccessful;
    }

    /**
     * Gets the amount of undos the specified player has.
     *
     * @param player the player the undos are associated with.
     * @return the number of undos left for that player.
     */
    public int getUndos(int player) {
        if (player == 1) {
            return playerOneUndos;
        } else {
            return playerTwoUndos;
        }
    }

    /**
     * (Wrapper for Board method)
     * Returns a copy of the current board pits, not including Mancalas.
     * Like getBoardStata, but it looks like this:
     * [13][12][11][10][ 9][ 8]
     * [ 1][ 2][ 3][ 4][ 5][ 6]
     * For when these need to be accessed in left-to-right, top-to-bottom order.
     * @return a copy of the current board pits.
     */
    public int[] getPits() {
        return moves.peek().getPits();
    }

    /**
     * (Wrapper for Board method)
     * Gives the number of stones in the Mancala of player 1.
     * @return the number of stones in the Mancala of player 1.
     */
    public int getMancala1() {
        return moves.peek().getMancala1(); //board[7] is, as shown in the diagram, player 1's Mancala.
    }

    /**
     * (Wrapper for Board method)
     * Gives the number of stones in the Mancala of player 2.
     * @return the number of stones in the Mancala of player 2.
     */
    public int getMancala2() {
        return moves.peek().getMancala2();
    }

    /**
     * Gets the board active player boolean.
     * @return true if it is Player 1's turn.
     */
    public boolean getPlayer1Turn() {
        return moves.peek().getPlayer1Turn();
    }

    /**
     * Plays a move in Row Major Order. Useful for access from mouse listeners.
     * Looks like this:
     * [ 0][ 1][ 2][ 3][ 4][ 5]
     * [ 6][ 7][ 8][ 9][10][11]
     * Basically, give the integer for which pit you want to try a move from.
     * @param pit the pit to attempt to play a move from.
     * @return Whether or not the move was valid.
     */
    public boolean playMoveRowMajorOrder(int pit) {
    	Board newMove = moves.peek().clone();
        boolean moveSuccessful = newMove.playMoveRowMajorOrder(pit);
        if (moveSuccessful) {
        	p1PlayedLast = moves.peek().player1Turn;
        	if (p1PlayedLast) playerTwoUndos = 3;
        	else playerOneUndos = 3;
        	canUndo = true;
            //Adds current board to stack for undo function
        	moves.push(newMove);
        }
        updateChangeListeners();
        return moveSuccessful;
    }

    /**
     * Registers a new ChangeListener.
     * 
     * @param c The new ChangeListener to register.
     */
    public void addChangeListener(ChangeListener c) {
        changeListeners.add(c);
    }
    
    /**
     * Tells if the game is over.
     * 
     * @return true if the game is over.
     */
    public boolean getGameOver() {
        return moves.peek().getGameOver();
    }

    /**
     * Updates all registered views.
     * Typically called after a state change of some sort.
     */
    public void updateChangeListeners() {
        for (ChangeListener c : changeListeners) {
            c.stateChanged(new ChangeEvent(this));
        }
    }
}
