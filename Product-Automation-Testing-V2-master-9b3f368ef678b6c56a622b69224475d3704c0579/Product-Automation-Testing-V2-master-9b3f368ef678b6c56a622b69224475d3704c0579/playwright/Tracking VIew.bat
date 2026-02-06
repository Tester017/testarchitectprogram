cd E:\Git\Product-Automation-Testing-V2\playwright
set java_home=E:\Software\Java\jdk1.8.0_144
set maven_home=E:\Software\apache-maven-3.8.2
set path=%path%;%java_home%\bin;%maven_home%\bin
cmd /k  mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="show-trace E:\Git\Product-Automation-Testing-V2\playwright\reports\02-May-2023_15-51-26\traces\RevenueModule52603.zip" 
    
   