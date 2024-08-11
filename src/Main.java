import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;


public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }


    class Canvas extends JPanel {
        Grid grid = new Grid();
        private final int TRAIL_SIZE = 100; // makes it so that the max amount of dots is 100
        private final Queue<Point> mouseTrails = new LinkedList<>();


        public Canvas() {
            setPreferredSize(new Dimension(720, 720));


            // Mouse motion listener to capture mouse movements
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (mouseTrails.size() >= TRAIL_SIZE) {
                        mouseTrails.poll(); // Remove oldest position if we have reached max size
                    }
                    mouseTrails.add(e.getPoint()); // Adds current position to the list
                    repaint(); // repaints the thing so it can show the trail
                }
            });
        }


        @Override
        public void paint(Graphics g) {
            super.paint(g); // Makes it so the background is always painted making show the trail on top
            grid.paint(g, getMousePosition());


            g.setColor(new Color(13, 168, 189)); // Sets the trail to blue-ish
            for (Point p : mouseTrails) {
                if (p != null) {
                    g.fillOval(p.x - 10, p.y - 10, 20, 20); // draws the cirlce at the mouse
                }
            }
        }
    }




    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }


    public void run() {
       
    }




}
