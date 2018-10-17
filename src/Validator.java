import java.io.File;
import java.util.Arrays;
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
                System.out.println("File Name: " + fileName);

                // get file extension
                String[] splitList = fileName.split("\\.");
                if(splitList.length <= 1){
                    System.out.println("The file doesn't have extension.");
                }else {
                    System.out.println("splitList: " + Arrays.toString(splitList));
                    String fileExtension = splitList[splitList.length - 1]; // haven't finish
                    System.out.println("fileExtension: " + fileExtension);
                    if(Validator.extensionCheck(fileExtension)){
                        System.out.println("correct file extension.");
                    }else {
                        System.out.printf("File '%s' failed validation.\n", fileName);
                        System.out.printf("Invalid File format.Expected 'csv' found '%s'\n", fileExtension);
                    }
                }
            } else {
                System.out.println("The file doesn't exist.");
            }

            System.out.println();
            System.out.println("Please input the file location or 'exit' to end the program: ");
            fileLocation = scanner.nextLine();
        }

    }

    public static boolean extensionCheck(String fileExtension){

        if(fileExtension.equals("csv")){
            return true;
        }else {
            return false;
        }
    }

}
