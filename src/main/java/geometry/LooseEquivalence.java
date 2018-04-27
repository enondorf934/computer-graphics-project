package geometry;

/**
 * A class for checking for equivalence in a world where doubles and floats don't play nice
 */
public class LooseEquivalence
{
	/**
	 * How much we don't care about limiting floating point errors
	 */
    private final static double EqualityTolerance = 0.00001d;
 
    /**
     * True if the absolute difference between d1 and d2 is less than EqualityTolerance; false, otherwise
     * @param d1 the first double to be compared
     * @param d2 the second double to be compared
     * @return true if the absolute difference between d1 and d2 is less than EqualityTolerance; false, otherwise
     */
    public static boolean IsEqual(double d1, double d2)
    {
        return Math.abs(d1-d2) <= EqualityTolerance;
    }
}
