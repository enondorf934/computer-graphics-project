package drawables.tree;

import javax.media.opengl.*;

import drawables.Drawable;

//******************************************************************************

/**
 * A class for describing and drawing a rectangular leaf cluster inspired by
 * Super Paper Mario tree design.
 *
 * @author ryanabrowning
 */
public final class LeafCluster implements Drawable
{
  private int x;        // x-coordinate of lower left corner of leaf cluster
  private int y;        // y-coordinate of lower left corner of leaf cluster
  private int w;        // width of leaf cluster
  private int h;        // height of leaf cluster

  private float r;      // red color value
  private float g;      // green color value
  private float b;      // blue color value
  private float alpha;  // alpha value

  // Constructors
  public LeafCluster(int x, int y, int w, int h)
  {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  public LeafCluster(int x, int y, int w, int h, float r, float g, float b, float alpha)
  {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;

    this.r = r;
    this.g = g;
    this.b = b;
    this.alpha = alpha;
  }

  public void setColor(float r, float g, float b, float alpha)
  {
    this.r = r;
    this.g = g;
    this.b = b;
    this.alpha = alpha;
  }

  public void draw(GL2 gl)
  {
    // Outline leaf cluster
    gl.glBegin(GL.GL_LINE_LOOP);
    gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f); // Black

    // Draw vertices clockwise starting from lower left corner
    gl.glVertex2i(x, y);  // lower left corner
    gl.glVertex2i(x, y + h);
    gl.glVertex2i(x + w, y + h);
    gl.glVertex2i(x + w, y);

    gl.glEnd();

    // Fill leaf cluster
    gl.glBegin(GL2.GL_QUADS);
    gl.glColor4f(r, g, b, alpha);

    // Draw vertices clockwise starting from lower left corner
    gl.glVertex2i(x, y);  // lower left corner
    gl.glVertex2i(x, y + h);
    gl.glVertex2i(x + w, y + h);
    gl.glVertex2i(x + w, y);

    gl.glEnd();
  }
}
