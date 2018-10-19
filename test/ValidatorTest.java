import org.junit.Test;
import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void testFileLocationCheck(){
        Validator val = new Validator("test");
        File f;

        f = new File("C:\\Users\\hylxm\\Desktop\\File-Name-Validator\\test");
        assertEquals(false, val.fileLocationCheck(f));

        f = new File("C:\\Users\\hylxm\\Desktop\\File-Name-Validator\\test\\csv");
        assertEquals(true, val.fileLocationCheck(f));

        f = new File("C:\\Users\\hylxm\\Desktop\\File-Name-Validator\\test\\Test_A_07121987.csv");
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
        assertEquals(false, val.fileNameCheck("Test_A_07121987_01_aaa.csv"));
        assertEquals(false, val.fileNameCheck("csv"));
        assertEquals(false, val.fileNameCheck("_TestA_07121987.csv"));
        assertEquals(false, val.fileNameCheck("Test_A_07121987_.csv"));
        assertEquals(true, val.fileNameCheck("Test_A_07121987_01.csv"));
        assertEquals(true, val.fileNameCheck("Test_A_07121987.csv"));

    }

    @Test
    public void testExtensionCheck(){
        Validator val = new Validator("test");

        assertEquals(false, val.extensionCheck("txt"));
        assertEquals(false, val.extensionCheck("测试"));
        assertEquals(true, val.extensionCheck("csv"));
    }

    @Test
    public void testPrefixCheck(){

    }

    @Test
    public void testPortfolioCheck(){

    }

    @Test
    public void testDateCheck(){

    }

    @Test
    public void testSequenceCheck(){

    }

}