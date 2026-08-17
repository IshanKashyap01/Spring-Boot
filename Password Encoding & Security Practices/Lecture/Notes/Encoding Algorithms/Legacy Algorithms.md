# Legacy Encoding Algorithms

- `MD5` (message digest) converts a password into a 128-bit value

- It is completely broken and should not be used for password hashing

- `SHA-1` returns a 160-bit whereas `SHA 256` returns a 256-bit value

- `SHA 256` is still used to this day for other things but is no longer safe
for raw password storage

## PBKD

- It stands for Password-based key derivation that keeps hashing

- It adds a (either a random or user-provided) *salt* to the password

- Then it runs the same hashing function over and over again iteratively for
a configurable number of times

- It is now a legacy/compliance only algorithm as its operations are simple
and it can be decoded through high computation power
