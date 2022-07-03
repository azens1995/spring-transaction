## Transaction Propagation

For reference video, refer [here](https://www.youtube.com/watch?v=O9vrhKlGZbE&list=PLOk4ziGG9MBdlyxIDw5wYvj6QZTQ22wvK&index=6).

### Theory
1. When is the transaction created? Is it created in the Service or in the Repository?
2. Where is the transaction ending? Is it getting end in the end of the repository method or is it getting end in the service method?
3. When is the transaction created? When does the transaction commit or rollback?
4. Are there multiple transaction created or is it only one?

- By default the propagation level is Required.
- Propagation level can be set by using the name propagation inside the `@Transactional` annotation.

### Types of Propagation
1. REQUIRED
2. REQUIRES_NEW
3. MANDATORY
4. NEVER
5. SUPPORTS
6. NOT_SUPPORTED
7. NESTED `This is only supported in the Spring.`

#### REQUIRED (Default)
A() --> B()

If method A calls method B and A() is annotated with the `@Transactional` annotation,
then transaction is created in the A(). Same transaction will be used by B(),
and transaction will be committed at the end of A().

- REQUIRED means, transaction is created if there's not transaction, and use the existing transaction if it already exists.
- If A() is annotated with `@Transactional` and so is B(), then A() will create the transaction and this transaction will be used by B() too. B() will not create the new transaction. It will use the existing one from the A().
- Runtime Exception marks the transaction for rollback by default.

#### REQUIRES_NEW
A() --> B() 
- A() is annotated with `@Transactional` with default propagation.
- B() is annotated with `@Transactional(propagation = REQUIRES_NEW)` with REQUIRES_NEW propagation level.
- It tells that even if the transaction is present, always create the transaction.
- The first transaction is created by the A(), and other transaction is created by B().
- If A() just calls B(), the exception occurred in A() will cause the Transaction to stop, and rollback will occur. Since, A doesnot executes SQL query itself, not rollback will be used. The transaction occured in the B() will not rollback.

#### MANDATORY
- It does not creates transaction. It just makes sure that you need to have the transaction when the method annotated with `@Transaction(propagation = MANDATORY)` is called from other method.
- If there's the transaction, then that transaction will be used.
- Forces a method to have transaction.

#### NEVER
- Opposite of Mandatory
- If there's the transaction and method is annotated with propagation level `NEVER`, the execution will fail.

#### NOT_SUPPORTED
`@Transactional(propagation = Propagation.NOT_SUPPORTED)`
A()
|
`@Transactional(propagation = Propagation.MANDATORY)`
B()

- Create not transaction when the method is called. Here, A() will not create the transaction, and since B() has MANDATORY propagation, it will throw an error.

#### SUPPORTS
`@Transactional`
A()
|
`@Transactional(propagation = Propagation.SUPPORTS)`
B()
- We can execute with the transaction, but if there's no transaction, method will be executed.
- A() will create the transaction, and same transaction will be used by B().
- If there's no transaction being created by A(), B() will still execute.

#### NESTED
- Creating a transaction, within the transaction


## ISOLATION
There are four isolation levels, and five isolation enum constant.
1. DEFAULT --> Read Committed
2. READ_UNCOMMITTED
3. READ_COMMITTED
4. REPEATABLE_READ
5. SERIALIZABLE

What is Isolation level?

PROBLEMS:
- Dirty Reads
- Repeatable Reads
- Phantom Reads

#### READ_UNCOMMITTED
T1
T2

If there's two transaction, and T1 reads the data, which the T2 has changed
and T2 is not yet committed, still T1 will be able to read the value changed by the T2.
It is referred to as dirty read. 

#### READ_COMMITTED

Dirty read issue will not occur. But there will still be repeatable reads and phantom reads issue.


### REPEATABLE_READS

