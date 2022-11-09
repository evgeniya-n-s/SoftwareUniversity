import java.util.Scanner;
public class TextNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstName = "Maria";
        String lastName = "Ivanova";
        int age = 19;
        String srt = firstName + "" + lastName + "@" + age;
        System.out.println(srt);
    }

}
