import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;


public class Main extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new); // calls the main constructor initializing the thinamabob
    }


    class Canvas extends JPanel {
        private static final int TRAIL_SIZE = 100;
        private final Queue<Point> mouseTrails = new LinkedList<>();
        private final Grid grid = new Grid();
        private boolean mouseMoved = false; // true or false if mouse moved


        public Canvas() {
            setPreferredSize(new Dimension(720, 720));
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    mouseMoved = true;
                    if (mouseTrails.size() >= TRAIL_SIZE) {
                        mouseTrails.poll(); // makes sure the dots dont go past 100
                    }
                    mouseTrails.add(e.getPoint()); // finds mouse pos
                    repaint(); //  repaints the thing so it can show the trail
                }
            });


           
            Timer timer = new Timer(100, e -> { // used to see if the mouse is moving, if not it clears the trail
                if (!mouseMoved) {
                    mouseTrails.clear(); // Clear trails if mouse hasn't moved
                    repaint(); // Repaint to update the display
                }
                mouseMoved = false; // Resets the boolean if statment
            });
            timer.start();
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Ensure the background is cleared
            grid.paint(g, getMousePosition());


            // Draw the mouse trails
            g.setColor(new Color(62, 62, 62)); // sets the colour to something thats really close to 44, 44, 44
            for (Point p : mouseTrails) { // i hate this if statement
                if (p != null) {
                    g.fillOval(p.x - 10, p.y - 10, 20, 20); // Draw circle centered at the mouse position
                }
            }
        }
    }
    private Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new Canvas());
        pack();
        setVisible(true);
    }
}
