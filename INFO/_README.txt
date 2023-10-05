_______________
На прикладі проекту JAX-RS-Jersey-Netty-Users, створіть проект JAX-RS-Jersey-Contacts.
Contact має: id, ім'я, телефон. В INFO/README.txt залиште приклади REST-запитів.
______________

TEST REST API
---------------

Отримання всіх даних
GET
http://localhost:8082/api/v1.0/contacts

Отримання даних за id
GET
http://localhost:8082/api/v1.0/contacts/2

Створення даних
POST
http://localhost:8082/api/v1.0/contacts

Налаштування в Postman: Body, raw, JSON.

{
    "id": 5,
    "name": "Eddy",
    "phone": "888 888-8888"
}

Оновлення даних за id
PUT
http://localhost:8082/api/v1.0/contacts/2

Налаштування в Postman: Body, raw, JSON.

{
    "phone": "111 111-1111"
}

Видалення даних за id
DELETE
http://localhost:8082/api/v1.0/contacts/3



