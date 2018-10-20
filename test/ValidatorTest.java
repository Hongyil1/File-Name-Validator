import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void testFileLocationCheck(){
        Validator val = new Validator("test");
        File f;

        f = new File("C:\\Users\\hylxm\\Desktop\\File-Name-Validator\\test\\testCase");
        assertEquals(false, val.fileLocationCheck(f));

        f = new File("C:\\Users\\hylxm\\Desktop\\File-Name-Validator\\test\\testCase\\csv");
        assertEquals(true, val.fileLocationCheck(f));

        f = new File("C:\\Users\\hylxm\\Desktop\\File-Name-Validator\\test\\testCase\\Test_A_07121987.csv");
        assertEquals(true, val.fileLocationCheck(f));
    }

    @Test
    public void testFileNameCheck(){
        Validator val = new Validator("test");

        assertEquals(false, val.fileNameCheck("abc.tar.gz"));
        assertEquals(false, val.fileNameCheck("Test_A_07121987."));
        assertEquals(false, val.fileNameCheck(".Test_A_07121987"));
        assertEquals(false, val.fileNameCheck("Test_A_07121987_01_aaa.csv"));
        assertEquals(false, val.fileNameCheck("Test_A.csv"));
        assertEquals(false, val.fileNameCheck("csv"));
        assertEquals(false, val.fileNameCheck("_TestA_07121987.csv"));
        assertEquals(false, val.fileNameCheck("Test_A_07121987_.csv"));
        assertEquals(false, val.fileNameCheck("A_B.b_C.D"));
        assertEquals(false, val.fileNameCheck("1_2_3.567_"));
        assertEquals(true, val.fileNameCheck("A_B_C.D"));
        assertEquals(true, val.fileNameCheck("1a_2B_3C.4D"));
        assertEquals(true, val.fileNameCheck("Test_A_07121987_01.csv"));
        assertEquals(true, val.fileNameCheck("Test_A_07121987.csv"));



    }

    @Test
    public void testExtensionCheck(){
        Validator val = new Validator("test");

        assertEquals(false, val.extensionCheck("txt"));
        assertEquals(false, val.extensionCheck("测试"));
        assertEquals(false, val.extensionCheck("00"));
        assertEquals(false, val.extensionCheck(""));
        assertEquals(false, val.extensionCheck("csv123"));
        assertEquals(true, val.extensionCheck("csv"));
    }

    @Test
    public void testPrefixCheck(){
        Validator val = new Validator("test");

        assertEquals(false, val.prefixCheck("test"));
        assertEquals(false, val.prefixCheck(""));
        assertEquals(false, val.prefixCheck("Hello"));
        assertEquals(false, val.prefixCheck("测试"));
        assertEquals(false, val.prefixCheck("123"));
        assertEquals(true, val.prefixCheck("Test"));

    }

    @Test
    public void testPortfolioCheck(){
        Validator val = new Validator("test");

        assertEquals(false, val.portfolioCheck("E"));
        assertEquals(false, val.portfolioCheck("123"));
        assertEquals(false, val.portfolioCheck("测试"));
        assertEquals(false, val.portfolioCheck("AB"));
        assertEquals(false, val.portfolioCheck("ABC"));
        assertEquals(false, val.portfolioCheck("A1"));
        assertEquals(false, val.portfolioCheck("a"));
        assertEquals(false, val.portfolioCheck("b"));
        assertEquals(false, val.portfolioCheck("c"));
        assertEquals(false, val.portfolioCheck(""));
        assertEquals(true, val.portfolioCheck("A"));
        assertEquals(true, val.portfolioCheck("B"));
        assertEquals(true, val.portfolioCheck("C"));

    }

    @Test
    public void testDateCheck(){
        Validator val = new Validator("test");

        assertEquals(false, val.dateCheck(""));
        assertEquals(false, val.dateCheck("0712198911"));
        assertEquals(false, val.dateCheck("7121989"));
        assertEquals(false, val.dateCheck("00121989"));
        assertEquals(false, val.dateCheck("07131989"));
        assertEquals(false, val.dateCheck("30021989"));
        assertEquals(false, val.dateCheck("29022001"));
        assertEquals(true, val.dateCheck("29022000"));
        assertEquals(true, val.dateCheck("07121989"));
    }

    @Test
    public void testSequenceCheck(){
        Validator val = new Validator("test");

        assertEquals(false, val.sequenceCheck(""));
        assertEquals(false, val.sequenceCheck("1"));
        assertEquals(false, val.sequenceCheck("000"));
        assertEquals(false, val.sequenceCheck("ab"));
        assertEquals(false, val.sequenceCheck("测试"));
        assertEquals(true, val.sequenceCheck(null));
        assertEquals(true,val.sequenceCheck("00"));
    }

}