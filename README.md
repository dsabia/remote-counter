### Distribute counter node test
#### Description
> The task is to write a server and a client that allows multiple clients to simultaneously access a counter - the resource. Clients
> should be able to increment and decrement the counter and get its current value, via a protocol of your own design. The protocol
> should be over TCP/IP and not be built upon HTTP, RMI or similar. Care should be taken to persist the counter across restarts and/or
> failures, and a database should not be used. You may use whatever libraries or frameworks you deem fit to assist you in the task.
> A command line interface is sufficient.

#### Notes

###### programming language
Java

###### programming paradigms
OO

###### libraries
JCS: java cache system for Lateral TCP Cache
Guava

######Frameworks

######Protocols
Lateral TCP Cache
Socket

######Architecture
1. client server architecture (simple socket sender->receiver) on localhost:4444 
2. distribuite cluster cache among nodes [localhost:11111-localhost:11110]

######Optimisations

######Coding style

######Test strategy 
...in progress

######Anything else

######Known issue
* Still not working the failure recovery on Windows
