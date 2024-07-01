![Uploading photo_2024-06-26_15-28-20.jpg…](src/main/java/com/example/javaeetest2/utils/Asserts/photo_2024-06-26_15-28-20.jpg)

# Currency Exchange REST API Project

## Обзор и мотивация создания проекта

Данный проект представляет из себя REST API
для описания валют и обменных курсов, а также конвертации любой суммы из одной валюты в другую.
Проект создан в образовательных целях
по [предоставленному ТЗ](https://zhukovsd.github.io/java-backend-learning-course/Projects/CurrencyExchange/)
в контексте [java-backend-learning-course](https://zhukovsd.github.io/java-backend-learning-course/#%D1%82%D1%80%D0%B5%D0%B1%D1%83%D0%B5%D0%BC%D1%8B%D0%B5-%D0%B7%D0%BD%D0%B0%D0%BD%D0%B8%D1%8F-%D0%B8-%D1%82%D0%B5%D1%85%D0%BD%D0%BE%D0%BB%D0%BE%D0%B3%D0%B8%D0%B8)
от Сергея Жукова.

## Используемые технологии/инструменты:
- JDBC
- SQL
- Jakarta Servlets
- SQLite
- Postman
- Maven

## Установка

### Требования
+ Java 21+
+ Apache Maven
+ Tomcat 10
+ Intellij IDEA

### Запуск проекта локально

1. Клонировать репозиторий и открыть проект в Intellij IDEA
   ```
   git clone https://github.com/SahaPWNZ/ExchangeService
   ```
2. Далее в Intellij IDEA выбрать select Run -> Edit Configuration.
3. Нажимаем на Add new configuration, там выбираем tomcat и его настраиваем
4. Далее там же в настройках tomcat нажимаем на "fix" и добавляем "war exploded"
5. Запускаем проект
## Использование API
Деплой проекта: http://158.160.78.11:8080/currency-api/

Для проверки была созданна [коллекция в Postman](src/main/java/com/example/javaeetest2/utils/Asserts/Exchange-api.postman_collection.json) 
со всеми запросами

### Currencies

#### GET `/currencies`

Возвращает список всех валют. Пример ответа::

```json
[
  {
    "id": 0,
    "fullName": "United States dollar",
    "code": "USD",
    "sign": "$"
  },
  {
    "id": 1,
    "fullName": "Euro",
    "code": "EUR",
    "sign": "€"
  },
  "..."
]
```

#### GET `/currency/USD`

Возвращает определенную валюту. Код валюты указан в адресе запроса. Пример ответа:

```json
[
  {
    "id": 0,
    "fullName": "United States dollar",
    "code": "USD",
    "sign": "$"
  }
]
```

#### POST `/currencies`

обавление новой валюты в базу данных. Данные передаются в теле запроса в формате x-www-form-urlencoded. Форма
поля: «code», «fullName», «sign». Пример ответа (вставленная запись):

```json
[
  {
    "id": 2,
    "fullName": "Czech Koruna",
    "code": "CZK",
    "sign": "Kč"
  }
]
```

### Exchange rates

#### GET `/exchangeRates`

Возвращает список всех обменных курсов. Пример ответа:

```json
[
  {
    "id": 0,
    "baseCurrency": {
      "id": 0,
      "fullName": "United States dollar",
      "code": "USD",
      "sign": "$"
    },
    "targetCurrency": {
      "id": 1,
      "fullName": "Euro",
      "code": "EUR",
      "sign": "€"
    },
    "rate": 0.93
  },
  {
    "id": 1,
    "baseCurrency": {
      "id": 0,
      "fullName": "United States dollar",
      "code": "USD",
      "sign": "$"
    },
    "targetCurrency": {
      "id": 2,
      "fullName": "Czech Koruna",
      "code": "CZK",
      "sign": "Kč"
    },
    "rate": 22.16
  },
  "..."
]
```

#### POST `/exchangeRates`

Добавление нового курса валют в базу данных. Данные передаются в теле запроса в формате x-www-form-urlencoded. Форма
поля `baseCurrencyCode`, `targetCurrencyCode`, `rate`.Пример ответа (вставленная запись):

```json
[
  {
    "id": 2,
    "baseCurrency": {
      "id": 1,
      "fullName": "Euro",
      "code": "EUR",
      "sign": "€"
    },
    "targetCurrency": {
      "id": 2,
      "fullName": "Czech Koruna",
      "code": "CZK",
      "sign": "Kč"
    },
    "rate": 23.75
  }
]
```

#### GET `/exchangeRate/USDEUR`

Возвращает определенный обменный курс. Валютная пара указывается последовательными кодами валют в адресе запроса.
Пример ответа:

```json
[
  {
    "id": 0,
    "baseCurrency": {
      "id": 0,
      "fullName": "United States dollar",
      "code": "USD",
      "sign": "$"
    },
    "targetCurrency": {
      "id": 1,
      "fullName": "Euro",
      "code": "EUR",
      "sign": "€"
    },
    "rate": 0.93
  }
]
```

#### PATCH `/exchangeRate/USDEUR`

Обновляет существующий обменный курс в базе данных. Валютная пара указывается последовательными кодами валют в
адрес запроса. Данные передаются в теле запроса в формате x-www-form-urlencoded. Единственное поле формы
это `rate`.
Пример ответа (вставленная запись):

```json
[
  {
    "id": 1,
    "baseCurrency": {
      "id": 0,
      "fullName": "United States dollar",
      "code": "USD",
      "sign": "$"
    },
    "targetCurrency": {
      "id": 2,
      "fullName": "Czech Koruna",
      "code": "CZK",
      "sign": "Kč"
    },
    "rate": 22.24
  }
]
```

## Currency exchange

#### GET `/exchange?from=BASE_CURRENCY_CODE&to=TARGET_CURRENCY_CODE&amount=$AMOUNT`

Рассчитывает конвертацию определенной суммы денег из одной валюты в другую. Валютная пара и сумма
указывается в адресе запроса. Пример ответа:

```json
{
  "baseCurrency": {
    "id": 0,
    "fullName": "United States dollar",
    "code": "USD",
    "sign": "$"
  },
  "targetCurrency": {
    "id": 2,
    "fullName": "Czech Koruna",
    "code": "CZK",
    "sign": "Kč"
  },
  "rate": 22.24,
  "amount": 100.00,
  "convertedAmount": 2224.00
}

