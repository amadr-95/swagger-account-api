# Relación Many To One
Se implementa una relación 1:N con JPA/Hibernate entre las tablas **Customer**
y **Account** como sigue:

![model](src/main/resources/images/model.png)

Operaciones CRUD:

| Methods | Urls                                   | Actions                                                   |
|---------|----------------------------------------|-----------------------------------------------------------|
| GET     | /api/customers                         | devuelve la lista de clientes                             |
| GET     | /api/customers/:id                     | devuelve un cliente :id                                   |
| GET     | /api/accounts                          | devuelve la lista de cuentas                              |
| GET     | /api/accounts/customer/:id             | devuelve una lista de todas las cuentas de un cliente :id |
| GET     | /api/accounts/:id                      | devuelve la cuenta :id                                    |
| POST    | /api/customers/new                     | crea un cliente nuevo                                     |
| POST    | /api/accounts/new/customer/:id         | crea una nueva cuenta para un cliente :id                 |
| PUT     | /api/customers/edit/:id                | actualiza los datos de un cliente :id                     |
| PUT     | /api/accounts/edit/:id                 | actualiza los datos de una cuenta :id                     |
| DELETE  | /api/customers/delete/:id              | borra un cliente por :id y todas sus cuentas asociadas    |
| DELETE  | /api/customers/:id/accounts/delete/:id | borra una cuenta :id de un cliente :id                    |

