cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 
set java_home=C:\automation\jdk1.8.0_144\jdk1.8.0_144
set maven_home=C:\automation\apache-maven-3.8.2-bin\apache-maven-3.8.2
set path=%path%;%java_home%\bin;%maven_home%\bin
mvn test -DClient=STEPHENS -Dtype=/ModuleWiseXML/ROITest -DProductionMode=false
