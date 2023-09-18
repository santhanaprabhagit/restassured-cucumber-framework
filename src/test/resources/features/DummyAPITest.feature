Feature: Test Sample Api

  @Sanity
  Scenario: Test list of products endpoint
    Given Send Request to get list of Products
    Then The response code receives is OK
    And The returned response is not empty
	#Can add more steps

  @Sanity
  Scenario: Add new product
    Given Send Request to add a new product
      | title          | description                     | price | discountPercentage | rating | stock | brand  | category        | thumbnail                                              | images                                         |
      | test product-1 | To test add new product request | 20    | 2                  | 4.9    | 5     | Golden | home-decoration | https://i.dummyjson.com/data/products/30/thumbnail.jpg | https://i.dummyjson.com/data/products/30/1.jpg |
    Then The response code receives is OK
    #And The returned response has created productId
