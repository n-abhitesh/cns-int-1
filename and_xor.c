#include <stdio.h>

int main() {
    char *str = "Hello world";
    char andResult[12]; // Length of "Hello world" + 1 for the null terminator
    char xorResult[12]; // Length of "Hello world" + 1 for the null terminator
    int i;

    // Process each character with AND and XOR operations
    for (i = 0; str[i] != '\0'; i++) {
        andResult[i] = str[i] & 127; // AND with 127
        xorResult[i] = str[i] ^ 127; // XOR with 127
    }
    andResult[i] = '\0'; // Null-terminate the result string for AND
    xorResult[i] = '\0'; // Null-terminate the result string for XOR

    // Display the results
    printf("Original string: %s\n", str);
    printf("Result after AND with 127: ");
    for (i = 0; andResult[i] != '\0'; i++) {
        printf("%d ", andResult[i]); // Print as integer values
    }
    printf("\n");

    printf("Result after XOR with 127: ");
    for (i = 0; xorResult[i] != '\0'; i++) {
        printf("%d ", xorResult[i]); // Print as integer values
    }
    printf("\n");

    return 0;
}
