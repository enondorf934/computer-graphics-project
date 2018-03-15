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
		// TODO: Draw tree trunk

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
