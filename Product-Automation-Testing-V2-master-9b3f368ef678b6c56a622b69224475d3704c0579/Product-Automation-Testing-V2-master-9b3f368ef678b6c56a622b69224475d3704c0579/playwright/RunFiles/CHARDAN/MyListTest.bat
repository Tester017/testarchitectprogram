cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 
mvn test -DClient=CHARDAN -Dtype=/ModuleWiseXML/MyListTest -DProductionMode=false
