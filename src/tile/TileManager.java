package tile;

import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import PlayerState.Player;
import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	private Player player;
	private String level;
	public TileManager(String _level, GamePanel gp, Player _player) {
		this.gp=gp;
		this.player=_player;
		this.level=_level;
		tile= new Tile[40];
		mapTileNum= new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		this.level="/maps/"+this.level+".txt";
		loadMap(this.level);
	}
	private void getTileImage() {
		try {
			// tile sky
			tile[0]= new Tile();
			//tile[0].image=ImageIO.read(getClass().getResourceAsStream("/skyTiles/00_skyTiles.png"));
			// black tile 
			tile[1]= new Tile();
			tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/92_tile.png"));
			// tree  2-5
			tile[2]= new Tile();
			tile[2].image= ImageIO.read(getClass().getResourceAsStream("/treeImage/165_tile.png"));
			tile[3]= new Tile();
			tile[3].image= ImageIO.read(getClass().getResourceAsStream("/treeImage/166_tile.png"));
			tile[4]= new Tile();
			tile[4].image= ImageIO.read(getClass().getResourceAsStream("/treeImage/198_tile.png"));
			tile[5]= new Tile();
			tile[5].image= ImageIO.read(getClass().getResourceAsStream("/treeImage/199_tile.png"));
			// 6-15 brick tile 
			tile[6]= new Tile();
			tile[6].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/16_tile.png"));
			tile[6].collision=true;
			tile[6].isGround=true;
			
			tile[7]= new Tile();
			tile[7].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/17_tile.png"));
			tile[7].collision=true;
			tile[7].isGround=true;
			
			tile[8]= new Tile();
			tile[8].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/18_tile.png"));
			tile[8].collision=true;
			tile[8].isGround=true;
			
			tile[9]= new Tile();
			tile[9].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/31_tile.png"));
			tile[9].collision=true;
			tile[10]= new Tile();
			tile[10].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/32_tile.png"));
			tile[10].collision=true;
			tile[11]= new Tile();
			tile[11].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/33_tile.png"));
			tile[11].collision=true;
			
			tile[12]= new Tile();
			tile[12].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/46_tile.png"));
			tile[12].collision=true;

			tile[13]= new Tile();
			tile[13].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/47_tile.png"));
			tile[13].collision=true;

			tile[14]= new Tile();
			tile[14].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/48_tile.png"));
			tile[14].collision=true;

			//wall brick
			tile[15]= new Tile();
			tile[15].image= ImageIO.read(getClass().getResourceAsStream("/tiles/76_tile.png"));
			tile[15].collision=true;
			tile[15].isGround=true;
			tile[15].isBrickInHigh=true;
			
			tile[16]= new Tile();
			tile[16].image= ImageIO.read(getClass().getResourceAsStream("/tiles/77_tile.png"));
			tile[16].collision=true;
			tile[16].isGround=true;
			tile[16].isBrickInHigh=true;
			
			tile[17]= new Tile();
			tile[17].image= ImageIO.read(getClass().getResourceAsStream("/tiles/78_tile.png"));
			tile[17].collision=true;
			tile[17].isGround=true;
			tile[17].isBrickInHigh=true;
			
			tile[18]= new Tile();
			tile[18].image= ImageIO.read(getClass().getResourceAsStream("/tiles/91_tile.png"));
			tile[18].collision=true;
			
			tile[19]= new Tile();
			tile[19].image= ImageIO.read(getClass().getResourceAsStream("/tiles/92_tile.png"));
			tile[19].collision=true;
			
			tile[20]= new Tile();
			tile[20].image= ImageIO.read(getClass().getResourceAsStream("/tiles/93_tile.png"));
			tile[20].collision=true;
			
			tile[21]= new Tile();
			tile[21].image= ImageIO.read(getClass().getResourceAsStream("/tiles/106_tile.png"));
			tile[21].collision=true;
			
			tile[22]= new Tile();
			tile[22].image= ImageIO.read(getClass().getResourceAsStream("/tiles/107_tile.png"));
			tile[22].collision=true;
			
			tile[23]= new Tile();
			tile[23].image= ImageIO.read(getClass().getResourceAsStream("/tiles/108_tile.png"));
			tile[23].collision=true;
			// brick 
			tile[24]= new Tile();
			tile[24].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/50_tile.png"));
			tile[25]= new Tile();
			tile[25].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/51_tile.png"));
			tile[26]= new Tile();
			tile[26].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/35_tile.png"));
			tile[27]= new Tile();
			tile[27].image= ImageIO.read(getClass().getResourceAsStream("/brickTile/36_tile.png"));
			// item 
			// thung go 
			tile[28]= new Tile();
			tile[28].image= ImageIO.read(getClass().getResourceAsStream("/itemTile/160_tile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap( String filePath) {
		try {
			InputStream is= getClass().getResourceAsStream(filePath);
			BufferedReader br= new BufferedReader(new InputStreamReader(is));
			int col=0;
			int row=0;
			while(col<gp.maxWorldCol&&row<gp.maxWorldRow) {
				String line = br.readLine();
				while (col<gp.maxWorldCol) {
					String number[]= line.split(" ");
					int num= Integer.parseInt(number[col]);
					mapTileNum[col][row]= num;
					col++;
				}
				if(col==gp.maxWorldCol) {
					col=0;
					row++;
				}
			}
		} catch (Exception e) {
		
		}
	}
	public void draw(Graphics2D g2) {
		int worldCol=0;
		int worldRow=0;
		while (worldCol<gp.maxWorldCol&&worldRow<gp.maxWorldRow) {
			int tileNum=mapTileNum[worldCol][worldRow];
			int worldX=worldCol*gp.tileSize;
			int worldY= worldRow*gp.tileSize;
			int screenX=worldX-player.worldX+player.screenX;
			int screenY= worldY-player.worldY+player.screenY;
			if(worldX+gp.tileSize>player.worldX-player.screenX&&
			   worldX-gp.tileSize<player.worldX+player.screenX&&
			   worldY+gp.tileSize>player.worldY-player.screenY&&
			   worldY-gp.tileSize<player.worldY+player.screenY)  
				g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
			worldCol++;
			if(worldCol==gp.maxWorldCol) {
				worldCol=0;
				worldRow++;
			}
			//g2.drawImage(tile[0].image,x,y,gp.tileSize,gp.tileSize,null);
			
		
		}
			
	}
	
}
