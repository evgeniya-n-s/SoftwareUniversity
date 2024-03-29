import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SumLines {
    public static void main(String[] args) throws IOException {

        Path path = Path.of("input.txt");

        List<String> lines = Files.readAllLines (path);

        lines.stream ().map ( String::toCharArray ).forEach ( e->{
             int sum = 0;
             for (char symbol:e){
                 sum+=symbol;
             }
            System.out.println (sum);
        } );
    }
}
