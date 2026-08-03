# REST API

## Web as a File System

- In the early days of web, URLs pointed to actual physical location of the
resources in the server

- For example, in `www.example.com/users/42`, `/users/42` was a real directory
in the server of `www.example.com`

- However, that exposes details about the server, thus creating a security risk

- **REST** uses these links as *virtual locations* for the resources

- The physical location is changed and a routing mechanism (or a mail room) is
put in its place

- So the frontend knows a made-up location, and the mail room can map it to the
real deal

## Application Programming Interface (API)

- API is the implementation detail that the *virtual addresses abstract*

- It is the *interface* that you talk to, when you wish to *program* to a
*software application*

- In web services, it works by decoding the request link and asking the
relevant resources from the server
