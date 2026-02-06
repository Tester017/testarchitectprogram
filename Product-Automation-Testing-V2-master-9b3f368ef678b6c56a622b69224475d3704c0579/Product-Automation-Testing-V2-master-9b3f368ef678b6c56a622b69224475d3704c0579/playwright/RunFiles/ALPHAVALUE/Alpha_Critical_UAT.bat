cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
mvn test -DClient="CANTOR" -Dtype=Critical -DProductionMode=false