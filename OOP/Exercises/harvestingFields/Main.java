package harvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {

		Class<?> clazz = RichSoilLand.class;

		List<Field> fields = Arrays.asList ( clazz.getDeclaredFields () );

		BufferedReader reader = new BufferedReader ( new InputStreamReader ( System.in ) );

		String input = reader.readLine ();

		StringBuilder output = new StringBuilder ();

		while (!input.equals ( "HARVEST" )){
			String modifier = input;

			fields.stream ()
					.filter ( f -> Modifier.toString ( f.getModifiers () ).equals ( modifier )
					|| modifier.equals ( "all" ))
					.forEach ( f-> output.append ( String.format ("%s %s %s%n",
							Modifier.toString ( f.getModifiers () ),
							f.getType ().getSimpleName (),
							f.getName ()) ));
			input = reader.readLine ();
		}

		System.out.print (output.toString ());
	}
}
