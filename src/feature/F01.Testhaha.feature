Feature: F01Testhaha
  Description: This feature will test F01 functionality

  Background:
    Given User opens jobsDB page
     
    
  @F01 @batch1 @Testhaha-01
  Scenario: Testhaha-01
    When User inputs Job in Searching box
    And User clicks "Search" button
    Then User will see the search result
    Then User selects a job at HK Jockey Club
    Then User is blocked by vpn problem

