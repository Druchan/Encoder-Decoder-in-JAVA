import java.util.Scanner;

public class TextEncoderDecoder {

    // Method to display the main menu
    public static void displayMenu() {
        System.out.println("\n========= TEXT ENCODER & DECODER =========");
        System.out.println("1. Encode a message");
        System.out.println("2. Decode a message");
        System.out.println("3. Change shift value");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Method to encode a message
    public static String encode(String message, int shift) {
        StringBuilder encoded = new StringBuilder();

        for (char ch : message.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                char c = (char) (((ch - 'A' + shift) % 26 + 26) % 26 + 'A');
                encoded.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) (((ch - 'a' + shift) % 26 + 26) % 26 + 'a');
                encoded.append(c);
            } else {
                // Non-alphabet characters remain the same
                encoded.append(ch);
            }
        }

        return encoded.toString();
    }

    // Method to decode a message (reverse shift)
    public static String decode(String message, int shift) {
        return encode(message, -shift); // Just reverse the shift
    }

    // Method to get valid integer input
    public static int getValidIntegerInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // clear invalid input
            }
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int shift = getValidIntegerInput(scanner, "Enter shift value: ");
        scanner.nextLine(); // Consume newline

        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter message to encode: ");
                    String msgToEncode = scanner.nextLine();
                    String encoded = encode(msgToEncode, shift);
                    System.out.println("Encoded Message: " + encoded);
                    break;

                case "2":
                    System.out.print("Enter message to decode: ");
                    String msgToDecode = scanner.nextLine();
                    String decoded = decode(msgToDecode, shift);
                    System.out.println("Decoded Message: " + decoded);
                    break;

                case "3":
                    shift = getValidIntegerInput(scanner, "Enter new shift value: ");
                    scanner.nextLine(); // Consume newline
                    break;

                case "4":
                    System.out.println("Exiting program. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
