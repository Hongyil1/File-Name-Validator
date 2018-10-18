import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Validator {

    private String fileName;
    private String fileExtension;
    private String filePrefix;
    private String filePortfolio;
    private String fileSequence;
    private String fileDate;
    private static String[] portList = new String[] {"A", "B", "C"};

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
                Validator validator = new Validator(fileName);

                // get file extension
                String[] splitList = fileName.split("\\.");
                validator.fileExtension = splitList[splitList.length - 1];
                if(splitList.length != 2){
                    System.out.printf("File '%s' failed validation.\n", validator.fileName);
                    System.out.printf("File format should be‘Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv'\n");
                } else {
                    String[] nameList = splitList[0].split("_");
                    System.out.println("nameList: " + Arrays.toString(nameList));
                    if(nameList.length == 3 || nameList.length == 4){
                        validator.filePrefix = nameList[0];
                        validator.filePortfolio = nameList[1];
                        validator.fileDate = nameList[2];
                        if(nameList.length == 4) {validator.fileSequence = nameList[3];}
                        System.out.println("prefix: " + validator.filePrefix + "; portfolio: " + validator.filePortfolio
                                + "; date: " + validator.fileDate + "; seq: " + validator.fileSequence);

                    } else {
                        System.out.printf("File '%s' failed validation.\n", fileName);
                        System.out.printf("File format should be‘Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv'\n");
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

    public Validator(String fileName){
        this.fileName = fileName;
    }

    private boolean extensionCheck(String fileExtension){
        if(fileExtension.equals("csv")){
            return true;
        }else {
            return false;
        }
    }

    private boolean prefixCheck(String filePrefix){
        if(filePrefix.equals("Test")){
            return true;
        }else{
            return false;
        }
    }

    private boolean portfolioCheck(String filePorfolio){
        for(int i =0; i< Validator.portList.length; i++){
            if(Validator.portList[i].equals(filePorfolio)){
                return true;
            }
        }
        return false;
    }

    private boolean dateCheck(String date){
        return false;
    }

}
