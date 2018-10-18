import org.junit.Test;
import java.io.File;
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