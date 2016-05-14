package com.gamelogic;

import com.badlogic.gdx.graphics.Camera;

public class Game
{
	// Private variables
	// ----------------------------------
	private Player m_Player;
	private FallingObjectsHandler m_FallingObjectsHandler;
	private CollisionHandler m_CollisionHandler;
	private long m_StartTime;
	private boolean m_Running;
	private Camera m_Camera;
	// ----------------------------------
	
	// Constructors
	// ----------------------------------
	public Game(Camera camera)
	{
		m_Player = new Player();
		m_FallingObjectsHandler = new FallingObjectsHandler();
		m_CollisionHandler = new CollisionHandler();
		m_StartTime = 0;
		m_Running = false;
		m_Camera = camera;
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
		
		// Update player score
		if(checkCollisions())
		{
			m_Player.setScore(m_Player.getScore() + 1);
		}
		
	}
	
	public void render()
	{
		m_Player.render(m_Camera);
		m_FallingObjectsHandler.render(m_Camera);
	}
	
	
	private boolean checkCollisions()
	{
		return m_CollisionHandler.colliding(m_Player, m_FallingObjectsHandler);
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
