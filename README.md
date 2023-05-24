# Демопроект автоматизации тестирования API на [reqres.in](https://reqres.in/)
<img title="REQRES" src="images/reqres.png">

## :page_facing_up: Cодержание
- [Технологии и иструменты](#hammer_and_wrench-технологии-и-иструменты)
- [Реализованные проверки](#white_check_mark-реализованные-проверки)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Allure отчет](#-allure-отчет)

## :hammer_and_wrench: Технологии и иструменты
<a href="https://www.java.com"><img src="https://github.com/gloomyana/gloomyana/blob/main/icons/java.svg" title="Java" alt="Java" width="40" height="40"/></a>
<a href="https://www.jetbrains.com/idea/"><img src="https://github.com/gloomyana/gloomyana/blob/main/icons/intellij_idea.svg" title="IntelliJ IDEA" alt="IntelliJ IDEA" width="40" height="40"/></a>
<a href="https://junit.org/junit5"><img src="https://github.com/gloomyana/gloomyana/blob/main/icons/junit5.svg" title="JUnit5" alt="JUnit5" width="40" height="40"/></a>
<a href="https://gradle.org"><img src="https://github.com/gloomyana/gloomyana/blob/main/icons/gradle.svg" title="Gradle" alt="Gradle" width="40" height="40"/></a>
<a href="https://www.jenkins.io"><img src="https://github.com/gloomyana/gloomyana/blob/main/icons/jenkins.svg" title="Jenkins" alt="Jenkins" width="40" height="40"/></a>
<a href="https://qameta.io/allure-report"><img src="https://github.com/gloomyana/gloomyana/blob/main/icons/allure_report.svg" title="Allure Report" alt="Allure Report" width="40" height="40"/></a>
<a href="https://rest-assured.io"><img src="https://github.com/gloomyana/gloomyana/blob/main/icons/rest_assured.svg" title="REST Assured" alt="REST Assured" width="40" height="40"/></a>

## :white_check_mark: Реализованные проверки
- Успешное создание пользователя
- Успешное изменение данных пользователя
- Неуспешная авторизация
- Успешный запрос на получение списка (с использованием JSON-схемы)
- Успешный запрос на получение данных
- Запрос несуществующих данных

## <img width="3%" title="Jenkins" src="https://github.com/gloomyana/gloomyana/blob/main/icons/jenkins.svg"> Сборка в Jenkins
[Сборка в Jenkins](https://jenkins.autotests.cloud/job/reqres_in_API/) 

<img title="Jenkins project page" src="images/jenkins_job.png">

## <img width="3%" title="Allure Report" src="https://github.com/gloomyana/gloomyana/blob/main/icons/allure_report.svg"> Allure отчет
[Allure отчет](https://jenkins.autotests.cloud/job/reqres_in_API/7/allure/) 

Главная страница отчета Allure содержит следующие блоки:
- **ALLURE REPORT** - отображает дату и время теста, общее количество запущенных тестов, а также диаграмму с процентом и количеством успешных, упавших и сломавшихся в процессе выполнения тестов
- **TREND** - отображает тенденцию выполнения тестов для всех запусков
- **SUITES** - отображает распределение тестов по сьютам

<img title="Allure Report" src="images/allure_report_main.png"> 

Список тестов с шагами:

<img title="Allure Report test 1" src="images/allure_report_test1.png">

<img title="Allure Report test 2" src="images/allure_report_test2.png">
