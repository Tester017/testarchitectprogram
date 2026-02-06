cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 
mvn test -DClient=ROSENBLATT -Dtype=/ModuleWiseXML/BulkCallTest -DProductionMode=false
