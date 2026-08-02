# RESTful API

- A RESTful API is an API that follows the *principles of REST*

- For an API to be RESTful, it must abide by the following principles:

## 1. Uniform Interface

- Resources are uniquely identifiable by their URIs and manipulated through
their representations (XML, JSON, etc.)

- Every response contain enough metadata to tell the client how to process it

- HTTP methods should be used according to their semantic meaning

## 2. Client-Server Decoupling

- The client and the server must have *separate responsibilities*

- The client requests the data to display it, whereas the server stores and
processes it

## 3. Statelessness

- Every request must contain all the information the server needs to process it

- The server should not need additional context from previous requests

## 4. Cacheability

- Server responses should *explicitly* label themselves as *cacheable* or
*non-cacheable*

- Allowing local storage reduces latency and eliminates repeated requests for
the same static data

## 5. Layered System Architecture

The client shouldn't know or need to know if it is talking directly to the
server or an intermediary like a proxy or a cache

## 6. Code on Demand (Optional)

The server may *temporarily* extend client capabilities by sending executable
code directly to it
