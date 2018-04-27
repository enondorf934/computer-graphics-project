package dem.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import reusable.graphicsPrimitives.Vec2;

public class IntersectionOps
{

	/**
	 * Finds the intersection point between two lines, the first of which is spanned
	 * by l1p1 and l1p2 and the second of which is spanned by l2p1 and l2p2
	 * 
	 * @param l1p1
	 *            a point on the first line
	 * @param l1p2
	 *            a point on the first line different than l1p1
	 * @param l2p1
	 *            a point on the second line
	 * @param l2p2
	 *            a point on the second line different than l2p1
	 * @return the point that both lines intersect, or null if the lines are
	 *         parallel
	 */
	public static Vec2 getIntersectionPoint(Vec2 l1p1, Vec2 l1p2, Vec2 l2p1, Vec2 l2p2)
	{
		double A1 = l1p2.getY() - l1p1.getY();
		double B1 = l1p1.getX() - l1p2.getX();
		double C1 = A1 * l1p1.getX() + B1 * l1p1.getY();

		double A2 = l2p2.getY() - l2p1.getY();
		double B2 = l2p1.getX() - l2p2.getX();
		double C2 = A2 * l2p1.getX() + B2 * l2p1.getY();

		double det = A1 * B2 - A2 * B1;

		// If the lines are parallel
		if (LooseEquivalence.IsEqual(det, 0d))
		{
			return null;
		}
		// If not
		else
		{
			double x = (B2 * C1 - B1 * C2) / det;
			double y = (A1 * C2 - A2 * C1) / det;
			boolean online1 = ((Math.min(l1p1.getX(), l1p2.getX()) < x
					|| LooseEquivalence.IsEqual(Math.min(l1p1.getX(), l1p2.getX()), x))
					&& (Math.max(l1p1.getX(), l1p2.getX()) > x
							|| LooseEquivalence.IsEqual(Math.max(l1p1.getX(), l1p2.getX()), x))
					&& (Math.min(l1p1.getY(), l1p2.getY()) < y
							|| LooseEquivalence.IsEqual(Math.min(l1p1.getY(), l1p2.getY()), y))
					&& (Math.max(l1p1.getY(), l1p2.getY()) > y
							|| LooseEquivalence.IsEqual(Math.max(l1p1.getY(), l1p2.getY()), y)));
			boolean online2 = ((Math.min(l2p1.getX(), l2p2.getX()) < x
					|| LooseEquivalence.IsEqual(Math.min(l2p1.getX(), l2p2.getX()), x))
					&& (Math.max(l2p1.getX(), l2p2.getX()) > x
							|| LooseEquivalence.IsEqual(Math.max(l2p1.getX(), l2p2.getX()), x))
					&& (Math.min(l2p1.getY(), l2p2.getY()) < y
							|| LooseEquivalence.IsEqual(Math.min(l2p1.getY(), l2p2.getY()), y))
					&& (Math.max(l2p1.getY(), l2p2.getY()) > y
							|| LooseEquivalence.IsEqual(Math.max(l2p1.getY(), l2p2.getY()), y)));

			if (online1 && online2)
				return new Vec2(x, y);
		}
		return null;
	}

	/**
	 * Finds the intersection point between two line segments, the first of which is bounded and spanned
	 * by l1p1 and l1p2 and the second of which is bounded and spanned by l2p1 and l2p2
	 * @param s1p1 an endpoint of the first line segment
	 * @param s1p2 an endpoint of the secoud line segment (different than s1p1)
	 * @param s2p1 an endpoint of the first line segment 
	 * @param s2p2 an endpoint of the second line segment (different than s2p2)
	 * @return the intersection point of these two line segments, or null if the point is not on the given line segments or the line segments are parallel.
	 */
	public static Vec2 getSegmentIntersection(Vec2 s1p1, Vec2 s1p2, Vec2 s2p1, Vec2 s2p2)
	{
		//Gets the intersection of the lines spanned by the provided points
		Vec2 intersectionPoint = getIntersectionPoint(s1p1, s1p2, s2p1, s2p2);
		//If they're parallel
		if (intersectionPoint == null)
			return null;
		//otherwise, make sure it's on both given line segments
		else
		{
			boolean isOnSegments = intersectionPoint.isOnLineSegment(intersectionPoint, s1p1, s1p2)
					&& intersectionPoint.isOnLineSegment(intersectionPoint, s2p1, s2p2);
			if (isOnSegments)
				return intersectionPoint;
			else
				return null;
		}
	}

	/**
	 * Finds the intersection between the line spanned by the given two points and the polygon represented by the vertices in the given ArrayList
	 * @param l1p1 a point on the line 
	 * @param l1p2 a point on the line, different than l1p1
	 * @param poly the list of vertices of the polygon 
	 * @return An ArrayList of the points of intersection of the given line and the boundaries of the polygon represented by the given vertices.
	 */
	public static ArrayList<Vec2> getIntersection(Vec2 l1p1, Vec2 l1p2, ArrayList<Vec2> poly)
	{
		ArrayList<Vec2> intersectionPoints = new ArrayList<Vec2>();
		for (int i = 0; i < poly.size(); i++)
		{

			int next = (i + 1 == poly.size()) ? 0 : i + 1;

			Vec2 intersectionPoint = getIntersectionPoint(l1p1, l1p2, poly.get(i), poly.get(next));

			if (intersectionPoint != null)
				intersectionPoints.add(intersectionPoint);
		}

		return intersectionPoints;
	}

	/**
	 * Returns true if the given point is inside the polygon represented by the given vertices; false, otherwise.
	 * @param test the point to test for its in-ness with regard to the given polygon
	 * @param poly a set of points representing the vertices of the polygon in which to check for the presence of the given point 
	 * @return true if the given point is inside the polygon represented by the given vertices; false, otherwise.
	 */
	public static boolean isPointInsidePoly(Vec2 test, ArrayList<Vec2> poly)
	{
		int i;
		int j;
		boolean result = false;
		for (i = 0, j = poly.size() - 1; i < poly.size(); j = i++)
		{
			if ((poly.get(i).getY() > test.getY()) != (poly.get(j).getY() > test.getY())
					&& (test.getX() < (poly.get(j).getX() - poly.get(i).getX()) * (test.getY() - poly.get(i).getY())
							/ (poly.get(j).getY() - poly.get(i).getY()) + poly.get(i).getX()))
			{
				result = !result;
			}
		}
		return result;
	}

	/**
	 * Returns a list of points that mark the intersection between poly1 and poly2. For use with Extra Credit 4
	 * @param poly1
	 * @param poly2
	 * @return the list of points in the intersection of poly1 and poly2
	 */
	public static ArrayList<Vec2> getIntersection(ArrayList<Vec2> poly1, ArrayList<Vec2> poly2)
	{
		ArrayList<Vec2> clippedCorners = new ArrayList<Vec2>();

		// add the corners of poly1 which are inside poly2
		for (int i = 0; i < poly1.size(); i++)
		{
			if (isPointInsidePoly(poly1.get(i), poly2))
				clippedCorners.add(poly1.get(i));
		}

		// add the intersection points
		if (poly1.size() > 1 || poly2.size() > 1)
		{
			for (int i = 0, next = 1; i < poly1.size(); i++, next = (i + 1 == poly1.size()) ? 0 : i + 1)
			{
				clippedCorners.addAll(getIntersection(poly1.get(i), poly1.get(next), poly2));
			}
		}

		// add the corners of poly2 which are inside poly1
		for (int i = 0; i < poly2.size(); i++)
		{
			if (isPointInsidePoly(poly2.get(i), poly1))
				clippedCorners.add(poly2.get(i));
		}

		orderClockwise(clippedCorners);
		return clippedCorners;
	}

	/**
	 * Orders the vectors clockwise about their centroid
	 * @param vecs the vectors to be ordered clockwise about their centroid
	 */
	public static void orderClockwise(ArrayList<Vec2> vecs)
	{
		// Find center (mean)
		double meanX = 0;
		double meanY = 0;
		for (Vec2 p : vecs)
		{
			meanX += p.getX();
			meanY += p.getY();
		}

		meanX /= vecs.size();
		meanY /= vecs.size();

		// Sort vecs

		class ClockwiseComparator implements Comparator<Vec2>
		{
			private double meanX;
			private double meanY;

			ClockwiseComparator(double meanX, double meanY)
			{
				this.meanX = meanX;
				this.meanY = meanY;
			}

			@Override
			// Compares based on angle
			public int compare(Vec2 p1, Vec2 p2)
			{
				double p1Val = Math.atan2(p1.getY() - meanY, p1.getX() - meanX);
				double p2Val = Math.atan2(p2.getY() - meanY, p2.getX() - meanX);

				if (p1Val < p2Val)
					return -1;
				else if (p2Val < p1Val)
					return 1;
				else
					return 0;
			}
		}
		Collections.sort(vecs, new ClockwiseComparator(meanX, meanY));
	}

}
