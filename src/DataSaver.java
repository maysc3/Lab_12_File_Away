import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
/**
 *
 * @author wulft
 * 
 * Demonstrates how to use Java NIO, a thread safe File IO library
 * to write a text file
 */
public class DataSaver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       // Test data the lines of the file we will write
       ArrayList <String>recs = new ArrayList<>();
       Scanner in = new Scanner(System.in);
       String csvrec = "";
       String firstName = "";
       String lastName = "";
       String idNum = "";
       String email = "";
       int yob = 0;
       boolean done = false;

       do {
           firstName = SafeInput.getNonZeroLenString(in,"Enter your first name");
           lastName = SafeInput.getNonZeroLenString(in,"Enter your last name");
           idNum = SafeInput.getRegExString(in,"Enter your ID Num","^\\d{6}$");
           email = SafeInput.getRegExString(in,"Enter your email",".+@.+");
           yob = SafeInput.getRangedInt(in,"Enter your year of birth",1000,9999);

           csvrec = firstName + ", " + lastName + ", " + idNum + ", " + email + ", " + yob;
           recs.add(csvrec);
           done = SafeInput.getYNConfirm(in,"Are you done");

       }while(!done);
       // uses a fixed known path:
       //  Path file = Paths.get("c:\\My Documents\\data.txt");
        
       // use the toolkit to get the current working directory of the IDE
       // will create the file within the Netbeans project src folder 
       // (may need to adjust for other IDE)
       // Not sure if the toolkit is thread safe...
       File workingDirectory = new File(System.getProperty("user.dir"));
       Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");
       
       try
       {
           // Typical java pattern of inherited classes
           // we wrap a BufferedWriter around a lower level BufferedOutputStream
           OutputStream out =
                   new BufferedOutputStream(Files.newOutputStream(file, CREATE));
           BufferedWriter writer = 
                   new BufferedWriter(new OutputStreamWriter(out));
           
           // Finally can write the file LOL!
           
           for(String rec : recs)
           {
               writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                            // 0 is where to start (1st char) the write
                            // rec. length() is how many chars to write (all)
               writer.newLine();  // adds the new line
               
           }
           writer.close(); // must close the file to seal it and flush buffer
           System.out.println("Data file written!");
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
       
    }
    
}
