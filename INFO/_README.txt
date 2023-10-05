
REST API
------------------------------
ТЕХСТЕК:

JAX-RS - специфікація API Jakarta EE, яка стандартизує
розробку та розгортання RESTful Web-сервісів за допомогою
технологій Java та JEE, відповідно до архітектурного шаблону
REST (REpresentational State Transfer).

Jersey RESTful Web Services Framework для розробки
RESTful Web Services на Java
https://projects.eclipse.org/projects/ee4j.jersey/

Jackson процесор JSON, який використовується для
маршалінгу/серіалізації та демаршалінгу/десеріалізації
об’єктів із Java у JSON і навпаки.
Jersey використовує Jackson внутрішньо.

Серіалізація — це процес перетворення об’єкта даних —
комбінації коду й даних, представлених в області зберігання
даних — у ряд байтів, який зберігає стан об’єкта у формі,
яку легко передавати. Десеріалізація - зворотній процес.

Такі формати даних, як JSON і XML, часто використовуються як
формат для зберігання серіалізованих даних.

Netty - NIO клієнт-серверний фреймворк, який дозволяє
розробляти мережеві програми, такі як протокольні сервери
та клієнти.
https://netty.io/

HK2 - фреймворк для впровадження залежностей.
https://javaee.github.io/hk2/

Lombok
https://projectlombok.org/

------------------------------

1) Створюємо Maven-проект.

2) Підтягуємо залежності (pom.xml).

3) Формуємо пакети, класи.

4) Запускаємо
org.example.app.App

5) Для тестування REST API, запускаємо
Postman (https://www.postman.com/)


TEST REST API
---------------

Отримання всіх даних
GET
http://localhost:8082/api/v1.0/users

Отримання даних за id
GET
http://localhost:8082/api/v1.0/users/2

Створення даних
POST
http://localhost:8082/api/v1.0/users

Налаштування в Postman: Body, raw, JSON.

{
    "id": 5,
    "name": "John",
    "email": "john@mail.com"
}

Оновлення даних за id
PUT
http://localhost:8082/api/v1.0/users/2

Налаштування в Postman: Body, raw, JSON.

{
    "email": "superbob@mail.com"
}

Видалення даних за id
DELETE
http://localhost:8082/api/v1.0/users/3



