package drawables;
import javax.media.opengl.*;

/**
 * An interface to guarantee an object may be drawn.
 * @author DEMcKnight
 */
public interface Drawable
{
	/**
	 * Method for drawing the implementing object onto an OpenGL2 object.
	 * @param gl The OpenGL2 object to be drawn upon.
	 */
	public void draw(GL2 gl);
}
