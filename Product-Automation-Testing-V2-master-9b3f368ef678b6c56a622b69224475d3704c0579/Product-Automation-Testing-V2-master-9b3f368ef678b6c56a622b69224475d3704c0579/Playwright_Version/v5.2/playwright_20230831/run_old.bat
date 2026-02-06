cd F:\Elavarasan\Framework old and new\Product-Automation-Testing-V2\playwright
set java_home=F:\Softwares\jdk1.8.0_144
set maven_home=F:\Softwares\apache-maven-3.8.2-bin\apache-maven-3.8.2
set path=%path%;%java_home%\bin;%maven_home%\bin
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
mvn test -Dclient="SIDOTI" -Dtype=Smoke
mvn test -Dclient="CAPITALONE" -Dtype=Smoke
mvn test -Dclient="CHARDAN" -Dtype=Smoke
mvn test -Dclient="CH" -Dtype=Smoke


  