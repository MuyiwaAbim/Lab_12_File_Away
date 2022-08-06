
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        PrintWriter out;
        Scanner in; // in is the file we are reading
        Scanner console = new Scanner(System.in);
        File selectedFile;
        JFileChooser chooser = new JFileChooser();
        String line;
        String outFileName = "";
        String defaultFileName = "default.txt";
        int lineCount = 0;
        int printCount = 0;
        int letterCount = 0;
        int wordCount = 0;
        try
        {
            // Set the JFileChooser to use the Netbeans projcet folder
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();  // this is a File object not a String filename
                in = new Scanner(selectedFile);  // Open the file for reading

                while(in.hasNextLine())
                {
                    printCount++;
                    line = in.nextLine();
                    lineCount++;
                    // Show the file on the console
//                    System.out.printf("\nLine %3d: %-30s", lineCount, line);
                    // if the current character is not a space/empty, then...
                    if (!line.equalsIgnoreCase("")) {
                        String tempSwitch = line.replaceAll("\\s+", "");
                        letterCount += tempSwitch.length();
                        wordCount += line.split(" ").length;
                    }
                }
                System.out.println();
                System.out.println("Number of chars: " + letterCount);
                System.out.println("Number of words: " + wordCount);

                in.close();
            }
            else  // they didn't select a file so exit...
            {
                JOptionPane.showMessageDialog(null, "Cancelled by User.  Exiting...");
                System.exit(0);
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found error!");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Your file has " + printCount + " lines.");
        System.out.println("Your file name is " + chooser.getSelectedFile().getName());
    }
}
