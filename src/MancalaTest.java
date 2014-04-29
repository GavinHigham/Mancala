/*
 * This class contains the main method to get the Mancala game started. 
 *
 * @author P.U.G.S
 */
public class MancalaTest
{
    public static void main(String[] args)
    {
        ApplicationState model = new ApplicationState();
        MenuView menu = new MenuView(model);
        JewelGameView newView = new JewelGameView(model);
        NumberGameView num = new NumberGameView(model);
        model.addChangeListener(menu);
        model.addChangeListener(newView);
        model.addChangeListener(num);
        //Starts state at menu
        model.setState(0);
    }
}
