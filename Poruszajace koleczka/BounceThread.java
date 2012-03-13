import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Wyœwietla animowan¹ pi³kê.
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
 */
public class BounceThread
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               JFrame frame = new BounceFrame();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}

/**
 * Klasa implementuj¹ca interfejs Runnable tworz¹ca animacjê pi³ki.
 */
class BallRunnable implements Runnable
{
   /**
    * Tworzy obiekt Runnable.
    * @aBall pi³ka
    * @aPanel komponent, w którym odbija siê pi³ka
    */
   public BallRunnable(Ball aBall, Component aComponent)
   {
      ball = aBall;
      component = aComponent;
   }

   public void run()
   {
      try
      {
         for (int i = 1; i <= STEPS; i++)
         {
            ball.move(component.getBounds());
            component.repaint();
            Thread.sleep(DELAY);
         }
      }
      catch (InterruptedException e)
      {
      }
   }

   private Ball ball;
   private Component component;
   public static final int STEPS = 1000;
   public static final int DELAY = 5;
}

/**
 * Ramka z panelem i przyciskami.
 */
class BounceFrame extends JFrame
{
   /**
    * Tworzy ramkê z komponentem zawieraj¹cym pi³kê i przyciski Start oraz Zamknij.
    */
   public BounceFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      setTitle("BounceThread");

      comp = new BallComponent();
      add(comp, BorderLayout.CENTER);
      JPanel buttonPanel = new JPanel();
      addButton(buttonPanel, "Start", new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               addBall();
            }
         });

      addButton(buttonPanel, "Zamknij", new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               System.exit(0);
            }
         });
      add(buttonPanel, BorderLayout.SOUTH);
   }

   /**
    * Dodaje przycisk do kontenera.
    * @param c kontener
    * @param title tytu³ przycisku
    * @param listener s³uchacz akcji przycisku
    */
   public void addButton(Container c, String title, ActionListener listener)
   {
      JButton button = new JButton(title);
      c.add(button);
      button.addActionListener(listener);
   }

   /**
    * Dodaje pi³kê do obszaru roboczego i uruchamia w¹tek wykonuj¹cy kod odpowiedzialny za jej odbijanie.
    */
   public void addBall()
   {
      Ball b = new Ball();
      comp.add(b);
      Runnable r = new BallRunnable(b, comp);
      Thread t = new Thread(r);
      t.getState();
      t.start();
   }

   private BallComponent comp;
   public static final int DEFAULT_WIDTH = 450;
   public static final int DEFAULT_HEIGHT = 350;
   public static final int STEPS = 1000;
   public static final int DELAY = 3;
}
