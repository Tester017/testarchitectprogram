cd../../
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
cmd /k mvn test -DClient="CH" -Dtype=DashboardTest -DProductionMode=false