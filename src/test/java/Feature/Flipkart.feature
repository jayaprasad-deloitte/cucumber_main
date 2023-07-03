Feature: Verify total price for flights is equal to the price of sum of both flights

  Scenario Outline: Flight-booking in flipkart
    Given : I Launch Flipkart website
    When : I Navigate to Travel section
    And : Fill all required details and From city "<SheetName>" and To city <RowNumber>
    Then : Verify Total price with sum on individual flight price
  Examples:
  |SheetName|RowNumber|
  |Sheet1|0        |





