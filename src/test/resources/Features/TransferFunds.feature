Feature: Transfer Funds Feature
  @TransferFunds
  Scenario: User will able to transfer funds from his account
    Given User is logged in
    And user clicked on link "Transfer Funds"
    When User enters the amount as "1000" to be transferred
    And user selects sender account number and recipient account number
    And User clicks on "TRANSFER" button
    Then "Transfer complete!" message will be displayed.
