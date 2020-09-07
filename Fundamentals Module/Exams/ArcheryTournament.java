import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArcheryTournament {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\|");
        List<Integer> targetsInArcheryField = new ArrayList<>();

        for (String s : input) {
            targetsInArcheryField.add(Integer.parseInt(s));
        }

        int point = 0;

        String command = scanner.nextLine();
        while (!"Game over".equals(command)) {
            String[] tokens = command.split("@");

            switch (tokens[0]) {
                case "Shoot Left":
                    int startIndexLeft = Integer.parseInt(tokens[1]);
                    int lengthLeft = Integer.parseInt(tokens[2]);
                    int countIndexLeft = 0;
                    int countLenghtLeft = 0;
                    if (startIndexLeft>targetsInArcheryField.size()-1){
                        break;
                    }
                    if (startIndexLeft == 0) {
                        int endIndex = targetsInArcheryField.size() - lengthLeft;
                        if(endIndex>0) {
                            for (int i = targetsInArcheryField.size(); i > endIndex; i--) {
                                countIndexLeft++;
                                countLenghtLeft++;
                            }
                        }else {
                            for (int i = targetsInArcheryField.size(); i > 0 ; i--) {
                                countIndexLeft++;
                                countLenghtLeft++;
                            }
                        }
                    } else if (startIndexLeft < lengthLeft) {
                        for (int i = startIndexLeft; i > 0; i--) {
                            countIndexLeft++;
                            countLenghtLeft++;
                        }
                    } else if (startIndexLeft > lengthLeft) {
                        int end = startIndexLeft - lengthLeft;
                        for (int i = startIndexLeft; i < end; i--) {
                            countIndexLeft++;
                            countLenghtLeft++;
                        }
                    }
                    if (!(countIndexLeft == lengthLeft)) {
                        countLenghtLeft = Math.abs(countLenghtLeft - lengthLeft);
                        for (int i = targetsInArcheryField.size(); i > targetsInArcheryField.size() - countLenghtLeft; i--) {
                            countIndexLeft++;
                        }
                    }
                    if (countIndexLeft == lengthLeft) {
                            if (startIndexLeft == 0) {
                                int changeIndex = targetsInArcheryField.size() - lengthLeft;
                                int countPoint = Math.abs(targetsInArcheryField.get(changeIndex) - 5);
                                if (countPoint < 5) {
                                    point = point + countPoint;
                                    targetsInArcheryField.set(changeIndex, 0);
                                } else {
                                    point = point + 5;
                                    targetsInArcheryField.set(changeIndex, countPoint);
                                }
                            } else {
                                int countChangeIndex = Math.abs(startIndexLeft - lengthLeft);
                                int countPoint = Math.abs(targetsInArcheryField.get(countChangeIndex) - 5);
                                if (countPoint < 5) {
                                    point = point + countPoint;
                                    targetsInArcheryField.set(countChangeIndex, 0);
                                } else {
                                    point = point + 5;
                                    targetsInArcheryField.set(countChangeIndex, countPoint);
                                }
                            }
                        }
                        break;
                        case "Shoot Right":
                            int startIndexRight = Integer.parseInt(tokens[1]);
                            int lengthRight = Integer.parseInt(tokens[2]);
                            int countIndexRight = 0;
                            int countLengthRight = 0;
                            if(startIndexRight>targetsInArcheryField.size()-1){
                                break;
                            }
                            if (startIndexRight == targetsInArcheryField.size()-1) {
                                for (int i = 0; i < lengthRight; i++) {
                                    countIndexRight++;
                                    countLengthRight++;
                                }
                            } else if (startIndexRight < lengthRight) {
                                int endIndexRight = startIndexRight + lengthRight;
                                for (int i = startIndexRight; i < endIndexRight; i++) {
                                    countIndexRight++;
                                    countLengthRight++;
                                }
                            } else if (startIndexRight > lengthRight) {
                                for (int i = startIndexRight; i > targetsInArcheryField.size(); i++) {
                                    countIndexRight++;
                                    countLengthRight++;
                                }
                            }
                            if (!(countIndexRight == lengthRight)) {
                                countLengthRight = Math.abs(countLengthRight - lengthRight);
                                for (int i = 0; i < countLengthRight; i++) {
                                    countIndexRight++;
                                }
                            }
                            if (countIndexRight == lengthRight) {
                                int calculateIndex = startIndexRight + lengthRight;
                                if (calculateIndex > targetsInArcheryField.size()-1) {
                                    int changeIndex = Math.abs(targetsInArcheryField.size() - calculateIndex);
                                    int countPoint = Math.abs(targetsInArcheryField.get(changeIndex) - 5);
                                    if (countPoint < 5) {
                                        point = point + countPoint;
                                        targetsInArcheryField.set(changeIndex, 0);
                                    } else {
                                        point = point + 5;
                                        targetsInArcheryField.set(changeIndex, countPoint);
                                    }
                                } else {
                                    int countChangeIndex = Math.abs(startIndexRight + lengthRight);
                                    int countPoint = Math.abs(targetsInArcheryField.get(countChangeIndex) - 5);
                                    if (countPoint < 5) {
                                        point = point + countPoint;
                                        targetsInArcheryField.set(countChangeIndex, 0);
                                    } else {
                                        point = point + 5;
                                        targetsInArcheryField.set(countChangeIndex, countPoint);
                                    }
                                }
                            }
                    break;
                case "Reverse":
                    int calculateIndex = targetsInArcheryField.size()-1;
                    int calculateLastIndex=0;
                    for (int i = 0; i <targetsInArcheryField.size()/2 ; i++) {
                        int temp = targetsInArcheryField.get(i);
                        int temp2 = targetsInArcheryField.get((targetsInArcheryField.size()-1)-calculateLastIndex);
                        targetsInArcheryField.set(i,temp2);
                        targetsInArcheryField.set(calculateIndex,temp);
                        calculateLastIndex++;
                        calculateIndex--;
                    }
                case "Game Over":
                    for (int i = 0; i <targetsInArcheryField.size() ; i++) {
                        if(i==0){
                            System.out.print(targetsInArcheryField.get(i));
                        } else {
                            System.out.print(" - " + targetsInArcheryField.get(i));
                        }
                    }
                    System.out.println();
                    System.out.printf("Iskren finished the archery tournament with %d points!",point);
                    break;
            }
            command = scanner.nextLine();
        }
    }
}
