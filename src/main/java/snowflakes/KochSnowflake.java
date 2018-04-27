package snowflakes;

import java.awt.Color;
import java.util.ArrayList;

import javax.media.opengl.GL;

import reusable.graphicsPrimitives.Vec2;
import reusable.Helpers;

public class KochSnowflake extends Snowflake
{
	
	public ArrayList<Vec2> coordinates;

	public KochSnowflake(Vec2 position, double radius, double angle, int numGenerations)
	{
		super(position, angle);
		
		coordinates = new ArrayList<Vec2>();
		for (int i=0; i<numGenerations; i++)
		{
			anotherLayer(radius, i);
		}	
	}
	
	
	public void anotherLayer(double radius, int genNumber)
	{
		if (genNumber == 0)
		{
			//Base case
			for (int i=0; i<3; i++)
			{
				double theta = i * 2*Math.PI/3.0;
				Vec2 disp = new Vec2(Math.cos(theta), Math.sin(theta)).multiply(radius);
				coordinates.add(disp);
			}
		}
		//Iterative version 
		else
		{
			//For every two coordinates in the current list, add a triangle at the third-of-the-way points between them
			for (int i = 0; i<coordinates.size(); i++)
			{
				//Grab first and second of the coordinates
				Vec2 start = coordinates.get(i);
				Vec2 end = coordinates.get((i+1)%coordinates.size());	
				
				//Find the vector between them and divide it by three
				Vec2 diff = end.subtract(start).multiply(1/3.0);
				
				//Then use that vector to generate  triangle between them
				Vec2 p1 = start.add(diff);
				Vec2 p2 = p1.add(diff.rotated(-Math.PI/3.0));
				Vec2 p3 = p1.add(diff);
				
				//Add the triangle's coordinates
				coordinates.add(i+1, p1);
				coordinates.add(i+2, p2);
				coordinates.add(i+3, p3);
				i+=3;
			}
		}
	}

	@Override
	public void internalDraw(GL gl)
	{
		Helpers.setColor(gl, Color.black);
		Helpers.drawPolygon(gl, coordinates);
		Helpers.setColor(gl, Color.white);
		Helpers.drawLineLoop(gl, coordinates);

	}
	
	

}
