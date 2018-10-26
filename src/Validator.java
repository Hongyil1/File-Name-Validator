/**
 *
 *  This program is used to validate a given filename. If the file is invalid,
 *  it should print the error details along with the status of the file as passed or failed.
 *  The file name format should:
 *  Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv
 *
 *  Test – hardcoded string prefix
 * <portfoliocode> - can only be A, B, C
 * <ddmmyyyy>– is valuation date format dd e.g 07, mm e.g 12, yyyy e.g 1987.
 * <2digit-sequencenumber> - is 2 digit sequence number
 *
 *
 *  @author Hongyi Lin
 *  @Time 2018-10-20
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Validator extends JFrame{

    private String fileName;
    private String fileExtension;
    private String filePrefix;
    private String filePortfolio;
    private String fileSequence = null;
    private String fileDate;

    // The format is used to check the date.
    private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    /* Java Swing */
    // South area
    private JLabel imageLable;

    // North area
    private JTextArea textArea;
    private JScrollPane scrollPane;

    // Central area
    private JPanel middlePanel;
    private JTextField textField;
    private JButton buttonRun;
    private JButton buttonHome;


    public static void main(String args[]){

        // Read file location from console
        System.out.println("Please input the file location or 'exit' to end the program: ");
        Scanner scanner = new Scanner(System.in);
        String fileLocation = scanner.nextLine();

        while(!fileLocation.equals("exit")){

            File f = new File(fileLocation.trim());
            String fileName= f.getName();
            Validator validator = new Validator(fileName);

            // Check whether the file exits or whether the given location is a folder
            if(validator.fileLocationCheck(f)){
                // Check whether filename format is "xxx_xxx_xxx.xxx" or "xxx_xxx_xxx_xxx.xxx"
                if(validator.fileNameCheck(validator.fileName)){
                    String[] splitList = fileName.split("\\.");
                    validator.fileExtension = splitList[splitList.length - 1];
                    String[] nameList = splitList[0].split("_");
                    validator.filePrefix = nameList[0];
                    validator.filePortfolio = nameList[1];
                    validator.fileDate = nameList[2];
                    // If sequence number exits, record the number
                    if(nameList.length == 4) {validator.fileSequence = nameList[3];};
                    // check the portfolio
                    if(!validator.portfolioCheck(validator.filePortfolio)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.printf("PortfolioCode should be A/B/C found %s.\n", validator.filePortfolio);
                    // Check the date
                    }else if(!validator.dateCheck(validator.fileDate)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.println("Valuation Date is not a valid date format 'ddmmyyyy'.\n");
                    // Check the prefix
                    }else if(!validator.prefixCheck(validator.filePrefix)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.printf("Prefix for the file should be 'Test' found '%s'.\n", validator.filePrefix);
                    // Check the extension
                    }else if(!validator.extensionCheck(validator.fileExtension)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.printf("Invalid File format. Expected 'csv' found '%s'.\n", validator.fileExtension);
                    // Check the sequence
                    } else if(!validator.sequenceCheck(validator.fileSequence)){
                        System.out.printf("File '%s' failed validation.\n", validator.fileName);
                        System.out.printf("Sequence number should be 2-digit number found '%s'.\n", validator.fileSequence);
                    // Pass the validation
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

    /**
     *
     *  Set the object's filename as the input filename.
     *
     *  @param fileName: the input filename from console.
     *  e.g. Test_A_07121987.csv.
     *
     * */
    public Validator(String fileName){
        this.fileName = fileName;
        // The parse method throw ParseException when the given input string is not in the specified format.
        Validator.format.setLenient(false);

        imageLable = new JLabel(new ImageIcon("images\\jpmorgan.png"));
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        middlePanel = new JPanel();
        textField = new JTextField(30);
        buttonRun = new JButton("Run");
        buttonHome = new JButton("Home");

        // Set the layout

        // Set the component
        middlePanel.add(textField);
        middlePanel.add(buttonRun);
        middlePanel.add(buttonHome);

        // Add to JFrame
        this.add(imageLable, BorderLayout.NORTH);
        this.add(middlePanel); //?
        this.add(scrollPane); //?

        // Attribute
        this.setSize(900, 600);
        this.setTitle("File Name Validator");
        this.setResizable(false);
        this.setIconImage(new ImageIcon("images\\jpm_icon.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     *
     *  Check whether the given file location is valid.
     *
     *  @param f: A File object. The object is defined according to the given location.
     *  e.g. File f = new File("C:\\Users\\hylxm\\Desktop\\File-Name-Validator\\test\\testCase\\csv");
     *
     *  @return
     *  If the given file exits and the the given one is not a folder, return true. Else, return false.
     *
     * */
    public boolean fileLocationCheck(File f){
        if(f.exists() && !f.isDirectory()){
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     *  Check whether the filename matches the format "xxx_xxx_xxx.xxx" or "xxx_xxx_xxx_xxx.xxx".
     *  "x" can be any character except from punctuation. e.g. number, letter, Chinese character
     *  The number of "x" is not limited. But the number of underscore should be 2 or 3.
     *
     *  @param fileName: The object's filename. e.g. Test_A_07121987.csv
     *
     *  @return
     *  If the given filename match the format, return true. Else, return false.
     *  e.g. A_B_C.abc true
     *       A_B_C_D.123 true
     *       1_2_3_4.567 true
     *       A+a_B.c_D.123 false
     *       A_B.asc false
     *       A_B_C_D_E.f false
     *       1_2_3.567_ false
     *
     * */
    public boolean fileNameCheck(String fileName){
        // before dot ".": one or more character except from "."
        // after dot ".": one or more character except from punctuation.
        if(fileName.matches("^[^\\.]+\\.\\P{P}+$")){
            String[] splitList = fileName.split("\\.");
            String name = splitList[0];
            // two or three underscore "_" in the string. \P{P}: any character except from punctuation.
            if(name.matches("^\\P{P}+_\\P{P}+_\\P{P}+$")
                    || name.matches("^\\P{P}+_\\P{P}+_\\P{P}+_\\P{P}+$")){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     *
     *  Check whether the extension equals to "csv".
     *
     *  @param fileExtension: the extension of file. e.g. "abc", "csv"
     *
     *  @return
     *  If the given extension equals to "csv", return true. Else return false.
     *  e.g. abc false
     *       123 false
     *       测试 false
     *       csv true
     * */
    public boolean extensionCheck(String fileExtension){
        if(fileExtension.equals("csv")){
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     *  Check whether the prefix equals to "Test".
     *
     *  @param filePrefix: the file's prefix. e.g. "Hello", "Test"
     *
     *  @return
     *  If filePrefix equals to "Test", return true. Else return false.
     *  e.g. "Hello" false
     *       "test" false
     *       "测试" false
     *       "123" false
     *       "Test" true
     *
     * */
    public boolean prefixCheck(String filePrefix){
        if(filePrefix.equals("Test")){
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     *  Check whether the portfolio is A, B or C.
     *
     *  @param filePortfolio: the file's portfolio. e.g. "A", "E".
     *
     *  @return
     *  If filePortfolio equals to "A", "B" or "C", return true. Else, return false.
     *  e.g. "E" false
     *       "123" false
     *       "测试" false
     *       "a" false
     *       "A" true
     *       "B" true
     *       "C" true
     *
     * */
    public boolean portfolioCheck(String filePortfolio){
        if(filePortfolio.matches("[ABC]")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     *  Check whether the date is valid.
     *
     *  @param fileDate: the date of file. e.g. 07121987. day:07, month: 12, year:1987.
     *
     *  @return
     *  If the fileDate consists of 8 numbers. And the day, month and year obey the rules, return true. Else false.
     *  e.g. "abc" false
     *       "7121986" false
     *       "32121987" false: Dec doesn't have 32
     *       "29022000" true: leap year
     *       "30022000" false: Feb doesn't have 30
     *       "07121987" true.
     *
     * */
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

    /**
     *
     *  Check whether the sequence is two digital.
     *
     *  @param fileSequence: the file's sequence. e.g. "00", "12" or null
     *
     *  @return
     *  If fileSequence is null or a two-digital number, return true. Else, false.
     *  e.g. "0" false
     *       "123" false
     *       "ac" false
     *       "00" true
     *       null true
     *
     * */
    public boolean sequenceCheck(String fileSequence){
        if(fileSequence == null || fileSequence.matches("[0-9]{2}")){
            return true;
        }else {
            return false;
        }
    }
}
