# JSON Web Token Authentication

- It allows the server to recognize a user on subsequent requests *without*
keeping a session

- It has three parts separated by a dot: `header.payload.signature`

- The header contains the token's type and the algorithm used for signing
it

- The payload contains public data about the user

- The signature is created as follows:

```java
public String getToken(String header, String payload, byte[] secret)
{
    String jwtHeader = toBase64(header);
    String jwtPayload = toBase64(payload);
    String signature = signingAlgorithm(jwtHeader + "." + jwtPayload, secret);
    return jwtHeader + "." + jwtPayload + "." + signature;
}
```
