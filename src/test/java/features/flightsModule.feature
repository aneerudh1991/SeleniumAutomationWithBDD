Feature: Flight booking operations

Scenario Outline: Flight booking operations of getting all fares, fastest and lowest fare
Given Initialize the browser with chrome
And Navigate to "https://paytm.com/flights" Site
When User enters From <From> and To <To> cities and perform search
And close browsers

Examples:
|From			|To	|
|Hyderabad	|Mumbai		|






