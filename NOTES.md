## Server Swap

Please make sure to create swap for the Amazon Lightsail Instance or it will be slow and sometimes crash.

## Disown Process
```
1. Remember to start server then Ctrl + Z
2. bg
3. disown %1
```
## Server Certificate Setup

Edit application.properties to include the following:

```
server.port=8443
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=[password goes here]
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=tomcat
```

In the same /resources directory in Tessera Server/src/main/resources put the keystore.p12 file

## Web Setup

In index.html, set API_URL to "https://www.tessera-lang.org:8443" (by default it is set to localhost)
