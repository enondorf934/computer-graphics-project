package drawables.tree;

import java.util.ArrayList;
import javax.media.opengl.GL2;
import java.awt.Color;

import drawables.Drawable;
import drawables.Shape;
import graphicsPrimitives.Vec2;

/**
 * A class for holding information about the tree to be drawn
 * @author DEMcKnight
 */
public class BasicTree implements Drawable, Shape
{

	//Class (static) variables

	//Instance variables
	Vec2 position;
	double width;
	double height;
	Color trunkColor;
	
	/**
	 * The tree's branches
	 */
	private ArrayList<BasicBranch> branches = new ArrayList<BasicBranch>();
	
	//Constructors
	
	/**
	 * Constructs a tree object with the given information.
	 * @param position The bottom-left-hand corner of the tree.
	 * @param scaleX The scale factor for the tree in the x-direction.
	 * @param scaleY The scale factor for the tree in the y-direction.
	 * @param trunkColor The color of the tree's trunk.
	 */
	public BasicTree (Vec2 position, double scaleX, double scaleY, Color trunkColor)
	{
		this.position=position;
		this.width=width;
		this.height=height;
		this.trunkColor=trunkColor;
	}
	
	//Class (static) methods
	
	//Instance methods
		
	//Interface methods
	
	@Override
	public void draw(GL2 gl)
	{
		for (BasicBranch branch: branches)
			branch.draw(gl);		
	}
		
	
	@Override
	public boolean containsPoint(double x, double y)
	{
		
		for (BasicBranch branch: branches)
		{
			if (branch.containsPoint(x, y))
				return true;
		}
		// TODO Auto-generated method stub
		return false;
	}
}
