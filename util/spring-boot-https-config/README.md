# Spring Boot with HTTPS

# Command to create self signed SSL Certificate JKS

`keytool -genkey -alias bhai-brother -storetype JKS -keyalg RSA -keysize 2048 -validity 365 -keystore https-example.jks`

# Secured access

`https://localhost:4490/greeting-message` - returns 'ACCESS BY HTTPS/SECURE PROTOCOL!'

# NB

`when it prompt for`
`Enter keystore password:`
`just press enter button (Dont type anything).It should work.`
`But in the application.properties set default password`
`server.ssl.key-password=changeit`