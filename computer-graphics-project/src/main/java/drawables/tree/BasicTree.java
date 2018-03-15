package drawables.tree;

import java.util.ArrayList;
import javax.media.opengl.*;
import javax.media.opengl.GL2;

import drawables.Drawable;
import drawables.Shape;

/**
 * A class for holding information about the tree to be drawn
 * @author DEMcKnight
 */
public class BasicTree implements Drawable, Shape
{

	//Instance variables

	/**
	 * The tree's branches
	 */
	private ArrayList<BasicBranch> branches;

	private ArrayList<LeafCluster> leafClusters;


	//Class (static) variables

	//Constructors
	public BasicTree()
	{
		branches = new ArrayList<BasicBranch>();
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
		// Fill tree trunk
		gl.glColor3f(0.644f, 0.446f, 0.106f);
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex2i(614, 426);
		gl.glVertex2i(648, 426);
		gl.glVertex2i(648, 591);
		gl.glVertex2i(614, 591);
		gl.glEnd();

		// Outline tree trunk
		gl.glColor3f(0.0f, 0.0f, 0.0f);	// black
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2i(614, 426);
		gl.glVertex2i(648, 426);
		gl.glVertex2i(648, 591);
		gl.glVertex2i(614, 591);
		gl.glEnd();

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
