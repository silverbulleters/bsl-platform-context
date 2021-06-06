# Контекст платформы 1С:Предприятие

Предоставление контекста платформы 1С:Предприятие в виде Java библиотеки.

## Возможности

Доступны данные:

* Типы платформы
* События платформы

Поддерживаемые версии платформы: 8.2.19 - 8.3.19

## Примеры

### Инициализация

```java
List<PlatformEdition> editions = List.of(PlatformEdition.VERSION_8_2_19);
BSLPlatformContext platformContext = new BSLPlatformContext(editions);
```

### Получение набора событий

```java
// ...
Set<Event> events = platformContext.getEventsByPlatform(PlatformEdition.VERSION_8_2_19);
events.forEach(event -> {
  // имя события на русском
  System.out.println(event.getName().getNameRu());

  event.getTypes().forEach(platformTypeReference -> {
      // идентификаторы возможных типов события
      System.out.println(platformTypeReference.getValue());
  });
});
```

## Как вести разработку

Для разработки используются следующие таргеты:

* JDK 11
* IntelliJ IDEA 2021.1.1

## Лицензия

Используется лицензия [GPL-3.0 License](LICENSE)