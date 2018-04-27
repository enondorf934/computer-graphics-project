package snowflakes;

import java.awt.Color;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import geometry.RegularPolygon;
import reusable.graphicsPrimitives.Vec2;
import reusable.Helpers;

/**
 * Prepares a modified version of the nflake fractal
 * @author DEMcKnight
 *
 */
public class nflake extends Snowflake
{
	
	/**
	 * The number of sides for the center polygon (and subsequent ones) to have.
	 * In other words, the n of this n-flake
	 */
	public int numSides = 6;
	
	/**
	 * ArrayList of the polygons that compose this nflake
	 */
	public ArrayList<RegularPolygon> polygons = new ArrayList<RegularPolygon>();
	
	/**
	 * Prepares an nflake with the given center position (passed up to Snowflake as position), overall radius, angle (passed up to Snowflake as angle), numSides (the n of this n-flake), numIterations (number of times to apply the production rule), and offset parameters. 
	 * @param center The center of this nflake (passed up to Snowflake as position)
	 * @param radius The overall circumradius of this nflake
	 * @param angle The angle offset of this nflake, counter-clockwise in radians (passed up to Snowflake as angle)
	 * @param numSides The number of sides for the center polygon (and subsequent ones) to have. In other words, the n of this n-flake.
	 * @param numIterations The number of iterations remaining to recursively apply the production rule
	 * @param centersOffset True if the location of subsequent polygons about their spawning polygon should be rotationally offset by PI/numSides
	 * @param centersRotated True if the angle of subsequent polygons should be rotationally offset by PI/numSides in comparison with their spawning polygon.
	 */
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
	
	/**
	 * Prepares an nflake with the given center position (passed up to Snowflake as position), overall radius, angle (passed up to Snowflake as angle), numSides (the n of this n-flake), numIterations (number of times to apply the production rule), and offset parameters. 
	 * @param center The center of this nflake (passed up to Snowflake as position)
	 * @param radius The overall circumradius of this nflake
	 * @param angle The angle offset of this nflake, counter-clockwise in radians (passed up to Snowflake as angle)
	 * @param numSides The number of sides for the center polygon (and subsequent ones) to have. In other words, the n of this n-flake.
	 * @param numIterations The number of iterations remaining to recursively apply the production rule
	 * @param centersOffset True if the location of subsequent polygons about their spawning polygon should be rotationally offset by PI/numSides
	 * @param centersRotated True if the angle of subsequent polygons should be rotationally offset by PI/numSides in comparison with their spawning polygon.
	 */
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
			polygons.add(new RegularPolygon(newCenter, theta + (centersRotated? Math.PI/numSides : 0), radius/2.0, numSides));
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