# StockTest

## Overview

This project is composed by two modules:
- stock-api: This module is responsible to expose all API operations, list, detail, insert and update a Stock. Was developed using Java and Spring Boot.
- stock-view: This module is responsible to show as Stock inserted in stock-api. Was developed using Angular JS.

## Run

### Docker

To run in docker is just use the commands:

> sudo docker run -h "stock-api-test.com" -p 8080:8080 --name stock-api-server rdelsole/stock-api

>docker run --link stock-api --name stock-view -p 8081:80 rdelsole/stock-view

Then access http://localhost:8081

### Local

To run local, add the entry in /etc/hostd the map stock-api-test.com to 127.0.0.1.
Use gradle to run stock-api and ng to run stock-view

## Usage

The API expose 4 methods

> List All - Get - http://stock-api-test.com/api/stocks

> Detail One - Get - http://stock-api-test.com/api/stocks/ID

> Create - Post - http://stock-api-test.com/api/stocks/ - JSON {"name":"Test","currentPrice": 100 }

> Update - Put - http://stock-api-test.com/api/stocks/ID - JSON {"id": ID,"currentPrice": 100 }





