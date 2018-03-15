package drawables.tree;

import java.util.ArrayList;
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
	private ArrayList<BasicBranch> branches = new ArrayList<BasicBranch>();


	//Class (static) variables

	//Constructors
	public BasicTree()
	{

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
