![Uploading photo_2024-06-26_15-28-20.jpg…](C:\Users\User\Desktop\photo_2024-06-26_15-28-20.jpg)

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

1. Скачайте код из репозитория.
2. Запустите npm install.
3. Запустите npm start.

## Использование

## API features

### Currencies

#### GET `/currencies`

Returns list of all currencies. Example of response:

```json
[
  {
    "id": 0,
    "name": "United States dollar",
    "code": "USD",
    "sign": "$"
  },
  {
    "id": 1,
    "name": "Euro",
    "code": "EUR",
    "sign": "€"
  },
  "..."
]
```

#### GET `/currency/USD`

Returns particular currency. The currency code is specified in the query address Example of response:

```json
[
  {
    "id": 0,
    "name": "United States dollar",
    "code": "USD",
    "sign": "$"
  }
]
```

#### POST `/currencies`

Adding a new currency to the database. Data is passed in the body of request in the x-www-form-urlencoded. The form
fields are `name`, `code`, `symbol`. Example of response (inserted record):

```json
[
  {
    "id": 2,
    "name": "Czech Koruna",
    "code": "CZK",
    "sign": "Kč"
  }
]
```

### Exchange rates

#### GET `/exchangeRates`

Returns list of all exchange rates. Example of response:

```json
[
  {
    "id": 0,
    "baseCurrency": {
      "id": 0,
      "name": "United States dollar",
      "code": "USD",
      "sign": "$"
    },
    "targetCurrency": {
      "id": 1,
      "name": "Euro",
      "code": "EUR",
      "sign": "€"
    },
    "rate": 0.93
  },
  {
    "id": 1,
    "baseCurrency": {
      "id": 0,
      "name": "United States dollar",
      "code": "USD",
      "sign": "$"
    },
    "targetCurrency": {
      "id": 2,
      "name": "Czech Koruna",
      "code": "CZK",
      "sign": "Kč"
    },
    "rate": 22.16
  },
  "..."
]
```

#### POST `/exchangeRates`

Adding a new exchange rate to the database. Data is passed in the body of request in the x-www-form-urlencoded. The form
fields are `baseCurrencyCode`, `targetCurrencyCode`, `rate`. Example of response (inserted record):

```json
[
  {
    "id": 2,
    "baseCurrency": {
      "id": 1,
      "name": "Euro",
      "code": "EUR",
      "sign": "€"
    },
    "targetCurrency": {
      "id": 2,
      "name": "Czech Koruna",
      "code": "CZK",
      "sign": "Kč"
    },
    "rate": 23.75
  }
]
```

#### GET `/exchangeRate/USDEUR`

Returns a particular exchange rate. The currency pair is specified by consecutive currency codes in the query address.
Example of response:

```json
[
  {
    "id": 0,
    "baseCurrency": {
      "id": 0,
      "name": "United States dollar",
      "code": "USD",
      "sign": "$"
    },
    "targetCurrency": {
      "id": 1,
      "name": "Euro",
      "code": "EUR",
      "sign": "€"
    },
    "rate": 0.93
  }
]
```

#### PATCH `/exchangeRate/USDEUR`

Updates the existing exchange rate in the database. The currency pair is specified by consecutive currency codes in the
query address. The data is passed in the body of the request in the x-www-form-urlencoded. The only form field
is `rate`.
Example of response (inserted record):

```json
[
  {
    "id": 1,
    "baseCurrency": {
      "id": 0,
      "name": "United States dollar",
      "code": "USD",
      "sign": "$"
    },
    "targetCurrency": {
      "id": 2,
      "name": "Czech Koruna",
      "code": "CZK",
      "sign": "Kč"
    },
    "rate": 22.24
  }
]
```

## Currency exchange

#### GET `/exchange?from=BASE_CURRENCY_CODE&to=TARGET_CURRENCY_CODE&amount=$AMOUNT`

Calculate the conversion of a particular amount of money from one currency to another. The currency pair and amount is
specified in the query address. Example of response:

```json
{
  "baseCurrency": {
    "id": 0,
    "name": "United States dollar",
    "code": "USD",
    "sign": "$"
  },
  "targetCurrency": {
    "id": 2,
    "name": "Czech Koruna",
    "code": "CZK",
    "sign": "Kč"
  },
  "rate": 22.24,
  "amount": 100.00,
  "convertedAmount": 2224.00
}
```

* Откройте веб-браузер.
* Перейдите на адрес http://localhost:3000.

## Пример