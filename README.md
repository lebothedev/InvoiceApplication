# Getting Started

### Installing

Clone the project to your local machine then run the following command:

```
mvn clean install
```

## Running the programs

Locate the target folder and locate the compiled JAR file:

```
target/invoiceservice-1.0-SNAPSHOT.jar
```
The program will launch an H2 in-memory database. this database can be accessed at:

```
http://localhost:8080/h2-console
```

Use the following credentials to login:

Username: `sa`

Password: `password`

You should see the following 3 tables:

```
INVOICE
INVOICE_LINE_ITEMS
LINE_ITEM
```

## Endpoints
The REST web server accepts requests on the following endpoints:

Retrieve all invoices:
```
GET http://localhost:8080/invoices
```

Retrieve a single invoice items:

```
GET http://localhost:8080/invoices/{id}
```

Record/Save an invoice:

```
POST http://localhost:8080/invoices
```

Here is an example of a request to save an invoice:

```
{
    "client": "Lebogang",
    "date": "2021-07-18",
    "lineItems": [
        {
            "description": "Tomatoes",
            "unitPrice": 12.99,
            "quantity": 5
        },
        {
            "description": "Brown Bread",
            "unitPrice": 10.99,
            "quantity": 3
        },
                {
            "description": "Milk",
            "unitPrice": 78.99,
            "quantity": 2
        }
    ]
}
```

## HTTP Return Codes
The program accepts universal HTTP codes for data creation and retrieval:

When an invoice is created: `201`

When an invoice is retrieved: `200`

When an invoice cannot be found: `204`

When a URL does not exist: `404`

When an invalid request has been submitted: `400`

Any other severe error: `500`


### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.2/maven-plugin/reference/html/#build-image)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.5.2/reference/htmlsingle/#boot-features-sql)
* [Spring Web Services](https://docs.spring.io/spring-boot/docs/2.5.2/reference/htmlsingle/#boot-features-webservices)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)

