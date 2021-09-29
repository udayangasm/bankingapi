#Banking API

REST END POINTS 

------------- Register Account ----------------

http://localhost:8080/register

Type: POST
Request Params:
{
"name":"udaya",
"accountId":"1234"
}

Response

{
"accountId": "1234",
"name": "udaya",
"result": "Account Successfully Created",
"resultCode": "CREATED"
}

------------- Deposit Money ----------------


http://localhost:8080/deposit

Type: POST
Request Params:
{
"accountId":"1234",
"amount":700
}

Response

{
"id": 2,
"result": "Deposit is successful",
"resultCode": "CREATED"
}

------------- Withdraw Money ----------------


http://localhost:8080/withdraw


Type: POST
Request Params:
{
"accountId":"1234",
"amount":300
}

Response

{
"id": 0,
"result": "Withdraw is successful",
"resultCode": "CREATED"
}

------------- Transfer Money ----------------

http://localhost:8080/transfer


Type: POST
Request Params:
{
"senderAccountId":"1234",
"recipientAccountId":"12345",
"amount":500
}

Response

{
"id": 0,
"result": "Transfer is successful",
"resultCode": "OK"
}

------------- Check Account Balance ----------------

http://localhost:8080/balance?id=1234


Type: GET


Response

{
"id": 2,
"result": "Deposit is successful",
"resultCode": "CREATED"
}


