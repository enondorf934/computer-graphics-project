package drawables.tree;

import java.awt.*;
import java.util.*;
import javax.media.opengl.*;

import drawables.Drawable;
import drawables.Shape;

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

  private static final int MIN_NUM_LEAF_CLUSTERS = 12;
  private static final int MAX_NUM_LEAF_CLUSTERS = 20;

  private static final double MIN_LEAF_WIDTH_RATI0 = 0.1;
  private static final double MAX_LEAF_WIDTH_RATIO = 0.5;

  private static final double MIN_LEAF_HEIGHT_RATI0 = 0.1;
  private static final double MAX_LEAF_HEIGHT_RATIO = 0.5;

  private static final double LEAF_CLUSTER_HEIGHT_RATIO = 0.75;

  private static final Color TRUNK_COLOR = new Color(166, 129, 62);
  private static final double TRUNK_WIDTH_RATIO = 0.1;
  private static final double TRUNK_HEIGHT_RATIO = 0.4;

  private double x;   // x-coordinate of lower left corner of tree bounding box
  private double y;   // y-coordinate of lower left corner of tree bounding box
  private double w;   // maximum width of tree
  private double h;   // maximum height of tree

  private ArrayList<LeafCluster> leafClusters;  // geometric leaf clusters

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

    // Generate random leaf clusters
    generateLeafClusters();
  }

  //****************************************************************************

  private void generateLeafClusters()
  {
    leafClusters = new ArrayList<LeafCluster>();

    // Generate random number of leaf clusters
    int numClusters = r.nextInt(MAX_NUM_LEAF_CLUSTERS - MIN_NUM_LEAF_CLUSTERS) + MIN_NUM_LEAF_CLUSTERS;
    System.out.println(numClusters);

    // Add each new leaf cluster to tree
    for (int i = 0; i < numClusters; i++)
    {
      // Generate random leaf cluster width (height) relative to tree width (height)
      // and within minimum and maximum ratios of tree width (height)
      double leaf_w = ((MAX_LEAF_WIDTH_RATIO - MIN_LEAF_WIDTH_RATI0) * r.nextDouble() + MIN_LEAF_WIDTH_RATI0) * w;
      double leaf_h = ((MAX_LEAF_HEIGHT_RATIO - MIN_LEAF_HEIGHT_RATI0) * r.nextDouble() + MIN_LEAF_HEIGHT_RATI0) * h;

      // Generate random leaf cluster position relative to tree width and hight
      // and given leaf cluster width and height
      double max_x = x + w - leaf_w;
      double max_y = y + h - leaf_h;
      double min_y = (1 - LEAF_CLUSTER_HEIGHT_RATIO) * h + y;
      double leaf_x = (max_x - x) * r.nextDouble() + x;
      double leaf_y = (max_y - min_y) * r.nextDouble() + min_y;

      leafClusters.add(new LeafCluster(leaf_x, leaf_y, leaf_w, leaf_h));
    }
  }

  private void drawTrunk(GL2 gl)
  {
    gl.glBegin(GL.GL_LINE_LOOP);
    gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f); // Black
    gl.glVertex2d(x, y);
    gl.glVertex2d(x, y + h);
    gl.glVertex2d(x + w, y + h);
    gl.glVertex2d(x + w, y);
    gl.glEnd();
  }

  private void drawLeafClusters(GL2 gl)
  {
    for (LeafCluster leafCluster : leafClusters)
    {
      leafCluster.draw(gl);
    }
  }

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
