cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
mvn test -DClient="PIPER" -Dtype=Smoke -DProductionMode=true