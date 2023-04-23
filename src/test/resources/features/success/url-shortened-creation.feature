Feature: Shorten an url

  Scenario: Create multiple valid shorter url then retrieve them for same domain
    When we create a shortened url for "https://www.notarius.com/career"
    Then it should be "https://www.notarius.com/1"
    When we create a shortened url for "https://www.notarius.com/employees"
    Then it should be "https://www.notarius.com/2"
    When retrieving the original url back from "https://www.notarius.com/1"
    Then it should be "https://www.notarius.com/career"
    When retrieving the original url back from "https://www.notarius.com/2"
    Then it should be "https://www.notarius.com/employees"

  Scenario: Create multiple valid shorter url then retrieve them for different domain.
    When we create a shortened url for "https://www.notarius1.com/career"
    Then it should be "https://www.notarius1.com/1"
    When we create a shortened url for "https://www.notarius2.com/employees"
    Then it should be "https://www.notarius2.com/1"
    When retrieving the original url back from "https://www.notarius1.com/1"
    Then it should be "https://www.notarius1.com/career"
    When retrieving the original url back from "https://www.notarius2.com/1"
    Then it should be "https://www.notarius2.com/employees"

  Scenario: Post the same url two times. The shorter url should still be the same.
    When we create a shortened url for "https://www.notarius.com/career"
    Then it should be "https://www.notarius.com/1"
    When we create a shortened url for "https://www.notarius.com/career"
    Then it should be "https://www.notarius.com/1"
    When retrieving the original url back from "https://www.notarius1.com/1"