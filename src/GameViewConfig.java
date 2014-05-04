
import java.awt.Color;

/**
 * Class that sets the standard methods used for the separate options of
 * displaying the stones as well as the pits.
 *
 * @author P.U.G.S
 */
public abstract class GameViewConfig {

    boolean showNumbers;
    boolean rectangularPits;

    /**
     * Initializes variables used in GameViewConfig class.
     * @param showNumbers True if the numbers are displayed.
     * @param rectangularPits True if the pits are displayed as rectangles
     */
    GameViewConfig(boolean showNumbers, boolean rectangularPits) {
        this.showNumbers = showNumbers;
        this.rectangularPits = rectangularPits;
    }

    /**
     * Gets the color of the stones
     * @return The color of the stones
     */
    public abstract Color getStoneColor();

    /**
     * Gets the color of the pits
     * @return The color of the pits
     */
    public abstract Color getPitColor();

    /**
     * Returns true if the pits display the number of stones in each pit
     * @return true if each pit has the number of stones displayed in each pit
     */
    public boolean showNumbers() {
        return showNumbers;
    }
    /**
     * Returns true if the pits are displayed as a rectangle
     * @return true if the pits are displayed as a rectangle
     */
    public boolean getRectangularPits() {
        return rectangularPits;
    }
}
