import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CopyBytes {
    public static void main(String[] args) throws IOException {

        String path = "input.txt";

        FileInputStream inputStream = new FileInputStream ( path );
        FileOutputStream outputStream = new FileOutputStream ( "03.CopyBytesOutput.txt" );

        Scanner scanner = new Scanner ( inputStream );

        int nextByte = inputStream.read ();

        while (nextByte !=-1){
            if(nextByte == ' ' || nextByte=='\n'){
                outputStream.write ( nextByte );
            }else {
                outputStream.write ( String.valueOf ( nextByte ).getBytes () );
            }
            nextByte= inputStream.read ();
        }
    }
}
