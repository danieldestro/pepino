Feature: Cucumber Tests for Pet Services

Background:
	Given there are no pets

Scenario: Check Pet Count Zero
	When I do nothing
	Then there are 0 pets

Scenario: Check Pet Count
	When I create a DOG with data
		| name 		| color | weight |
		| Chewbacca	| WHITE | 8		 |
	Then there are 1 pets

Scenario: Complex Test Scenario
	When I create a DOG with data
		| name 		| color | weight |
		| Chewbacca	| WHITE | 10	 |
	When I create a CAT with data
		| name 		| color | weight |
		| Gargamel	| BLACK | 5		 |
	When I create a LIZARD with data
		| name 		| color | weight |
		| Geico		| GREEN | 2		 |
	Then there are 3 pets
	 And I can count 3 pets