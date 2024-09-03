set -e

output="cat > openapi.json"
if command -v jq > /dev/null 2>&1; then
  output="jq -M > openapi.json"
fi
curl 'https://apifox.com/api/v1/projects/4036118/shared-docs/5a4bbc0b-85fe-4632-8753-0d49e725a1eb/export-data' \
  -H 'Content-Type: application/json;charset=UTF-8' \
  -H 'X-Client-Version: 2.2.30' \
  -H 'X-Project-Id: 4036118' \
  --data-raw '{"type":"openapi","id":"5a4bbc0b-85fe-4632-8753-0d49e725a1eb","version":"3.0","excludeExtension":true,"projectId":4036118}' \
  --compressed | eval $output

openapi-generator generate \
    -i openapi.json \
    -o ./openapi \
    -c ./openapi-config.yaml \
    -g spring \
    --library spring-boot \
    --artifact-id smartobserv \
    --artifact-version 0.0.1-SNAPSHOT \
    --group-id com.stardata \
    --api-package com.stardata.observ.api \
    --invoker-package com.stardata.observ \
    --model-package com.stardata.observ.vo \
    --package-name com.stardata.observ
