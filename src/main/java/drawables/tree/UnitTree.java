package drawables.tree;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.media.opengl.*;

import drawables.Drawable;
import drawables.Shape;
import reusable.Helpers;

//******************************************************************************

/**
 * The <CODE>UnitTree</CODE> class.<P>
 *
 * @author Ryan Browning
 */
public final class UnitTree implements Drawable, Shape
{
  private static final Random r = new Random();

  private static final double WIDTH = 300.0;
  private static final double HEIGHT = 500.0;

  private static final Color TRUNK_COLOR = new Color(166, 129, 62);
  private static final double TRUNK_WIDTH_RATIO = 0.075;
  private static final double TRUNK_HEIGHT_RATIO = 0.4;

  private static final int MIN_NUM_LEAF_CLUSTERS = 15;
  private static final int MAX_NUM_LEAF_CLUSTERS = 25;

  private static final double MIN_RADIUS_RATIO = 0.075;
  private static final double MAX_RADIUS_RATIO = 0.3;

  private static final double LEAF_CLUSTER_HEIGHT_RATIO = 0.80;

  //****************************************************************************

  private double x;   // x-coordinate of lower left corner of tree bounding box
  private double y;   // y-coordinate of lower left corner of tree bounding box
  private double w;   // maximum width of tree
  private double h;   // maximum height of tree

  private double trunkWidth;
  private double trunkHeight;

  private double minRadius;
  private double maxRadius;

  private ArrayList<Point2D.Double> trunkFillVertices;    // vertices to fill tree trunk
  private ArrayList<Point2D.Double> trunkOutlineVertices; // vertices to outline tree trunk

  private ArrayList<LeafCluster> leafClusters;            // geometric leaf clusters

  //****************************************************************************

  // Constructor
  public UnitTree()
  {
    // Initialize lower left corner of bounding box to origin
    x = 0.0;
    y = 0.0;

    // Initialize width and height
    w = WIDTH;
    h = HEIGHT;

    // Initialize trunk
    trunkWidth = TRUNK_WIDTH_RATIO * w;
    trunkHeight = TRUNK_HEIGHT_RATIO * h;

    calculateTrunkVertices();

    // Initialize minimum and maximum leaf cluster values
    minRadius = MIN_RADIUS_RATIO * w;
    maxRadius = MAX_RADIUS_RATIO * w;

    generateLeafClusters();
  }

  //****************************************************************************

  public double getWidth()
  {
    return w;
  }

  //****************************************************************************

  private void calculateTrunkVertices()
  {
    trunkFillVertices = new ArrayList<Point2D.Double>();
    trunkOutlineVertices = new ArrayList<Point2D.Double>();

    double center_x = w / 2;


    // Calculate vertices to fill tree trunk
    trunkFillVertices.add(new Point2D.Double(center_x - (1.5 * trunkWidth), y));
    trunkFillVertices.add(new Point2D.Double(center_x - (0.5 * trunkWidth), y + trunkWidth));
    trunkFillVertices.add(new Point2D.Double(center_x - (0.5 * trunkWidth), y)); // remove for outline

    trunkFillVertices.add(new Point2D.Double(center_x - (0.5 * trunkWidth), y + trunkHeight));
    trunkFillVertices.add(new Point2D.Double(center_x + (0.5 * trunkWidth), y + trunkHeight));

    trunkFillVertices.add(new Point2D.Double(center_x + (0.5 * trunkWidth), y)); // remove for outline
    trunkFillVertices.add(new Point2D.Double(center_x + (0.5 * trunkWidth), y + trunkWidth));
    trunkFillVertices.add(new Point2D.Double(center_x + (1.5 * trunkWidth), y));

    // Calculate vertices to outline tree trunk (copy fill vertices)
    for (Point2D.Double vertex : trunkFillVertices)
    {
      trunkOutlineVertices.add(new Point2D.Double(vertex.x, vertex.y));
    }

    // Remove fill vertices unnecessary for outline
    trunkOutlineVertices.remove(5);
    trunkOutlineVertices.remove(2);
  }

  private void generateLeafClusters()
  {
    leafClusters = new ArrayList<LeafCluster>();

    // Generate random number of leaf clusters
    int numClusters = r.nextInt(MAX_NUM_LEAF_CLUSTERS - MIN_NUM_LEAF_CLUSTERS) + MIN_NUM_LEAF_CLUSTERS;

    // Add each new leaf cluster to tree
    for (int i = 0; i < numClusters; i++)
    {
      // Generate random leaf cluster radius within range
      double radius = (maxRadius - minRadius) * r.nextDouble() + minRadius;

      // Calculate range for leaf cluster center
      double minCenterX = x + radius;       // minimum x-value for center of leaf cluster
      double maxCenterX = x + w - radius;   // maximum x-value for center of leaf cluster
      double minCenterY = (1 - LEAF_CLUSTER_HEIGHT_RATIO) * h + y + radius; // minimum y-value for center of leaf cluster
      double maxCenterY = y + h - radius;   // maximum y-value for center of leaf cluster

      // Generate random leaf cluster center within range
      double cx = (maxCenterX - minCenterX) * r.nextDouble() + minCenterX;
      double cy = (maxCenterY - minCenterY) * r.nextDouble() + minCenterY;

      leafClusters.add(new LeafCluster(cx, cy, radius));
    }
  }

  private void drawTrunk(GL2 gl)
  {
    // Set trunk color
    Helpers.setColor(gl, TRUNK_COLOR);

    // Fill left triangle
    gl.glBegin(GL2.GL_POLYGON);
    for (int i = 0; i < 3; i++)
    {
      gl.glVertex2d(trunkFillVertices.get(i).x, trunkFillVertices.get(i).y);
    }
    gl.glEnd();

    // Fill trunk
    gl.glBegin(GL2.GL_POLYGON);
    for (int i = 2; i < 6; i++)
    {
      gl.glVertex2d(trunkFillVertices.get(i).x, trunkFillVertices.get(i).y);
    }
    gl.glEnd();

    // Fill right triangle
    gl.glBegin(GL2.GL_POLYGON);
    for (int i = 5; i < 8; i++)
    {
      gl.glVertex2d(trunkFillVertices.get(i).x, trunkFillVertices.get(i).y);
    }
    gl.glEnd();

    // Outline trunk
    gl.glBegin(GL.GL_LINE_LOOP);
    gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f); // Black

    for (Point2D.Double vertex : trunkOutlineVertices)
    {
      gl.glVertex2d(vertex.x, vertex.y);
    }

    gl.glEnd();
  }

  private void drawLeafClusters(GL2 gl)
  {
    for (LeafCluster leafCluster : leafClusters)
    {
      leafCluster.draw(gl);
    }
  }

  //****************************************************************************

  @Override
  public void draw(GL2 gl)
  {
    drawTrunk(gl);
    drawLeafClusters(gl);
  }

  @Override
  public boolean containsPoint(double x, double y)
  {
    // TODO Auto-generated method stub
    return false;
  }
}
