import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MessagesManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> sendMap = new LinkedHashMap<>();
        Map<String, Integer> receivedMap = new LinkedHashMap<>();

        int capacity = Integer.parseInt(scanner.nextLine());
        String command = scanner.nextLine();

        while (!"Statistics".equals(command)) {
            String[] tokens = command.split("=");
            String typeCommand = tokens[0];

            switch (typeCommand) {
                case "Add":
                    String username = tokens[1];
                    int send = Integer.parseInt(tokens[2]);
                    int received = Integer.parseInt(tokens[3]);
                    sendMap.putIfAbsent(username, send);
                    receivedMap.putIfAbsent(username, received);
                    break;
                case "Message":
                    String sender = tokens[1];
                    String receiver = tokens[2];
                    if(sendMap.containsKey(sender)&& receivedMap.containsKey(receiver)){
                        int currentValueSendMap = sendMap.get(sender);
                        sendMap.put(sender,currentValueSendMap+1);
                        int currentValueReceivedMap = receivedMap.get(receiver);
                        receivedMap.put(receiver,currentValueReceivedMap+1);
                        int currentSendMap = sendMap.get(sender);
                        int currentRecMap =receivedMap.get(sender);
                        if((currentSendMap+currentRecMap)==capacity){
                            sendMap.remove(sender);
                            receivedMap.remove(sender);
                            System.out.println(String.format("%s reached the capacity!",sender));
                        }
                        int currentReceivedMap = receivedMap.get(receiver);
                        int currentSMap = sendMap.get(receiver);
                        if((currentReceivedMap+currentSMap)==capacity){
                            receivedMap.remove(receiver);
                            sendMap.remove(receiver);
                            System.out.println(String.format("%s reached the capacity!",receiver));
                        }
                    }
                    break;
                case "Empty":
                    String userName = tokens[1];
                    if(sendMap.containsKey(userName)&& receivedMap.containsKey(userName)){
                        sendMap.remove(userName);
                        receivedMap.remove(userName);
                    }
                    if("All".equals(userName)){
                        sendMap.clear();
                        receivedMap.clear();
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        System.out.println("Users count: "+sendMap.entrySet().size());
        receivedMap.entrySet().stream().sorted((r1,r2)->{
            int result = r2.getValue().compareTo(r1.getValue());
            if(result==0){
                result = r1.getKey().compareTo(r2.getKey());
            }
            return result;
        }).forEach(e-> System.out.println(String.format("%s - %s",e.getKey(),e.getValue()+sendMap.get(e.getKey()))));
    }
}
