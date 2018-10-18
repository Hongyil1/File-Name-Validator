import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void testFileLocationCheck(){
        Validator val = new Validator("test");
        File f = new File("C:\\Users\\hylxm\\Desktop\\File-Name-Validator\\test");
        boolean result1 = val.fileLocationCheck(f);
        assertEquals(false, result1);
    }

    @Test
    public void testFileNameCheck(){

    }

    @Test
    public void testExtensionCheck(){

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