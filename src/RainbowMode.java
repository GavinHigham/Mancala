
import java.awt.Color;
import java.util.Random;

public class RainbowMode extends GameViewConfig {

    RainbowMode(boolean showNumbers, boolean rectangularPits) {
        super(showNumbers, false, rectangularPits); //Yes numbers, no rainbow.
    }

    public Color getStoneColor() {
        return getRandomColor();
    }

    public Color getPitColor() {
        return getRandomColor().brighter();
    }

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
