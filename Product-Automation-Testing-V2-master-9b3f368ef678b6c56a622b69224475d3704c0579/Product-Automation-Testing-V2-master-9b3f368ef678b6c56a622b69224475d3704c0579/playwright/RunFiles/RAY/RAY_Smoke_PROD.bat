cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
mvn test -DClient="RAY" -Dtype=Smoke -DProductionMode=true