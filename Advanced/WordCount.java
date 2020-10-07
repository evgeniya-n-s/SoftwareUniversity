import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WordCount {
    public static void main(String[] args) throws IOException {

        Path path = Path.of ( "C:\\Users\\Evgeniq.000\\Desktop\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\Exercises Resources\\words.txt" );

        List<String> line = Files.readAllLines (path);

        LinkedHashMap<String, Integer> wordsCount = new LinkedHashMap<> (  );

        for (String lines:line){
            String[] words = lines.split("\\s+");
            for (String word:words){
                wordsCount.put ( word,0 );
            }
        }

        Path path1 = Path.of ( "C:\\Users\\Evgeniq.000\\Desktop\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\Exercises Resources\\text.txt" );
        List<String> searchLine = Files.readAllLines ( path1 );

        for (String lines: searchLine){
            Arrays.stream ( lines.split("\\s+") ).forEach ( word ->{
                if(wordsCount.containsKey ( word )){
                    wordsCount.put ( word,wordsCount.get ( word ) + 1);
                }
            } );
        }

        for (Map.Entry<String, Integer> entry : wordsCount.entrySet ()){
            System.out.println (entry.getKey () + " - " + entry.getValue ());
        }
    }
}
