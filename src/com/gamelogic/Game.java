package com.gamelogic;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Game
{
	// Private variables
	// ----------------------------------
	private Player m_Player;
	private FallingObjectsHandler m_FallingObjectsHandler;
	private long m_StartTime;
	private boolean m_Running;
	// ----------------------------------
	
	// Constructors
	// ----------------------------------
	public Game()
	{
		m_Player = new Player();
		m_FallingObjectsHandler = new FallingObjectsHandler();
		m_StartTime = 0;
		m_Running = false;
	}
	
	public Game(String playerName)
	{
		m_Player = new Player(playerName);
	}
	// ----------------------------------
	
	// Methods
	// ----------------------------------
	public void startGame()
	{
		m_StartTime = System.currentTimeMillis();
		m_Running = true;
	}
	
	public void update(float dt)
	{
		m_Player.update(dt);
		m_FallingObjectsHandler.update(dt);
		if (m_FallingObjectsHandler.isDead())
		{
			m_Running = false;
		}
		checkCollisions();
	}
	
	public void render()
	{
		m_Player.render();
		m_FallingObjectsHandler.render();
	}
	
	
	private void checkCollisions()
	{
		int nrOfFallingObjects = m_FallingObjectsHandler.getNrOfFallingObjects();
		if (nrOfFallingObjects > 0)
		{
			Rectangle playerRectangle = m_Player.getSprite().getBoundingRectangle();
			for (int i = 0; i < nrOfFallingObjects; i++)
			{
				FallingObject fallingObject = m_FallingObjectsHandler.getFallingObject(i);
				if (fallingObject == null)
				{
					continue;
				}
				Rectangle fallingObjectRectangle = fallingObject.getSprite().getBoundingRectangle();
				
				if (Intersector.intersectRectangles(playerRectangle, fallingObjectRectangle, fallingObjectRectangle))
				{
					m_Player.setScore(m_Player.getScore() + 1);
					m_FallingObjectsHandler.removeFallingObject(i);
				}
			}
		}
	}
	
	private long getElapsedTime()
	{
		return System.currentTimeMillis() - m_StartTime;
	}
	
	public boolean isRunning()
	{
		return m_Running;
	}
	
	public int getScore()
	{
		return m_Player.getScore();
	}
	
	public Player getPlayer()
	{
		return this.m_Player;
	}
	// ----------------------------------
}
