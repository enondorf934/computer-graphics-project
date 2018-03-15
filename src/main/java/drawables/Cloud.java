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
	//Class (static) variables
	
	//Instance variables
		
	//Constructors

	//Class (static) methods
	
	//Instance methods
	
	//Interface methods
	
	@Override
	public void draw(GL2 gl)
	{
		Cloud cloud = this;

		gl.glBegin(GL2.GL_POLYGON);
		gl.glColor4f(1.0f, 0.0f, 0.0f, cloud.alpha);

		gl.glVertex2i(cloud.cx, cloud.cy);
		gl.glVertex2i(cloud.cx, cloud.cy + cloud.height);
		gl.glVertex2i(cloud.cx + cloud.width, cloud.cy + cloud.height);
		gl.glVertex2i(cloud.cx + cloud.width, cloud.cy);
		gl.glVertex2i(cloud.cx, cloud.cy);

		gl.glEnd();
	}

	@Override
	public boolean containsPoint(double x, double y)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
