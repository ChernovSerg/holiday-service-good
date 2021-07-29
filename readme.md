# Описание
Микросервис holiday-service-good определяет следующий/предыдущий РАБОЧИЙ день, находящийся на workDaysCount (может быть положительным и отрицательным числом) дней от заданной даты date.
Сервис содержит кеш (бд h2), в котором хранится информация о запросах, и в случае ошибки внешнего сервиса ответ берется из кеша.

Входные параметры: json вида

{
"date": "22-07-2021",
"workDaysCount": 3
}

# Для запуска проекта необходимо
1. Наличие установленного docker и docker-compose
2. Наличие проекта с микросервисом holiday-unstable-service, который проверяет, является ли день выходным. Данный микросервис вызывается внутри текущего проекта.
3. Каталоги проектов микросервисов holiday-service-good и holiday-unstable-service должны находится на одном уровне в иерархии каталогов.
4. Из каталога, в котором содержатся каталоги проектов микросервисов, запускаем команду: docker-compose -f holiday-service-good\docker-compose.yml up

# Примеры использования
curl -X 'POST' \
'http://localhost:8888/goodHoliday/nextWorkDay' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"date": "24-07-2021",
"workDaysCount": 2
}'

curl http://localhost:8888/actuator/health - health check, возвращает статус сервера

curl http://localhost:8888/holidays-swagger.html - документация swagger для текущего микросервиса holiday-controller