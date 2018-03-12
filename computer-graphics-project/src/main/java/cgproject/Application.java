package cgproject;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

public final class Application implements GLEventListener
{
    public static final GLU		GLU = new GLU();
	public static final GLUT	GLUT = new GLUT();
	public static final Random	RANDOM = new Random();

    private int				k = 0;		// Just an animation counter
	private int				w;			// Canvas width
	private int				h;			// Canvas height
	private TextRenderer	renderer;

    public static void main(String[] args)
	{
		GLProfile		profile = GLProfile.getDefault();
		GLCapabilities	capabilities = new GLCapabilities(profile);
		GLCanvas		canvas = new GLCanvas(capabilities);
		JFrame			frame = new JFrame("Template");

		frame.setBounds(50, 50, 600, 600);
		frame.getContentPane().add(canvas);
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



    public void		init(GLAutoDrawable drawable)
	{
		w = drawable.getWidth();
		h = drawable.getHeight();

		renderer = new TextRenderer(new Font("Serif", Font.PLAIN, 18),true, true);
	}

	public void		dispose(GLAutoDrawable drawable)
	{
		renderer = null;
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



    private void	update()
	{
		k++;									// Counters are useful, right?
	}

	private void	render(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);		// Clear the buffer
		drawSomething(gl);						// Draw something
		drawSomeText(drawable);					// Draw some text
	}


    private void	drawSomething(GL2 gl)
	{
		gl.glBegin(GL.GL_POINTS);

		gl.glColor3f(1.0f, 1.0f, 1.0f);
		gl.glPointSize(2.0f);
		gl.glVertex2d(0.0, 0.0);

		gl.glEnd();
	}


    private void	drawSomeText(GLAutoDrawable drawable)
	{
		renderer.beginRendering(drawable.getWidth(), drawable.getHeight());
		renderer.setColor(1.0f, 1.0f, 0, 1.0f);
		renderer.draw("This is a point", w/2 + 8, h/2 - 5);
		renderer.endRendering();
	}
}