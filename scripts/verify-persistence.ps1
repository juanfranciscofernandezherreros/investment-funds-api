$ErrorActionPreference = 'Stop'
$verificationIsin = "ES$([DateTimeOffset]::UtcNow.ToUnixTimeSeconds().ToString().PadLeft(10, '0'))"

docker compose config
docker compose up -d --build

for ($attempt = 1; $attempt -le 30; $attempt++) {
    $health = Invoke-RestMethod -Uri 'http://localhost:8080/actuator/health' -ErrorAction SilentlyContinue
    if ($health.status -eq 'UP') { break }
    Start-Sleep -Seconds 2
}

$request = @{ isin = $verificationIsin; name = 'Persistence verification fund'; currency = 'EUR'; active = $true } | ConvertTo-Json
$response = Invoke-RestMethod -Uri 'http://localhost:8080/api/v1/funds' -Method Post -ContentType 'application/json' -Body $request
$fundId = $response.id
Write-Host "REQUEST POST /api/v1/funds $request"
Write-Host "RESPONSE 201 $($response | ConvertTo-Json -Compress)"

docker compose exec -T sqlserver /opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P $env:SQLSERVER_SA_PASSWORD -d investment_funds -Q "SELECT id, isin FROM investment_funds WHERE id = $fundId" -No
docker compose down
docker compose up -d

for ($attempt = 1; $attempt -le 30; $attempt++) {
    try { $retrieved = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/funds/$fundId"; break } catch { Start-Sleep -Seconds 2 }
}
if ($retrieved.isin -ne $verificationIsin) { throw 'Persistence verification failed' }
docker compose exec -T sqlserver /opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P $env:SQLSERVER_SA_PASSWORD -d investment_funds -Q "SELECT id, isin FROM investment_funds WHERE id = $fundId" -No
Write-Host 'Persistence verification passed; named volume retained data.'
