Feature: Cucumber Tests for Pet Services

Background:
	Given there are no pets

Scenario: Check Pet Count Zero
	When I do nothing
	Then there are 0 pets

Scenario: Check Pet Count
	Given I create a DOG with data
		| name 		| color | weight |
		| Chewbacca	| WHITE | 8		 |
	Then there are 1 pets

Scenario: Check Pet Count of Different Species
	Given I create a DOG with data
		| name 		| color | weight |
		| Chewbacca	| WHITE | 10	 |
	 And I create a CAT with data
		| name 		| color | weight |
		| Gargamel	| BLACK | 5		 |
	 And I create a LIZARD with data
		| name 		| color | weight |
		| Geico		| GREEN | 2		 |
	Then there are 3 pets
	 And I can count 3 pets

Scenario: Check Pet Count of Different Species
	Given I create a DOG with data
		| name 		| color | weight |
		| Chewbacca	| WHITE | 10	 |
	 And I create a DOG with data
		| name 		| color | weight |
		| Xuxa		| BLACK | 12	 |
	 And I create a DOG with data
		| name 		| color | weight |
		| Caramelo	| BROWN | 9		 |
	 And I create a CAT with data
		| name 		| color | weight |
		| Gargamel	| BLACK | 5		 |
	 And I create a CAT with data
		| name 		| color | weight |
		| Whiskas	| WHITE | 3		 |
	 And I create a LIZARD with data
		| name 		| color | weight |
		| Geico		| GREEN | 2		 |
	Then I can count 6 pets
	 And I can count 3 DOG
	 And I can count 2 CAT
	 And I can count 1 LIZARD