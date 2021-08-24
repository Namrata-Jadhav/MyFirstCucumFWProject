Feature: Temp

@t @datatable
Scenario: Example Scn
Given I want to do something
When I have a list of students to send as
| John     |
| Tanu     |
| Shraddha |
| Namrata  |

When I have a list of students and their marks to send as
| John     | 60 |
| Tanu     | 65 |
| Shraddha | 70 |
| Namrata  | 72 |
Then Something should happen

@Example
Scenario Outline: I want to search for products
Given i am on the search page
When I search for the product: "<Product>"
Then Search result should be displayed to "<Product>"
Examples:
| Product   |
| computer  |
| Mobile    |
| Laptops   |
| Earphones |