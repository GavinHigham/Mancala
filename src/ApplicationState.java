import java.util.ArrayList;
/**
 * This class keeps track of the state of the game and the board.
 * It keeps track of which views are being displayed. 
 * 
 * @author
 */
public class ApplicationState
{
    private ArrayList<View> views;
    private Board board;
    private int state;
    private Board currentBoard;
    private View displyedView;
   // private View[] theviews;
    
    public ApplicationState()
    {
        views = new ArrayList<>();
        board = new Board();
        
    } 
    /**
     * 
     */
    public void stateChanged()
    {
        //redraw everything
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
}
