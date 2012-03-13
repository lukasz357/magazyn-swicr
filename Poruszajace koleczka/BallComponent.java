import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Komponent rysuj�cy pi�ki.
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
 */
public class BallComponent extends JComponent
{
   /**
    * Dodaje pi�k� do panelu.
    * @param b pi�ka
    */
   public void add(Ball b)
   {
      balls.add(b);
   }

   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      for (Ball b : balls)
      {
         g2.fill(b.getShape());
      }
   }

   private ArrayList<Ball> balls = new ArrayList<Ball>();
}

