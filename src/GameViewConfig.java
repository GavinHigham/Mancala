import java.awt.Color;

public abstract class GameViewConfig {
	boolean showNumbers;
	boolean rainbowMode;
	
	GameViewConfig(boolean showNumbers, boolean rainbowMode) {
		this.showNumbers = showNumbers;
		this.rainbowMode = rainbowMode;
	}
	
	public abstract Color getStoneColor();
	public abstract Color getPitColor();
	
	public boolean showNumbers() {
		return showNumbers;
	}
	public boolean getRainbowMode() {
		return rainbowMode;
	}
}
