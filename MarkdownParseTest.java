import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {

    @Test
    public void snippedOneTest throws() IOException{ 
        Path fileName = Path.of("Snipped1.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        List<String> expected = List.of("url.com");
        assertEquals(expected, links);
    }

    @Test
    public void snippedTwoTest() throws IOException{
        Path fileName = Path.of("Snipped2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        List<String> expected = List.of("b.com");
        assertEquals(expected, links);      
    }

    @Test
    public void snippedThreeTest() throws IOException{
        Path fileName = Path.of("Snipped3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        List<String> expected = List.of("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
        assertEquals(expected, links);      
    }
}