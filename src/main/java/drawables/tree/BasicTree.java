package drawables.tree;

import java.util.ArrayList;
import javax.media.opengl.GL2;

import java.awt.Color;

import drawables.Drawable;
import drawables.Shape;
import reusable.Helpers;
import reusable.graphicsPrimitives.Vec2;

/**
 * A class for holding information about the tree to be drawn
 * @author DEMcKnight
 */
public class BasicTree implements Drawable, Shape
{

	//Class (static) variables

	//Instance variables
	double x;
	double y;
	double width;
	double height;
	Color trunkColor;
	
	ArrayList<Vec2> trunkTriangleStrip = new ArrayList<Vec2>();
	ArrayList<Vec2> trunkOutline = new ArrayList<Vec2>();
	
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
	public BasicTree (double posX, double posY, double scaleX, double scaleY, Color trunkColor)
	{
		
		this.x=posX;
		this.y=posY;
		
		this.width=width;
		this.height=height;
		this.trunkColor=trunkColor;
		
		trunkTriangleStrip.add(new Vec2(x,y));						//1
		trunkTriangleStrip.add(new Vec2(x+20*scaleX, y));			//8
		trunkTriangleStrip.add(new Vec2(x+5*scaleX, y+5*scaleY));	//2
		trunkTriangleStrip.add(new Vec2(x+15*scaleX, y+5*scaleY));	//7
		trunkTriangleStrip.add(new Vec2(x+6*scaleX, y+9*scaleY));	//3
		trunkTriangleStrip.add(new Vec2(x+14*scaleX, y+11*scaleY));	//6
		trunkTriangleStrip.add(new Vec2(x+6*scaleX, y+89*scaleY));	//4
		trunkTriangleStrip.add(new Vec2(x+14*scaleX, y+89*scaleY));	//5
		
		trunkOutline.add(new Vec2(x,y));						//1
		trunkOutline.add(new Vec2(x+5*scaleX, y+5*scaleY));		//2
		trunkOutline.add(new Vec2(x+6*scaleX, y+9*scaleY));		//3
		trunkOutline.add(new Vec2(x+6*scaleX, y+89*scaleY));	//4
		trunkOutline.add(new Vec2(x+14*scaleX, y+89*scaleY));	//5
		trunkOutline.add(new Vec2(x+14*scaleX, y+11*scaleY));	//6
		trunkOutline.add(new Vec2(x+15*scaleX, y+5*scaleY));	//7
		trunkOutline.add(new Vec2(x+20*scaleX, y));				//8
	}
	
	//Class (static) methods
	
	//Instance methods
		
	//Interface methods
	
	@Override
	public void draw(GL2 gl)
	{
		//Draw trunk
		Helpers.setColor(gl,  trunkColor);
		Helpers.drawTriangleStrip(gl, trunkTriangleStrip);
		Helpers.setColor(gl, Color.BLACK);
		Helpers.drawLineLoop(gl, trunkOutline);
		
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
