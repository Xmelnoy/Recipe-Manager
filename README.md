# Recipe Manager (Spring Boot Web)

Современное web-приложение для управления рецептами, недельным меню и списком покупок.

## Технологии

- Java 21
- Maven
- Spring Boot
- Spring MVC
- Thymeleaf
- HTML + CSS

## Структура проекта

```text
src/main/java/com/recipemanager/
   RecipeManagerApplication.java
   controller/
      HomePageController.java
      RecipePageController.java
      MealPlanPageController.java
      ShoppingListPageController.java
      RecipeController.java
   model/
      Recipe.java
      Ingredient.java
      MealPlan.java
      MealType.java
      ShoppingListItem.java
   service/
      RecipeService.java
      MealPlanService.java
      ShoppingListService.java
   store/
      InMemoryRecipeStore.java
      DemoDataFactory.java

src/main/resources/
   application.properties
   templates/
      dashboard.html
      meal-plan.html
      shopping-list.html
      recipes/
         list.html
         form.html
         details.html
   static/css/
      app.css
```

## Функциональность

- Просмотр списка рецептов
- Добавление, редактирование, удаление рецептов
- Детальная карточка рецепта
- Поиск рецептов по ингредиентам и категории
- Масштабирование по порциям
- Планирование меню на неделю
- Генерация списка покупок по плану

## Запуск

```bash
mvn spring-boot:run
```

После запуска приложение доступно по адресу:

- http://localhost:8080

## Примечание

Хранение данных на текущем этапе in-memory (без БД), с демо-данными в `DemoDataFactory`.
