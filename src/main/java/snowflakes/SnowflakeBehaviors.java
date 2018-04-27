package snowflakes;

import java.util.function.*;

import reusable.graphicsPrimitives.Vec2;

/**
 * Functions for attaching to Snowflakes to define the Snowflakes behavior when its Update function is called
 * @author DEMcKnight
 */
public class SnowflakeBehaviors
{
		
	/**
	 * Retrieves a falling behavior for use with a Snowflake
	 * @param velocity The rate at which the Snowflake should fall (pixels/second)
	 * @return A function that defines falling behavior for a Snowflake at the specified velocity
	 */
	public static BiConsumer<Double, Snowflake> getStraightFallBehavior(double velocity)
	{
		return (Double timestep, Snowflake s)->
		{
			s.setPosition(s.getPosition().add(Vec2.Down.makeCopy().multiply(velocity*timestep)));
		};
	}
	
	/**
	 * Retrieves a rotation behavior for use with a Snowflake
	 * @param rotateRate The rate at which the Snowflake should rotate counter-clockwise (radians/second)
	 * @return A function that defines rotation behavior for a Snowflake at the specified rotational velocity
	 */
	public static BiConsumer<Double, Snowflake> getRotationBehavior(double rotateRate)
	{
		return (Double timestep, Snowflake s)->
		{
			s.setAngle(s.getAngle()+timestep*rotateRate);
		};
	}
	
	/**
	 * Retrieves A back-and-forth cycloid behavior for use with a Snowflake
	 * @param radius The radius of the circle about which the cycloid is based 
	 * @param startPoint Where in the cycle to start
	 * @return A function that defines a back-and-forth behavior for a Snowflake using a circle of the specified radius
	 */
	public static BiConsumer<Double, Snowflake> getCycloidBehavior(double radius, double startPoint)
	{
		return (Double timestep, Snowflake s)->
		{
			//How much the x and y values should change
			double xDiff;
			double yDiff;
			
			//Where in the cycloid we are (x-wise)
			double cyclePos;
			double offset = startPoint*4*Math.PI;
			double t = s.getTimeElapsed() + offset;

			//Find the position of the cycle in the original cycloid
			//cyclePos = radius * (t  - Math.sin(t)) + startPoint * 4 * radius;
			cyclePos = radius * (t  - Math.sin(t)) ;

			
			//Get cycles of 4r (so that we have 2 periods)
			cyclePos %= (4 * Math.PI *radius);
			
			//Find the difference between the future position and the current position of the original cycloid
			xDiff = radius * (t+timestep - Math.sin(t+timestep)) - radius * (t  - Math.sin(t));
			yDiff =  (radius * (1+Math.cos(t+timestep)) - radius * (1+Math.cos(t)));
			
			//If we're on the second of the two periods, reverse the direction of motion
		    if (cyclePos > 2* Math.PI * radius)
		    {
				xDiff = -xDiff;
		    }

		    //Update the position of the snowflake with the displacement (xDiff and yDiff) values
			s.setPosition(s.getPosition().add(new Vec2(xDiff, yDiff/3.0)));

		};
	};
}
