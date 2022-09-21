import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class UPR {
    public static void main(String[] args) throws IOException {
        String path = "output.txt";
        File file = new File (path);

        PrintWriter writer = new PrintWriter ( file );
        writer.println ( "Java Advanced" );
        writer.close ();

        List<String> lines = Files.readAllLines ( Path.of(path));
        lines.forEach ( line-> System.out.println (lines) );
    }
}
