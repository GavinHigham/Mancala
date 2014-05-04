
import java.awt.Color;
import java.util.Random;

/**
 * Creates pits where each pit is a random color and each pit's stones is a
 * random
 *
 * @author P.U.G.S
 */
public class RainbowMode extends GameViewConfig {

    /**
     * Initializes the variables used in RainbowMode
     *
     * @param showNumbers true if numbers are displayed in each pit depicting
     * the number of stones
     * @param rectangularPits true if the pits are rectangular instead of
     * ellipses
     */
    RainbowMode(boolean showNumbers, boolean rectangularPits) {
        super(showNumbers, rectangularPits); //Yes numbers, no rainbow.
    }

    /**
     * Gets the color of the stones
     *
     * @return the color of the stones in the pit
     */
    @Override
    public Color getStoneColor() {
        return getRandomColor();
    }

    /**
     * Gets the color of the pit
     * @return the color of the pit
     */
    @Override
    public Color getPitColor() {
        return getRandomColor().brighter();
    }

    /**
     * Creates a random color
     * @return a random color
     */
    public Color getRandomColor() {
        Random rand = new Random();
        int max = 255;
        int min = 0;

        long range = (long) max - (long) min + 1;

        long randRed = (long) (range * rand.nextDouble());
        long randBlue = (long) (range * rand.nextDouble());
        long randGreen = (long) (range * rand.nextDouble());

        int randRedInt = (int) randRed;
        int randBlueInt = (int) randBlue;
        int randGreenInt = (int) randGreen;

        Color randColor = new Color(randRedInt, randBlueInt, randGreenInt);

        return randColor;
    }
}
