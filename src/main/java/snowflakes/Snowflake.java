
package snowflakes;

import reusable.graphicsPrimitives.Vec2;

import java.util.ArrayList;
import java.util.function.*;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

/**
 * Abstract Snowflake class for defining behaviors that all Snowflakes should have in the context of our program
 * @author DEMcKnight
 *
 */
public abstract class Snowflake
{
	/**
	 * The offset of this Snowflake. The amount we'll translate the Model matrix during our OpenGL draw call
	 */
	private Vec2 position = Vec2.Zero;
	
	/**
	 * The angular offset of this Snowflake in radians counterclockwise. The amount we'll rotate the Model matrix during our OpenGL draw call
	 */
	private double angle = 0;
	
	/**
	 * For some behavioral functions its useful to know how long it's been since the snowflake was spawned
	 */
	private double timeElapsed = 0;
	
	/**
	 * A list of behavioral lambda functions that are invoked (with timestep) when update(timestep) is called. 
	 */
	private ArrayList<BiConsumer<Double, Snowflake>> updateFunctions = new ArrayList<BiConsumer<Double, Snowflake>>();
	
	/**
	 * Creates a snowflake with the given position and angle
	 * @param position The offset of this Snowflake. The amount we'll translate the Model matrix during our OpenGL draw call
	 * @param angle The angular offset of this Snowflake in radians counterclockwise. The amount we'll rotate the Model matrix during our OpenGL draw call
	 */
	public Snowflake(Vec2 position, double angle)
	{
		setPosition(position);
		setAngle(angle);
	}
	
	public Vec2 getPosition()
	{
		return position.makeCopy();
	}
	
	public void setPosition(Vec2 v)
	{
		this.position = v;
	}
	
	public double getAngle()
	{
		return angle;
	}
	
	public void setAngle(double angle)
	{
		this.angle = angle;
	}	
	
	public double getTimeElapsed()
	{
		return timeElapsed;
	}
	
	public void setTime(double d)
	{
		this.timeElapsed = d;
	}	
	
	/**
	 * Adds a lambda function to the updateFunctions list. Will consequently be invoked (with the timestep parameter) when update(timestep) is called
	 * @param updateFunc the behavior to attach to this Snowflake
	 */
	public void addUpdateFunc(BiConsumer<Double, Snowflake> updateFunc)
	{
		this.updateFunctions.add(updateFunc);
	}
	
	/**
	 * Invokes all of the lambda functions in updateFunction in order with the given timestep parameter
	 * @param timestep How much time to progress this Snowflake and to pass into the updateFunctions
	 */
	public void update(double timestep)
	{
		for (BiConsumer<Double, Snowflake> updateFunction : updateFunctions)
			updateFunction.accept(timestep, this);
		timeElapsed+=timestep;
	}
	
	/**
	 * Translates and rotates, then calls internal draw
	 * @param gl
	 */
	public final void draw(GL gl)
	{
		GL2 gl2=gl.getGL2();
		gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl2.glPushMatrix();
				
		gl2.glTranslated(position.getX(), position.getY(), 0);
		gl2.glRotated(angle, 0, 0, 1);

		
		internalDraw(gl);
		gl2.glPopMatrix();
	}
	
	/**
	 * Called by draw after translation and rotation occur
	 * @param gl
	 */
	protected abstract void internalDraw(GL gl);
	
}
