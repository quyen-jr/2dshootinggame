package tile;

import java.awt.image.BufferedImage;

public class Tile {
	public BufferedImage image;
	public boolean collision=false;
	public boolean isGround=false;
	// la is ground nhung  nam ở trên cao phân biệt với brick ở dưới đất;
	public boolean isBrickInHigh=false;
	public boolean isCeiling=false;
}
