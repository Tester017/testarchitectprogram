cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 
mvn test -DClient=QA -Dtype=/ModuleWiseXML/ContactTearSheetTest -DProductionMode=false
