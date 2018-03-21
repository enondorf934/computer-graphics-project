package drawables;

import javax.media.opengl.GL2;

/**
 * A class for holding information about a cloud to be drawn
 * @author DEMcKnight
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
		
	}

	@Override
	public boolean containsPoint(double x, double y)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
