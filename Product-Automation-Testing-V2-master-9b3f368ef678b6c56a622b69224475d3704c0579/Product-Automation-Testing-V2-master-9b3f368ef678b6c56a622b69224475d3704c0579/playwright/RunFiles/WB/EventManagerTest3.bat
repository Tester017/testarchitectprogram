cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 
set java_home=J:\Kowsalya\Automation Script\Java 1.8.0_211\Java 1.8.0_211\jdk1.8.0_211
set maven_home=J:\Kowsalya\Automation Script\apache-maven-3.8.2-bin\apache-maven-3.8.2
set path=%path%;%java_home%\bin;%maven_home%\bin
mvn test -DClient=WB -Dtype=/ModuleWiseXML/EventManagerTest3 -DProductionMode=true
