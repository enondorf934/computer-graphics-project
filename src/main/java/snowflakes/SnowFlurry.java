package snowflakes;


import java.util.ArrayList;
import java.util.Random;

import java.util.function.*;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import reusable.graphicsPrimitives.Vec2;

public class SnowFlurry
{
	//Snowflurry boundaries (defines where snowflakes begin and disappear
	public double minX;
	public double maxX;
	public double minY;
	public double maxY;
	
	//How often snowflakes should occur
	public double flakeOccurrence = 0.5;
	Random randy = new Random();
	
	//List of snowflakes being drawn
	public ArrayList<Snowflake> snowflakes;
	
	/**
	 * Instantiates a Snowflurry box with the given boundaries
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 * @param flakeOccurrence Probability a new snowflake should appear at each frame
	 */
	public SnowFlurry(double minX, double maxX, double minY, double maxY, double flakeOccurrence) 
	{
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		
		this.flakeOccurrence = flakeOccurrence;
		
		init();
	}
	
	private void init()
	{
		snowflakes = new ArrayList<Snowflake>();
	}
	
	
	public void iterate(double timestep)
	{
		if (randy.nextDouble()<flakeOccurrence)
		{
			int snowflakeType = randy.nextInt(5);
			double rotationVelocity = (2*randy.nextDouble()-1)*Math.PI/2.0;
			double fallRate = 30;
			double cycloidCircleRadius = randy.nextDouble()*20;
			double radius = randy.nextDouble() * 15 + 11;
			double angle = randy.nextDouble() * 2 * Math.PI;
			double startPos = randy.nextDouble();
			//snowflakes.add(generateSnowflake(snowflakeType, radius, angle, rotationVelocity, fallRate, cycloidCircleRadius, randy.nextBoolean(), randy.nextBoolean()));
			snowflakes.add(generateSnowflake(snowflakeType, radius, angle, rotationVelocity, fallRate, cycloidCircleRadius, startPos, false, false));

		}
		
		
		//for each of the snowflakes
		for (int i = 0; i<snowflakes.size(); i++)
		{
			//grab a snowflake
			Snowflake s = snowflakes.get(i);
			
			//update it
			s.update(timestep);
			
			//if its position is too high or too low, remove it
			Vec2 pos = s.getPosition();	
			if (pos.getY()<minY || pos.getY() > maxY)
			{
				snowflakes.remove(i);
				i--;
			}				
		}
		
	}
	
	/**
	 * Generate a Snowflake and assign it behaviors based on the given parameters
	 * @param i the the type of Snowflake to generate
	 * @param rotationVelocity
	 * @param fallRate
	 * @param cycloidCircleRadius
	 * @return a Snowflake (either an n-flake or a Koch Snowflake) with behaviors based on the given parameters
	 */
	public Snowflake generateSnowflake(int i, double radius, double angle, double rotationVelocity, double fallRate, double cycloidCircleRadius, double startPos,  boolean centersPositionOffset, boolean centersRotateOffset)
	{
		
		//Prepare a snowflake for returning, /s/
		Snowflake s;
		
		//Randomly generate a position spanning along the top of the screen
		Vec2 position = new Vec2(randy.nextDouble()*maxX-minX, maxY);
		
		//Randomly select a snowflake type
		switch(i)
		{
		case 0:
			// generate a triflake
			s = new nflake(position, radius, angle, 3, 3, centersPositionOffset, centersRotateOffset);
			break;
		case 1:
			// generate a quadriflake
			s = new nflake(position, radius, angle, 4, 3, centersPositionOffset, centersRotateOffset);
			break;
		case 2:
			// generate a pentaflake
			s = new nflake(position, radius, angle, 5, 3, centersPositionOffset, centersRotateOffset);
			break;
		case 3:
			// generate a hexaflake
			s = new nflake(position, radius, angle, 6, 2, centersPositionOffset, centersRotateOffset);
			break;
		case 4:
			// generate a Koch snowflake
			s = new KochSnowflake(position, radius, angle, 3);
			break;
		default:
			// generate a Koch snowflake
			s = new KochSnowflake(position, radius, angle, 3);
			break;
		}
		
		//assign behaviors
		s.addUpdateFunc(SnowflakeBehaviors.getCycloidBehavior(cycloidCircleRadius, startPos));
		s.addUpdateFunc(SnowflakeBehaviors.getRotationBehavior(rotationVelocity));
		s.addUpdateFunc(SnowflakeBehaviors.getStraightFallBehavior(fallRate));		
		
		//return the snowflake
		return s;
	}
	
	/**
	 * Calls draw on each of the Snowflakes contained by this SnowFlurry
	 * @param gl
	 */
	public void draw(GL gl)
	{		
		for (Snowflake flake: snowflakes)
			flake.draw(gl);
	}
		
	
}
