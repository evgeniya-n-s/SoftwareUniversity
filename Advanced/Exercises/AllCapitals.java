import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AllCapitals {
    public static void main(String[] args) throws IOException {

        Path inpath = Path.of ( "input.txt" );
        Path outpath = Path.of ( "output.txt" );

        List<String> lines = Files.readAllLines (inpath);
        lines.forEach ( line-> System.out.println (line.toUpperCase()) );

//        PrintWriter writer = new PrintWriter ( "output.txt" );
//        lines.forEach ( line->{
//            writer.println ( line.toUpperCase () );
//        } );
//        writer.close ();
    }
}
