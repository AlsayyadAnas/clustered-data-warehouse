# Clustered Data Warehouse Service
A service expose API's to manage the deals details


## Table of content
- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Testing](#Testing)

## installation
### Prerequisites
- List of any dependency required
- mongo db
### Steps
1. Clone the repository
   got clone https://github.com/AlsayyadAnas/clustered-data-warehouse.git
   
## usage
To start the application
mvn spring-boot:run
Go to http://localhost:8080/data-warehouse/v1/swagger-ui/index.html
### Run the service
1- Run the service locally by adding the vm option -Dspring.profiles.active=dev <br>
2- Run the service on docker by using the default profile

## configuration
The configuration can be adjusted in `application.yml` and `application-dev.yml` for internal use


## api-documentation

### POST /deals
**Description** Save deal details. <br>
**Request** <br>
```json
{
"dealUniqueId": "string",
"orderingFromCurrency": "string",
"orderingToCurrency": "string",
"dealDateTime": "2024-08-27T08:29:54.624Z",
"dealAmount": 0
}
```
**Response**: deal Id as String

### GET /deals
**Description** Get all the saved deal details. <br>
**Response** <br>
```json
[
   {
      "dealUniqueId": "string",
      "orderingFromCurrency": "string",
      "orderingToCurrency": "string",
      "dealDateTime": "2024-08-27T08:32:04.457Z",
      "dealAmount": 0
   }
]
```

## Testing
To run tests use:
mvn test




