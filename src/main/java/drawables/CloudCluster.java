package drawables;

import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

/**
 * A class for holding information about a cloud cluster to be drawn
 * @author Beth Nondorf
 */
public class CloudCluster implements Drawable, Shape
{

    public int cx;
    public int cy;
	public int height;
    public int width;
    public ArrayList<Cloud> clouds;

    public CloudCluster(int cx, int cy, int height, int width, ArrayList<Cloud> clouds)
    {
       Random rand = new Random();

        int numClouds = rand.nextInt((8-5) +1) + 5;

        //create the clouds in each cluster and add them to the list of clouds
        for(int i = 0; i<numClouds; i++)
        {
            Random rand2 = new Random();

            int cloudx = rand2.nextInt(width) + cx;
            int cloudy = rand2.nextInt(height) + cy;
 

            int cloudHeight = rand2.nextInt(((60-20)+1)) + 20;
            int cloudWidth = rand2.nextInt(((80-40)+1)) + 40;

            float r = rand2.nextFloat()/2f + 0.5f;
            float g = rand2.nextFloat()/2f + 0.5f;
            float b = rand2.nextFloat()/2f + 0.5f;
            float alpha = rand2.nextFloat();

            clouds.add(new Cloud(cloudx, cloudy, cloudHeight, cloudWidth, r, g, b, alpha));
        }
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