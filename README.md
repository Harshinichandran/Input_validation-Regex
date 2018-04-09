# Input_validation-Regex
Program that validates its input using regular expressions.

Input Validation:
Instructions to execute the code:
Compile the java code:
       	  Javac Phonebook.java
          
Run the java code :
	Java Phonebook Add “args1” “args2”    (args1 Name ; args2 Phone Number)
	Java Phonebook List
	Java Phonebook Delete “args1”                 (args1  Name or Phone Number)

Description of Code:
The code takes a choice of input from user to 
o	Add- any new contact with name and phone number to the file
o	List – display all existing contacts in the file
o	Delete – remove any existing contact by name or phone number (If there are duplicates, all duplicates will be removed as well)
Depending on the choice by the user, the respective functions for Add(), List() and Delete() are invoked.

The Add() function gets user inputs for Name and Number. Validation using regular expression is performed for both the inputs by ValidName() and ValidNumber() functions . The functions compare the inputs from the command line arguments to the respective regular expressions before adding the data in to the text file (Phonebook.txt).

The List() Function displays all the existing contact details present in the text File. This function does not take any arguments and it provides the list of all existing names and their corresponding Phone numbers present in the Text File (Phonebook.txt).

The Delete() Function gets input from the user to delete a contact detail by either name or number. This function checks if the user has provided the input, name or number to delete a required contact and invokes the functions DeleteName() and DeleteNumber() accordingly. When the user tries to delete a non-existing name/ number from the directory, the function displays an error message and return an exit code of 1.



