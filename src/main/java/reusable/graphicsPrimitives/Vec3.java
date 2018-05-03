package reusable.graphicsPrimitives;
/**
 * Vec3 is a class for storing (x,y,z) information. Supports operations with other vectors.
 * @author DEMcKnight
 */
public class Vec3
{

	//Static variables
	public final static Vec3 Zero = new Vec3(0,0,0);		//!<(0,0,0)
	public final static Vec3 Left = new Vec3(-1,0,0); 	//!<(-1, 0, 0)
	public final static Vec3 Right = new Vec3(1,0,0); 	//!<(1, 0, 0)
	public final static Vec3 Up = new Vec3(0,1,0); 		//!<(0, 1, 0)
	public final static Vec3 Down = new Vec3(0,-1,0); 	//!<(0, -1, 0)
	public final static Vec3 Forward = new Vec3(0,0,1); 	//!<(0, 0, 1)
	public final static Vec3 Backward = new Vec3(0,0,-1); //!<(0, 0, -1)
	public final static Vec3 Unit = new Vec3(1,1,1); 		//!<(1, 1, 1)
	
	//Instance variables
	protected double x;
	protected double y;
	protected double z;

	//Constructor(s)
	/**
	 * Constructs a Vec3 object with the given x and y parameters. Sets z to 0.
	 * @param x	The x-coordinate.
	 * @param y The y-coordinate.
	 */
	public Vec3(double x, double y)
	{
		this(x, y, 0);
	}
	
	/**
	 * Constructs a Vec3 object with the given x, y, and z parameters.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @param z The z-coordinate.
	 */
	public Vec3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	//Getters
	/**
	 * Getter for x
	 * @return x
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * Getter for y
	 * @return y
	 */
	public  double getY()
	{
		return y;
	}
	
	/**
	 * Getter for z
	 * @return z
	 */
	public  double getZ()
	{
		return z;
	}
	
	//Setters
	/**
	 * Setter for x
	 * @param x the value to set this vector's x-value to
	 */
	public void setX(double x)
	{
		this.x=x;
	}
	/**
	 * Setter for y
	 * @param y the value to set this vector's y-value to
	 */
	public void setY(double y)
	{
		this.y=y;
	}
	/**
	 * Setter for z
	 * @param z the value to set this vector's z-value to
	 */
	public void setZ(double z)
	{
		this.z=z;
	}
	
	/**
	 * Copies the contents of otherVec into this vector, overwriting values already here
	 * @param otherVec The vector to copy contents from.
	 */
	public void copy(Vec3 otherVec)
	{
		this.x=otherVec.x;
		this.y=otherVec.y;
		this.z=otherVec.z;
	}

	//Unary Operators
	/**
	 * Returns a vector with its x, y, and z parameters holding the additive inverses of this vector's x, y, and z values
	 * @return a vector with x, y, and z parameters additive inverses of this vector's.
	 */
	public Vec3 additiveInverse ()
	{
		return new Vec3(-x, -y, -z);
	}

	//Binary operators
	
	//Addition operators.
	/**
	 * Addition. Returns the sum of this vector and another vector.
	 * @param addend
	 * @return the sum of this vector and the given addend.
	 */
	public Vec3 add (Vec3 addend)
	{
		return new Vec3(x + addend.x, y + addend.y, z + addend.z);
	}

	/**
	 * Addition assignment. Finds the sum of this vector and the given addend and assigns it to this vector.
	 * @param addend
	 * @return A reference to this vector.
	 */
	public Vec3 addAssignment (Vec3 addend)
	{
		x += addend.x;
		y += addend.y;
		z += addend.z;
		return this;
	}

	//Subtraction operators.

	/**
	 * Subtraction. Returns the difference of this vector and the given subtrahend.
	 * @param subtrahend
	 * @return the difference of this vector and the given subtrahend.
	 */
	public Vec3 subtract (Vec3 subtrahend)
	{
		return new Vec3(x - subtrahend.x, y - subtrahend.y, z - subtrahend.z);
	}

	/**
	 * Subtraction assignment. Finds the difference of this vector and the given subtrahend and assigns it to this vector.
	 * @param subtrahend
	 * @return A reference to this vector.
	 */
	public Vec3 subtractAssignment (Vec3 subtrahend)
	{
		x -= subtrahend.x;
		y -= subtrahend.y;
		z -= subtrahend.z;
		return this;
	}

	//Multiplication operators

	/**
	 * Scalar multiplication. Returns the product of this vector and the given scalar.
	 * @param scalar
	 * @return The product of this vector and the given scalar.
	 */
	public Vec3 multiply (double scalar)
	{
		return new Vec3(x * scalar, y * scalar, z * scalar);
	}

	/**
	 * Scalar multiplication assignment. Finds the product of this vector and the given scalar and assigns it to this vector.
	 * @param scalar
	 * @return A reference to this vector.
	 */
	public Vec3 multiplyAssignment (double scalar)
	{
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}

	//Division operators
	
	/**
	 * Scalar division. Returns the quotient of this vector and the given scalar.
	 * @param scalar
	 * @return The quotient of this vector and the given scalar.
	 */
	public Vec3 divide (double scalar)
	{
		return new Vec3(x / scalar, y / scalar, z / scalar);
	}

	/**
	 * Scalar division assignment. Finds the quotient of this vector and the given scalar and assigns it to this vector.
	 * @param scalar
	 * @return A reference to this vector.
	 */
	public Vec3 divideAssignment (double scalar)
	{
		x /= scalar;
		y /= scalar;
		z /= scalar;
		return this;
	}

	//Boolean operators

	/**
	 * Equivalence operation. Returns true if the x, y, and z values of the given vector and this one are equivalent; false, otherwise.
	 * @param vector
	 * @return True if the x, y, and z values of the given vector and this one are equivalent; false, otherwise.
	 */
	public boolean equals(Vec3 vector)
	{
		if (this.x == vector.getX() && this.y == vector.getY() && this.z == vector.getZ())
		{
			return true;
		}
		return false;
	}

	/**
	 * Inequality operator. Returns false if the x, y, and z values of the given vector and this one are equivalent; true, otherwise.
	 * @param vector
	 * @return False if the x, y, and z values of the given vector and this one are equivalent; true, otherwise.
	 */
	public boolean isNotEqual(Vec3 vector)
	{
		return !(this.equals(vector));
	}

	/**
	 * ToString operation. Converts this vector into the string representation "(x, y, z)".
	 */
	public String toString()
	{
		return "(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")";
	}

	//Other instance methods

	/**
	 * Dot product. Returns the dot product of this vector and the given multiplicand.
	 * @param multiplicand
	 * @return The dot product of this vector and the given multiplicand.
	 */
	public  double dot(Vec3 multiplicand)
	{
		return x * multiplicand.x + y * multiplicand.y + z * multiplicand.z;
	}

	/**
	 * Cross product. Returns the cross product of this vector and the given multiplicand.
	 * @param multiplicand
	 * @return The cross product of this vector and the given multiplicand.
	 */
	public  Vec3 cross(Vec3 multiplicand)
	{
		 double thisx = x;
		 double thisy = y;
		 double thisz = z;
		return new Vec3(thisy * multiplicand.getZ() - multiplicand.getY() * thisz, thisz * multiplicand.getX() - multiplicand.getZ() * thisx, thisx * multiplicand.getY() - multiplicand.getX() * thisy); //z value - y value - x value
	}

	/**
	 * Element-wise multiplication. Multiplies each of this vector's x, y, and z values by the respective values in the multiplicand and returns a Vector3 with those values as its x, y, and z information.
	 * @param multiplicand
	 * @return The element-wise product of this vector and the multiplicand.
	 */
	public  Vec3 elementwiseMult(Vec3 multiplicand)
	{
		return new Vec3(multiplicand.getX(), multiplicand.getY(), multiplicand.getZ());
	}

	/**
	 * Element-wise division. Divides each of this vector's x, y, and z values by the respective values in the divisor and returns a Vector3 with those values as its x, y, and z information.
	 * @param multiplicand
	 * @param divisor
	 * @return The element-wise quotient of this vector and the divisor.
	 */
	public  Vec3 elementwiseDiv(Vec3 divisor)
	{
		return new Vec3(divisor.getX(), divisor.getY(), divisor.getZ());
	}

	/**
	 * Returns the length (magnitude) of this vector.
	 * @return The length (magnitude) of this vector.
	 */
	public  double magnitude()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Returns a normalized version of this vector.
	 * @return A normalized version of this vector.
	 */
	public  Vec3 normalized()
	{
		return (this).divide(this.magnitude());
	}

	/**
	 * Normalizes this vector.
	 * @return A reference to this vector.
	 */
	public  Vec3 normalize()
	{
		copy(this.normalized());
		return this;	
	}

	/**
	 * Returns the distance to another vector's head as if their tails were centered at the origin.
	 * @param otherVec
	 * @return the distance between the given vector's head and otherVec's head as if their tails were centered at the origin.
	 */
	public  double distanceTo(Vec3 otherVec)
	{
		return (this.subtract(otherVec)).magnitude();
	}

	/**
	 * Returns the (signed) angle from this vector to another vector as if their tails were touching.
	 * @param otherVec
	 * @return the (signed) angle from this vector to otherVec as if their tails were touching.
	 */
	public  double angleTo(Vec3 otherVec)
	{
		return Math.acos(dot(otherVec, this) / (this.magnitude() * otherVec.magnitude()));
	}

	//Static versions of some of the above methods.

	/**
	 * Vector addition. Returns the sum of the given augend and addend.
	 * @param augend
	 * @param addend
	 * @return the sum of the given augened and addend.
	 */
	public static Vec3 add(Vec3 augend, Vec3 addend)
	{
		return augend.add(addend);
	}

	/**
	 * Vector subtraction. Returns the difference of the given minuend and subtrahend.
	 * @param minuend
	 * @param subtrahend
	 * @return The difference of the given minuend and subtrahend.
	 */
	public static Vec3 subtract(Vec3 minuend, Vec3 subtrahend)
	{
		return minuend.subtract(subtrahend);
	}

	/**
	 * Vector dot product. Returns the dot product of the given multiplier and multiplicand.
	 * @param multiplier
	 * @param multiplicand
	 * @return The dot product of the given multiplier and multiplicand.
	 */
	public static double dot(Vec3 multiplier, Vec3 multiplicand)
	{
		return multiplier.dot(multiplicand);
	}

	/**
	 * Vector cross product. Returns the cross product of the given multiplier and multiplicand.
	 * @param multiplier
	 * @param multiplicand
	 * @return The cross product of the given multiplier and multiplicand.
	 */
	public static Vec3 cross(Vec3 multiplier, Vec3 multiplicand)
	{
		return multiplier.cross(multiplicand);
	}

	/**
	 * Element-wise multiplication of vectors. Multiplies each of x, y, and z in the multiplier 
	 * by the corresponding counterparts in the multiplicand and returns a vector consisting of those products as its x, y, and z values.
	 * @param multiplier
	 * @param multiplicand
	 * @return The element-wise product of the given multiplier and multiplicand.
	 */
	public static Vec3 elementwiseMult(Vec3 multiplier, Vec3 multiplicand)
	{
		return multiplier.elementwiseMult(multiplicand);
	}

	/**
	 * Element-wise division of vectors. Divides each of x, y, and z in the dividend
	 * by the corresponding counterparts in the divisor and returns a vector consisting of those quotients as its x, y, and z values.
	 * @param multiplier
	 * @param multiplicand
	 * @return The element-wise quotient of the given dividend and divisor.
	 */
	public static Vec3 elementwiseDiv(Vec3 dividend, Vec3 divisor)
	{
		return dividend.elementwiseDiv(divisor);
	}

	/**
	 * Returns the distance between two vectors as if their tails were touching.
	 * @param firstVector
	 * @param secondVector
	 * @return the distance between firstVector and secondVector as if their tails were touching.
	 */
	public static double distanceBetween(Vec3 firstVector, Vec3 secondVector)
	{
		return firstVector.distanceTo(secondVector);
	}

	/**
	 * Returns the (signed) angle between two vectors as if their tails were touching.
	 * @param firstVector
	 * @param secondVector
	 * @return The (signed) angle from the firstVector to the secondVector as if their tails were touching.
	 */
	public static double angleBetween(Vec3 firstVector, Vec3 secondVector)
	{
		return firstVector.angleTo(secondVector);
	}

}