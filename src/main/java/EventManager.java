
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

import drawables.tree.BasicTree;
import drawables.Cloud;
import drawables.Mountain;


/**
 * A class that gets hooked into the GLCanvas that tracks updates and draws to the screen on refreshes.
 * @author DEMcKnight
 */
public class EventManager implements GLEventListener, KeyListener, MouseListener, MouseMotionListener
{

	private int virtualWidth=1920;
	private int virtualHeight=1080;
	
	private int screenWidth = 1920;
	private int screenHeight = 1080;	
	
	private static int horizon = 467;
	private static int mountainHorizon = horizon - 2;
	
	
	//The points in the galaxy (modeled by a Lorenz attractor) that will be drawn
	public static BasicTree theTree = new BasicTree(960, horizon, 2, 2, new Color(166, 129, 62));
	
	float targetAspectRatio = virtualWidth/virtualHeight;
	
	int[] viewport = new int[4];
	private double[] projectionMatrix = new double[16];
	private double[] modelMatrix = new double[16];
		
	private Point2D.Double cameraOrigin = new Point2D.Double(0, 0);	
	private Point2D.Double mousePosition = new Point2D.Double(0, 0);

	public static ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	public static ArrayList<Mountain> mountains = new ArrayList<Mountain>();
	public static boolean isFirstRender = true;

	/******************************************/
	/*GLEventListener methods*/
	/******************************************/
	
	//Called by the drawable to initiate OpenGL rendering by the client.
	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();		
	
		update();	
		updateProjectionMatrix(drawable);
		
		render(drawable);		
	}
	
	private void update()
	{	
	}
	
	private void updateProjectionMatrix(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();		
		
		//Project to the window
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();	
		
		//Scale what's being drawn to account for changes to the window
		float scaleX = screenWidth/(float)virtualWidth;
		float scaleY = screenHeight/(float)virtualHeight;		
		if (scaleX < scaleY)
			scaleY = scaleX;
		else
			scaleX = scaleY;
		
		gl.glOrtho(cameraOrigin.x, (screenWidth + cameraOrigin.x)/scaleX, cameraOrigin.y, (screenHeight + cameraOrigin.y)/scaleY, 0, 1);		
	
		//Update projection matrices
		gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
        gl.glGetDoublev(GLMatrixFunc.GL_PROJECTION_MATRIX, projectionMatrix, 0);
        gl.glGetDoublev(GLMatrixFunc.GL_MODELVIEW_MATRIX,  modelMatrix, 0);
	
	}

    //Called by the drawable when the display mode or the display device associated with the GLAutoDrawable has changed.
	@Override
	public void dispose(GLAutoDrawable arg0)
	{		}
	
	//Called by the drawable immediately after the OpenGL context is initialized.
	@Override
	public void init(GLAutoDrawable canvas)
	{
		//Prepare the Lorenz attractor information for drawing the galaxy later (this way it's not called every update or reshape)
		
	}

	//Called by the drawable during the first repaint after the component has been resized.
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{		
		screenWidth = width;
		screenHeight = height;	
	}

	public static void initializeClouds(GL2 gl)
	{
		clouds.add(new Cloud(gl, 100, 100, 20, 50, 0.5f));
		clouds.add(new Cloud(gl, 100, 120, 35, 55, 0.4f));
		clouds.add(new Cloud(gl, 90, 90, 40, 60, 0.3f));
	}

	public static void initializeMountains(GL2 gl)
	{
		mountains.add(new Mountain(gl, 500, mountainHorizon, 50, 30));
		mountains.add(new Mountain(gl, 800, mountainHorizon, 300, 350));
		mountains.add(new Mountain(gl, 1760, mountainHorizon, 200, 280));
		mountains.add(new Mountain(gl, 900, mountainHorizon, 200, 250));

		mountains.add(new Mountain(gl, 1500, mountainHorizon, 180, 200));
		mountains.add(new Mountain(gl, 1400, mountainHorizon, 160, 140));
		mountains.add(new Mountain(gl, 1200, mountainHorizon, 100, 120));
		mountains.add(new Mountain(gl, 1600, mountainHorizon, 90, 100));
		mountains.add(new Mountain(gl, 1710, mountainHorizon, 75, 50));
		mountains.add(new Mountain(gl, 1680, mountainHorizon, 50, 30));

		mountains.add(new Mountain(gl, 1750, mountainHorizon, 40, 30));


		mountains.add(new Mountain(gl, 875, mountainHorizon, 100, 110));
		mountains.add(new Mountain(gl, 1000, mountainHorizon, 50, 75));
		mountains.add(new Mountain(gl, 1050, mountainHorizon, 100, 200));
		mountains.add(new Mountain(gl, 600, mountainHorizon, 100, 200));
		mountains.add(new Mountain(gl, 400, mountainHorizon, 150, 200));
		mountains.add(new Mountain(gl, 75, mountainHorizon, 200, 175));
		mountains.add(new Mountain(gl, 100, mountainHorizon, 70, 120));
		mountains.add(new Mountain(gl, 10, mountainHorizon, 100, 60));
		mountains.add(new Mountain(gl, 50, mountainHorizon, 40, 60));
	}
	
	//Actually does the rendering
	public static void render(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();

		if(isFirstRender)
		{
			initializeClouds(gl);
			initializeMountains(gl);

			isFirstRender = false;
		}

		//Draw sky background
		Drawers.drawSkyRect(gl, new Color[]{new Color(2, 125, 254), new Color(82, 192, 255), new Color(188, 245, 255)}, 0, 1920, horizon, 1080);

		//Draw the ground
		Drawers.drawGroundRect(gl,  new Color(82, 63, 63), new Color(97, 143, 81), 0, 1920, 196, horizon-1);

		Drawers.drawMountains(gl, mountains);

		for(Cloud cloud : clouds)
		{
			Drawers.drawCloud(gl, cloud);
		}
		
		//Draw the tree
		Drawers.drawTree(gl,  theTree);


		
	
	}


	@Override
	public void keyPressed(KeyEvent e)
	{
		
		switch (e.getKeyCode())
		{
			//TODO
		}
		
		return;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		updateMousePosition(e);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		updateMousePosition(e);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		updateMousePosition(e);
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		updateMousePosition(e);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		updateMousePosition(e);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		updateMousePosition(e);
		//mouseIsPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		updateMousePosition(e);
		//mouseIsPressed = false;	
	}
	
	public void updateMousePosition(MouseEvent e)
	{
		double wCoord[] = new double[4];// wx, wy, wz;// returned xyz coords

		new GLU().gluUnProject(e.getX(), e.getY(), 0.0, //
	              modelMatrix, 0,
	              projectionMatrix, 0, 
	              viewport, 0, 
	              wCoord, 0);
			mousePosition = new Point2D.Double(wCoord[0], virtualHeight-wCoord[1]);	
	}
}
