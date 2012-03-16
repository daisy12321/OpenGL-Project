package project;
import java.io.*;
import java.util.Scanner;

public class Parser {
  public static float[][] coords;
  
  /**
   Constructor.
   @param aFileName full name of an existing, readable file.
 * @throws FileNotFoundException 
  */
  public Parser(String string) throws FileNotFoundException{
	fFile = new File(string);  
	process();
	log("Done.");

  }
  
  public Parser() throws FileNotFoundException {
	fFile = new File("C:\\Users\\Daisy\\Documents\\Middlebury\\CSCI461\\untitled3.obj");  
	process();
	log("Done.");
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
  public void process() throws FileNotFoundException{
	Scanner scanner = new Scanner(new FileReader(fFile));
    //use a second Scanner to parse the content of each line 
    scanner.useDelimiter(" ");
    int j = 0;
    coords = new float[3][400];
    while ( scanner.hasNext() ){
      //int face = j/12;
      coords[j % 3][j/3] = Float.valueOf(scanner.next());
      //log("Now on the " + (j/3) + "th vertex with " + coords[j % 3][j/3]);
      j++;
    }
    //no need to call scanner.close(), since the source is a String
  }
  
  // PRIVATE 
  private final File fFile;
  
  private static void log(Object aObject){
    System.out.println(String.valueOf(aObject));
  }
  
} 
