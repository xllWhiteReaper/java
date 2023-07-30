package models;

/*
      A trivial applet that tests the StopWatchTimer component.
      The applet just creates and shows a StopWatchTimer.
   */

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class TestStopWatchRunner extends Applet {

   public void init() {

      StopWatchLabel watch = new StopWatchLabel();
      watch.setFont(new Font("SansSerif", Font.BOLD, 24));
      watch.setBackground(Color.white);
      watch.setForeground(new Color(180, 0, 0));
      setBackground(Color.white);
      setLayout(new BorderLayout());
      add(watch, BorderLayout.CENTER);

   }

}