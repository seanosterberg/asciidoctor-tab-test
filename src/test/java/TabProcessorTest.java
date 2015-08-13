import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.mule.docs.AsciiDocProcessor;
import org.mule.docs.Utilities;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;

public class TabProcessorTest {

    @Test
    public void createTestAsciiDocFile_SuccessfullyRendersFile() {
        AsciiDocProcessor processor = AsciiDocProcessor.getProcessorInstance();
        String result = processor.convertFile(getValidFile());
        System.out.println(result);
        assertNotNull(result);
    }

    private File getValidFile() {
        String path = Utilities.getConcatPath(new String[] { getTestResourcesPath(), "test-file3.adoc" });
        return new File(path);
    }

    public static String getTestResourcesPath() {
        URL pathToTestResources = TabProcessorTest.class.getClassLoader().getResource("");
        String testResourcesPath = "";
        if(pathToTestResources != null) {
            testResourcesPath = pathToTestResources.getFile();
            if (!StringUtils.isEmpty(testResourcesPath)) {
                return testResourcesPath;
            }
        }
        throw new RuntimeException("Test resources path was null.");
    }

}
