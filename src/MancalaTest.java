/*
 * This class contains the main method to get the Mancala game started. 
 *
 * @author P.U.G.S
 */

public class MancalaTest {

    public static void main(String[] args) {
        ApplicationState model = new ApplicationState();
        MenuView menu = new MenuView(model);
        GameViewConfig blueConfig = new DefaultBlue(true, false);
        GameViewConfig RainbowConfig = new RainbowMode(true, false);
        GameView blue = new GameView(model, blueConfig);
        GameView rain = new GameView(model, RainbowConfig);
        model.addChangeListener(menu);
        model.addChangeListener(blue);
        model.addChangeListener(rain);
        model.setState(0);
    }
}
