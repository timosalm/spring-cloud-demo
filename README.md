# Demo for a typical Spring Cloud Architecture

![](architecture.png)

## Deployment on Cloud Foundry
```
cf create-service p.mysql db-small mysql
cf create-service p.service-registry standard service-registry
cf create-service p.config-server standard config-server -c '{"git": { "uri": "https://github.com/tsalm-pivotal/spring-cloud-demo", "label": "main", "searchPaths": "config-server-configuration"} }'
cf create-service p-rabbitmq standard rabbit-mq
cf create-service p-redis shared-vm redis #cf create-service p-cloudcache extra-small cloud-cache

./mvnw clean package -DskipTests
cf push
```

## API usage  
- Fetch products:
	```
	curl https://sc-gateway.YOUR_DOMAIN/SC-PRODUCT-SERVICE/api/v1/products
	```
- Fetch orders:
	```
	curl https://sc-gateway.YOUR_DOMAIN/SC-ORDER-SERVICE/api/v1/orders
	```
- Create order (After 10 seconds the status of the order should be DELIVERED)
	```
	curl --header "Content-Type: application/json" --request POST \
	  --data '{"productId":1,"shippingAddress":"Test address"}' \
	  https://sc-gateway.YOUR_DOMAIN/SC-ORDER-SERVICE/api/v1/orders
	```