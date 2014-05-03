import java.awt.Color;


public class DefaultBlue extends GameViewConfig {
	
	DefaultBlue(boolean showNumbers) {
		super(showNumbers, false); //Yes numbers, no rainbow.
	}

	@Override
	public Color getStoneColor() {
		// TODO Auto-generated method stub
		return new Color(100, 100, 255);
	}

	@Override
	public Color getPitColor() {
		// TODO Auto-generated method stub
		return new Color(255, 255, 255);
	}
}
