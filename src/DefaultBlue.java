import java.awt.Color;


public class DefaultBlue extends GameViewConfig {
	
	DefaultBlue(boolean showNumbers, boolean rectangularPits) {
		super(showNumbers, false, rectangularPits); //Yes numbers, no rainbow.
	}

	public Color getStoneColor() {
		return new Color(100, 100, 255);
	}

	public Color getPitColor() {
		return new Color(255, 255, 255);
	}
}
