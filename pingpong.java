import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PingPongGame extends JPanel implements ActionListener, KeyListener {
    private int ballX = 200, ballY = 200; // Initial ball position
    private int ballXSpeed = 2, ballYSpeed = 2; // Ball movement speed
    private int paddleY = 300; // Paddle position
    private boolean upKey = false, downKey = false; // Paddle movement flags

    public PingPongGame() {
        Timer timer = new Timer(5, this); // Create a timer
        JFrame frame = new JFrame("Ping Pong Game"); // Create a frame
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);

        timer.start(); // Start the game loop
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600); // Set the background

        g.setColor(Color.WHITE);
        g.fillRect(390, paddleY, 10, 80); // Draw the paddle

        g.fillOval(ballX, ballY, 20, 20); // Draw the ball
    }

    public void actionPerformed(ActionEvent e) {
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        if (ballY <= 0 || ballY >= 560) {
            ballYSpeed = -ballYSpeed; // Bounce off top and bottom
        }

        if (ballX <= 0 || (ballX <= 400 && ballY >= paddleY && ballY <= paddleY + 80)) {
            ballXSpeed = -ballXSpeed; // Bounce off the paddle or the left wall
        }

        if (ballX >= 780) {
            ballX = 200;
            ballY = 200;
            ballXSpeed = 2;
            ballYSpeed = 2;
        }

        if (upKey && paddleY > 0) {
            paddleY -= 5;
        }
        if (downKey && paddleY < 520) {
            paddleY += 5;
        }

        repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            upKey = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downKey = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            upKey = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downKey = false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PingPongGame());
    }
}
