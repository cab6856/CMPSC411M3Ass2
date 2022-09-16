import javax.swing.*;
import java.awt.*;

public class SquareDrawing extends JFrame {
    JPanel canvas = new JPanel();

    private int numSquares = 15;
    private int p = 8;
    private int initSize = 15;

    public SquareDrawing() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(canvas, BorderLayout.CENTER);
        setSize(500, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SquareDrawing();
    }

    @Override
    public void paint(Graphics g) {
        Graphics g2 = canvas.getGraphics();
        int width = canvas.getWidth(), height = canvas.getHeight();

        super.paint(g);
        g2.setColor(Color.black);

        SquareSequence sgTop = new SquareSequence(g2, width, height, new int[]{0, 0}, new int[]{1, 0}, new int[]{0, 1}, numSquares, initSize, p);
        SquareSequence sgRight = new SquareSequence(g2, width, height, new int[]{width, 0}, new int[]{0, 1}, new int[]{-1, 0}, numSquares, initSize, p);
        SquareSequence sgBottom = new SquareSequence(g2, width, height, new int[]{width, height}, new int[]{-1, 0}, new int[]{0, -1}, numSquares, initSize, p);
        SquareSequence sgLeft = new SquareSequence(g2, width, height, new int[]{0, height}, new int[]{0, -1}, new int[]{1, 0}, numSquares, initSize, p);

        for (int i = 0; i < numSquares - 1; i++) {
            sgTop.drawSquareInSequence(i);
            sgRight.drawSquareInSequence(i);
            sgBottom.drawSquareInSequence(i);
            sgLeft.drawSquareInSequence(i);
        }
    }
}