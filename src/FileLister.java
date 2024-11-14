import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 * @author wulft
 * 
 * Use the thread safe NIO IO library to read a file
 */
public class FileLister
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        String fileName = "";
        String[] words;
        int line = 0;
        int charCnt = 0;
        int wordCnt = 0;

        
       try {
           // uses a fixed known path:
           //  Path file = Paths.get("c:\\My Documents\\data.txt");

           // use the toolkit to get the current working directory of the IDE
           // Not sure if the toolkit is thread safe...
           File workingDirectory = new File(System.getProperty("user.dir"));

           // Typiacally, we want the user to pick the file so we use a file chooser
           // kind of ugly code to make the chooser work with NIO.
           // Because the chooser is part of Swing it should be thread safe.
           chooser.setCurrentDirectory(workingDirectory);
           // Using the chooser adds some complexity to the code.
           // we have to code the complete program within the conditional return of
           // the filechooser because the user can close it without picking a file

           if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
               selectedFile = chooser.getSelectedFile();
               Path file = selectedFile.toPath();
               // Typical java pattern of inherited classes
               // we wrap a BufferedWriter around a lower level BufferedOutputStream
               InputStream in =
                       new BufferedInputStream(Files.newInputStream(file, CREATE));
               BufferedReader reader =
                       new BufferedReader(new InputStreamReader(in));

               // Finally we can read the file LOL!
               while (reader.ready()) {
                   rec = reader.readLine();
                   line++;
                   charCnt += rec.length();
                   words = rec.split(" ");
                   wordCnt += words.length;
                   // echo to screen
                   System.out.printf("\nLine %4d %-60s ", line, rec);
               }
               reader.close(); // must close the file to seal it and flush buffer
               System.out.println("\n\nData file read!");

               System.out.println("The filename is " + file.getFileName());
               System.out.println("The total lines are " + line);
               System.out.println("The total words are " + wordCnt);
               System.out.println("The characters are " + charCnt);


           } else  // User closed the chooser without selecting a file
           {
               System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
           }
       }
       catch (FileNotFoundException e)
       {
           System.out.println("File not found!!!");
           e.printStackTrace();
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
    }
    
}
