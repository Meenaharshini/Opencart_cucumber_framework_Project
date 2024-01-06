Feature: Login Data Driven Test with Excel file

	@smoke
		Scenario Outline: Login DataDrivenTest with Excel data
			Given user navigate to Login Page
    	Then user navigates to MyAccount Page by passing email and password with excel row "<rowIndex>"
    	
    	Examples:
    		| rowIndex | 
    		|	1 | 
    		| 2 |
    		| 3 | 