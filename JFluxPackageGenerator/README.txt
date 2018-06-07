Installation Requirements:
1) Java 7
2) Maven 3.1.1 (at least)

Make Maven --> Update Project 
Execute command "clean package" and you get the application in "target" folder

Execute command "jetty:run" and you get working application on localhost:8080
BTW (For Reksoft users) you should use the proxy setting in order to "jetty:run" works correctly:
-Dhttp.proxyHost=proxy.reksoft.ru -Dhttp.proxyPort=3128