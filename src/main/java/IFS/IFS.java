package IFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import reusable.graphicsPrimitives.Mat2;
import reusable.graphicsPrimitives.Vec2;

/**
 * A class for holding information for an iterated function system with iterations of the form x2 = Ax1 + b
 * @author DEMcKnight
 */
public class IFS
{
	Random randy = new Random();
	List<Mat2> matrices;
	List<Vec2> addends;
	Vec2 currentPosition = new Vec2(0,0);
	
	/**
	 * Constructs a new Iterated Function System that uses the given matrices and addends to transform the current coordinates each frame.
	 * @param matrices
	 * @param addends
	 */
	public IFS(List<Mat2> matrices, List<Vec2> addends)
	{
		this.matrices = matrices;
		this.addends = addends;
	}
	
	/**
	 * Iterates this IFS forward, computing new Xs and Ys and returning a copy of them
	 * @return a copy of a vector of the new X and Y values
	 */
	public Vec2 iterate()
	{
		int i = randy.nextInt(matrices.size());
		currentPosition = matrices.get(i).multiply(currentPosition).add(addends.get(i));
		return currentPosition.makeCopy();
	}
}
