
import java.awt.Color;
import java.awt.Point;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

import IFS.DrawableIFS;
import reusable.graphicsPrimitives.Mat2;
import drawables.tree.BasicTree;
import drawables.tree.UnitTree;
import geometry.IntersectionOps;
import geometry.RegularPolygon;
import reusable.Helpers;
import reusable.graphicsPrimitives.Vec2;
import snowflakes.SnowFlurry;
import drawables.Cloud;
import drawables.Mountain;
import drawables.CloudCluster;
import drawables.tree.LeafCluster;


/**
 * A class that gets hooked into the GLCanvas that tracks updates and draws to the screen on refreshes.
 * @author DEMcKnight
 */
public class EventManager implements GLEventListener, KeyListener, MouseListener, MouseMotionListener
{

	private int counter = 0;

	private static int virtualWidth=1920;
	private static int virtualHeight=1080;

	private static int screenWidth = 1920;
	private static int screenHeight = 1080;

	private static int horizon = 275;
	private static int mountainHorizon = horizon - 2;

	private static int lowestCloudLevel = horizon + 325;
	
	private static boolean drawWinter = false;

	//The points in the galaxy (modeled by a Lorenz attractor) that will be drawn
	
	private static 





	float targetAspectRatio = virtualWidth/virtualHeight;

	int[] viewport = new int[4];
	private double[] projectionMatrix = new double[16];
	private double[] modelMatrix = new double[16];

	private Point2D.Double cameraOrigin = new Point2D.Double(0, 0);
	private Point2D.Double mousePosition = new Point2D.Double(0, 0);

	
	public static RegularPolygon theSun = new RegularPolygon(new Vec2(1920*.9, 1080*.9), 0, 70, 30);
	
	// FIXME
	public static UnitTree tree = new UnitTree();
	
	public static ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	public static ArrayList<Mountain> mountains = new ArrayList<Mountain>();
	public static ArrayList<CloudCluster> cloudClusterList = new ArrayList<CloudCluster>();
	public static DrawableIFS ifs;
	public static SnowFlurry flurry;
	
	public static boolean seasonChanged = false;

	public static boolean isFirstRender = true;

	public static boolean isCloudMoving = false;
	public static boolean isCloudDirectionToRight = true;

	/******************************************/
	/*GLEventListener methods*/
	/******************************************/

	//Called by the drawable to initiate OpenGL rendering by the client.
	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f );
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		update();
		updateProjectionMatrix(drawable);

		if (!drawWinter)
			renderSummer(drawable);
		else
			renderWinter(drawable);
		
		
	}

	private void updateCloudsCounter()
	{
		counter++;
	}
	private void update()
	{			
		
		if (seasonChanged)
		{
			if (!drawWinter)
				initSummer();
			else
				initWinter();
			seasonChanged =false;
		}
		
		//If it's summer
		if (!drawWinter)
		{
			if(isCloudMoving)
			{
				updateCloudsCounter();
			}
			updateClouds(counter, screenWidth);
		}
		
		//If it's winter
		if(drawWinter)
		{	
			for (int i=0; i<10; i++)
			{
				ifs.iterate();
			}
			flurry.iterate(1/60.0);
		}
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
		initSummer();
		initWinter();
	}

	//Called by the drawable during the first repaint after the component has been resized.
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		screenWidth = width;
		screenHeight = height;
	}
	
	
	public static void initSummer()
	{
		initializeMountains();
		initializeClouds();
	}
	
	public static void initWinter()
	{
		//Prepare IFS coords
		ArrayList<Mat2> matrices = new ArrayList<Mat2>();
		matrices.add(new Mat2(0.195,-0.488,0.344,0.443));
		matrices.add(new Mat2(0.462,0.414,-0.252,0.361));
		matrices.add(new Mat2(-0.637,0,0,0.501));
		matrices.add(new Mat2(-0.035,0.07,-0.469,0.022));
		matrices.add(new Mat2(-0.058,-0.07,0.453,-0.111));
		
		ArrayList<Vec2> vertices = new ArrayList<Vec2>();
		vertices.add(new Vec2(0.4431,0.2452));
		vertices.add(new Vec2(0.2511,0.5692));
		vertices.add(new Vec2(0.8562,0.2512));
		vertices.add(new Vec2(0.4884,0.5069));
		vertices.add(new Vec2(0.5976,0.0969));
		
		//Prepare the IFS and the snowflurry
		ifs = new DrawableIFS(matrices, vertices);
		flurry = new SnowFlurry(0, virtualWidth, 0, virtualHeight, 1/3.0);
	}

	public static void initializeClouds()
	{

		//generate the centers for the clusters and add the cluster to the list
		for(int i = 0; i<10; i++)
		{
			Random rand = new Random();
			int x = rand.nextInt((1700)+1) + 100;
			int y = rand.nextInt((200)+1) + lowestCloudLevel;

			cloudClusterList.add(new CloudCluster(x, y, 100, 150, new ArrayList<Cloud>()));
		}
	}

	public static void initializeMountains()
	{
		mountains.add(new Mountain(500, mountainHorizon, 50, 30));
		mountains.add(new Mountain(800, mountainHorizon, 300, 350));
		mountains.add(new Mountain(1760, mountainHorizon, 200, 280));
		mountains.add(new Mountain(900, mountainHorizon, 200, 250));
		mountains.add(new Mountain(1500, mountainHorizon, 180, 200));
		mountains.add(new Mountain(1400, mountainHorizon, 160, 140));
		mountains.add(new Mountain(1200, mountainHorizon, 100, 120));
		mountains.add(new Mountain(1600, mountainHorizon, 90, 100));
		mountains.add(new Mountain(1710, mountainHorizon, 75, 50));
		mountains.add(new Mountain(1680, mountainHorizon, 50, 30));
		mountains.add(new Mountain(1750, mountainHorizon, 40, 30));
		mountains.add(new Mountain(875, mountainHorizon, 100, 110));
		mountains.add(new Mountain(1000, mountainHorizon, 50, 75));
		mountains.add(new Mountain(1050, mountainHorizon, 100, 200));
		mountains.add(new Mountain(600, mountainHorizon, 100, 200));
		mountains.add(new Mountain(400, mountainHorizon, 150, 200));
		mountains.add(new Mountain(75, mountainHorizon, 200, 175));
		mountains.add(new Mountain(100, mountainHorizon, 70, 120));
		mountains.add(new Mountain(10, mountainHorizon, 100, 60));
		mountains.add(new Mountain(50, mountainHorizon, 40, 60));
	}
	

	//Actually does the rendering
	public static void renderSummer(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();

		//Draw sky background
		Drawers.drawSkyRect(gl, new Color[]{new Color(2, 125, 254), new Color(82, 192, 255), new Color(188, 245, 255)}, 0, 1920, horizon, 1080);

		//Draw the ground
		Drawers.drawGroundRect(gl,  new Color(82, 63, 63), new Color(97, 143, 81), 0, 1920, 0, horizon-1);
		
		//Draw the sun
		Helpers.setColor(gl, Color.YELLOW);
		Helpers.drawPolygon(gl, theSun.boundaryPoints);

		//Draw the background mountains
		Drawers.drawMountains(gl, mountains);

		//Draw the clouds
		Drawers.drawCloud(gl, cloudClusterList);

		// Transform and draw the tree
		gl.glPushMatrix(); // Copy the CT for local changes
		gl.glTranslated((virtualWidth - tree.getWidth()) / 2, horizon, 0.0);
		Drawers.drawTree(gl, tree);
		gl.glPopMatrix();	// Restore the CT from before
	}
	
	public static void renderWinter(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		
		//Cover the screen with blackness
		Drawers.drawGroundRect(gl, Color.BLACK, Color.BLACK, 0, 1920, 0, 1080);
		
		//Draw the moon
		Helpers.setColor(gl, Color.WHITE);
		Helpers.drawPolygon(gl, theSun.boundaryPoints);
		
		//We'll draw most of the flakes white
		Helpers.setColor(gl, Color.WHITE);
		
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glPushMatrix();
		gl.glTranslated(670, 0, 0);
		ifs.draw(gl);
		gl.glPopMatrix();
		
		flurry.draw(gl);
	}
	public static void updateClouds(int counter, int screenWidth)
	{
		for(CloudCluster cluster : cloudClusterList)
		{
			for(Cloud cloud: cluster.clouds)
			{
				if(counter % 4 == 0)
				{
					if(isCloudDirectionToRight)
					{
						if(cloud.getCx() >= virtualWidth)
						{
							cloud.setCx(0 - cloud.getWidth());
						}
						cloud.setCx(cloud.getCx() + 1);
					}
					else
					{
						if(cloud.getCx() + cloud.getWidth() <= 0)
						{
							cloud.setCx(virtualWidth);
						}

						cloud.setCx(cloud.getCx() -1);
					}
				}
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent e)
	{

		switch (e.getKeyCode())
		{
			case KeyEvent.VK_ENTER:
				isCloudMoving = !isCloudMoving;
				break;
			case KeyEvent.VK_RIGHT:
				isCloudDirectionToRight = true;
				break;
			case KeyEvent.VK_LEFT:
				isCloudDirectionToRight = false;
				break;
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
		if (IntersectionOps.isPointInsidePoly(new Vec2(mousePosition.getX(), mousePosition.getY()), theSun.boundaryPoints))
		{
			drawWinter = !drawWinter;
			seasonChanged = true;
		}
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
