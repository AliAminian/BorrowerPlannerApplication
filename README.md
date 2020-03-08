# BorrowerPlannerApplication
A Restful API that has one endpoint to generate a borrower plan via HTTP in JSON. Plan for loan by its information.

## Assumptions
For simplicity, we will have the following day convention: each month has 30 days , a year has 360 days .

# Solution
url : http://localhost:8080
Transaction:
Path: /generate-plan
Http methods: POST
Controller: BorrowerPlanController
Dto: 

## Tools and frameworks:
JAVA 8
Maven
Spring REST Docs , Spring boot
JUnit / Mockito


## Sample Request
POST /generate-plan HTTP/1.1 <br/>
Content-Type: application/json;charset=UTF-8 <br/>
Accept: application/json;charset=UTF-8 <br/>
Host: localhost:8080 <br/>
Content-Length: 84 <br/>

{ <br/>
  &nbsp;&nbsp;&nbsp;"loanAmount":"100", <br/>
  &nbsp;&nbsp;&nbsp;"nominalRate":"5.0", <br/>
  &nbsp;&nbsp;&nbsp;"duration":5, <br/>
  &nbsp;&nbsp;&nbsp;"startDate":"2017-12-31T23:00:01Z" <br/>
} <br/>

## Sample Response
HTTP/1.1 200 OK <br/>
Content-Type: application/json;charset=UTF-8 <br/>
Content-Length: 541 <br/>

[ <br/>
&nbsp;&nbsp;&nbsp;{ <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"borrowerPaymentAmount":"33.61", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"date":"2018-01-01T00:00:01Z", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"initialOutstandingPrincipal":"100.00", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"interest":"0.42", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"principal":"33.19", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"remainingOutstandingPrincipal":"66.81" <br/>
&nbsp;&nbsp;&nbsp;}, <br/>
&nbsp;&nbsp;&nbsp;{ <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"borrowerPaymentAmount":"33.61", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"date":"2018-02-01T00:00:01Z", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"initialOutstandingPrincipal":"66.81", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"interest":"0.28", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"principal":"33.33", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"remainingOutstandingPrincipal":"33.48" <br/>
&nbsp;&nbsp;&nbsp;}, <br/>
&nbsp;&nbsp;&nbsp;{ <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"borrowerPaymentAmount":"33.61", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"date":"2018-03-01T00:00:01Z", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"initialOutstandingPrincipal":"33.48", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"interest":"0.14", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"principal":"33.47", <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"remainingOutstandingPrincipal":"0.01" <br/>
&nbsp;&nbsp;&nbsp;} <br/>
]

## CURL sample

$ curl 'http://localhost:8080/generate-plan' -i -X POST \ <br/>
    -H 'Content-Type: application/json;charset=UTF-8' \ <br/>
    -H 'Accept: application/json;charset=UTF-8' \ <br/>
    -d '{"loanAmount":100,"nominalRate":5.0,"duration":5,"startDate":"2017-12-31T23:00:01Z"}'
