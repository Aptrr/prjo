package com.mygdx.prjo;

import javax.swing.JOptionPane;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamelogic.InputManager;
import com.highscore.DatabaseConnector;

public class PRJO extends ApplicationAdapter {
	
	// Private member variables
	// ------------------------------------
	private SpriteBatch m_SpriteBatch;
	private Texture m_Img;
	private BitmapFont m_ScoreFont;
	private BitmapFont m_InstructionFont;
	private com.gamelogic.Game m_Game;
	private InputManager m_InputManager;
	private DatabaseConnector m_DbConnector;
	private Sprite m_Background;
	// ------------------------------------
	
	// Public member variable
	// ------------------------------------
	//public static float SCALE_RATIO = 3289 / Gdx.graphics.getWidth();
	// ------------------------------------
	
	@Override
	public void create() 
	{
		try
		{
			m_SpriteBatch = new SpriteBatch();
			//img = new Texture("badlogic.jpg");
			m_ScoreFont = new BitmapFont();
			m_InstructionFont = new BitmapFont();
			m_Game = new com.gamelogic.Game();
			// Set Gdx to use custom input manager
			m_InputManager = new InputManager();
			Gdx.input.setInputProcessor(m_InputManager);
			m_DbConnector = new DatabaseConnector();
			
			// Initialize background sprite
			m_Background = createScaledSprite(new Texture("background1.png"));   
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fel", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void render() 
	{
		try
		{
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			m_SpriteBatch.begin();
			m_Background.draw(m_SpriteBatch);
			m_SpriteBatch.end();
			
			if (m_Game.isRunning())
			{
				handleGameLogic();
			}
			else
			{
				handleMenu();
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fel", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public com.gamelogic.Game getGame()
	{
		return this.m_Game;
	}
	
	// Start game
	public void startGame()
	{
		if (m_Game != null)
		{
			m_Game = null;
		}
		m_Game = new com.gamelogic.Game();
		m_Game.startGame();
	}
	
	// Update game state and render the game scene
	private void handleGameLogic()
	{
		m_Game.update(Gdx.graphics.getDeltaTime());
		m_Game.render();
	}
	
	// Handle menu and render the menu scene
	private void handleMenu()
	{
		m_SpriteBatch.begin();
		
		// Show score if a game has been played
		if (m_Game.getScore() > 0)
		{
			m_ScoreFont.draw(m_SpriteBatch, "Score: " + m_Game.getScore(), Gdx.graphics.getWidth()/2 - 100, (Gdx.graphics.getHeight()/2) + 50);
		}
		m_InstructionFont.draw(m_SpriteBatch, "Press enter to start the game", Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight()/2);
		
		m_SpriteBatch.end();
	}
	
	private static Sprite createScaledSprite(Texture texture) 
	{
		Sprite sprite = new Sprite(texture);
		sprite.getTexture().setFilter(TextureFilter.Linear,
				TextureFilter.Linear);
		sprite.setSize(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		return sprite;
    }
}
