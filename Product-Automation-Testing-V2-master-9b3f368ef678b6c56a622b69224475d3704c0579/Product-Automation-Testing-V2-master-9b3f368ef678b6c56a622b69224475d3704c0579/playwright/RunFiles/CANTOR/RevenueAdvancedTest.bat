cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 
set java_home=C:Usersser-dynamicsDownloadsjd 2jdk1.8.0_144
set maven_home=C:Usersser-dynamicsDownloadsapache-maven-3.8.2 1apache-maven-3.8.2
set path=%path%;%java_home%\bin;%maven_home%\bin
mvn test -DClient=CANTOR -Dtype=/ModuleWiseXML/RevenueAdvancedTest -DProductionMode=true
