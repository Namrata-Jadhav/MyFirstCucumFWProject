Feature: Bill Pay feature

  @BillPayFeature
  Scenario: User will able to pay different types  of bills
    Given User is logged in
    And user clicked on the link "Bill Pay"
    When user enters Payee name as "john" and address as "hno.12, Shree ganesh society"
    And user enters city as "Pune" and state as "Maharashtra"
    And user enters Zip code as "411006" and phone no. as "1234567890"
    And user enters account number as "13344" and Verify account as "13344"
    And user enters the amount as "100" and enters existing account number
    And user clicks on the "SEND PAYMENT" Button
    Then  message willbe displayed as "Bill Payment Complete"