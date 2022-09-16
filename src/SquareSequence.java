import java.awt.*;

public class SquareSequence {
    private Graphics g;
    private int width, height; // width and height of canvas
    private int centerWidth, centerHeight; // width and height from the center of the first square to the center of the last square
    private int[] origin; // array representing starting point to draw sequence of squares
    private int[] dir; // array representing direction the sequence will be drawn in
    private int[] offsetDir; // array representing the direction towards the center for all squares to offset by half their size
    private int numSquares;
    private int initSize;
    private int p; // number of pixels to increase size by
    private int[] sizes; // array to hold sizes of all squares in the sequence


    public SquareSequence(Graphics g, int width, int height, int[] origin, int[] dir, int[] offsetDir, int numSquares, int initSize, int p) {
        this.g = g;
        this.width = width;
        this.height = height;
        this.centerWidth = width - initSize;
        this.centerHeight = height - initSize;
        this.origin = origin;
        this.dir = dir;
        this.offsetDir = offsetDir;
        this.numSquares = numSquares;
        this.initSize = initSize;
        this.p = p;
        sizes = new int[numSquares]; // allocate one extra space in the sizes array
        sizes[0] = initSize - p;     // so we can reference the previous size for the first square
    }

    public void drawSquareInSequence(int i) {
        int idx = i + 1; // add one to i to start accessing at the second index of the sizes array
        int x, y, sign, correctiveTerm;

        // offset the indices by half of numSquares - 1 to change the range to (-numSquares-1)/2 -> (numSquares-1)/2, with 0 in the middle
        // then take the sign of that range, this gives us -1 for all indices up to the middle, 0 for the middle, and +1 for the rest
        // then multiply by -1 to flip the signs so we have +1 -> 0 -> -1 (this notation refers to left side, middle, and right side)
        // we will use this to increase (+1) by p for the first half and decrease (-1) by p for the second half,
        // but the middle is still 0, meaning it will not increase
        sign = -(int) Math.signum(i - (numSquares - 1) / 2);
        // this corrective term allows the center to increase correctly, it is 0 for both sides and +1 in the middle
        correctiveTerm = 1 - Math.abs(sign);
        // the size of the current square is determined by adding or subtracting from the size of the previous square,
        // with the (sign + correctiveTerm) being +1 -> +1 -> -1
        sizes[idx] = sizes[idx - 1] + (p * (sign + correctiveTerm));

        // makes it as if squares were drawn from their centers
        double sizeOffset = sizes[idx] / 2.0;
        // push the entire sequence towards the direction it is facing by half of the size of the first square
        double xFirstSquareOffset = (dir[0] * (initSize / 2.0));
        double yFirstSquareOffset = (dir[1] * (initSize / 2.0));
        // centerWidth gives correct total width available, numSquares - 1 is because we are only drawing one corner in each sequence
        double xInterval = (centerWidth / (double) (numSquares - 1));
        double yInterval = (centerHeight / (double) (numSquares - 1));
        // how far towards the direction of the sequence the square should go
        double xMoveAmount = ((dir[0] * i * xInterval));
        double yMoveAmount = ((dir[1] * i * yInterval));
        // offsets squares toward the center, regardless of which side they are on
        double xToCenterOffset = (offsetDir[0] * (sizes[idx] / 2.0));
        double yToCenterOffset = (offsetDir[1] * (sizes[idx] / 2.0));

        // cast to int at the end to avoid rounding errors
        x = (int) (origin[0] - sizeOffset + xFirstSquareOffset + xMoveAmount + xToCenterOffset);
        y = (int) (origin[1] - sizeOffset + yFirstSquareOffset + yMoveAmount + yToCenterOffset);
        g.drawRect(x, y, sizes[idx], sizes[idx]);
    }
}
