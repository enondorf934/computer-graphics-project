//package IFS;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import dem.reusable.Tuple.Tuple2;
//import dem.reusable.graphicsPrimitives.Mat2;
//import dem.reusable.graphicsPrimitives.Vec2;
//
///**
// * A class for holding information for an iterated function system with iterations of the form x2 = Ax1 + b
// * @author DEMcKnight
// */
//public class WavingIFS
//{
//	Random randy = new Random();
//	List<Mat2> As;
//	List<Mat2> Bs;
//	List<Vec2> vs;
//	List<Vec2> ws;
//
//	
//	Vec2 currentPosition = new Vec2(0,0);
//	
//	/**
//	 * Constructs a new Iterated Function System that uses the given matrices and addends to transform the current coordinates each frame.
//	 * @param matrices
//	 * @param addends
//	 */
//	public WavingIFS(List<Mat2> matrices, List<Vec2> addends)
//	{
//		this.As = matrices;
//		this.Bs = matrices;
//		this.vs = addends;
//		this.ws = addends;
//	}
//	
//	/**
//	 * Iterates this IFS forward, computing new Xs and Ys and returning a copy of them
//	 * @return a copy of a vector of the new X and Y values
//	 */
//	public Vec2 iterate()
//	{
//		int i = randy.nextInt(As.size());
//		currentPosition = matrices.get(i).multiply(currentPosition).add(addends.get(i));
//		return currentPosition.makeCopy();
//	}
//}
