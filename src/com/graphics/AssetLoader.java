package com.graphics;

// Singleton responsible for loading textures, sounds etc

public class AssetLoader
{
	private static AssetLoader instance = null;
	
	protected AssetLoader()
	{
		
	}
	
	public static AssetLoader getInstance()
	{
		if (instance == null)
		{
			instance = new AssetLoader();
		}
		return instance;
	}
}
