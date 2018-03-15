package reusable;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import reusable.graphicsPrimitives.Vec2;

/**
 * Class containing static methods to help draw and fill things with JOGL
 * @author DEMcKnight
 */
public class Helpers
{		
	
	
	//Uses OpenGL to draw a polygon specified by a collection of points to the screen
	public static void drawPolygon(GL gl, ArrayList<Vec2> polygon)
	{
		GL2 gl2=gl.getGL2();
		gl2.glBegin(GL2.GL_POLYGON);
		for(Vec2 p:polygon)
			gl2.glVertex2d(p.getX(), p.getY());
		gl2.glEnd();
	}
	
	
	//Uses OpenGL to draw the triangle strip specified by a collection of points to the screen
	public static void drawTriangleStrip(GL gl, ArrayList<Vec2> triangleStrip)
	{
		GL2 gl2=gl.getGL2();
		gl2.glBegin(GL2.GL_TRIANGLE_STRIP);
		for(Vec2 p:triangleStrip)
			gl2.glVertex2d(p.getX(), p.getY());
		gl2.glEnd();
	}
	
	
	//Uses OpenGL to draw the triangle strip specified by a collection of points to the screen
	public static void drawLineLoop(GL gl, ArrayList<Vec2> lineLoop)
	{
		GL2 gl2=gl.getGL2();
		gl2.glBegin(GL2.GL_LINE_LOOP);
		for(Vec2 p:lineLoop)
			gl2.glVertex2d(p.getX(), p.getY());
		gl2.glEnd();
	}
	
	
	//Sets the color OpenGL is using for drawing things.
	public static void setColor(GL gl, Color color)
	{
		gl.getGL2().glColor4d(color.getRed()/255.0, color.getGreen()/255.0, color.getBlue()/255.0, color.getAlpha()/255.0);
	}
}
