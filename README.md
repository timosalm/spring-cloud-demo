# spring-cloud-demo
Demo for a typical Spring Cloud Architecture

```
cf create-service p.mysql db-small mysql
cf create-service p.service-registry standard service-registry
cf create-service p.config-server standard config-server -c '{"git": { "uri": "https://github.com/tsalm-pivotal/spring-cloud-demo", "label": "main", "searchPaths": "config-server-configuration"} }'
cf create-service p-rabbitmq standard rabbit-mq
cf create-service p-redis shared-vm redis #cf create-service p-cloudcache extra-small cloud-cache
cf create-service p-circuit-breaker-dashboard standard circuit-breaker-dashboard

./mvnw clean package -DskipTests
cf push
```