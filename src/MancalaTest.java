/*
 * This class contains the main method to get the Mancala game started. 
 *
 * @author Dakota
 */
public class MancalaTest
{
    public static void main(String[] args)
    {
        ApplicationState start = new ApplicationState();
        JewelGameView newView = new JewelGameView();
        start.addChangeListener(newView);
    }
}
