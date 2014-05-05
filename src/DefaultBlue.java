import java.awt.Color;

/**
 * Creates pits where each pit is white and each stone is blue
 * 
 * @author P.U.G.S
 */
public class DefaultBlue extends GameViewConfig {
	
    /**
     * Initializes variables used in DefaultBlue
     * 
     * @param showNumbers true if numbers are displayed in each pit depicting the number of stones
     * @param rectangularPits true if the pits are rectangular instead of ellipses
     */
	DefaultBlue(boolean showNumbers, boolean rectangularPits) {
		super(showNumbers, rectangularPits); //Yes numbers, no rainbow.
	}

        /**
         * Gets the color of the stones
         * 
         * @return the color of the stones
         */
	public Color getStoneColor() {
		return new Color(100, 100, 255);
	}

        /**
         * Gets the color of the pit
         * 
         * @return the color of the pit
         */
	public Color getPitColor() {
		return new Color(255, 255, 255);
	}
}
