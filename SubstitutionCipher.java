import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SubstitutionCipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    // Method to create the substitution map
    private static Map<Character, Character> createSubstitutionMap(String key) {
        Map<Character, Character> substitutionMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            substitutionMap.put(ALPHABET.charAt(i), key.charAt(i));
        }
        return substitutionMap;
    }

    // Method to create the reverse substitution map
    private static Map<Character, Character> createReverseSubstitutionMap(String key) {
        Map<Character, Character> reverseSubstitutionMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            reverseSubstitutionMap.put(key.charAt(i), ALPHABET.charAt(i));
        }
        return reverseSubstitutionMap;
    }

    // Method to encrypt the plaintext
    public static String encrypt(String plaintext, String key) {
        Map<Character, Character> substitutionMap = createSubstitutionMap(key);
        StringBuilder encrypted = new StringBuilder();
        
        for (char character : plaintext.toUpperCase().toCharArray()) {
            if (substitutionMap.containsKey(character)) {
                encrypted.append(substitutionMap.get(character));
            } else {
                // Non-alphabet characters remain unchanged
                encrypted.append(character);
            }
        }
        return encrypted.toString();
    }

    // Method to decrypt the ciphertext
    public static String decrypt(String ciphertext, String key) {
        Map<Character, Character> reverseSubstitutionMap = createReverseSubstitutionMap(key);
        StringBuilder decrypted = new StringBuilder();
        
        for (char character : ciphertext.toUpperCase().toCharArray()) {
            if (reverseSubstitutionMap.containsKey(character)) {
                decrypted.append(reverseSubstitutionMap.get(character));
            } else {
                // Non-alphabet characters remain unchanged
                decrypted.append(character);
            }
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the substitution key (26 unique characters): ");
        String key = scanner.nextLine().toUpperCase();

        // Validate key length and uniqueness
        if (key.length() != 26 || !isUnique(key)) {
            System.out.println("Key must be 26 unique characters.");
            return;
        }
        
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        
        // Encrypt the plaintext
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);
        
        // Decrypt the ciphertext
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
        
        scanner.close();
    }

    // Helper method to check if all characters in the key are unique
    private static boolean isUnique(String key) {
        boolean[] charSet = new boolean[26];
        for (char c : key.toCharArray()) {
            if (charSet[c - 'A']) {
                return false; // Duplicate found
            }
            charSet[c - 'A'] = true;
        }
        return true;
    }
}
