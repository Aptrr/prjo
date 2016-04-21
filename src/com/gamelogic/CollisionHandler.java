package com.gamelogic;


// This class handles the very simple collision detected required for this game
public class CollisionHandler
{
	public CollisionHandler()
	{
		// TODO Auto-generated constructor stub
	}
	
	public boolean colliding(Player player, FallingObjectsHandler foh)
	{
		boolean collision = false;
		
		for (int i = 0; i < foh.getNrOfFallingObjects(); i++)
		{
			// Make sure the object is at or below the player height
			if (foh.getFallingObject(i).getPos().y <= player.getSprite().getY() + player.getSprite().getHeight())
			{
				switch (foh.getFallingObject(i).getPosition()) {
					case 0:
						if (player.getMoveLeft())
						{
							foh.removeFallingObject(i);
						}
						break;
					case 2:
						if (player.getMoveRight())
						{
							foh.removeFallingObject(i);
						}
						break;
					default:
						foh.removeFallingObject(i);
						break;
				}
			}
		}
		
		return collision;
	}
}
