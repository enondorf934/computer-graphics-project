
package snowflakes;

import reusable.graphicsPrimitives.Vec2;

import java.util.ArrayList;
import java.util.function.*;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

public abstract class Snowflake
{
	private Vec2 position = Vec2.Zero;
	private double angle = 0;	
	private double timeElapsed = 0;
	
	private ArrayList<BiConsumer<Double, Snowflake>> updateFunctions = new ArrayList<BiConsumer<Double, Snowflake>>();
	
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
	
	public void addUpdateFunc(BiConsumer<Double, Snowflake> updateFunc)
	{
		this.updateFunctions.add(updateFunc);
	}
	
	public void update(double timestep)
	{
		for (BiConsumer<Double, Snowflake> updateFunction : updateFunctions)
			updateFunction.accept(timestep, this);
		timeElapsed+=timestep;
	}
	
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
	
	protected abstract void internalDraw(GL gl);
	
}
