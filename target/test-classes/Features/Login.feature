Feature: Login Feature

  Scenario: User is able to login in the application
    Given user opened the browser
    And user navigated to the application url
    When user enter the username as "john" and password as "demo" and click the login button
    Then user is able to login in the application
