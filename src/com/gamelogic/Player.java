package com.gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.graphics.TextureManager;
import com.mygdx.prjo.PRJO;

public class Player extends Object
{
	// Private variables
	// ----------------------------------
	private int m_Score;
	private String m_Name;
	private BitmapFont m_Font;
	private boolean m_MoveLeft;
	private boolean m_MoveRight;
	// ----------------------------------
	
	// Constructors
	// ----------------------------------
	public Player()
	{
		m_Score = 0;
		m_Name = "";
		//m_Sprite = TextureManager.getInstance().createSprite("idle", 0, TextureManager.PLAYER);
		AtlasRegion atlasRegion = TextureManager.getInstance().getAtlasRegion("idle", 0, TextureManager.PLAYER);
		m_Sprite = new Sprite(atlasRegion);
		// Set player sprite size
		//float scale = (float) 1.5;
		//float width = m_Sprite.getWidth() / scale;
		//float height = m_Sprite.getHeight() / scale;
		float spriteX = PRJO.WORLD_WIDTH * 0.2f;
		float spriteY = PRJO.WORLD_HEIGHT * 0.15f;
		
		m_Sprite.setSize(spriteX, spriteY);
		
		// Set the sprite position
		m_Pos = new Vector2(PRJO.WORLD_WIDTH/2 - (spriteX/2), PRJO.WORLD_HEIGHT * 0.075f);
		m_Font = new BitmapFont();
	}
	// ----------------------------------

	// Methods
	// ----------------------------------
	public void update(float dt)
	{
		super.update(dt);
		if (m_MoveLeft)
		{
			this.m_Sprite.setRegion(TextureManager.getInstance().getAtlasRegion("attack", 1, TextureManager.PLAYER));
			this.m_Sprite.flip(true, false);
		}
		else if (m_MoveRight)
		{
			this.m_Sprite.setRegion(TextureManager.getInstance().getAtlasRegion("attack", 1, TextureManager.PLAYER));
		}
		else
		{
			this.m_Sprite.setRegion(TextureManager.getInstance().getAtlasRegion("idle", 0, TextureManager.PLAYER));
		}
	}
	
	public void render(Camera camera)
	{
		super.render(camera);
		m_SpriteBatch.begin();
		//m_Font.draw(m_SpriteBatch, "Score: " + m_Score, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 5);
		m_Sprite.draw(m_SpriteBatch);
		m_SpriteBatch.end();
	}
	
	public void setPos(int x, int y)
	{
		this.m_Pos.x = x;
		this.m_Pos.y = y;
	}
	
	public void setX(int x)
	{
		this.m_Pos.x = x;
	}
	
	public int getScore() 
	{
		return m_Score;
	}

	public void setScore(int m_Score) 
	{
		this.m_Score = m_Score;
	}

	public String getName() 
	{
		return m_Name;
	}

	public void setName(String m_Name) 
	{
		this.m_Name = m_Name;
	}
	// ----------------------------------

	public boolean getMoveLeft()
	{
		return m_MoveLeft;
	}

	public void setMoveLeft(boolean m_MoveLeft)
	{
		if (this.m_MoveRight)
		{
			this.m_MoveRight = false;
		}
		this.m_MoveLeft = m_MoveLeft;
	}

	public boolean getMoveRight()
	{
		if (this.m_MoveLeft)
		{
			this.m_MoveLeft = false;
		}
		return m_MoveRight;
	}

	public void setMoveRight(boolean m_MoveRight)
	{
		this.m_MoveRight = m_MoveRight;
	}
}
