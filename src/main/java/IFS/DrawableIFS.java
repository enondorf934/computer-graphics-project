package IFS;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import reusable.graphicsPrimitives.Mat2;
import reusable.graphicsPrimitives.Vec2;

public class DrawableIFS extends IFS
{
	ArrayList<Vec2> coordinates = new ArrayList<Vec2>();
	private Vec2 origin;
	private double xScaling;
	private double yScaling;

	public DrawableIFS(Vec2 origin, double xScaling, double yScaling, List<Mat2> matrices, List<Vec2> addends)
	{
		super(matrices, addends);
		this.origin = origin;
		this.xScaling = xScaling;
		this.yScaling = yScaling;
	}
	
	public Vec2 iterate()
	{
		Vec2 returnVal = super.iterate();
		coordinates.add(returnVal);
		return returnVal.makeCopy();
	}
	
	public void draw(GL2 gl)
	{
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glPushMatrix();
		
		gl.glTranslated(origin.x, origin.y, 0);
		gl.glScaled(xScaling, yScaling, 1);
		
		gl.glBegin(GL.GL_POINTS);
		for (Vec2 coordinate: coordinates)
		{
			gl.glVertex2d(coordinate.getX(), coordinate.getY());
		}
		gl.glEnd();
		
		gl.glPopMatrix();
	}
	
}
