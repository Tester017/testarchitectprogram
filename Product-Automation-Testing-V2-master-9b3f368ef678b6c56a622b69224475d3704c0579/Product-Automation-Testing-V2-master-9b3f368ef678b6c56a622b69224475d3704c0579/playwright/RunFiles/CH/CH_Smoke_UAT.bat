cd../../
set java_home=E:\softwares\Java 1.8.0_211\Java 1.8.0_211\jdk1.8.0_211
set maven_home=E:\softwares\apache-maven-3.8.2-bin\apache-maven-3.8.2
set path=%path%;%java_home%\bin;%maven_home%\bin
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
cmd /k mvn test -DClient="CH" -Dtype=Smoke -DProductionMode=false