package myPack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class anim implements ActionListener {

    /**
     * @author PayCity
     * Simple color animation as background on JPanel
     */

    Color color0 = Color.orange;
    Color color1 = Color.orange;
    Color color2 = Color.blue;
    int Duration = 5000;
    long startTime;

    JFrame f = new JFrame("Colors");
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    Animation anim = new Animation();

    public anim() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        f.setSize(screenWidth / 2, screenHeight / 2);
        f.setLocation(screenWidth / 4, screenHeight / 4);

        f.setLayout(null);

        panel.setBounds(0, 0, 200, 200);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JButton button = new JButton("Button");
        button.setBounds(20, 20, 100, 20);
        panel.add(button);

        anim.setBounds(0, 0, 150, 150);
        panel.add(anim);
        f.add(panel);

        f.setVisible(true);

        Timer timer = new Timer(5, this);
        timer.setInitialDelay(1000);
        startTime = 1000 + System.nanoTime() / 1000000;
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        long currentTime = System.nanoTime() / 1000000;
        long totalTime = currentTime - startTime;
        if (totalTime > Duration) {
            startTime = currentTime;
        }


        float fraction = (float)totalTime / Duration;
        fraction = Math.min(1.0f, fraction);
        int r = (int)(fraction * color2.getRed() + (1 - fraction) * color1.getRed());
        int g = (int)(fraction * color2.getGreen() + (1 - fraction) * color1.getGreen());
        int b = (int)(fraction * color2.getBlue() + (1 - fraction) * color1.getBlue());
        color0 = new Color(r, g, b);
        anim.repaint();
    }

    class Animation extends JLabel {

        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g) {
            g.setColor(color0);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

    }

    public static void main(String[] args) {
        new anim();
    }

}