cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
cmd /k mvn test -DClient="RENMAC" -Dtype=Smoke -DProductionMode=false


  