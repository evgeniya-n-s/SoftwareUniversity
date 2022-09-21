import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SumBytes {
    public static void main(String[] args) throws IOException {

        Path path = Path.of ( "input.txt" );

        long sum = 0;

        byte[] symbol = Files.readAllBytes ( path );
        for (byte bytes: symbol){
            if(bytes ==10 || bytes==13){
                continue;
            }
            sum+=bytes;
        }

        System.out.println (sum);
    }
}
