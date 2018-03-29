package drawables;

import javax.media.opengl.GL2;

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
	
	public int getCx() {
		return this.cx;
	}

	public int getCy() {
		return this.cy;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public float getAlpha() {
		return this.alpha;
	}

	@Override
	public void draw(GL2 gl)
	{
		// int cx = cloud.getCx();
		// int cy = cloud.getCy();
		// int height = cloud.getHeight();
		// int width = cloud.getWidth();

		gl.glBegin(GL2.GL_POLYGON);
		gl.glColor4f(1.0f, 1.0f, 1.0f, alpha);

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
