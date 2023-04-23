Feature: Shorten an url

  Scenario: Get full url from non-existent one
    When retrieving the original url back from "https://www.notarius.com/1"
    Then it should be fail with not found

  Scenario: Post url without protocol
    When retrieving the original url back from "www.notarius.com/1"
    Then it should be fail with bad request

