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

		//animator.start();
	}

  //**********************************************************************
	// Override Methods (GLEventListener)
	//**********************************************************************

  public void		init(GLAutoDrawable drawable)
	{
		w = drawable.getWidth();
		h = drawable.getHeight();
	}

	public void		dispose(GLAutoDrawable drawable)
	{
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

    // Enable alpha blending to support translucency
    gl.glEnable(GL.GL_BLEND);
    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

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
    gl.glVertex2i(0, 0);
    gl.glVertex2i(1280, 0);
    gl.glColor3f(1.0f, 1.0f, 1.0f); // White
    gl.glVertex2i(1280, 591);
    gl.glVertex2i(0, 591);

    gl.glEnd();
  }

  private void drawGround(GL2 gl)
  {
    gl.glBegin(GL2.GL_QUADS);

    gl.glColor3f(0.292f, 0.491f, 0.125f); // Green
    gl.glVertex2i(0, 591);
		gl.glVertex2i(1280, 591);
		gl.glVertex2i(1280, 720);
		gl.glVertex2i(0, 720);

		gl.glEnd();
  }

  private void drawTree(GL2 gl)
  {
    BasicTree tree = new BasicTree();

    tree.addLeafCluster(new LeafCluster(628, 156, 154, 122, 0.02f, 0.42f, 0.15f));  // dark green
    tree.addLeafCluster(new LeafCluster(536, 75, 169, 155));
    tree.addLeafCluster(new LeafCluster(654, 119, 156, 99));
    tree.addLeafCluster(new LeafCluster(498, 102, 81, 101, 0.57f, 0.80f, 0.40f)); // yellow
    tree.addLeafCluster(new LeafCluster(465, 326, 119, 60, 0.02f, 0.42f, 0.15f)); // dark green
    tree.addLeafCluster(new LeafCluster(465, 185, 146, 124));
    tree.addLeafCluster(new LeafCluster(495, 245, 120, 141));
    tree.addLeafCluster(new LeafCluster(675, 167, 84, 91));
    tree.addLeafCluster(new LeafCluster(573, 212, 81, 66));
    tree.addLeafCluster(new LeafCluster(584, 276, 124, 150));
    tree.addLeafCluster(new LeafCluster(651, 248, 42, 129, 0.81f, 0.47f, 0.58f)); // pink
    tree.addLeafCluster(new LeafCluster(696, 242, 120, 93, 0.57f, 0.80f, 0.40f)); // yellow
    tree.addLeafCluster(new LeafCluster(663, 279, 108, 111));
    tree.addLeafCluster(new LeafCluster(675, 255, 93, 59, 0.57f, 0.80f, 0.40f));  // yellow
    tree.addLeafCluster(new LeafCluster(524, 276, 115, 90, 0.57f, 0.80f, 0.40f)); // yellow
    tree.addLeafCluster(new LeafCluster(537, 336, 92, 104, 0.57f, 0.80f, 0.40f)); // yellow
    tree.addLeafCluster(new LeafCluster(488, 353, 72, 100, 0.81f, 0.47f, 0.58f)); // pink
    tree.addLeafCluster(new LeafCluster(668, 356, 135, 75, 0.02f, 0.42f, 0.15f)); // dark green

    tree.draw(gl);
  }
}
