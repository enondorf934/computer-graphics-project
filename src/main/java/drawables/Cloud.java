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

    public int cx;
    public int cy;
	public int height;
	public int width;
	public float r;
	public float g;
	public float b;
	public float alpha;

    public Cloud(int cx, int cy, int height, int width, float r, float g, float b, float alpha)
    {
        this.cx = cx;
        this.cy = cy;
		this.height = height;
		this.width = width;
		this.r = r;
		this.g = g;
		this.b = b;
		this.alpha = alpha;
    }

	@Override
	public void draw(GL2 gl)
	{

		gl.glBegin(GL2.GL_POLYGON);
		
		Helpers.setColor(gl, new Color(r, g, b, alpha));

		gl.glVertex2i(cx, cy);
		gl.glVertex2i(cx, cy + height);
		gl.glVertex2i(cx + width, cy + height);
		gl.glVertex2i(cx + width, cy);
		gl.glVertex2i(cx, cy);
		gl.glEnd();

		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor4f(153/255f,186/255f,239/255f,alpha);
		gl.glVertex2i(cx, cy); 
		gl.glVertex2i(cx, cy + height);
		gl.glVertex2i(cx + width, cy + height);
		gl.glVertex2i(cx + width, cy);
		gl.glVertex2i(cx, cy);
		gl.glEnd();
	}

	public void setCx(int n)
	{
		this.cx = n;
	}

	public void setCy(int n)
	{
		this.cy = n;
	}

	public int getCx()
	{
		return this.cx;
	}

	public int getCy()
	{
		return this.cy;
	}

	public int getWidth() 
	{
		return this.width;
	}

	@Override
	public boolean containsPoint(double x, double y)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
