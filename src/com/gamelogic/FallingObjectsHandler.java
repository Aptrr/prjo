package com.gamelogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Pool;

public class FallingObjectsHandler
{
	private static final int[] SPAWNPOSITIONS = {
		(Gdx.graphics.getWidth() / 2)-90,
		(Gdx.graphics.getWidth() / 2)+40
		};
	private static final float MINSPAWNINTERVAL = 0.25f;
	private ArrayList<FallingObject> m_FallingObjects;
	//private ArrayList<FallingObject> m_FallingObjects;
	private float m_SpawnInterval = 1.5f;
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
	
	public void render()
	{
		for (int i = 0; i < m_FallingObjects.size(); i++)
		{
			m_FallingObjects.get(i).render();
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
			Fruit fruit = new Fruit(SPAWNPOSITIONS[spawnPosition], Gdx.graphics.getHeight());
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
