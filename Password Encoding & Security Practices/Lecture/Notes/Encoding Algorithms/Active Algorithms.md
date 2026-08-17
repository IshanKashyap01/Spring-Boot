# Currently Used Algorithms

## BCrypt

- Just like PBKD, it uses a salt and iteratively hashes a password

- However, PBKD iterates linearly whereas BCrypt iterates *exponentially*

- It is a highly secure and was a former standard

- Although, it is officially classified as legacy, it is still highly secured
and used in active production

## Argon2id

- It provides even more customization than BCrypt and is safer against GPU
based attacks

- It is the current gold standard for password hashing
