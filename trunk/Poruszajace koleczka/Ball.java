import java.awt.geom.*;

/**
   Pi³ka poruszaj¹ce siê w obrêbie i odbijaj¹ca od krawêdzi prostok¹ta.
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
*/
public class Ball
{
   /**
      Przesuwa pi³kê w nowe po³o¿enie i odwraca jej kierunek, jeœli uderzy w krawêdŸ.
   */
   public void move(Rectangle2D bounds)
   {
      x += dx;
      y += dy;
      if (x < bounds.getMinX())
      { 
         x = bounds.getMinX();
         dx = -dx;
      }
      if (x + XSIZE >= bounds.getMaxX())
      {
         x = bounds.getMaxX() - XSIZE; 
         dx = -dx; 
      }
      if (y < bounds.getMinY())
      {
         y = bounds.getMinY(); 
         dy = -dy;
      }
      if (y + YSIZE >= bounds.getMaxY())
      {
         y = bounds.getMaxY() - YSIZE;
         dy = -dy; 
      }
   }

   /**
      Tworzy kszta³t pi³ki w jej aktualnym po³o¿eniu.
   */
   public Ellipse2D getShape()
   {
      return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
   }

   private static final int XSIZE = 15;
   private static final int YSIZE = 15;
   private double x = 0;
   private double y = 0;
   private double dx = 1;
   private double dy = 1;
}
