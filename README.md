# BackendFinalProject

-TODOS
[ ] Tests
[ ] interfaces
[ ] dto
[ ] services
[ ] controllers
[ ] diagramas
[ ] readme
[ ] comentar codigo

[ ] Interest on credit cards is added to the balance monthly. If you have a 12% interest rate (0.12) then 1% interest
 will be added to the account monthly. When the balance of a credit card is accessed, check to determine if it
has been 1 month or more since the account was created or since interest was added,
 and if so, add the appropriate interest to the balance.
CreditCard accounts have a default creditLimit of 100
CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
CreditCards have a default interestRate of 0.2
CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1


[ ]  role poner fijo a ACCOUNT_HOLDER

[ ]  When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.

[ ]  Interest on savings accounts is added to the account annually at the rate of specified interestRate per year.
    That means that if I have 1000000 in a savings account with a 0.01 interest rate, 1% of 1 Million is added
    to my account after 1 year. When a savings account balance is accessed, you must determine if it has been
     1 year or more since either the account was created or since interest was added to the account, and add
     the appropriate interest to the balance if necessary.
Savings accounts have a default interest rate of 0.0025
Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5
Savings accounts should have a default minimumBalance of 1000
Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100
