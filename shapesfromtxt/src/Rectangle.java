import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape{
    private int width;
    private int height;
    private static int numRectangles = 0;

    public Rectangle(int px, int py, int w, int h, boolean f, Color c) {
        super(px, py, f, c);
        this.width = w;
        this.height = h;
        numRectangles++;
    }

    public boolean contains(int px, int py){
        return ((this.x <= px && px <= this.x + width) && (this.y <= py && this.y <= py + height));
    }

    public void draw(Graphics g){
        g.setColor(color);
        if(filled)
            g.fillRect(x, y, width, height);
        else
            g.drawRect(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public static int getNumRectangles() {
        return numRectangles;
    }

    @Override
    public String toString() {
        return super.toString() + " " + width + " " + height;
    }
}
