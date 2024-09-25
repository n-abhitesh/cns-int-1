import java.util.Scanner;

public class CaesarCipher {

    // Method to encrypt the plaintext
    public static String encrypt(String plaintext, int shift) {
        StringBuilder encrypted = new StringBuilder();
        
        for (char character : plaintext.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                // Shift the character and wrap around the alphabet
                char shiftedChar = (char) ((character + shift - base) % 26 + base);
                encrypted.append(shiftedChar);
            } else {
                // Non-letter characters remain unchanged
                encrypted.append(character);
            }
        }
        return encrypted.toString();
    }

    // Method to decrypt the ciphertext
    public static String decrypt(String ciphertext, int shift) {
        return encrypt(ciphertext, 26 - shift); // Decrypting is just shifting in the opposite direction
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        
        System.out.print("Enter the shift value (1-25): ");
        int shift = scanner.nextInt();
        
        // Ensure the shift is within 1-25
        if (shift < 1 || shift > 25) {
            System.out.println("Shift value must be between 1 and 25.");
            return;
        }
        
        // Encrypt the plaintext
        String encryptedText = encrypt(plaintext, shift);
        System.out.println("Encrypted Text: " + encryptedText);
        
        // Decrypt the ciphertext
        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Decrypted Text: " + decryptedText);
        
        scanner.close();
    }
}
