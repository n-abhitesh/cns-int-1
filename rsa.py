# Function to find greatest common divisor

import random

def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

# Function to calculate the modular inverse using Extended Euclidean Algorithm
def mod_inverse(e, phi):
    d_old, d_new = 0, 1
    r_old, r_new = phi, e
    while r_new != 0:
        quotient = r_old // r_new
        r_old, r_new = r_new, r_old - quotient * r_new
        d_old, d_new = d_new, d_old - quotient * d_new
    return d_old % phi

# Function to perform modular exponentiation (x^y) % p
def mod_exp(base, exp, mod):
    result = 1
    base = base % mod
    while exp > 0:
        if exp % 2 == 1:
            result = (result * base) % mod
        exp = exp >> 1
        base = (base * base) % mod
    return result

# Function to check if a number is prime
def is_prime(num):
    if num < 2:
        return False
    for i in range(2, int(num ** 0.5) + 1):
        if num % i == 0:
            return False
    return True

# Function to generate RSA keypair using user-chosen primes
def generate_keypair(p, q):
    if not (is_prime(p) and is_prime(q)):
        raise ValueError("Both numbers must be prime.")
    if p == q:
        raise ValueError("p and q cannot be the same.")

    # Calculate n = p * q
    n = p * q

    # Calculate phi(n) = (p-1)*(q-1)
    phi = (p - 1) * (q - 1)

    # Choose e such that 1 < e < phi and gcd(e, phi) = 1
    e = random.randrange(1, phi)
    while gcd(e, phi) != 1:
        e = random.randrange(1, phi)

    # Calculate d, the modular inverse of e mod phi
    d = mod_inverse(e, phi)

    # Public key is (e, n) and private key is (d, n)
    return ((e, n), (d, n))

# Function to encrypt plaintext using public key
def encrypt(public_key, plaintext):
    e, n = public_key
    cipher = [mod_exp(ord(char), e, n) for char in plaintext]
    return cipher

# Function to decrypt ciphertext using private key
def decrypt(private_key, ciphertext):
    d, n = private_key
    plain = [chr(mod_exp(char, d, n)) for char in ciphertext]
    return ''.join(plain)

# Main RSA implementation
if __name__ == '__main__':
    print("RSA Encryption/Decryption")

    # Taking user input for primes p and q
    p = int(input("Enter a prime number p: "))
    q = int(input("Enter a prime number q: "))

    # Generating RSA keypair using user-chosen primes
    try:
        public_key, private_key = generate_keypair(p, q)
    except ValueError as e:
        print(e)
        exit()

    print("Public key:", public_key)
    print("Private key:", private_key)

    message = input("Enter a message to encrypt: ")

    encrypted_message = encrypt(public_key, message)
    print("Encrypted message:", ''.join(map(lambda x: str(x), encrypted_message)))

    decrypted_message = decrypt(private_key, encrypted_message)
    print("Decrypted message:", decrypted_message)
