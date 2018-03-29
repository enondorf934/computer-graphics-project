package drawables;

import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for holding information about a cloud cluster to be drawn
 * @author Beth Nondorf
 */
public class CloudCluster implements Drawable, Shape
{

    public GL2 gl;
    public int cx;
    public int cy;
	public int height;
    public int width;
    public ArrayList<Cloud> clouds;

    public CloudCluster(GL2 gl, int cx, int cy, int height, int width, ArrayList<Cloud> clouds)
    {
        Random rand = new Random();

        int numClouds = rand.nextInt((8-5) +1) + 5;

        //create the clouds in each cluster and add them to the list of clouds
        for(int i = 0; i<numClouds; i++)
        {
            int cloudx = rand.nextInt(((cx + (int).5*width) - (cx - (int).5*width)) +1) + (cx - (int).5*width);
            int cloudy = rand.nextInt(((cx + (int).5*height) - (cx - (int).5*height)) +1) + (cx - (int).5*height);

            int cloudHeight = rand.nextInt(((60-20)+1) + 20);
            int cloudWidth = rand.nextInt(((80-40)+1) + 40);

            float alpha = rand.nextFloat();

            clouds.add(new Cloud(gl, cloudx, cloudy, cloudHeight, cloudWidth, alpha));
        }


        this.gl = gl;
        this.cx = cx;
        this.cy = cy;
		this.height = height;
        this.width = width;
        this.clouds = clouds;
    }

	@Override
	public void draw(GL2 gl)
	{
        for(Cloud cloud : clouds)
        {
            cloud.draw(gl);
        }
	}

	@Override
	public boolean containsPoint(double x, double y)
	{
		// TODO Auto-generated method stub
		return false;
	}
}