package main;

import tank.Tank;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this. gp = gp;
	}
	
	public void checkTile(Tank tank) {
		
		
		int tankLeftX = tank.screenX + tank.solidArea.x;
		int tankRightX = tank.screenX + tank.solidArea.x + tank.solidArea.width;
		int tankTopY = tank.screenY + tank.solidArea.y;
		int tankBottomY = tank.screenY + tank.solidArea.y + tank.solidArea.height;
		
		int tankLeftCol = tankLeftX/gp.tileSize;
		int tankRightCol = tankRightX/gp.tileSize;
		int tankTopRow = tankTopY/gp.tileSize;
		int tankBottomRow = tankBottomY/gp.tileSize;
		
		int tileNum1 = 0, tileNum2 = 0;
		boolean collisionWithEnt = false;
		System.out.println(tankRightX);
		switch(tank.direction) {
		case "up":
			tankTopRow = (tankTopY - tank.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[tankLeftCol][tankTopRow];
			tileNum2 = gp.tileM.mapTileNum[tankRightCol][tankTopRow];
			break;
		case "down":
			tankBottomRow = (tankBottomY + tank.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[tankLeftCol][tankBottomRow];
			tileNum2 = gp.tileM.mapTileNum[tankRightCol][tankBottomRow];
			break;
		case "left":
			tankLeftCol = (tankLeftX - tank.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[tankLeftCol][tankTopRow];
			tileNum2 = gp.tileM.mapTileNum[tankLeftCol][tankBottomRow];
			break;
		case "right":
			tankRightCol = (tankRightX + tank.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[tankRightCol][tankTopRow];
			tileNum2 = gp.tileM.mapTileNum[tankRightCol][tankBottomRow];
			break;
		}
		if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true || collisionWithEnt) {
			tank.collisionOn = true;
		}
	}
}
