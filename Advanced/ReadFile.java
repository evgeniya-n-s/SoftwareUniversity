import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner ( System.in );

        String path = "C:\\Users\\000\\Desktop\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

//        FileInputStream inputStream = new FileInputStream ( path );
//
//        int nextByte = inputStream.read ();

        try (InputStream in = new FileInputStream(path)) {

            int oneByte = in.read();

            while (oneByte >= 0) {

                System.out.printf("%s ",
                        Integer.toBinaryString(oneByte));

                oneByte = in.read();

            }

        }

        catch (IOException e) {

            e.printStackTrace();

        }

//        while (nextByte !=-1){
//            System.out.print (Integer.toBinaryString ( nextByte ));
//            nextByte = inputStream.read ();
//        }
    }
}
