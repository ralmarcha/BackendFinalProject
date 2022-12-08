# Bank BackEnd Project

## Table of Contents

1. [General Info](#general-information)
2. [User Requirements](#user-requirements)
3. [End Points](#end-points)
4. [Class Diagram](#class-diagram)
5. [Use Case Diagram](#use-case-diagram)

## General Information:

## User Requirements:

- Database schema: `project`
- ADMIN user:  username: `admin` , password: `admin`

## END POINTS

***POST :***

  - /transfer *(Role: "ACCOUNT_HOLDER")*
  
```
  {
     "originAccountId": Long,
     "destinationAccountId": Long,
     "amount": BigDecimal,
     "senderName": String
  }
```
  - /create-checking-account *(Role: "ADMIN")*

  - /create-savings-account *(Role: "ADMIN")*

  - /create-credit-card *(Role: "ADMIN")*

```
  {
      "secretKey": String,
      "balance": BigDecimal,
      "primaryOwner": AccountHolder 
  } 
```  
   - /transfer/send *(Role: "THIRD_PARTY")*

```
 {
       "accountId": Long,
       "amount": BigDecimal
 }
```
  - /transfer/receive *(Role: "THIRD_PARTY")*

```
{
       "accountId": Long,
       "secretKey": String,
       "amount": BigDecimal
} 
```
  - /create-account-holder *(Role: "ADMIN")*

```
{
       "username": String,
       "password": String,
       "address": PrimaryAddress,
       "dateOfBirth": LocalDate
}
```
  - /create-third-party *(Role: "ADMIN")*
```
{
       "hashKey": String,
       "name": String,
}
```
  - /create-admin *(Role: "ADMIN")*
```
{
       "username": String,
       "password": String,
}
```

***GET :***

  - /check-user-balance *(Role: "ACCOUNT_HOLDER")*
    @RequestParam Long accountId, @RequestParam Long userId

  - /check-balance/{id} *(Role: "ADMIN")*
  
  - /accounts *(Roles: "ADMIN", "ACCOUNT_HOLDER")*
  
  - /account/{id} *(Roles: "ADMIN", "ACCOUNT_HOLDER")*

***PUT***

  - /update-checking-account/{id} *(Role: "ADMIN")*

```
{
    "secretKey": String,
    "lastUpdateDate": LocalDate,
    "primaryOwnerId": Long,
    "creationDate": LocalDate,
    "secondaryOwner": AccountHolder
}
```

***PATCH***

  - /set-balance/{id} *(Role: "ADMIN")*
    @RequestParam BigDecimal balance
***DELETE***

  - /delete-account/{id} *(Role: "ADMIN")*


## Class Diagram

![](./)

## Use Case Diagram

![](./use-case-diagram.jpeg)
