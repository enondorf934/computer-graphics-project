package reusable.graphicsPrimitives;

/**
 * A 2x2 matrix class for use with Vec2s
 * @author DEMcKnight
 */
public class Mat2
{
	/**The top-left element of the matrix*/
	double a11;
	/**The top-right element of the matrix*/
	double a12;
	/**The bottom-left element of the matrix*/
	double a21;
	/**The bottom-right element of the matrix*/
	double a22;
	
	/**
	 * Instantiates a new 2x2 matrix
	 * @param a11 The top-left element of the matrix
	 * @param a12 The top-right element of the matrix
	 * @param a21 The bottom-left element of the matrix
	 * @param a22 The bottom-right element of the matrix
	 */
	public Mat2(double a11, double a12, double a21, double a22)
	{
		this.a11=a11;
		this.a12=a12;
		this.a21=a21;
		this.a22=a22;
	}
	
	/**
	 * Multiplies this 2x2 matrix by the given vector
	 * @param Multiplicand the vector to multiply this matrix by
	 * @return The calling matrix times the given vector
	 */
	public Vec2 multiply(Vec2 multiplicand)
	{
		double e1 = a11 * multiplicand.x + a12 * multiplicand.y;
		double e2 = a21 * multiplicand.x + a22 * multiplicand.y;
		return new Vec2(e1, e2);
	}
	

	/**
	 * Multiplies the given matrix by the given vector
	 * @param multiplier The matrix to multiply by the given multiplicand vector
	 * @param multiplicand The vector by which the given multiplier matrix is multiplied
	 * @return The product of the given matrix multiplier and the given vector multiplicand
	 */
	public static Vec2 multiply(Mat2 multiplier, Vec2 multiplicand)
	{
		double e1 = multiplier.a11 * multiplicand.x + multiplier.a12 * multiplicand.y;
		double e2 = multiplier.a21 * multiplicand.x + multiplier.a22 * multiplicand.y;
		return new Vec2(e1, e2);
	}
	
	/**
	 * Matrix-multiplies The two given factors together
	 * @param multiplier The first factor
	 * @param multiplicand The second factor
	 * @return The product of the two given factors
	 */
	public static Mat2 multiply(Mat2 multiplier, Mat2 multiplicand)
	{
		double a11 = multiplier.a11 * multiplicand.a11 + multiplier.a12 * multiplicand.a21;
		double a12 = multiplier.a11 * multiplicand.a12 + multiplier.a12 * multiplicand.a22;
		double a21 = multiplier.a21 * multiplicand.a11 + multiplier.a22 * multiplicand.a21;
		double a22 = multiplier.a21 * multiplicand.a12 + multiplier.a22 * multiplicand.a22;
		return new Mat2(a11, a12, a21, a22);
	}
	
	public String toString()
	{
		return "[[" + a11 + ", " + a12 + "], [" + a21 + ", " + a22 + "]]";
	}
}
