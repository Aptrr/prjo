package com.gamelogic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.prjo.PRJO;

public class FallingObjectsHandler
{
	private static final float[] SPAWNPOSITIONS = {
		(PRJO.WORLD_WIDTH / 2) * 0.7f,
		PRJO.WORLD_WIDTH / 2,
		(PRJO.WORLD_WIDTH / 2) * 1.3f,
		};
	
	private static final float MINSPAWNINTERVAL = 0.3f;
	private ArrayList<FallingObject> m_FallingObjects;
	private float m_SpawnInterval = 1.0f;
	private float m_TimeSinceLastSpawn = 0.0f;
	private boolean m_Dead = false;
	
	public FallingObjectsHandler()
	{
		m_FallingObjects = new ArrayList<FallingObject>();
	}
	
	public void update(float dt)
	{
		spawnFallingObject(dt);
		for (int i = 0; i < m_FallingObjects.size(); i++)
		{
			m_FallingObjects.get(i).update(dt);
			if (m_FallingObjects.get(i).getPos().y < 0)
			{
				m_Dead = true;
				removeFallingObject(i);
			}
		}
	}
	
	public void render(Camera camera)
	{
		for (int i = 0; i < m_FallingObjects.size(); i++)
		{
			m_FallingObjects.get(i).render(camera);
		}
	}
	
	public void removeFallingObject(int index)
	{
		if (index < m_FallingObjects.size())
		{
			m_FallingObjects.get(index).Dispose();
			m_FallingObjects.remove(index);
		}
	}
	
	public FallingObject getFallingObject(int index)
	{
		if (index < m_FallingObjects.size())
		{
			return m_FallingObjects.get(index);
		}
		else
		{
			return null;
		}
	}
	
	public int getNrOfFallingObjects()
	{
		return m_FallingObjects.size();
	}
	
	

	private void spawnFallingObject(float dt)
	{
		m_TimeSinceLastSpawn += dt;
		if (m_TimeSinceLastSpawn >= m_SpawnInterval)
		{
			int spawnPosition = getRandomNumber(0, SPAWNPOSITIONS.length);
			// Create fruit
			Fruit fruit = new Fruit(SPAWNPOSITIONS[spawnPosition], PRJO.WORLD_HEIGHT);
			// Set position array for collision handling
			fruit.setPoisition(spawnPosition);
			// Add the fruit to the falling objects
			m_FallingObjects.add(fruit);
			
			// Update spawn timer
			m_TimeSinceLastSpawn = 0.0f;
			if (m_SpawnInterval > MINSPAWNINTERVAL)
			{
				m_SpawnInterval -= 0.02f;
			}
		}
	}
	
	private int getRandomNumber(int lower, int upper)
	{
		Random rand = new Random();

		return rand.nextInt(upper) + lower;
	}
	
	public boolean isDead()
	{
		return m_Dead;
	}
}
