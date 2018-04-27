package geometry;

import java.util.ArrayList;

import javax.media.opengl.GL;

import reusable.graphicsPrimitives.Vec2;
import reusable.Helpers;

public class RegularPolygon
{
	public ArrayList<Vec2> boundaryPoints;
	public double circumRadius;
	public Vec2 center;
	
	public RegularPolygon(Vec2 center, double angle, double circumRadius, int numSides)
	{
		this.center = center;
		boundaryPoints = new ArrayList<Vec2>(numSides);
		for (int i=0; i<numSides; i++)
		{
			boundaryPoints.add(center.add((new Vec2(Math.cos(angle + i*2*Math.PI/numSides), Math.sin(angle + i*2*Math.PI/numSides)).multiplyAssignment(circumRadius))));		
		}
	}
	
	public void draw(GL gl)
	{
		Helpers.drawPolygon(gl, boundaryPoints);
	}

}
