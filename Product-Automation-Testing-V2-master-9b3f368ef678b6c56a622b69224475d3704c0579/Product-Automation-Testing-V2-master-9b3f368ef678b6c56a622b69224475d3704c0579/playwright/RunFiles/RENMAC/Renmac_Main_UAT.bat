cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
cmd /k mvn clean install test -DClient="QA" -Dtype=Main -DProductionMode=false


  