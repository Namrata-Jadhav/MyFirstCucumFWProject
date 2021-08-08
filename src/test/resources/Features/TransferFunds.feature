Feature: Transfer Funds Feature
  @TransferFunds
  Scenario: User will able to transfer funds from his account
    Given User is logged in
    And user clicked on link "Transfer Funds"
    When User enters the "amount" to be transferred
    And user selects first account number "from" which to transfer and another account number "to" which to be transferred
   Then User clicks on "TRANSFER" button "Transfer complete!" message will be displayed
