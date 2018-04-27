package IFS;

import java.util.List;
import java.util.Random;

import reusable.graphicsPrimitives.Mat2;
import reusable.graphicsPrimitives.Vec2;

/**
 * A class for holding information for an iterated function system with iterations of the form x2 = Ax1 + b
 * 
 * @author DEMcKnight
 */
public class IFS
{
	/**
	 * Random number generator
	 */
	Random randy = new Random();
	/**
	 * The matrix transformation to apply to currentPosition when iterate() is called prior to adding the addend
	 */
	List<Mat2> matrices;
	/**
	 * The translation transformation to apply to currentPosition when iterate() is called after applying the matrix transformation
	 */
	List<Vec2> addends;
	/**
	 * The current position of this IFS object. It will be transformed when iterate() is called.
	 */
	Vec2 currentPosition = new Vec2(0, 0);

	/**
	 * Constructs a new Iterated Function System that uses the given matrices and addends to transform the current coordinates each frame.
	 * 
	 * @param matrices The transformations to apply prior to translating by the corresponding addend when iterate() is called.
	 * @param addends The translation transformations to apply after applying the corresponding matrix transformation when iterate() is called.
	 */
	public IFS(List<Mat2> matrices, List<Vec2> addends)
	{
		this.matrices = matrices;
		this.addends = addends;
	}

	/**
	 * Iterates this IFS forward, applying the transformation to currentPosition and returning a deep copy of it
	 * 
	 * @return a copy of the updated currentPosition vector
	 */
	public Vec2 iterate()
	{
		//Select a transformation to apply
		int i = randy.nextInt(matrices.size());
		//Apply the transformation
		currentPosition = matrices.get(i).multiply(currentPosition).add(addends.get(i));
		//Return a copy of the current position
		return currentPosition.makeCopy();
	}
}
