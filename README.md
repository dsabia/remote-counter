### Distribute counter node test
#### Description
> The task is to write a server and a client that allows multiple clients to simultaneously access a counter - the resource. Clients
> should be able to increment and decrement the counter and get its current value, via a protocol of your own design. The protocol
> should be over TCP/IP and not be built upon HTTP, RMI or similar. Care should be taken to persist the counter across restarts and/or
> failures, and a database should not be used. You may use whatever libraries or frameworks you deem fit to assist you in the task.
> A command line interface is sufficient.

#### Installation
1. from main root, execute command

 > mvn clean compile
 
2. on project **counter-server**, execute command

 > mvn install
 
3. on project **counter-client**, in another console, run

 > mvn install


#### Notes

###### Programming language
Java

###### Programming paradigms
OO with some deign pattern:

1. Command
2. Factory
3. EventBus 
4. Publish–Subscribe

###### Libraries
* JCS: java cache system for Lateral TCP Cache
* Guava
* Junit

######Frameworks
*ops, no spring!*

######Protocols
* Lateral TCP Cache
* Socket

######Architecture
1. client server architecture (simple socket sender->receiver) on **localhost:4444**
2. distribuite cluster cache among nodes **[localhost:11111-localhost:11110]**

######Optimisations
1. Separation of cluster and socket, for improve testing

######Coding style


######Test strategy 
*...in progress*

For intense use of third party library, it is suggest to use Arqillian to test some caches configuration.

#####Anything else

######Known issue
* Still not working the failure recovery on Windows: probably test not passed.
* **counter-serever** project name is wrong directory name
 
######TODO
* properties file for the confiuration
* improve log

