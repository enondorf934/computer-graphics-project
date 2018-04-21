package drawables;

import java.awt.Color;

import javax.media.opengl.GL2;

import reusable.Helpers;

/**
 * A class for holding information about a cloud to be drawn
 * @author Beth Nondorf
 */
public class Cloud implements Drawable, Shape
{

	public GL2 gl;
    public int cx;
    public int cy;
	public int height;
	public int width;
    public float alpha;

    public Cloud(GL2 gl, int cx, int cy, int height, int width, float alpha)
    {
        this.gl = gl;
        this.cx = cx;
        this.cy = cy;
		this.height = height;
		this.width = width;
        this.alpha = alpha;
    }

	@Override
	public void draw(GL2 gl)
	{

		gl.glBegin(GL2.GL_POLYGON);
		//Helpers.setColor(gl, new Color(146, 204, 199, alpha));
		//gl.glColor4f(1.0f, 1.0f, 1.0f, alpha);

		gl.glColor4f(146/255f, 204/255f, 199/255f, alpha/255f);

		gl.glVertex2i(cx, cy);
		gl.glVertex2i(cx, cy + height);
		gl.glVertex2i(cx + width, cy + height);
		gl.glVertex2i(cx + width, cy);
		gl.glVertex2i(cx, cy);
		gl.glEnd();

		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor4f(153/255f,186/255f,239/255f,alpha/255f);
		gl.glVertex2i(cx, cy); 
		gl.glVertex2i(cx, cy + height);
		gl.glVertex2i(cx + width, cy + height);
		gl.glVertex2i(cx + width, cy);
		gl.glVertex2i(cx, cy);
		gl.glEnd();
	}

	@Override
	public boolean containsPoint(double x, double y)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
