package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tiles;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;

		tiles = new Tile[10];
		mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];
		
		loadMap("/maps/map01.txt");
		getTileImage();
	}

	public void getTileImage() {
		try {
			// GRASS
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));
			
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			
			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			String[] numsArray = new String[gp.maxScreenRow];
			int col = 0;
			int row = 0;
			for(int i = 0; i < gp.maxScreenRow; i ++) {
				numsArray[i] = br.readLine();
			}
			while(row < gp.maxScreenRow) {
				String nums = numsArray[row];
				while(col < gp.maxScreenCol) {
					mapTileNum[col][row] = Integer.parseInt(String.valueOf(nums.charAt(col)));
					col++;
				}
				col = 0;
				row++;
			}
			for (String nums: numsArray) {
				System.out.println(nums);
			}
		}
		catch(Exception e) {	
		}
	}
	

	public void draw(Graphics2D g2) {
		for(int i = 0; i < gp.maxScreenCol; i++) {
			for(int j = 0; j < gp.maxScreenRow; j++) {
				g2.drawImage(tiles[mapTileNum[i][j]].image, gp.tileSize * i, gp.tileSize * j, gp.tileSize, gp.tileSize, null);
			}
		}


	}

}
