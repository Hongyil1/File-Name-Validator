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
                String fileName= f.getName();
                // get file extension
                String[] splitList = fileName.split("\\.");
                if(splitList.length != 2){
                    System.out.printf("File '%s' failed validation.\n", fileName);
                    System.out.printf("File format should be‘Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv'\n");
                } else {
                    System.out.println("splitList: " + Arrays.toString(splitList));
                    String fileExtension = splitList[splitList.length - 1];
                    String[] nameList = splitList[0].split("-");

                    if(Validator.extensionCheck(fileExtension)){
                        System.out.println("correct file extension.");
                    }else {
                        System.out.printf("File '%s' failed validation.\n", fileName);
                        System.out.printf("Invalid File format.Expected 'csv' found '%s'\n", fileExtension);
                    }
                }
            } else {
                System.out.println("The file doesn't exist or the given location is a folder.");
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

    public static boolean prefixCheck(String filePrefix){
        return false;
    }

    public static boolean portfolioCheck(String filePorfolio){
        return false;
    }

    public static boolean dateCheck(String date){
        return false;
    }

}
