import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends Shape{
    private int a;
    private int b;
    private static int numEllipses;
    
    public Ellipse(int px, int py, int a, int b, boolean f, Color c) {
        super(px, py, f, c);
        this.a = a;
        this.b = b;
        numEllipses++;
    }

    public boolean contains(int px, int py){
        double ex = Math.pow((px - this.x), 2);
        double why = Math.pow((py - this.y), 2);
        double exden = a * a;
        double whyden = b * b;
        return((ex / exden) + (why / whyden) <= 1);
    }

    public void draw(Graphics g){
        g.setColor(color);
        if (filled)
            g.fillOval(x - a, y - b, 2 * a, 2 * b);
        else            
            g.drawOval(x - a, y - b, 2 * a, 2 * b);
    }

    public static int getNumEllipses() {
        return numEllipses;
    }


    @Override
    public String toString() {
        return super.toString() + " " + a + " " + b;
    }
}
