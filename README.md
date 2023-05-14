# Relación One To Many
Se implementa una relación 1:N con JPA/Hibernate entre las tablas **Client**
y **Account** como sigue:

![model](src/main/resources/images/modelo_datos.png)

Operaciones CRUD:

| Methods | Urls                                 | Actions                                                     |
|---------|--------------------------------------|-------------------------------------------------------------|
| GET     | /api/clients                         | devuelve la lista de clientes                               |
| GET     | /api/accounts                        | devuelve la lista de cuentas                                |
| GET     | /api/clients/:id/accounts            | devuelve una lista de todas las cuentas de un cliente :id   |
| GET     | /api/accounts/:id                    | devuelve la cuenta :id                                      |
| POST    | /api/clients/new                     | crea un cliente nuevo con una lista de cuentas (al menos 1) |
| POST    | /api/clients/:id/accounts/new        | crea una nueva cuenta para un cliente :id                   |
| PUT     | /api/clients/edit/:id                | actualiza los datos de un cliente :id                       |
| PUT     | /api/accounts/edit/:id               | actualiza los datos de una cuenta :id                       |
| DELETE  | /api/clients/delete/:id              | borra un cliente por :id y todas sus cuentas asociadas      |
| DELETE  | /api/clients/:id/accounts/delete/:id | borra una cuenta :id de un cliente :id                      |

