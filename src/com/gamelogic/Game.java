package com.gamelogic;

public class Game
{
	// Private variables
	// ----------------------------------
	private Player m_Player;
	private FallingObjectsHandler m_FallingObjectsHandler;
	private CollisionHandler m_CollisionHandler;
	private long m_StartTime;
	private boolean m_Running;
	// ----------------------------------
	
	// Constructors
	// ----------------------------------
	public Game()
	{
		m_Player = new Player();
		m_FallingObjectsHandler = new FallingObjectsHandler();
		m_CollisionHandler = new CollisionHandler();
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
		m_CollisionHandler.colliding(m_Player, m_FallingObjectsHandler);
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
