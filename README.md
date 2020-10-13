# spring-cloud-demo
Demo for a typical Spring Cloud Architecture

```
cf create-service cleardb spark mysql
cf create-service p-service-registry standard service-registry
cf create-service p-config-server standard config-server -c '{"git": { "uri": "https://github.com/tsalm-pivotal/spring-cloud-demo", "label": "main", "searchPaths": "config-server-configuration"} }'
cf create-service cloudamqp lemur rabbit-mq
cf create-service p-cloudcache extra-small cloud-cache
./mvnw clean package -DskipTests
cf push
```