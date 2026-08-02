# HTTP Methods

HTTP defines semantic methods like `GET`, `POST`, `PUT`, and `DELETE` to
communicate over the web

The semantic meaning of these methods are as follows:

1. `GET` read data from the server, but make no writes/modifications

2. `POST` write new data to the server

3. `PUT` update data on the server, if no match exists, the server may add this
data

4. `DELETE` delete data on the server, if it exists, otherwise, do nothing

- `GET` is *safe* to use as it makes no changes to the server state but others
are not

- `GET`, `PUT` and `DELETE` are *idempotent* i.e. sending the same request
multiple times has the same effect as sending it once
