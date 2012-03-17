package project;
import java.io.*;
import java.util.Scanner;

public class Parser {
  public static Vertex3[] vertices;
  public static Face3[] faces;
  private Mode mode;
  private enum Mode {
	    VERTICES, FACES, NORMALS 
	}

  private int num_v = 1; //counting start at 1!
  private int num_f = 1; //counting start at 1!
  /**
   Constructor.
   @param aFileName full name of an existing, readable file.
 * @throws FileNotFoundException 
  */
  public Parser(String string) throws FileNotFoundException{
	fFile = new File(string);  
	processLineByLine();
	log("Done.");

  }
  
  public Parser() throws FileNotFoundException {
	fFile = new File("C:\\Users\\Daisy\\Documents\\Middlebury\\CSCI461\\untitled2.obj");  
	processLineByLine();
	log("Done.");
  }
  
  public final void processLineByLine() throws FileNotFoundException {
	    //Note that FileReader is used, not File, since File is not Closeable
	    Scanner scanner = new Scanner(new FileReader(fFile));
	    try {
	      //first use a Scanner to get each line
	      while ( scanner.hasNextLine() ){
	        processLine( scanner.nextLine() );
	      }
	    }
	    finally {
	      //ensure the underlying stream is always closed
	      //this only has any effect if the item passed to the Scanner
	      //constructor implements Closeable (which it does in this case).
	      scanner.close();
	    }
	  }
  
  /** 
   Overridable method for processing lines in different ways.
    
   <P>This simple default implementation expects simple name-value pairs, separated by an 
   '=' sign. Examples of valid input : 
   <tt>height = 167cm</tt>
   <tt>mass =  65kg</tt>
   <tt>disposition =  "grumpy"</tt>
   <tt>this is the name = this is the value</tt>
 * @throws FileNotFoundException 
  */
  protected void processLine(String aLine){
    //use a second Scanner to parse the content of each line 
    Scanner scanner = new Scanner(aLine);
    //use a second Scanner to parse the content of each line 
    scanner.useDelimiter(" ");
    vertices = new Vertex3[100];
    faces = new Face3[100];
    //coords = new float[3][400];
    while ( scanner.hasNext() ){
      String tmp = scanner.next();
      //System.out.println(tmp);
      //int face = j/12;
      if (tmp.contains("v")) {mode = Mode.VERTICES;}
      else if (tmp.contains("f")) {mode = Mode.FACES;}
      else if (mode == Mode.VERTICES){
    	  float x = Float.parseFloat(tmp);
    	  float y = Float.parseFloat(scanner.next());
    	  float z = Float.parseFloat(scanner.next());
    	  vertices[num_v] = new Vertex3(x, y, z);
    	  log("For the "+ num_v+"th vertex, "+vertices[num_v].x+" "+vertices[num_v].y+" "+vertices[num_v].z);
    	  num_v++;
    	  //System.out.println(num_v);
      } else if (mode == Mode.FACES) {
    	  int a = Integer.parseInt(tmp);
    	  int b = Integer.parseInt(scanner.next());
    	  int c = Integer.parseInt(scanner.next());
    	  faces[num_f] = new Face3(a, b, c);
    	  log("For the "+ num_f+"th face, "+faces[num_f].a+" "+faces[num_f].b+" "+faces[num_f].c);
     	  num_f++;
     	  //System.out.println(num_f);
      } else {System.out.println("Comments");}
    }
    //no need to call scanner.close(), since the source is a String
  }
  
  // PRIVATE 
  private final File fFile;
  
  private static void log(Object aObject){
    System.out.println(String.valueOf(aObject));
  }
  
} 
