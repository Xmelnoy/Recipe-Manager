# Recipe-Manager

Стартовый каркас проекта для менеджера рецептов на **Java** с использованием:
- **Spring Boot** (веб-часть и REST API),
- **Thymeleaf** (шаблоны для веб-страниц),
- **JavaFX** (desktop UI),
- **Maven** (сборка и управление зависимостями).

## Технологический стек

- Java 21
- Maven
- Spring Boot 3
- JavaFX 21
- JUnit 5

## Поверхностная структура проекта

```text
Recipe-Manager/
├─ pom.xml
├─ README.md
├─ data/
│  └─ .gitkeep
├─ docs/
│  └─ KANBAN.md
└─ src/
   ├─ main/
   │  ├─ java/com/recipemanager/
   │  │  ├─ RecipeManagerApplication.java
   │  │  ├─ JavaFxLauncher.java
   │  │  ├─ controller/RecipeController.java
   │  │  ├─ model/Recipe.java
   │  │  └─ service/RecipeService.java
   │  └─ resources/
   │     ├─ application.properties
   │     └─ templates/index.html
   └─ test/java/com/recipemanager/
      └─ RecipeManagerApplicationTests.java
```

## Быстрый старт

### 1) Проверка сборки и тестов

```bash
mvn test
```

### 2) Запуск веб-приложения (Spring Boot)

```bash
mvn spring-boot:run
```

После запуска:
- веб-приложение доступно на `http://localhost:8080`
- REST endpoint доступен на `http://localhost:8080/api/recipes`

### 3) Запуск JavaFX приложения

```bash
mvn javafx:run
```

## Kanban

Актуальная Kanban-доска: [`docs/KANBAN.md`](docs/KANBAN.md).
