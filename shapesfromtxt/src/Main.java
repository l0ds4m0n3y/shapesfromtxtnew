import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main( String [] args ) throws IOException {
        // Create a Scene object.
        Scene scn = new Scene();

        // Load the scene
        scn.load("poopy.txt");
        System.out.println(scn.aveWidthOfRectanglesContaining(250, 250));

        // Create a DrawingCanvas and get the Graphics object.
        DrawingCanvas can = new DrawingCanvas(1024, 768);
        Graphics g = can.getGraphics();

        // Draw the Scene
        scn.draw(g);
        scn.save("poopy.txt");
    }
}
