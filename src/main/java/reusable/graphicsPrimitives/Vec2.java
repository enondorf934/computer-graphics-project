package reusable.graphicsPrimitives;
import java.util.ArrayList;

import geometry.LooseEquivalence;
import reusable.graphicsPrimitives.Vec3;

/*!
\author David "Dawn" Estes McKnight
\date 21 February 2017
\version 3`,y) information. Supports operations with other vectors and with matrices.
*/

/**
 * Vector is a class for storing (x,y,z) coordinate information. Supports operations with other vectors.
 * @author DEMcKnight
 */
public class Vec2
{
	
	//Static variables 
	public static final Vec2 Zero = new Vec2(0,0); 	//(0,0)
	public static final Vec2 Left = new Vec2(-1,0); 	//(-1, 0)
	public static final Vec2 Right= new Vec2(1,0); 	//(1, 0)
	public static final Vec2 Up  	= new Vec2(0,1); 	//(0, 1)
	public static final Vec2 Down = new Vec2(0,-1); 	//(0, -1)
	public static final Vec2 Unit = new Vec2(1, 1); 	//(1, 1)	
	
	//Instance variables
	public double x;
	public double y;
	
	//Constructor(s)
	
	/**
	 * Constructs a Vec2 object with the given x and y parameters.
	 * @param x	The x-coordinate.
	 * @param y The y-coordinate.
	 */
	public Vec2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Constructs a Vec2 object from an array of coordinates. If there are not enough coordinates, assigns values of 0.
	 * @param coords The coordinates
	 */
	public Vec2(double[] coords)
	{
		if (coords.length<2)
		{
			if (coords.length<1)
				x=0;
			y=0;
		}
		
		this.x=coords[0];		
		this.y=coords[0];
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
	public double getY()
	{
		return y;
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
	 * Copies the contents of otherVec into this vector, overwriting values already here
	 * @param otherVec The vector to copy contents from.
	 */
	public void copy(Vec2 otherVec)
	{
		this.x=otherVec.x;
		this.y=otherVec.y;
	}
	
	/**
	 * Returns a deep copy of this vector
	 * @return a deep copy of this vector
	 */
	public Vec2 makeCopy()
	{
		return new Vec2(x, y);
	}
	

	//Unary Operators

	/**
	 * Additive inversion. Returns a vector with its x and y parameters holding the additive inverses of this vector's x and y values
	 * @return a vector with x and y values additive inverses of this vector's.
	 */
	public Vec2 additiveInverse ()
	{
		return new Vec2(-x, -y);
	}
	
	/**
	 * Returns a rotated form of the original vector given the angle to rotate by in radians.
	 * @param radians
	 * @return A rotated form of the original vector
	 */
	public Vec2 rotated(double radians)
	{	
		double newX = Math.cos(radians)*x-Math.sin(radians)*y;
		double newY = Math.sin(radians)*x+Math.cos(radians)*y;
		return new Vec2(newX, newY);
	}

	/**
	 * Type conversion. Returns a Vec3 version of this Vec2 object.
	 * @return A Vec3 version of this Vec2 object with equivalent x and y values and z set to 0.
	 */
	public Vec3 toVec3()
	{
		return new Vec3(x, y);
	}

	//Binary operators

	/**
	 * Addition. Returns the sum of this vector and another vector.
	 * @param addend
	 * @return the sum of this vector and the given addend.
	 */
	public Vec2 add (Vec2 addend)
	{
		return new Vec2(x + addend.x, y + addend.y);
	}

	/**
	 * Addition assignment. Finds the sum of this vector and the given addend and assigns it to this vector.
	 * @param addend
	 * @return A reference to this vector.
	 */
	public Vec2 addAssignment (Vec2 addend)
	{
		x += addend.x;
		y += addend.y;
		return this;
	}

	//Subtraction operators

	/**
	 * Subtraction. Returns the difference of this vector and the given subtrahend.
	 * @param subtrahend
	 * @return the difference of this vector and the given subtrahend.
	 */
	public Vec2 subtract (Vec2 subtrahend)
	{
		return new Vec2(x - subtrahend.x, y - subtrahend.y);
	}

	/**
	 * Subtraction assignment. Finds the difference of this vector and the given subtrahend and assigns it to this vector.
	 * @param subtrahend
	 * @return A reference to this vector.
	 */
	public Vec2 subtractAssignment (Vec2 subtrahend)
	{
		x -= subtrahend.x;
		y -= subtrahend.y;
		return this;
	}

	//Multiplication operators

	/**
	 * Scalar multiplication. Returns the product of this vector and the given scalar.
	 * @param scalar
	 * @return The product of this vector and the given scalar.
	 */
	public Vec2 multiply (double scalar)
	{
		return new Vec2(x * scalar, y * scalar);
	}

	/**
	 * Scalar multiplication assignment. Finds the product of this vector and the given scalar and assigns it to this vector.
	 * @param scalar
	 * @return A reference to this vector.
	 */
	public Vec2 multiplyAssignment (double scalar)
	{
		x *= scalar;
		y *= scalar;
		return this;
	}

	//Division operators.

	/**
	 * Scalar division. Returns the quotient of this vector and the given scalar.
	 * @param scalar
	 * @return The quotient of this vector and the given scalar.
	 */
	public Vec2 divide (double scalar)
	{
		return new Vec2(x / scalar, y / scalar);
	}

	/**
	 * Scalar division assignment. Finds the quotient of this vector and the given scalar and assigns it to this vector.
	 * @param scalar
	 * @return A reference to this vector.
	 */
	public Vec2 divideAssignment (double scalar)
	{
		x /= scalar;
		y /= scalar;
		return this;
	}

	//Boolean operators

	/**
	 * Equivalence operation. Returns true if the x and y values of the given vector and this one are equivalent; false, otherwise.
	 * @param vector
	 * @return True if the x and y values of the given vector and this one are equivalent; false, otherwise.
	 */
	public boolean equals(Vec2 vector)
	{
		if (this.x == vector.getX() && this.y == vector.getY())
		{
			return true;
		}
		return false;
	}

	/**
	 * Inequality operator. Returns false if the x and y values of the given vector and this one are equivalent; true, otherwise.
	 * @param vector
	 * @return False if the x and y values of the given vector and this one are equivalent; true, otherwise.
	 */
	public boolean isNotEqual(Vec2 vector)
	{
		return !(this.equals(vector));
	}

	//Other instance methods.
	
	/**
	 * ToString operation. Converts this vector into the string representation "(x, y, z)".
	 */	
	public String toString()
	{
		return "(" + this.getX() + ", " + this.getY() + ")";
	}
	
	/**
	 * Returns the angle from the positive x-axis of this vector
	 * @return the angle from the positive x-axis of this vector
	 */
	public double getAngle()
	{
		return Math.atan2(y, x);
	}

	/**
	 * Dot product. Returns the dot product of this vector and the given multiplicand.
	 * @param multiplicand
	 * @return The dot product of this vector and the given multiplicand.
	 */	
	public double dot(Vec2 multiplicand) 
	{
		return x * multiplicand.x + y * multiplicand.y;
	}

	/**
	 * Element-wise multiplication. Multiplies each of this vector's x and y values by the respective values in the multiplicand and returns a Vector2 with those values as its x and y information.
	 * @param multiplicand
	 * @return The element-wise product of this vector and the multiplicand.
	 */
	public Vec2 elementwiseMult(Vec2 multiplicand)
	{
		return new Vec2(multiplicand.getX(), multiplicand.getY());
	}

	/**
	 * Element-wise division. Divides each of this vector's x and y values by the respective values in the divisor and returns a Vector2 with those values as its x and y information.
	 * @param multiplicand
	 * @param divisor
	 * @return The element-wise quotient of this vector and the divisor.
	 */	
	public Vec2 elementwiseDiv(Vec2 divisor)
	{
		return new Vec2(divisor.getX(), divisor.getY());
	}

	/**
	 * Returns the length (magnitude) of this vector.
	 * @return The length (magnitude) of this vector.
	 */	 
	public double magnitude()
	{
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * Returns a normalized version of this vector.
	 * @return A normalized version of this vector.
	 */
	public Vec2 normalized()
	{
		return (this).divide(this.magnitude());
	}
	
	/**
	 * Normalizes this vector.
	 * @return A reference to this vector.
	 */
	public Vec2 normalize()
	{
		copy(this.normalized());
		return this;
	}

	/**
	 * Returns the distance to another vector's head as if their tails were centered at the origin.
	 * @param otherVec
	 * @return the distance between the given vector's head and otherVec's head as if their tails were centered at the origin.
	 */
	public double distanceTo(Vec2 otherVec)
	{
		return (this.subtract(otherVec)).magnitude();
	}
	
	/**
	 * Returns the distance from the point represented by this vector to the line spanned by the given parameter vectors
	 * @param point1 a point on the line to which we're finding the distance
	 * @param point2 another (distinct) point on the line to which we're finding the distance
	 * @return the distance from the point represented by this vector to the line spanned by the given parameter vectors
	 */
	public double distanceToLine(Vec2 point1, Vec2 point2)
	{
		Vec2 p12 = point2.subtract(point1);
		Vec2 p01 = point1.subtract(this);
		double det = p12.getX()*p01.getY() - p12.getY() * p01.getX();
		det/=p12.getX();
		return det<0? - det : det;
	}


	/**
	 * Returns the (signed) angle from this vector to another vector as if their tails were touching.
	 * @param otherVec
	 * @return the (signed) angle from this vector to otherVec as if their tails were touching.
	 */
	public double angleTo(Vec2 otherVec)
	{
		return Math.atan2(otherVec.y, otherVec.x) - Math.atan2(y,x);
	}
	
	//Static versions of some of the above methods.

	/**
	 * Vector addition. Returns the sum of the given augend and addend.
	 * @param augend
	 * @param addend
	 * @return the sum of the given augend and addend.
	 */
	public static Vec2 add(Vec2 augend, Vec2 addend)
	{
		return augend.add(addend);
	}

	/**
	 * Vector subtraction. Returns the difference of the given minuend and subtrahend.
	 * @param minuend
	 * @param subtrahend
	 * @return The difference of the given minuend and subtrahend.
	 */
	public static Vec2 subtract(Vec2 minuend, Vec2 subtrahend)
	{
		return minuend.subtract(subtrahend);
	}

	/**
	 * Vector dot product. Returns the dot product of the given multiplier and multiplicand.
	 * @param multiplier
	 * @param multiplicand
	 * @return The dot product of the given multiplier and multiplicand.
	 */
	public static double dot(Vec2 multiplier, Vec2 multiplicand)
	{
		return multiplier.dot(multiplicand);
	}

	/**
	 * Element-wise multiplication of vectors. Multiplies each of x, and y in the multiplier by
	 * the corresponding counterparts in the multiplicand and returns a vector consisting of those products as its x and y values.
	 * @param multiplier
	 * @param multiplicand
	 * @return The element-wise product of the given multiplier and multiplicand.
	 */
	public static Vec2 elementwiseMult(Vec2 multiplier, Vec2 multiplicand)
	{
		return multiplier.elementwiseMult(multiplicand);
	}

	/**
	 * Element-wise division of vectors. Divides each of x and y in the dividend by
	 * the corresponding counterparts in the divisor and returns a vector consisting of those quotients as its x and y values.
	 * @param multiplier
	 * @param multiplicand
	 * @return The element-wise quotient of the given dividend and divisor.
	 */
	public static Vec2 elementwiseDiv(Vec2 dividend, Vec2 divisor)
	{
		return dividend.elementwiseDiv(divisor);
	}

	/**
	 * Returns the distance between two vectors as if their tails were touching.
	 * @param firstVector
	 * @param secondVector
	 * @return the distance between firstVector and secondVector as if their tails were touching.
	 */
	public static double distanceBetween(Vec2 firstVector, Vec2 secondVector)
	{
		return firstVector.distanceTo(secondVector);
	}
	

	/**
	 * Returns the (signed) angle between two vectors as if their tails were touching.
	 * @param firstVector
	 * @param secondVector
	 * @return The (signed) angle from the firstVector to the secondVector as if their tails were touching.
	 */
	public static double angleBetween(Vec2 firstVector, Vec2 secondVector)
	{
		return firstVector.angleTo(secondVector);
	}

	/**
	 * Returns the closest point on the line spanned by l1 and l2 to the given point
	 * @param point the point to which we're finding the closest point on the line
	 * @param l1 one end of the line segment
	 * @param l2 the other end of the line segment
	 * @return the closest point on the line spanned by l1 and l2 to the given point
	 */
	public static Vec2 closestPointOnLine(Vec2 point, Vec2 l1, Vec2 l2)
	{
		
		//vector from l1 to l2
		Vec2 lineVector = l2.subtract(l1);
		
		//vector from l1 to point
		Vec2 segEndToPoint = point.subtract(l1);
		
		//get scalar projection amount
		double scalProj = Vec2.dot(segEndToPoint, lineVector)/lineVector.magnitude();
		
		//get ortho projection vector
		Vec2 orthogonalProj = lineVector.normalized().multiply(scalProj);
		
		//add projection vector to original point
		return l1.add(orthogonalProj);
	}
	
	
	/**
	 * Returns true if the given point is on the line segment spanned by l1 and l2; false, otherwise.
	 * @param point the point being tested to see if it's on the given line segment
	 * @param l1 one end of the line segment
	 * @param l2 one end of the line segment
	 * @return true if the given point is on the line segment spanned by l1 and l2; false, otherwise.
	 */
	public static boolean isOnLineSegment(Vec2 point, Vec2 l1, Vec2 l2)
	{
		//Get distances from point to segement ends
		double distTo1 = point.distanceTo(l1);
		double distTo2 = point.distanceTo(l2);
			
		//If the sum of those distances isn't the length of the line segment
		if (!LooseEquivalence.IsEqual(distTo1+distTo2, l1.distanceTo(l2)))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Finds the closest point to the given point on the line segment from l1 to l2 
	 * @param point
	 * @param l1 the first bound of the line segment
	 * @param l2 the second bound of the line segment
	 * @return the closest point to the given point on the line segment from l1 to l2 
	 */
	public static Vec2 closestPointOnLineSegment(Vec2 point, Vec2 l1, Vec2 l2)
	{
		Vec2 supposedClosestPoint = closestPointOnLine(point, l1, l2);
		
		//Check to see if point is not on line segment
		double distTo1 = supposedClosestPoint.distanceTo(l1);
		double distTo2 = supposedClosestPoint.distanceTo(l2);
		
		if (!LooseEquivalence.IsEqual(distTo1+distTo2, l1.distanceTo(l2)))
		{
			return distTo1<distTo2 ? l1.makeCopy() : l2.makeCopy();
		}
			
		return supposedClosestPoint;
	}
	
	/**
	 * Finds the closest point on the polygon from the given point
	 * @param point the point to which the closest point on the polygon is being found
	 * @param corners the vertices of the polygon	
	 * @return the closest point on the polygon from the given point
	 */
	public static Vec2 closestPointOnPolygon(Vec2 point, ArrayList<Vec2> corners)
	{
		double closestDist = Double.POSITIVE_INFINITY;
		Vec2 closestPoint = null;
		for (int i=0; i<corners.size(); i++)
		{
			Vec2 closestPointToSeg = closestPointOnLineSegment(point, corners.get(i), corners.get((i+1)%corners.size()));
			double distance = point.distanceTo(closestPointToSeg);
			if (distance<closestDist)
			{
				closestPoint = closestPointToSeg;
				closestDist = distance;
			}
		}
		return closestPoint;
	}
}