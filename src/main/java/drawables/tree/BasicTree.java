package drawables.tree;

import java.util.ArrayList;
import javax.media.opengl.*;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

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

	//The tree outline points and the triangulation of those points
	ArrayList<Vec2> trunkTriangleStrip = new ArrayList<Vec2>();
	ArrayList<Vec2> trunkOutline = new ArrayList<Vec2>();

	
	//The tree's branches and leaves
	private ArrayList<BasicBranch> branches = new ArrayList<BasicBranch>();
	private ArrayList<LeafCluster> leafClusters;

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

		this.width=scaleX;
		this.height=scaleY;
		this.trunkColor=trunkColor;

		trunkTriangleStrip.add(new Vec2(0,0));				//1
		trunkTriangleStrip.add(new Vec2(1, 0));				//8
		trunkTriangleStrip.add(new Vec2(0.25, 0.05618));	//2
		trunkTriangleStrip.add(new Vec2(0.75, 0.05618));	//7
		trunkTriangleStrip.add(new Vec2(0.3, 0.101124));	//3
		trunkTriangleStrip.add(new Vec2(0.7, 0.123596));	//6
		trunkTriangleStrip.add(new Vec2(0.3, 1));			//4
		trunkTriangleStrip.add(new Vec2(0.7, 1));			//5

		trunkOutline.add(new Vec2(0,0));			//1
		trunkOutline.add(new Vec2(0.25, 0.05618));	//2
		trunkOutline.add(new Vec2(0.3, 0.101124));	//3
		trunkOutline.add(new Vec2(0.3, 1));			//4
		trunkOutline.add(new Vec2(0.7, 1));			//5
		trunkOutline.add(new Vec2(0.7, 0.123596));	//6
		trunkOutline.add(new Vec2(0.75, 0.05618));	//7
		trunkOutline.add(new Vec2(1, 0));			//8


		leafClusters = new ArrayList<LeafCluster>();
	}

	//Class (static) methods

	//Instance methods
	public void addLeafCluster(LeafCluster leafCluster)
	{
		leafClusters.add(leafCluster);
	}

	//Interface methods
	@Override
	public void draw(GL2 gl)
	{
		
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glTranslated(x, y, 0);
		
		gl.glScaled(width, height, 1);
		//Draw trunk
		
		Helpers.setColor(gl,  trunkColor);
		Helpers.drawTriangleStrip(gl, trunkTriangleStrip);
		Helpers.setColor(gl, Color.BLACK);
		Helpers.drawLineLoop(gl, trunkOutline);

		
		
		//Move this after the below for loops to make scaling effect leaf clusters
		gl.glPopMatrix();

		for (BasicBranch branch: branches)
			branch.draw(gl);

		for (LeafCluster leafCluster : leafClusters)
			leafCluster.draw(gl);		
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
