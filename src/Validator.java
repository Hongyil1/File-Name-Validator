import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Validator {

    private String fileName;
    private String fileExtension;
    private String filePrefix;
    private String filePortfolio;
    private String fileSequence;
    private String fileDate;
    private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String args[]){

        // Read file location from console
        System.out.println("Please input the file location or 'exit' to end the program: ");
        Scanner scanner = new Scanner(System.in);
        String fileLocation = scanner.nextLine();

        while(!fileLocation.equals("exit")){

            // Check whether the file exists
            File f = new File(fileLocation.trim());
            String fileName= f.getName();
            Validator validator = new Validator(fileName);

            if(validator.fileLocationCheck(f)){
                if(validator.fileNameCheck(validator.fileName)){
                    String[] splitList = fileName.split("\\.");
                    validator.fileExtension = splitList[splitList.length - 1];
                    String[] nameList = splitList[0].split("_");
                    validator.filePrefix = nameList[0];
                    validator.filePortfolio = nameList[1];
                    validator.fileDate = nameList[2];
                    if(nameList.length == 4) {validator.fileSequence = nameList[3];}
                    if(!validator.portfolioCheck(validator.filePortfolio)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.printf("PortfolioCode should be A/B/C found %s.\n", validator.filePortfolio);
                    }else if(!validator.dateCheck(validator.fileDate)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.println("Valuation Date is not a valid date format 'ddmmyyyy'.\n");
                    }else if(!validator.prefixCheck(validator.filePrefix)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.printf("Prefix for the file should be 'Test' found '%s'.\n", validator.filePrefix);
                    }else if(!validator.extensionCheck(validator.fileExtension)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.printf("Invalid File format.Expected 'csv' found '%s'.\n", validator.fileExtension);
                    } else if(!validator.sequenceCheck(validator.fileSequence)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.printf("Sequence number should be 2-digit number found '%s'.\n", validator.fileSequence);
                    } else {
                        System.out.printf("File '%s' passed validation.\n", validator.fileName);
                    }
                } else {
                    System.out.printf("File '%s' failed validation.\n", validator.fileName);
                    System.out.printf("File format should be‘Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv'\n");
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
        Validator.format.setLenient(false);
    }

    public boolean fileLocationCheck(File f){
        if(f.exists() && !f.isDirectory()){
            return true;
        }else {
            return false;
        }
    }

    public boolean fileNameCheck(String fileName){
        if(fileName.matches("^.+\\.\\w+$")){
            String[] splitList = fileName.split("\\.");
            String name = splitList[0];  // regular expression
            if(name.matches("^[a-zA-Z]+_[A-Z]_[0-9]{8}$")
                    || name.matches("^[a-zA-Z]+_[A-Z]_[0-9]{8}_[0-9]{2}$")){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public boolean extensionCheck(String fileExtension){
        if(fileExtension.equals("csv")){
            return true;
        }else {
            return false;
        }
    }

    public boolean prefixCheck(String filePrefix){
        if(filePrefix.equals("Test")){
            return true;
        }else{
            return false;
        }
    }

    public boolean portfolioCheck(String filePorfolio){
        if(filePorfolio.matches("[ABC]")){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean dateCheck(String fileDate){
        if(fileDate.matches("[0-9]{8}")){
            String dd = fileDate.substring(0,2);
            String mm = fileDate.substring(2,4);
            String yyyy = fileDate.substring(4,8);
            String formatDate = dd+"-"+mm+"-"+yyyy;

            try{
                Validator.format.parse(formatDate);
                return true;
            }catch (ParseException ex){
                return false;
            }
        }else {
            return false;
        }
    }

    public boolean sequenceCheck(String fileSequence){
        if(fileSequence == null || fileSequence.matches("[0-9]{2}")){
            return true;
        }else {
            return false;
        }
    }
}
