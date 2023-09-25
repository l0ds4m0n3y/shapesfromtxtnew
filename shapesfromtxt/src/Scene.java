import java.awt.Graphics;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scene {

    /**
     * The list of Shape objects that make up this Scene.
     */
    private ArrayList<Shape> shapes;

    /**
     * Creates an empty Scene.
     */
    public Scene() {
        shapes = new ArrayList<>();
    }

    /**
     * Loads Scene data from the given file.
     *
     * @param fileName the name of the file to load
     * @throws IOException when an error occurs when reading from the file.
     */
    public void load( String fileName ) throws IOException {
        File inFile = new File(fileName);
        Scanner scan = new Scanner(inFile);

        while(scan.hasNext()) {
            String objectType = scan.next();

            if( !( objectType.equals("E") || objectType.equals("R") ) ) {
                throw new RuntimeException("Invalid object type found in input file");
            }

            int x = scan.nextInt();
            int y = scan.nextInt();
            boolean fill = scan.nextBoolean();
            int r = scan.nextInt();
            int g = scan.nextInt();
            int b = scan.nextInt();

            Shape s = null;
            if( objectType.equals("E")  ) {
                int aLen = scan.nextInt();
                int bLen = scan.nextInt();
                s = new Ellipse(x, y, aLen, bLen, fill, new Color(r, g, b));
            } else if(objectType.equals("R")) {
                int width = scan.nextInt();
                int height = scan.nextInt();
                s = new Rectangle(x, y, width, height, fill, new Color(r, g, b));
            }
            shapes.add(s);
        }
        scan.close();
    }

    /**
     * Draws the entire Scene to the given Graphics object.
     *
     * @param g the Graphics object to which the Scene is to be drawn.
     */
    public void draw(Graphics g) {
        for(Shape s : shapes){
            s.draw(g);
        }
    }

    /**
     * Finds and returns the first Shape in this Scene that contains the given point.
     * If no Shapes contain the point, returns null.
     *
     * @param x the x-coordinate of the point to test
     * @param y the y-coordinate of the point to test
     * @return the first Shape that contains (x,y), or null if no Shapes contain the point
     */
    public Shape findFirstContaining( int x, int y) {
        for(Shape s : shapes){
            if(s.contains(x, y)){
                return s;
            }
        }
        return null;
    }

    /**
     * Returns all Shapes that contain the given point.
     * @param x the x-coordinate of the point to test.
     * @param y the y-coordinate of the point to test.
     * @return an array of Shape objects that contain (x,y)
     */
    public Shape[] findAllContaining( int x, int y ) {
        ArrayList<Shape> shapesList = new ArrayList<>();
        for(Shape s : shapes){
            if (s.contains(x, y)) shapes.add(s);
        }
        return shapesList.toArray(new Shape[shapesList.size()]);
    }

    /**
     * Counts and returns the number of ellipses in this Scene.
     * @return the number of Ellipse objects.
     */
    public int numEllipses() {
        return Ellipse.getNumEllipses();
    }

    /**
     * Returns the first Ellipse object that contains the given point.  If no Ellipses
     * contain the point, returns null.
     * @param x the x-coordinate of the point to test.
     * @param y the y-coordinate of the point to test.
     * @return the first Ellipse object containing (x,y) or null.
     */
    public Ellipse findFirstEllipseContaining( int x, int y ) {
        for(Shape s : shapes){
            if(s instanceof Ellipse && s.contains(x, y)){
                return (Ellipse) s;
            }
        }
        return null;
     }

    /**
     * Computes and returns the average width of all Rectangle objects that
     * contain the given point.  If no Rectangles contain the point, returns 0.0.
     *
     * @param x the x-coordinate of the point to test.
     * @param y the y-coordinate of the point to test.
     * @return the average width of all Rectangle objects containing (x,y), or 0.0 if
     *         no Rectangles contain (x,y).
     */
    public double aveWidthOfRectanglesContaining( int x, int y ) {
        double sumOfWidth = 0;
        double numOfRect = 0;
        for(Shape s : shapes){
            if(s instanceof Rectangle && s.contains(x, y)){
                sumOfWidth += ((Rectangle)s).getWidth();
                numOfRect++;
            }
        }
        return sumOfWidth / numOfRect;
    }

    /**
     * Saves this Scene to a file in a format that is suitable for loading via the
     * load method above. (EXTRA CREDIT)
     *
     * @param fileName the name of the file to save to.
     */
    public void save( String fileName ) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for(Shape s : shapes){
            if(s instanceof Rectangle) 
                writer.write("R ");
            else 
                writer.write("E ");
            writer.write(s.toString());
            writer.write('\n');
        }
        writer.close();
    }
}