### Usage
**Launch the tests:**


```
first - put your login name and password from the linkedin in the 'Account' class instead of "your email" and "your password"
(main/java/entities package)
AND then just do RghtClick on package or method with annotation @Test from Intelij IDE

OR use command in terminal
>gradle test --tests LoginTest - run separate test (replace LoginTest by another test class to run different test)

gradle test - run all tests


```
**Generate Allure report:**
```
>gradle allureReport
```

