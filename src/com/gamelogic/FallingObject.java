package com.gamelogic;

public class FallingObject extends Object
{
	// Private variables
	// ----------------------------------
	protected double m_Velocity;
	// Array pos 0 is left, 1 is center and 2 is right
	private boolean m_Position[];
	
	// Constructors
	// ----------------------------------
	public FallingObject()
	{
		this.m_Position = new boolean[3];
	}
	// ----------------------------------
	
	// Methods
	// ----------------------------------
	public void update(float dt)
	{
		super.update(dt);
	}
	
	public void setPoisition(int index)
	{
		if (index >= 0 && index < this.m_Position.length)
		{
			this.m_Position[index] = true;
			
			// Set position to false for every other position in the array
			for (int i = 0; i < this.m_Position.length; i++)
			{
				if (index != i)
				{
					this.m_Position[i] = false; 
				}
			}
		}
		else
		{
			System.out.println(String.format("Invalid index (%d) specified in FallingObject.setPosition()", index));
		}
	}
	
	public int getPosition()
	{
		int index = 0;
		
		for (int i = 0; i < this.m_Position.length; i++)
		{
			if (this.m_Position[i])
			{
				index = i;
				i = this.m_Position.length;
			}
		}
		
		return index;
	}
	// ----------------------------------
}
