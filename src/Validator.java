import java.io.File;
import java.util.Scanner;

public class Validator {

    public static void main(String args[]){

        // Read file location from console
        System.out.println("Please input the file location or 'exit' to end the program: ");
        Scanner scanner = new Scanner(System.in);
        String fileLocation = scanner.nextLine();

        while(!fileLocation.equals("exit")){
            // Check whether the file exists
            File f = new File(fileLocation.trim());
            if(f.exists() && !f.isDirectory()){
                System.out.println("File exist.");
                String fileName= f.getName();
                System.out.println(fileName);

                // get file extension
                String[] temList = fileName.split(".");
                String fileExtension = temList[-1]; // haven't finish

            } else {
                System.out.println("The file doesn't exist.");
            }

            System.out.println();
            System.out.println("Please input the file location or 'exit' to end the program: ");
            fileLocation = scanner.nextLine();
        }

    }

}
