package com.graphics;

import java.util.Hashtable;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

// Singleton responsible for loading textures and providing textures

public class TextureManager
{
	// Singleton
	private static TextureManager instance = null;
	
	// Fruit variables
	private TextureAtlas mFruitTextures;
	
	// Player variables
	private TextureAtlas mPlayerTextures;
	
	private Texture mBackgroundTexture;
	
	private Hashtable<String, AtlasRegion> mLoadedRegions;
	
	// Constant variables
	public static final int FRUIT = 0;
	public static final int PLAYER = 1;
	
	public TextureManager()
	{
		if (!initialize())
		{
			System.out.println("Could not initialize TextureManager");
		}
	}
	
	public static TextureManager getInstance()
	{
		if (instance == null)
		{
			instance = new TextureManager();
		}
		return instance;
	}
	
	// Load relevant textures
	public boolean initialize()
	{
		boolean initialized = true;
		
		// Load fruit texture
		mFruitTextures = new TextureAtlas(Gdx.files.internal("vegies_output/vegies.atlas"));
		
		// Load player texture
		mPlayerTextures = new TextureAtlas(Gdx.files.internal("character_output/viking.atlas"));
		
		mLoadedRegions = new Hashtable<String, TextureAtlas.AtlasRegion>();
		
		return initialized;
	}
	
	/**
	 * Get texture for the specified atlas item
	 * @param Name of the image in the atlas
	 * @param Index. If index < 0 then only request the name
	 * @param Type to find.
	 * @return AtlasRegion. Can be null if no valid texture is found
	 */
	public AtlasRegion getAtlasRegion(String atlasName, int index, int type)
	{
		AtlasRegion atlasRegion = null;
		TextureAtlas textureAtlas = null;
		try
		{
			// Use the specified texture atlas
			if (type == PLAYER)
			{
				textureAtlas = mPlayerTextures;
			}
			else if (type == FRUIT)
			{
				textureAtlas = mFruitTextures;
			}
			else
			{
				System.out.println("Invalid type specified in TextureManager.getAtlasRegion");
			}
			
			
			atlasRegion = getAtlasRegion(textureAtlas, atlasName, index);
			
			if (atlasRegion == null)
			{
				throw new Exception(String.format("Unable to find an atlas region for values key: %s, index: %d", atlasName, index));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return atlasRegion;
	}
	
	/**
	 * Create sprite for the specified atlas item
	 * @param Name of the image in the atlas
	 * @param Index. If index < 0 then only request the name
	 * @param Type to find.
	 * @return Sprite. Texture can be null if no valid texture is found
	 */
	public Sprite createSprite(String atlasName, int index, int type)
	{
		Sprite sprite = null;
		switch (type) {
			case FRUIT:
				if (index < 0)
				{
					sprite = mFruitTextures.createSprite(atlasName, index);
				}
				else
				{
					sprite = mFruitTextures.createSprite(atlasName);
				}
				break;
			case PLAYER:
				if (index < 0)
				{
					sprite = mPlayerTextures.createSprite(atlasName, index);
				}
				else
				{
					sprite = mPlayerTextures.createSprite(atlasName);
				}
				break;
			default:
				System.out.println("Invalid type specified in TextureManager.getSprite");
				break;
		}
		
		return sprite;
	}
	
	public AtlasRegion getRandomFruit()
	{
		Array<AtlasRegion> atlasRegions = mFruitTextures.getRegions();
		
		// Get a random number within the altas region
		Random ran = new Random();
		int nr = 0 + ran.nextInt((atlasRegions.size - 1) - 0 + 1);
		
		return atlasRegions.get(nr);
	}
	
	/**
	 * Find if the atlas region is loaded. If it isn't, load it and store it in the hashtable
	 * @param key to search for
	 * @return boolean
	 */
	private boolean isAtlasRegionLoaded(String key)
	{
		if (mLoadedRegions.containsKey(key))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Find if the atlas region is loaded. 
	 * @param key to search for
	 * @param index
	 * @return boolean
	 */
	private boolean isAtlasRegionLoaded(String key, int index)
	{
		String keyWithIndex = key + index;
		if (mLoadedRegions.containsKey(keyWithIndex))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Get AtlasRegion. Will get AtlasRegion from the cache if available
	 * @param textureAtlas
	 * @param key
	 * @param index
	 * @return
	 */
	private AtlasRegion getAtlasRegion(TextureAtlas textureAtlas, String key, int index)
	{
		AtlasRegion atlasRegion;
		if (index < 0)
		{
			if (isAtlasRegionLoaded(key))
			{
				atlasRegion = mLoadedRegions.get(key);
			}
			else
			{
				atlasRegion = textureAtlas.findRegion(key);
				addToCache(key, atlasRegion);
			}
		}
		else
		{
			// key used to store key + index in hashtable
			String keyWithIndex = key + index;
			
			if (isAtlasRegionLoaded(keyWithIndex))
			{
				atlasRegion = mLoadedRegions.get(keyWithIndex);
			}
			else
			{
				atlasRegion = textureAtlas.findRegion(key, index);
				addToCache(keyWithIndex, atlasRegion);
			}
		}
		
		return atlasRegion;
	}
	
	private boolean addToCache(String key, AtlasRegion atlasRegion)
	{
		boolean addedToCache = false;
		
		if (atlasRegion != null)
		{
			mLoadedRegions.put(key, atlasRegion);
		}
		
		return addedToCache;
	}
}
