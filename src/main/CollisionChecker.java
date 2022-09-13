package main;

import entity.Entity;
import entity.EvilTank;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this. gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		EvilTank evilTank = gp.evilTank;
		
		int entityLeftX = entity.screenX + entity.solidArea.x;
		int entityRightX = entity.screenX + entity.solidArea.x + entity.solidArea.width;
		int entityTopY = entity.screenY + entity.solidArea.y;
		int entityBottomY = entity.screenY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftX/gp.tileSize;
		int entityRightCol = entityRightX/gp.tileSize;
		int entityTopRow = entityTopY/gp.tileSize;
		int entityBottomRow = entityBottomY/gp.tileSize;
		
		int evilLeftX = evilTank.screenX + evilTank.solidArea.x;
		int evilRightX = evilTank.screenX + evilTank.solidArea.x + evilTank.solidArea.width;
		int evilTopY = evilTank.screenY + evilTank.solidArea.y;
		int evilBottomY = evilTank.screenY + evilTank.solidArea.y + evilTank.solidArea.height;
		
		int evilLeftCol = evilLeftX/gp.tileSize;
		int evilRightCol = evilRightX/gp.tileSize;
		int evilTopRow = evilTopY/gp.tileSize;
		int evilBottomRow = evilBottomY/gp.tileSize;
		
		int tileNum1 = 0, tileNum2 = 0;
		boolean collisionWithEnt = false;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if (entityTopY <= evilBottomY && entityRightX >= evilLeftX && entityLeftX <= evilRightX && entityBottomY > evilTopY) {
				collisionWithEnt = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (entityBottomY >= evilTopY && entityRightX >= evilLeftX && entityLeftX <= evilRightX && entityTopY < evilBottomY) {
				collisionWithEnt = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if (entityLeftX <= evilRightX && entityTopY <= evilBottomY && entityBottomY >= evilTopY && entityRightX > evilLeftX) {
				collisionWithEnt = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (entityRightX >= evilLeftX && entityTopY <= evilBottomY && entityBottomY >= evilTopY && entityLeftX < evilRightX) {
				collisionWithEnt = true;
			}
			break;
		}
		System.out.println("good " + entityRightX);
		System.out.println("bad " + evilLeftX);
		if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true || collisionWithEnt) {
			entity.collisionOn = true;
		}
	}
}
