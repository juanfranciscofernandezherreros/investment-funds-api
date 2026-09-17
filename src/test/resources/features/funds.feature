Feature: Investment funds HTTP API
  Scenario: Create and retrieve a valid fund
    Given the fund service accepts a valid fund
    When I send a POST request to "/api/v1/funds" with body '{"isin":"ES0000000001","name":"Fund","currency":"EUR","active":true}'
    Then the HTTP response status is 201
    And the response contains '"id":1'

  Scenario: Reject a duplicate fund creation
    Given the fund service rejects a duplicate ISIN
    When I send a POST request to "/api/v1/funds" with body '{"isin":"ES0000000001","name":"Fund","currency":"EUR","active":true}'
    Then the HTTP response status is 409

  Scenario: Reject an invalid fund creation
    When I send a POST request to "/api/v1/funds" with body '{"isin":""}'
    Then the HTTP response status is 400

  Scenario: Return not found for an absent fund
    Given the fund service cannot find fund 99
    When I send a GET request to "/api/v1/funds/99"
    Then the HTTP response status is 404

  Scenario: Retrieve an existing fund
    Given the fund service finds fund 1
    When I send a GET request to "/api/v1/funds/1"
    Then the HTTP response status is 200

  Scenario: Search funds with valid filters
    Given the fund service returns a page of funds
    When I send a GET request to "/api/v1/funds?currency=EUR&page=0&size=20"
    Then the HTTP response status is 200

  Scenario: Reject an invalid search page
    When I send a GET request to "/api/v1/funds?page=-1"
    Then the HTTP response status is 400

  Scenario: Update and delete a valid fund
    Given the fund service accepts an updated fund
    When I send a PATCH request to "/api/v1/funds/1" with body '{"name":"Changed"}'
    Then the HTTP response status is 200
    When I send a DELETE request to "/api/v1/funds/1"
    Then the HTTP response status is 204

  Scenario: Reject an invalid update and missing deletion
    When I send a PATCH request to "/api/v1/funds/1" with body '{"riskLevel":8}'
    Then the HTTP response status is 400
    Given the fund service cannot find fund 2
    When I send a DELETE request to "/api/v1/funds/2"
    Then the HTTP response status is 404

  Scenario: Reject an update with a duplicate ISIN
    Given the fund service rejects a duplicate update
    When I send a PATCH request to "/api/v1/funds/1" with body '{"isin":"ES0000000002"}'
    Then the HTTP response status is 409
