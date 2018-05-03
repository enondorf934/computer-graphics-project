package reusable.graphicsPrimitives;

/**
 * Class of methods for helping out with vector objects
 * @author DEMcKnight
 *
 */
public class VecHelpers
{

	/**
	 * Vector cross product
	 * @param a
	 * @param b
	 * @return
	 */
	public static Vec3 cross(Vec3 a, Vec3 b)
	{
		double i = a.getY()*b.getZ() - a.getZ()*b.getY();
		double j = a.getZ()*b.getX() - a.getX()*b.getZ();
		double k = a.getX()*b.getY() - a.getY()*b.getX();	
		return (new Vec3(i, j, k));
	}
	
	/**
	 * Performs a cross product operation as if a and be were vectors in three dimensions attached to the xy plane
	 * @param a
	 * @param b
	 * @return
	 */
	public static Vec3 sortaCross(Vec2 a, Vec2 b)
	{
		Vec3 firstVec = new Vec3(a.getX(), a.getY(), 0);
		Vec3 secondVec = new Vec3(b.getX(), b.getY(), 0);
				
		return cross(firstVec, secondVec);
	}
}
