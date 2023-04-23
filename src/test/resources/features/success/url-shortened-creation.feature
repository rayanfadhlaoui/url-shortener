Feature: Shorten an url

  Scenario: Create multiple valid shorter url then retrieve them for same domain
    When we create a shortened url for "https://www.notaris.com/career"
    Then it should be "https://www.notaris.com/1"
    When we create a shortened url for "https://www.notaris.com/employees"
    Then it should be "https://www.notaris.com/2"
    When retrieving the original url back from "https://www.notaris.com/1"
    Then it should be "https://www.notaris.com/career"
    When retrieving the original url back from "https://www.notaris.com/2"
    Then it should be "https://www.notaris.com/employees"

  Scenario: Create multiple valid shorter url then retrieve them for different domain
    When we create a shortened url for "https://www.notaris1.com/career"
    Then it should be "https://www.notaris1.com/1"
    When we create a shortened url for "https://www.notaris2.com/employees"
    Then it should be "https://www.notaris2.com/1"
    When retrieving the original url back from "https://www.notaris1.com/1"
    Then it should be "https://www.notaris1.com/career"
    When retrieving the original url back from "https://www.notaris2.com/1"
    Then it should be "https://www.notaris2.com/employees"