/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstraction;

/**
 *
 * @author genebuchite
 */
import javafx.util.StringConverter;

public class PersonConverter extends StringConverter<Person>
{
	// Method to convert a Person-Object to a String
	@Override
	public String toString(Person person)
	{
		return person == null? null : person.getLastName() + ", " + person.getFirstName();
	}

	// Method to convert a String to a Person-Object
	@Override
	public Person fromString(String string)
	{
		Person person = null;

		if (string == null)
		{
			return person;
		}

		int commaIndex = string.indexOf(",");

		if (commaIndex == -1)
		{
			person = new Person(string, null);
		}
		else
		{
			String firstName = string.substring(commaIndex + 2);
			String lastName = string.substring(0, commaIndex);
			person = new Person(firstName, lastName);
		}

		return person;
	}
}
