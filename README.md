# remote-ejb-call-from-login-module
Repo dedicated as proof that there is a possible bug when a EJB remote call is done inside a javax.security module.

## Clone this repo
```
git clone https://github.com/jpmaida/remote-ejb-call-from-login-module.git
```

## Build
```
$ cd consumer/
$ mvn clean package -DskipTests
$ cd producer/
$ mvn clean package -DskipTests
```

