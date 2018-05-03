package drawables.tree;

import java.awt.*;
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
  private static final Random random = new Random();

  private static final int NUM_SIDES = 6;

  private static final float HUE = 133.37f;
  private static final float SATURATION = 0.53f;
  private static final float BRIGHTNESS = 0.68f;

  private static final float MIN_BRIGHTNESS = 0.50f;
  private static final float MAX_BRIGHTNESS = 0.80f;

  private double cx;               // x-coordinate of center of leaf cluster
  private double cy;               // y-coordinate of center of leaf cluster
  private double radius;           // radius of leaf cluster

  private ArrayList<Point2D.Double> vertices; // vertices of leaf cluster

  private float h;              // hue component of the color
  private float s;              // saturation component of the color
  private float b;              // brightness component of the color
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
    
    generateRandomColor();
  }

  public LeafCluster(double cx, double cy, double radius, float h, float s, float b)
  {
    this.cx = cx;
    this.cy = cy;
    this.radius = radius;

    this.h = h;
    this.s = s;
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

  private void generateRandomColor()
  {
    // Set hue and saturation components to default values
    h = HUE;
    s = SATURATION;

    // Generate random brightness component within range
    b = (MAX_BRIGHTNESS - MIN_BRIGHTNESS) * (float)random.nextDouble() + MIN_BRIGHTNESS;
  }

  private void setColor(GL2 gl)
  {
    // Convert color components from HSB to RGB
    Color rgb = new Color(Color.HSBtoRGB(h, s, b));

    // Use RGB components to set color
    gl.glColor4f(rgb.getRed()/255.f, rgb.getGreen()/255.0f, rgb.getBlue()/255.0f, alpha);
  }

  private void fillLeafCluster(GL2 gl)
  {
    gl.glBegin(GL2.GL_POLYGON);
    setColor(gl);

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
