Feature: Open New Account Feature

  @OpenNewAccount
  Scenario: User will able to Open a new account
    Given User is logged in
    And user clicked on link Open New Account
    When user select account as "SAVINGS" and account number
    And user clicks on Button "OPEN NEW ACCOUNT"
    Then "Account Opened!" message is displayed
    And a new account number is generated

