package drawables.tree;

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
  private static final double WIDTH = 100.0;
  private static final double HEIGHT = 400.0;

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

  }

  private void drawTrunk(GL2 gl)
  {

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
