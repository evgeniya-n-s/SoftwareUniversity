import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchoolLibrary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("&");
        List<String> bookLibrary = new ArrayList<>();

        for (String s : input) {
            bookLibrary.add(s);
        }

        String command = scanner.nextLine();
        while (!"Done".equals(command)) {
            String[] tokens = command.split("\\| ");
            switch (tokens[0]) {
                case "Add Book ":
                    boolean isTheBookExist = false;
                    for (int i = 0; i <bookLibrary.size() ; i++) {
                        if(tokens[1].equals(bookLibrary.get(i))){
                            isTheBookExist =true;
                        }
                    }
                    if(!isTheBookExist){
                        bookLibrary.add(0,tokens[1]);
                    }
                    break;
                case "Take Book ":
                    boolean isTheBookTaken = false;
                    for (int i = 0; i <bookLibrary.size() ; i++) {
                        if(tokens[1].equals(bookLibrary.get(i))){
                            isTheBookTaken = true;
                        }
                    }
                    if(isTheBookTaken){
                        bookLibrary.remove(tokens[1]);
                    }
                    break;
                case "Swap Books ":
                    boolean isTheFirstBookInTheLibrary = false;
                    boolean isTheSecondBookInTheLibrary = false;
                    int firstBookCount = 0;
                    int secondBookCount = 0;

                    for (int i = 0; i <bookLibrary.size() ; i++) {
                        if(tokens[1].equals(bookLibrary.get(i))){
                            isTheFirstBookInTheLibrary = true;
                            firstBookCount = i;
                        }
                        if(tokens[2].equals(bookLibrary.get(i))){
                            isTheSecondBookInTheLibrary = true;
                            secondBookCount = i;
                        }
                    }

                    if(isTheFirstBookInTheLibrary && isTheSecondBookInTheLibrary){
                        String firstBook = bookLibrary.get(firstBookCount);
                        String secondBook = tokens[2];
                        bookLibrary.remove(tokens[1]);
                        bookLibrary.add(firstBookCount,secondBook);
                        bookLibrary.remove(tokens[2]);
                        bookLibrary.add(secondBookCount,firstBook);
                    }
                    break;
                case "Insert Book ":
                    bookLibrary.add(tokens[1]);
                    break;
                case "Check Book ":
                    int index = Integer.parseInt(tokens[1]);
                    if( index>=0 && index<=bookLibrary.size()){
                        for (int i = 0; i <bookLibrary.size() ; i++) {
                            if(index == i){
                                System.out.println(bookLibrary.get(i));
                            }
                        }
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        for (int i = 0; i <bookLibrary.size() ; i++) {
            if(i==0){
                System.out.print(bookLibrary.get(i));
            }else {
                System.out.print(", " + bookLibrary.get(i));
            }

        }
    }
}
