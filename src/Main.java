/* 
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
    }
}
*/ 

/* Task 3 */ 
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame { 
    public static void main (String[] args) throws Exception {
        Main window = new Main();
    }

    Grid grid;

    class Canvas extends JPanel { 
        public Canvas () { 
            setPreferredSize(new Dimension(720, 720));
            grid = new Grid(); 
        }

        @Override
        public void paint(Graphics g) { 
            grid.paint(g, getMousePosition()); 
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
        while (true) {
            this.repaint();
            
        }
    }

}

/* Task 4 





*/  

