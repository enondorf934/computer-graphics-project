package IFS;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import reusable.graphicsPrimitives.Mat2;
import reusable.graphicsPrimitives.Vec2;

public class DrawableIFS extends IFS
{
	ArrayList<Vec2> coordinates = new ArrayList<Vec2>();

	public DrawableIFS(List<Mat2> matrices, List<Vec2> addends)
	{
		super(matrices, addends);
	}
	
	public Vec2 iterate()
	{
		Vec2 returnVal = super.iterate();
		coordinates.add(returnVal);
		return returnVal.makeCopy();
	}
	
	public void draw(GL2 gl)
	{
		gl.glBegin(GL.GL_POINTS);
		for (Vec2 coordinate: coordinates)
		{
			gl.glVertex2d(coordinate.getX()*500, coordinate.getY()*500);
		}
		gl.glEnd();
	}
	
}
