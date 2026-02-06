cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 
set java_home=H:\AutomationScript\Softwares\Java\jdk1.8.0_144
set maven_home=H:\AutomationScript\Softwares\apache-maven-3.8.2
set path=%path%;%java_home%\bin;%maven_home%\bin
mvn test -DClient=MIZUHO -Dtype=/ModuleWiseXML/MyListTest -DProductionMode=true
