package snowflakes;

import java.awt.Color;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import geometry.RegularPolygon;
import reusable.graphicsPrimitives.Vec2;
import reusable.Helpers;

public class nflake extends Snowflake
{
	
	public int numSides = 6;
	
	public ArrayList<RegularPolygon> polygons = new ArrayList<RegularPolygon>();
	
	public nflake(Vec2 center, double radius, double angle, int numSides, int numIterations, boolean centersOffset, boolean centersRotated)
	{
		super(center, angle);
		this.numSides = numSides;
		
		//The center polygon's radius is different than the full radius.
		//As such, letting c be the radius of the center polygon,
		// r = c + (summation from i=1 to n-1 of  (1/2)^(n-1)*c) and solving for c, we get
		double radius2 = radius/(1 + 2-Math.pow(2, 1-numIterations));
		
		//Create the center polygon
		polygons.add(new RegularPolygon(Vec2.Zero, 0, radius2, numSides));
		
		//Perform iterations
		if (numIterations>0)
		{
			iterate(Vec2.Zero, radius2, 0.0, numSides, numIterations-1, centersOffset, centersRotated);
		}
	}
	
	public void iterate(Vec2 center, double radius, double angle, int numSides, int numIterations, boolean centersOffset, boolean centersRotated)
	{

		for (int i=0; i<numSides; i++)
		{
			double theta = angle + (i + (centersOffset? 0.5 : 0)) * 2 * Math.PI/(double)numSides;
			//Calculate the offset to the current new polygon from the original polygon
			Vec2 offset = new Vec2(Math.cos(theta), Math.sin(theta)).multiply(radius*1.5);
			//Find the center of the current new polygon
			Vec2 newCenter = center.add(offset);
			//Create and add the current new polygon to the overall polygon list
			polygons.add(new RegularPolygon(newCenter, theta, radius/2.0, numSides));
			//If we're not at the final iteration yet, iterate
			if (numIterations>0)
				iterate(newCenter, radius/2.0, theta, numSides, numIterations-1, centersOffset, centersRotated);
		}
	}

	@Override
	public void internalDraw(GL gl)
	{
		if (polygons != null)
			for (RegularPolygon flake: polygons)
				Helpers.drawPolygon(gl, flake.boundaryPoints);
	}
	


}