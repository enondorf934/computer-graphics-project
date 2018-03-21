package drawables;

import java.awt.Color;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import reusable.Helpers;

/**
 * A class for holding information about a cloud to be drawn
 * @author DEMcKnight
 */
public class Mountain implements Drawable, Shape
{

    public GL2 gl;
    public int cx;
    public int cy;
	public int height;
	public int width;

    public Mountain(GL2 gl, int cx, int cy,  int height, int width)
    {
        this.gl = gl;
        this.cx = cx;
        this.cy = cy;
		this.height = height;
		this.width = width;
    }

	@Override
    public void draw(GL2 gl)
	{
        Helpers.setColor(gl, Color.BLACK);
        gl.glBegin(GL2.GL_POLYGON);

        for(int k = 0; k<180; k += 10)
        {
            double rad = (k*Math.PI)/180;
			gl.glVertex2f((float)(cx + Math.cos(rad) * width), (float)( cy + Math.sin(rad) * height));
        }

        gl.glEnd();
	}

	@Override
	public boolean containsPoint(double x, double y)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
