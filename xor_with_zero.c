#include <stdio.h>

int main() {
    char *str = "Hello world";
    char result[12]; // Length of "Hello world" + 1 for the null terminator
    int i;

    // XOR each character with 0
    for (i = 0; str[i] != '\0'; i++) {
        result[i] = str[i] ^ 0; // XOR with 0
    }
    result[i] = '\0'; // Null-terminate the result string

    // Display the result
    printf("Original string: %s\n", str);
    printf("Result after XOR with 0: %s\n", result);

    return 0;
}
