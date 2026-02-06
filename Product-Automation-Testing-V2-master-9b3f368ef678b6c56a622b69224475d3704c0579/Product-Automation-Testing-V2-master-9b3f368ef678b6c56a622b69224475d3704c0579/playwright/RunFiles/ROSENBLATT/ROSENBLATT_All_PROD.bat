cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
cmd /k mvn test -DClient="ROSENBLATT" -Dtype=All -DProductionMode=true


  