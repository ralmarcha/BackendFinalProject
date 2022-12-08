# Bank BackEnd Project
## Table of Contents
1. [General Info](#general-info)
2. [Account Requirements](#account-requirements)
3. [User Requirement](#user-requirements)
4. [Technical Requirement](#technical-requirement)
5. [Deliverables](#deliverables)

## Requisitos:

- Tener instalado Java 17.
- Tener instalado mysql.
- Crear base de datos: `project`
- ADMIN user: - username: `admin` , password: `admin`

## ENDPOINTS

* POST :

  > /transfer (Role: "ACCOUNT_HOLDER")
  
```
  {
     "originAccountId": Long,
     "destinationAccountId": Long,
     "amount": BigDecimal,
     "senderName": String
  }
  
```
  > /create-checking-account (Role: "ADMIN")
  > /create-savings-account (Role: "ADMIN")
  > /create-credit-card (Role: "ADMIN")

```
  {
      "secretKey": String,
      "balance": BigDecimal,
      "primaryOwner": AccountHolder 
  }
  
```  
   > /transfer/send (Role: "THIRD_PARTY")

```
 {
       "accountId": Long,
       "amount": BigDecimal
 }

```
  > /transfer/receive (Role: "THIRD_PARTY")

```
{
       "accountId": Long,
       "secretKey": String,
       "amount": BigDecimal
}
  
```
  > /create-account-holder (Role: "ADMIN")

```
{
       "username": String,
       "password": String,
       "address": PrimaryAddress,
       "dateOfBirth": LocalDate
}
  
```
  > /create-third-party (Role: "ADMIN")
```
{
       "hashKey": String,
       "name": String,
}
  
```
  > /create-admin (Role: "ADMIN")
```
{
       "username": String,
       "password": String,
}
  
```

  * GET :
  > /check-user-balance (Role: "ACCOUNT_HOLDER")
    @RequestParam Long accountId, @RequestParam Long userId

  > /check-balance/{id} (Role: "ADMIN")
  > /accounts (Roles: "ADMIN", "ACCOUNT_HOLDER")
  
  > /account/{id} (Roles: "ADMIN", "ACCOUNT_HOLDER")
  
* PUT :
  > /update-checking-account/{id} (Role: "ADMIN")

```
{
    "secretKey": String,
    "lastUpdateDate": LocalDate,
    "primaryOwnerId": Long,
    "creationDate": LocalDate,
    "secondaryOwner": AccountHolder
}

```

* PATCH :
  > /set-balance/{id} (Role: "ADMIN")
  "balance": BigDecimal
* DELETE :
  > /delete-account/{id} (Role: "ADMIN")


## Class Diagram

![](./)

## Use Case Diagram

![](./use-case-diagram.jpeg)
