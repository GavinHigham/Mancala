import java.awt.Color;

public abstract class GameViewConfig {
	boolean showNumbers;
	boolean rainbowMode;
	boolean rectangularPits;
	
	GameViewConfig(boolean showNumbers, boolean rainbowMode, boolean rectangularPits) {
		this.showNumbers = showNumbers;
		this.rainbowMode = rainbowMode;
		this.rectangularPits = rectangularPits;
	}
	
	public abstract Color getStoneColor();
	public abstract Color getPitColor();
	
	public boolean showNumbers() {
		return showNumbers;
	}
	public boolean getRainbowMode() {
		return rainbowMode;
	}
	public boolean getRectangularPits() {
		return rectangularPits;
	}
}
