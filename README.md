# Distance Calculator
Web service (REST) application for distance calculation.

## 1.Technologies

1. Maven
2. Java 11
3. Spring Boot 2.4.5 + Tomcat
4. MySQL 5.7
5. Liquibase 4.3.2
6. JAXB

## 2. Database

1. City
  1.1 Name
  1.2 Latitude
  1.3 Longitude
2. Distance
  2.1 From city
  2.2 To city
  2.3 Distance
  
  Database login: `root` password: `root`

## 3.Functionality

1. Display the distance between two cities crow flight
2. Display the distance via "distance matrix"
3. Upload XML file with cities and distances and stores into database.
4. Display cities list

## 4.Endpoints

`GET api/city` - display cities

#### Request

empty

#### Response

Response is made up of an array of cities made up of identifiers and names:

```
[{
"id": LONG
"name": STRING
}...
]
```
---

`PUT api/city` - upload cities and thear distance

#### Request

file.xml (example is in the test resources folder) 

#### Response

Empty response with code 200

---

`POST api/distance` - display distance between two cities

#### Request

```
{
"calculation_type": STRING
"from_city_list": 
[ "name": STRING
... ]
"to_city_list": 
[ "name": STRING
... ]
}
```

#### Response

```
{
data: [
{
"city_from": STRING
"city_to": STRING 
"distance_crow_fright": DOUBLE (optional)
"distance_matrix": DOUBLE (optional)
}
... ]
}
```
There are three output options depending on the parameter `calculation_type` in request:
`crow_flight`,
`distance_matrix`,
`all`
