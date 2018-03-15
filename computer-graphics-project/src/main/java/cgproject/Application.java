package cgproject;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

import drawables.*;
import drawables.tree.*;

public final class Application implements GLEventListener
{
  //**********************************************************************
  // Public Class Members
  //**********************************************************************

  public static final GLU		GLU = new GLU();
	public static final GLUT	GLUT = new GLUT();
	public static final Random	RANDOM = new Random();

  //**********************************************************************
	// Private Members
	//**********************************************************************

  // State (internal) variables
  private int				k = 0;		// Just an animation counter

	private int				w;			// Canvas width
	private int				h;			// Canvas height

  private ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	private ArrayList<BasicTree> trees = new ArrayList<BasicTree>();

  //**********************************************************************
	// Main
	//**********************************************************************

  public static void main(String[] args)
	{
		GLProfile		profile = GLProfile.getDefault();
		GLCapabilities	capabilities = new GLCapabilities(profile);
		GLCanvas		canvas = new GLCanvas(capabilities);
		JFrame			frame = new JFrame("Template");

    canvas.setPreferredSize(new Dimension(1280, 720));

		frame.setBounds(50, 50, 600, 600);
		frame.getContentPane().add(canvas);
    frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

		canvas.addGLEventListener(new Application());

		FPSAnimator		animator = new FPSAnimator(canvas, 60);

		animator.start();
	}

  //**********************************************************************
	// Override Methods (GLEventListener)
	//**********************************************************************

  public void		init(GLAutoDrawable drawable)
	{
		w = drawable.getWidth();
		h = drawable.getHeight();

		// renderer = new TextRenderer(new Font("Serif", Font.PLAIN, 18),true, true);
	}

	public void		dispose(GLAutoDrawable drawable)
	{
		// renderer = null;
	}

	public void		display(GLAutoDrawable drawable)
	{
		update();
		render(drawable);
	}

	public void		reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
	{
		this.w = w;
		this.h = h;
	}

  //**********************************************************************
	// Private Methods (Rendering)
	//**********************************************************************

  private void	update()
	{
		k++;									// Counters are useful, right?
	}

	private void	render(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);		// Clear the buffer

    setScreenProjection(gl);

    drawSky(gl);
    drawGround(gl);

    drawTree(gl);

    // for (Cloud cloud: clouds)
		// {
		// 	cloud.draw(gl);
		// }
		// for (BasicTree tree: trees)
		// {
		// 	tree.draw(gl);
		// }
	}

  //**********************************************************************
	// Private Methods (Coordinate System)
	//**********************************************************************

  private void	setScreenProjection(GL2 gl)
	{
		GLU		glu = new GLU();

		gl.glMatrixMode(GL2.GL_PROJECTION);			// Prepare for matrix xform
		gl.glLoadIdentity();						// Set to identity matrix
		glu.gluOrtho2D(0.0f, 1280.0f, 720.0f, 0.0f);// 2D translate and scale
	}

  //**********************************************************************
	// Private Methods (Scene)
	//**********************************************************************

  private void drawSky(GL2 gl)
  {
    gl.glBegin(GL2.GL_QUADS);

    gl.glColor3f(0.458f, 0.643f, 0.996f); // Light blue
    gl.glVertex2i(0, 720);
    gl.glVertex2i(1280, 720);
    gl.glColor3f(1.0f, 1.0f, 1.0f); // White
    gl.glVertex2i(1280, 129);
    gl.glVertex2i(0, 129);

    gl.glEnd();
  }

  private void drawGround(GL2 gl)
  {
    gl.glBegin(GL2.GL_QUADS);

    gl.glColor3f(0.292f, 0.491f, 0.125f); // Green
    gl.glVertex2i(0, 129);
		gl.glVertex2i(1280, 129);
		gl.glVertex2i(1280, 0);
		gl.glVertex2i(0, 0);

		gl.glEnd();
  }

  private void drawTree(GL2 gl)
  {
    BasicTree tree = new BasicTree();

    tree.addLeafCluster(new LeafCluster())

  }
}
