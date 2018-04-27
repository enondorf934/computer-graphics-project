package IFS;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import reusable.graphicsPrimitives.Mat2;
import reusable.graphicsPrimitives.Vec2;

/**
 * An extension of the Iterated Function System class for storing and drawing results of iterations
 * @author DEMcKnight
 *
 */
public class DrawableIFS extends IFS
{
	/**
	 * The coordinates that this IFS has iterated through
	 */
	ArrayList<Vec2> coordinates = new ArrayList<Vec2>();
	
	/**
	 * The origin of this IFS in some other coordinate space
	 */
	private Vec2 origin;
	
	/**
	 * The amount to scale this IFS in the horizontal direction in some other coordinate space
	 */
	private double xScaling;
	
	/**
	 * The amount to scale this IFS in the vertical direction in some other coordinate space
	 */
	private double yScaling;

	/**
	 * Prepares a Drawable iterated functions system with the IFS component being dictated by IFS(matrices, addends), the position for drawing being determined by origin, and the scaling factors for drawing being determined by xScaling and yScaling
	 * @param origin The position for drawing this IFS in some other coordinate space
	 * @param xScaling The amount to scale the model matrix horizontally when drawing to a GL object
	 * @param yScaling The amount to scale the model matrix vertically when drawing to a GL object
	 * @param matrices The transformations to apply to currentPosition prior to translating by the corresponding addend when iterate() is called. 
	 * @param addends The translation transformations to apply to currentPosition after applying the corresponding matrix transformation when iterate() is called.
	 */
	public DrawableIFS(Vec2 origin, double xScaling, double yScaling, List<Mat2> matrices, List<Vec2> addends)
	{
		super(matrices, addends);
		this.origin = origin;
		this.xScaling = xScaling;
		this.yScaling = yScaling;
	}

	/**
	 * Moves this IFS forward, calling super.iterate() and storing the result in coordinates
	 */
	public Vec2 iterate()
	{
		Vec2 returnVal = super.iterate();
		coordinates.add(returnVal);
		return returnVal.makeCopy();
	}

	/**
	 * Draws the coordinates this IFS has traveled through to the given GL2 object, translating by Origin and scaling by xScaling and yScaling
	 * @param gl
	 */
	public void draw(GL2 gl)
	{
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glPushMatrix();

		gl.glTranslated(origin.getX(), origin.getY(), 0);
		gl.glScaled(xScaling, yScaling, 1);

		gl.glBegin(GL.GL_POINTS);
		for (Vec2 coordinate : coordinates)
		{
			gl.glVertex2d(coordinate.getX(), coordinate.getY());
		}
		gl.glEnd();

		gl.glPopMatrix();
	}

}
