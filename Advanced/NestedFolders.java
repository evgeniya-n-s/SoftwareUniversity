import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class NestedFolders {
    public static void main(String[] args) {

        File file = new File ("C:\\Users\\000\\Desktop\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\Files-and-Streams");

        Deque<File> deque = new ArrayDeque<> (  );
        deque.add(file);

        int count = 1;
        while (!deque.isEmpty ()){
            File f = deque.poll ();
            count++;
            System.out.println (f.getName ());
            File[] files = f.listFiles ();
            for(File innerFile: files){
                if(innerFile.isDirectory ()){
                    deque.offer ( innerFile );
                }
            }
        }
        System.out.println (count + " folders");
    }
}
