package main;

import tank.GreenTank;
import tank.Tank;

public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this. gp = gp;
	}

	public void checkTile(Tank tank) {
		int tankColSpeed = tank.speed * 2;
		// Get position of Red and Green tanks
		int tankLeftX = gp.redTank.screenX + gp.redTank.solidArea.x;
		int tankRightX = gp.redTank.screenX + gp.redTank.solidArea.x + gp.redTank.solidArea.width;
		int tankTopY = gp.redTank.screenY + gp.redTank.solidArea.y;
		int tankBottomY = gp.redTank.screenY + gp.redTank.solidArea.y + gp.redTank.solidArea.height;

		int tankLeftCol = tankLeftX/gp.tileSize;
		int tankRightCol = tankRightX/gp.tileSize;
		int tankTopRow = tankTopY/gp.tileSize;
		int tankBottomRow = tankBottomY/gp.tileSize;

		int otherTankLeftX = gp.greenTank.screenX + gp.greenTank.solidArea.x;
		int otherTankRightX = gp.greenTank.screenX + gp.greenTank.solidArea.x + gp.greenTank.solidArea.width;
		int otherTankTopY = gp.greenTank.screenY + gp.greenTank.solidArea.y;
		int otherTankBottomY = gp.greenTank.screenY + gp.greenTank.solidArea.y + gp.greenTank.solidArea.height;
		
		if(tank instanceof GreenTank) {
			tankLeftX = gp.greenTank.screenX + gp.greenTank.solidArea.x;
			tankRightX = gp.greenTank.screenX + gp.greenTank.solidArea.x + gp.greenTank.solidArea.width;
			tankTopY = gp.greenTank.screenY + gp.greenTank.solidArea.y;
			tankBottomY = gp.greenTank.screenY + gp.greenTank.solidArea.y + gp.greenTank.solidArea.height;

			tankLeftCol = tankLeftX/gp.tileSize;
			tankRightCol = tankRightX/gp.tileSize;
			tankTopRow = tankTopY/gp.tileSize;
			tankBottomRow = tankBottomY/gp.tileSize;

			otherTankLeftX = gp.redTank.screenX + gp.redTank.solidArea.x;
			otherTankRightX = gp.redTank.screenX + gp.redTank.solidArea.x + gp.redTank.solidArea.width;
			otherTankTopY = gp.redTank.screenY + gp.redTank.solidArea.y;
			otherTankBottomY = gp.redTank.screenY + gp.redTank.solidArea.y + gp.redTank.solidArea.height;
		}

		int tileNum1 = 0, tileNum2 = 0;
		boolean collisionWithEnt = false;
		switch(tank.direction) {
		case "up":
			tankTopRow = (tankTopY - tank.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[tankLeftCol][tankTopRow];
			tileNum2 = gp.tileM.mapTileNum[tankRightCol][tankTopRow];
			if(tankTopY - (tankColSpeed) < otherTankBottomY && tankRightX + (tankColSpeed) > otherTankLeftX && tankLeftX - (tankColSpeed) < otherTankRightX && tankBottomY > otherTankTopY) {
				collisionWithEnt = true;
			}
			break;
		case "down":
			tankBottomRow = (tankBottomY + tank.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[tankLeftCol][tankBottomRow];
			tileNum2 = gp.tileM.mapTileNum[tankRightCol][tankBottomRow];
			if(tankBottomY + (tankColSpeed) > otherTankTopY && tankRightX + (tankColSpeed) > otherTankLeftX && tankLeftX - (tankColSpeed) < otherTankRightX && tankTopY < otherTankBottomY) {
				collisionWithEnt = true;
			}
			break;
		case "left":
			tankLeftCol = (tankLeftX - tank.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[tankLeftCol][tankTopRow];
			tileNum2 = gp.tileM.mapTileNum[tankLeftCol][tankBottomRow];
			if(tankLeftX - (tankColSpeed) < otherTankRightX && tankTopY - tankColSpeed < otherTankBottomY && tankBottomY + tankColSpeed > otherTankTopY && tankRightX > otherTankLeftX){
				collisionWithEnt = true;
			}
			break;
		case "right":
			tankRightCol = (tankRightX + tank.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[tankRightCol][tankTopRow];
			tileNum2 = gp.tileM.mapTileNum[tankRightCol][tankBottomRow];
			if(tankRightX + (tankColSpeed) > otherTankLeftX && tankTopY - tankColSpeed < otherTankBottomY && tankBottomY + tankColSpeed > otherTankTopY && tankLeftX < otherTankRightX){
				collisionWithEnt = true;
			}
			break;
		}
		if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true || collisionWithEnt) {
			tank.collisionOn = true;
		}
	}
}
