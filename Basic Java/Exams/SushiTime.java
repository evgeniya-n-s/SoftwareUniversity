import java.util.Scanner;

public class SushiTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String typeSushi = scanner.nextLine();
        String nameRestaurant = scanner.nextLine();
        int numberServing = Integer.parseInt(scanner.nextLine());
        String orderSymbol = scanner.nextLine();

        double priceServing = 0.0;
        double homeFoodPrice = 0.0;
        double totalPrice = 0.0;

        if (nameRestaurant.equals("Sushi Zone")){
            if (typeSushi.equals("sashimi")){
                priceServing = numberServing * 4.99;
            } else if (typeSushi.equals("maki")){
                priceServing = numberServing * 5.29;
            } else if (typeSushi.equals("uramaki")){
                priceServing = numberServing * 5.99;
            } else if (typeSushi.equals("temaki")){
                priceServing = numberServing * 4.29;
            }
        }
        if (nameRestaurant.equals("Sushi Time")){
            if (typeSushi.equals("sashimi")){
                priceServing = numberServing * 5.49;
            } else if (typeSushi.equals("maki")){
                priceServing = numberServing * 4.69;
            } else if (typeSushi.equals("uramaki")){
                priceServing = numberServing * 4.49;
            } else if (typeSushi.equals("temaki")){
                priceServing = numberServing * 5.19;
            }
        }
        if (nameRestaurant.equals("Sushi Bar")){
            if (typeSushi.equals("sashimi")){
                priceServing = numberServing * 5.25;
            } else if (typeSushi.equals("maki")){
                priceServing = numberServing * 5.55;
            } else if (typeSushi.equals("uramaki")){
                priceServing = numberServing * 6.25;
            } else if (typeSushi.equals("temaki")){
                priceServing = numberServing * 4.75;
            }
        }
        if (nameRestaurant.equals("Asian Pub")){
            if (typeSushi.equals("sashimi")){
                priceServing = numberServing * 4.50;
            } else if (typeSushi.equals("maki")){
                priceServing = numberServing * 4.80;
            } else if (typeSushi.equals("uramaki")){
                priceServing = numberServing * 5.50;
            } else if (typeSushi.equals("temaki")){
                priceServing = numberServing * 5.50;
            }
        }
        if ((!(nameRestaurant.equals("Sushi Zone"))) && (!(nameRestaurant.equals("Sushi Time"))) && (!(nameRestaurant.equals("Sushi Bar"))) && (!(nameRestaurant.equals("Asian Pub")))){
            System.out.printf("%s is invalid restaurant!", nameRestaurant);
            return;
        }


        if (orderSymbol.equals("Y")){
            homeFoodPrice = priceServing * 0.2;
            totalPrice = Math.ceil(priceServing + homeFoodPrice);
            System.out.printf("Total price: %.0f lv.", totalPrice);
        } else {
            totalPrice = Math.ceil(priceServing);
            System.out.printf("Total price: %.0f lv.", totalPrice);
        }
    }
}
