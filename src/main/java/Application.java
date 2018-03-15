
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.awt.GLJPanel;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;

/**
 * Class for spawning an OpenGL Canvas and hosting it in a Java Swing window.
 * @author DEMcKnight
 * Lifts code directly from
 * @author Chris Weaver
 */
public class Application
{	
	public static void main(String[] args)
	{
		//Create JFrame
		JFrame			frame = new JFrame("Template");
		frame.setBounds(50, 50, 600, 600);
		//Create GLCanvas
		GLJPanel		canvas = new GLJPanel();
		canvas.setPreferredSize(new Dimension(1920,1080));
		canvas.setFocusTraversalKeysEnabled(false);

		//Attach interaction to GLJPanel
		//Performs canvas.addGLEventListener(view);
		//Performs canvas.view = view;
		EventManager eventManager = new EventManager();
		canvas.addGLEventListener(eventManager);
		canvas.addMouseListener(eventManager);
		canvas.addMouseMotionListener(eventManager);
		canvas.addKeyListener(eventManager);
		
		//Insert GLCanvas into JFrame and make visible
		frame.getContentPane().add(canvas);
		frame.pack();
		frame.setVisible(true);

		//Closing behavior
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
				

		FPSAnimator		animator = new FPSAnimator(canvas, 60);

		animator.start();
	}
}


