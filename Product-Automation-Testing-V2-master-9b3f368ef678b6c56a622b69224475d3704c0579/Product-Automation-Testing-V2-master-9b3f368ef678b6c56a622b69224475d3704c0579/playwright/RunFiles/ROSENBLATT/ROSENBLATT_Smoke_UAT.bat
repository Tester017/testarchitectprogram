cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
set maven_home = D:\apache-maven-3.8.2-bin\apache-maven-3.8.2\bin
cmd /k mvn test -DClient="ROSENBLATT" -Dtype=ModuleWiseXML/AccountCoverageManagementTest -DProductionMode=false


  