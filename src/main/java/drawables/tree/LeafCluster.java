package drawables.tree;

import java.awt.geom.*;
import java.util.*;
import javax.media.opengl.*;

import drawables.Drawable;
import drawables.Shape;

//******************************************************************************

/**
 * The <CODE>LeafCluster</CODE> class.<P>
 *
 * @author ryanabrowning
 */
public final class LeafCluster implements Drawable, Shape
{
  private static final int NUM_SIDES = 6;

  private double cx;               // x-coordinate of center of leaf cluster
  private double cy;               // y-coordinate of center of leaf cluster
  private double radius;           // radius of leaf cluster

  private ArrayList<Point2D.Double> vertices; // vertices of leaf cluster

  private float r;              // red color value
  private float g;              // green color value
  private float b;              // blue color value
  private float alpha = 0.60f;  // default transparency

  private boolean isFalling;    // true if the leaf cluster is falling, else false

  //****************************************************************************

  // Constructors
  public LeafCluster(double cx, double cy, double radius)
  {
    this.cx = cx;
    this.cy = cy;
    this.radius = radius;

    calculateVertices();

    // Default green
    r = 0.32f;
    g = 0.68f;
    b = 0.40f;

    isFalling = false;
  }

  //****************************************************************************

  public void setColor(float r, float g, float b)
  {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public void setIsFalling(boolean isFalling)
  {
    this.isFalling = isFalling;
  }

  public boolean getIsFalling()
  {
    return isFalling;
  }

  public double getCy()
  {
    return cy;
  }

  public double getRadius()
  {
    return radius;
  }

  //****************************************************************************

  private void calculateVertices()
  {
    vertices = new ArrayList<Point2D.Double>();

    double theta = Math.PI / 2;
    double delta = 2 * Math.PI / NUM_SIDES;

    for (int i = 0; i < NUM_SIDES; i++)
    {
      double px = cx + radius * Math.cos(theta);
      double py = cy + radius * Math.sin(theta);
      vertices.add(new Point2D.Double(px, py));

      theta += delta;
    }
  }

  private void fillLeafCluster(GL2 gl)
  {
    gl.glBegin(GL2.GL_POLYGON);
    gl.glColor4f(r, g, b, alpha);

    for (Point2D.Double vertex : vertices)
    {
      gl.glVertex2d(vertex.x, vertex.y);
    }

    gl.glEnd();
  }

  private void outlineLeafCluster(GL2 gl)
  {
    gl.glBegin(GL.GL_LINE_LOOP);
    gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f); // Black

    for (Point2D.Double vertex : vertices)
    {
      gl.glVertex2d(vertex.x, vertex.y);
    }

    gl.glEnd();
  }

  //****************************************************************************

  @Override
  public void draw(GL2 gl)
  {
    fillLeafCluster(gl);
    outlineLeafCluster(gl);
  }

  @Override
  public boolean containsPoint(double x, double y)
  {
    // TODO Auto-generated method stub
    return false;
  }
}
