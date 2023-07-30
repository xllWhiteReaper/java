
import java.awt.Font;

import javax.swing.JFrame;
import models.StopWatchLabel;
import models.TestStopWatchRunner;

public class App extends JFrame {
    public static void main(String[] args) {
        System.out.println("Works");
        TestStopWatchRunner stopWatchRunner = new TestStopWatchRunner();
        stopWatchRunner.init();

        JFrame frame = new JFrame("My App");

        frame.getContentPane().add(stopWatchRunner);

        frame.setSize(400, 400);
        frame.setVisible(true);
        // watch.setBackground(Color.white);
        // watch.setForeground(new Color(180, 0, 0));
        // setBackground(Color.white);
        // setLayout(new BorderLayout());
        // add(watch, BorderLayout.CENTER);
    }
}
