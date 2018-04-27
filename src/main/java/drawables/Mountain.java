package drawables;

import java.awt.Color;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;
import reusable.Helpers;

/**
 * A class for holding information about a mountain to be drawn
 * @author Beth Nondorf
 */
public class Mountain implements Drawable, Shape
{

    public int cx;
    public int cy;
	public int height;
    public int width;
    public float r;
    public float g;
    public float b;
    public float alpha;

    public Mountain(int cx, int cy,  int height, int width)
    {
        this.cx = cx;
        this.cy = cy;
		this.height = height;
        this.width = width;
        
        Random rand = new Random();

        double randValue = rand.nextDouble();

        if(randValue >= 0 && randValue <.20)
        {
            this.r = 0.564f;
            this.g = 0.105f;
            this.b = 0.035f;
        }
        else if(randValue >=.20 && randValue <.40)
        {
            this.r = 0.701f;
            this.g = 0.282f;
            this.b = 0.215f;
        }
        else if(randValue >=.40 && randValue <.60)
        {
            this.r = 0.835f;
            this.g = 0.376f;
            this.b = 0.164f;
        }
        else if(randValue >=.60 && randValue <.80)
        {
            this.r = 0.878f;
            this.g = 0.443f;
            this.b = 0.043f;
        }
        else if(randValue >=.80 && randValue <=1.0)
        {
            this.r = 0.913f;
            this.g = 0.611f;
            this.b = 0.184f;
        }

		this.alpha = 1f;
    }

	@Override
    public void draw(GL2 gl)
	{

        Helpers.setColor(gl, new Color(r, g, b, alpha));
        gl.glBegin(GL2.GL_POLYGON);

        for(int k = 0; k<=180; k += 10)
        {
            double rad = (k*Math.PI)/180;
			gl.glVertex2f((float)(cx + Math.cos(rad) * width), (float)( cy + Math.sin(rad) * height));
        }

        gl.glEnd();

        Helpers.setColor(gl, Color.BLACK);
        gl.glBegin(GL2.GL_LINE_LOOP);

        for(int k = 0; k<=180; k += 10)
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
