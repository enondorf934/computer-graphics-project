

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import drawables.tree.BasicTree;
import reusable.Helpers;
public class Drawers
{

	//Method for drawing the sky backdrop (the gradient)
	public static void drawSkyRect(GL2 gl, Color[] gradientColors, double leftX, double rightX, double bottomY, double topY)
	{	
		gl.glBegin(GL2.GL_QUADS);	
		Helpers.setColor(gl, gradientColors[2]);
		gl.glVertex2d(leftX, bottomY);  
		gl.glVertex2d(rightX, bottomY);  
		Helpers.setColor(gl, gradientColors[1]);
		gl.glVertex2d(rightX,bottomY+(topY-bottomY)/2.8);  
		gl.glVertex2d(leftX,bottomY+(topY-bottomY)/2.8);  

		Helpers.setColor(gl, gradientColors[1]);
		gl.glVertex2d(leftX,bottomY+(topY-bottomY)/2.8);  
		gl.glVertex2d(rightX,bottomY+(topY-bottomY)/2.8);
		Helpers.setColor(gl, gradientColors[0]);
		gl.glVertex2d(rightX, topY);  
		gl.glVertex2d(leftX, topY); 
		gl.glEnd();  
	}

	//Method for drawing the ground backdrop (the gradient)
	public static void drawGroundRect(GL2 gl, Color topColor, Color botColor, double leftX, double rightX, double bottomY, double topY)
	{	
		gl.glBegin(GL2.GL_QUADS);	
		Helpers.setColor(gl, botColor);
		gl.glVertex2d(leftX, bottomY);  
		gl.glVertex2d(rightX, bottomY);  
		Helpers.setColor(gl, topColor);
		gl.glVertex2d(rightX,bottomY+(topY-bottomY));  
		gl.glVertex2d(leftX,bottomY+(topY-bottomY));  
		gl.glEnd();  
	}
	
	//Method for drawing the ground backdrop (the gradient)
	public static void drawTree(GL2 gl, BasicTree tree)
	{	
		tree.draw(gl);;
	}
}
