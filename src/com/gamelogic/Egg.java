package com.gamelogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.graphics.TextureManager;

public class Egg extends FallingObject
{
	private static final float FALLSPEED = 750.0f;
	
	public Egg()
	{
		m_Velocity = FALLSPEED;
		m_Pos = new Vector2(50.0f, 250.0f);
		m_Dir = new Vector2(0.0f, -1.0f);
		TextureRegion textureRegion = new TextureRegion(new Texture("Vegies.png"), 32, 32);
		m_Sprite = new Sprite(new Texture("akra.png"));
	}
	
	public Egg(float x, float y)
	{
		m_Velocity = FALLSPEED;
		m_Pos = new Vector2(x, y);
		m_Dir = new Vector2(0.0f, -1.0f);
		AtlasRegion atlasRegion = TextureManager.getInstance().getRandomFruit();
		m_Sprite = new Sprite(atlasRegion);
		m_Sprite.setSize(32, 32);
	}
	
	public void update(float dt)
	{
		super.update(dt);
		
		m_Pos.y -= m_Velocity * dt; 
	}
	
	public void render()
	{
		super.render();
		m_SpriteBatch.begin();
		m_Sprite.draw(m_SpriteBatch);
		m_SpriteBatch.end();
	}
}
