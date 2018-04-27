package geometry;

import java.util.ArrayList;

import javax.media.opengl.GL;

import reusable.graphicsPrimitives.Vec2;
import reusable.Helpers;

/**
 * A class for creating regular polygons
 * @author DEMcKnight
 *
 */
public class RegularPolygon
{
	/**
	 * The vertices of this regular polygon (not offset by center)
	 */
	public ArrayList<Vec2> boundaryPoints;
	
	/**
	 * The circumradius of this regular polygon
	 */
	public double circumRadius;
	
	/**
	 * The amount to offset this regular polygon when draw is called
	 */
	public Vec2 center;
	
	/**
	 * Creates a regular polygon with the given parameters
	 * @param center The amount to offset this regular polygon when draw is called
	 * @param angle The angle to offset this regular polygon in all cases (does impact boundaryPoints)
	 * @param circumRadius The circumradius of this regular polygon
	 * @param numSides The number of sides this regular polygon has
	 */
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
