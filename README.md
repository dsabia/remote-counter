### Distribute counter node test
#### Description
> The task is to write a server and a client that allows multiple clients to simultaneously access a counter - the resource. Clients
> should be able to increment and decrement the counter and get its current value, via a protocol of your own design. The protocol
> should be over TCP/IP and not be built upon HTTP, RMI or similar. Care should be taken to persist the counter across restarts and/or
> failures, and a database should not be used. You may use whatever libraries or frameworks you deem fit to assist you in the task.
> A command line interface is sufficient.

#### Notes

Here, few some answare:

###### programming language
Java

###### programming paradigms
OO with some deign pattern:

1. Command

2. Factory

3. EventBus 

4. Publish–Subscribe


###### libraries
* JCS: java cache system for Lateral TCP Cache
* Guava
* Junit

######Frameworks

######Protocols
* Lateral TCP Cache
* Socket

######Architecture
1. client server architecture (simple socket sender->receiver) on *localhost:4444*
2. distribuite cluster cache among nodes *[localhost:11111-localhost:11110]*

######Optimisations
1. Separation of cluster and socket, for improve testing

######Coding style

######Test strategy 
...in progress

third party library integration not suggest a fast way to test but more the use of some framework and mock. Mainly is to test configuration.

#####Anything else

######Known issue
* Still not working the failure recovery on Windows: probably test not passed.
 
######TODO
* properties file for the confiuration
* improve log
* found a way to test client and server working togheter (inproject for Acceptance Test)

