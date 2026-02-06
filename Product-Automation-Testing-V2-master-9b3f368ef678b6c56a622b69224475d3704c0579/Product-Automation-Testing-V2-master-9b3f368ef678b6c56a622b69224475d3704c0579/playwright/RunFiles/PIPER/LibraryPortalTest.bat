cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 
set java_home=I:\Automation\jdk1.8.0_144
set maven_home=I:\Automation\apache-maven-3.8.2
set path=%path%;%java_home%\bin;%maven_home%\bin
mvn test -DClient=PIPER -Dtype=/ModuleWiseXML/LibraryPortalTest -DProductionMode=true
