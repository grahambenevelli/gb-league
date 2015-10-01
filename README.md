# GB League API

This is the rest api for gb fantasy league.  

## Starting up the Server

The server has several parts that need to be run. The first is the running the VM. Which runs nginx and is the main server to hit. 

```
vagrant up
```

The second is the api server.

```
./gradlew clean run
```