package com.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureManager
{
	private Texture[] mFruitsTextures;
	private Texture[] mPlayerTextures;
	private Texture mBackgroundTexture;
	
	public TextureManager()
	{
		if (!initialize())
		{
			System.out.println("Could not initialize TextureManager");
		}
	}
	
	// Load relevant textures
	public boolean initialize()
	{
		boolean initialized = true;
		
		// Load fruit textures
		TextureRegion textureRegion = new TextureRegion(new Texture("Vegies.png"), 32, 32);
		TextureRegion[][] tmp = textureRegion.split(32, 32);
		
		
		return initialized;
	}
}
