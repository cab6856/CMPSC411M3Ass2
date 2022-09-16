import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class SquareGraphics {
    private Graphics g;
    private int width;
    private int height;
    private int[] origin;
    private int[] dir;
    private int[] offsetDir;
    private int numSquares;
    private int initSize;
    private int p;
    private int[] sizes;
    private int[][] positions;


    public SquareGraphics(Graphics g, int width, int height, int[] origin, int[] dir, int[] offsetDir, int numSquares, int initSize, int p) {
        this.g = g;
        this.width = width;
        this.height = height;
        this.origin = origin;
        this.dir = dir;
        this.offsetDir = offsetDir;
        this.numSquares = numSquares;
        this.initSize = initSize;
        this.p = p;
        sizes = new int[numSquares];
        sizes[0] = initSize - p;
        positions = new int[numSquares][2];
//        positions[0][0];
//        positions[0][1];
    }

    public void drawSquareInSequence(int i) {
        int idx = i+1;
        int x, y, sign, correctiveTerm, shiftedi, size, interval;
        shiftedi = i - (numSquares-1)/2;
        sign = -(int)Math.signum(shiftedi);
        correctiveTerm = 1 - Math.abs(sign);
        sizes[idx] = sizes[idx-1]+(p*(sign+correctiveTerm));
        positions[i][0] = origin[0] + (dir[0] * i * (width/(numSquares-1))) - ((sizes[idx]/2));
        positions[i][1] = origin[1] + (dir[1] * i * (height/(numSquares-1))) - ((sizes[idx]/2));


        g.drawRect(positions[i][0], positions[i][1], sizes[idx], sizes[idx]);
    }
}
