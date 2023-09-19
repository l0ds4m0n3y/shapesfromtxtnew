import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main( String [] args ) throws IOException {
        // Create a Scene object.
        Scene scn = new Scene();

        // Load the scene
        scn.load("simple.txt");
        scn.aveWidthOfRectanglesContaining(0, 0);
        System.out.println(Rectangle.getNumRectangles());

        // Create a DrawingCanvas and get the Graphics object.
        DrawingCanvas can = new DrawingCanvas(1024, 768);
        Graphics g = can.getGraphics();

        // Draw the Scene
        scn.draw(g);
    }
}
