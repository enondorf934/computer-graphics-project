package drawables;

/**
 * Objects that implement this interface have a distinct shape. This will be useful for ray-tracing.
 * @author DEMcKnight
 */
public interface Shape
{
	/**
	 * Checks to see if this shape contains the given point.
	 * @param x	The x-coordinate of the point to be checked.
	 * @param y	The y=coordinate of the point to be checked.
	 * @return	True if this shape contains the point specified by x and y.
	 */
	public boolean containsPoint(double x, double y);
}
