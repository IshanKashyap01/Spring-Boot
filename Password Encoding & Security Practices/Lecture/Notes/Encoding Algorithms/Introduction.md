# Introduction to Password Encoding

- When a user registers, his password is passed through a hash function which
encodes it to a different string

- This new string gets stored in the database and the hashing is not reversible

- When the user logs in again, the password gets hashed again

- This hashed value gets compared to the value stored in the database

- This works reliably because the same password will always gets hashed to the
same value

- Different types of password encoders generate a different length of encoded
value

- The longer the encoded value, the more potential values it can be decoded
into

- Though, only one of those possible values would be correct
