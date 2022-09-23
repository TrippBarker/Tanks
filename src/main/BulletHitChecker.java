package main;

import tank.Bullet;
import tank.GreenTank;
import tank.RedTank;

public class BulletHitChecker {
	GamePanel gp;

	public BulletHitChecker(GamePanel gp) {
		this. gp = gp;
	}
	public void checkTile(Bullet bullet) {
		
		int bulletLeftX = gp.redBullet.screenX + gp.redBullet.solidArea.x;
		int bulletRightX = gp.redBullet.screenX + gp.redBullet.solidArea.x + gp.redBullet.solidArea.width;
		int bulletTopY = gp.redBullet.screenY + gp.redBullet.solidArea.y;
		int bulletBottomY = gp.redBullet.screenY + gp.redBullet.solidArea.y + gp.redBullet.solidArea.height;

		int bulletLeftCol = bulletLeftX/gp.tileSize;
		int bulletRightCol = bulletRightX/gp.tileSize;
		int bulletTopRow = bulletTopY/gp.tileSize;
		int bulletBottomRow = bulletBottomY/gp.tileSize;
		
		int otherTankLeftX = gp.greenTank.screenX + gp.greenTank.solidArea.x;
		int otherTankRightX = gp.greenTank.screenX + gp.greenTank.solidArea.x + gp.greenTank.solidArea.width;
		int otherTankTopY = gp.greenTank.screenY + gp.greenTank.solidArea.y;
		int otherTankBottomY = gp.greenTank.screenY + gp.greenTank.solidArea.y + gp.greenTank.solidArea.height;
		
		if(bullet.tankName instanceof GreenTank) {
			bulletLeftX = gp.greenBullet.screenX + gp.greenBullet.solidArea.x;
			bulletRightX = gp.greenBullet.screenX + gp.greenBullet.solidArea.x + gp.greenBullet.solidArea.width;
			bulletTopY = gp.greenBullet.screenY + gp.greenBullet.solidArea.y;
			bulletBottomY = gp.greenBullet.screenY + gp.greenBullet.solidArea.y + gp.greenBullet.solidArea.height;

			bulletLeftCol = bulletLeftX/gp.tileSize;
			bulletRightCol = bulletRightX/gp.tileSize;
			bulletTopRow = bulletTopY/gp.tileSize;
			bulletBottomRow = bulletBottomY/gp.tileSize;
			
			otherTankLeftX = gp.redTank.screenX + gp.redTank.solidArea.x;
			otherTankRightX = gp.redTank.screenX + gp.redTank.solidArea.x + gp.redTank.solidArea.width;
			otherTankTopY = gp.redTank.screenY + gp.redTank.solidArea.y;
			otherTankBottomY = gp.redTank.screenY + gp.redTank.solidArea.y + gp.redTank.solidArea.height;
		}
		
		int tileNum1 = 0, tileNum2 = 0;
		boolean collisionWithEnt = false;
		switch(bullet.direction) {
		case "up":
			bulletTopRow = (bulletTopY - bullet.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[bulletLeftCol][bulletTopRow];
			tileNum2 = gp.tileM.mapTileNum[bulletRightCol][bulletTopRow];
			break;
		case "down":
			bulletBottomRow = (bulletBottomY + bullet.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[bulletLeftCol][bulletBottomRow];
			tileNum2 = gp.tileM.mapTileNum[bulletRightCol][bulletBottomRow];
			break;
		case "left":
			bulletLeftCol = (bulletLeftX - bullet.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[bulletLeftCol][bulletTopRow];
			tileNum2 = gp.tileM.mapTileNum[bulletLeftCol][bulletBottomRow];
			break;
		case "right":
			bulletRightCol = (bulletRightX + bullet.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[bulletRightCol][bulletTopRow];
			tileNum2 = gp.tileM.mapTileNum[bulletRightCol][bulletBottomRow];
			break;
		}
		if(bulletTopY < otherTankBottomY && bulletRightX > otherTankLeftX && bulletLeftX < otherTankRightX && bulletBottomY > otherTankTopY ||
		   bulletBottomY > otherTankTopY && bulletRightX > otherTankLeftX && bulletLeftX < otherTankRightX && bulletTopY < otherTankBottomY ||
		   bulletLeftX < otherTankRightX && bulletTopY < otherTankBottomY && bulletBottomY > otherTankTopY && bulletRightX > otherTankLeftX ||
		   bulletRightX > otherTankLeftX && bulletTopY < otherTankBottomY && bulletBottomY > otherTankTopY && bulletLeftX < otherTankRightX) {
			collisionWithEnt = true;
			if (bullet.tankName instanceof RedTank) {
				gp.greenTank.lifePoints--;
				gp.greenGun.lifePoints--;
				gp.greenBullet.lifePoints--;
				gp.greenHealth.updatePoints(gp.greenTank.lifePoints);
			} else {
				gp.redTank.lifePoints--;
				gp.redGun.lifePoints--;
				gp.redBullet.lifePoints--;
				gp.redHealth.updatePoints(gp.redTank.lifePoints);
			}
		}
		if (gp.tileM.tiles[tileNum1].flyOver == false || gp.tileM.tiles[tileNum2].flyOver == false || collisionWithEnt) {
			bullet.collisionOn = true;
		}
		
		
	}
}
