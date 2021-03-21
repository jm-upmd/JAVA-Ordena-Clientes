package clientes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.CollationKey;
import java.text.Collator;
import java.text.CompactNumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		final String F_ENTRADA = "H:\\Cursos Actuales\\Curso Java\\Practica Clientes\\Clientes_CR.txt";
		final String F_SALIDA = "H:\\Cursos Actuales\\Curso Java\\Proyectos Eclipse\\Practica Clientes\\ClientesSalida.txt";

		// Para guardar todos los objetos Cliente
		ArrayList<Cliente> listaClientes = new ArrayList<>();

		try {
			leeClientes(listaClientes, F_ENTRADA);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			System.exit(0);
		}

		// Muestra clientes de España
		System.out.println("*** CLIENTES DE ESPAÑA ***");
		muestraPorNacionalidad(listaClientes);

		System.out.println("\n\n************************\n");

		// Muestra clientes de Alemania
		System.out.println("*** CLIENTES DE ALEMANIA ***");

		muestraPorNacionalidad(listaClientes, "Alemania");

		System.out.println("\nGrabando clientes en fichero:" + F_SALIDA);

		grabaClientes(listaClientes, F_SALIDA);

		ordenaLista(listaClientes, 'n');

		imprimeClientes(listaClientes, "Ordenada por Nombre");

	} // fin main()

	static void leeClientes(ArrayList<Cliente> lista, String fichero) throws FileNotFoundException {

		// Descriptor al fichero de recurso.
		InputStream in = Main.class.getResourceAsStream("/Clientes_CR.txt");

		// Scanner para leer del fichero
		Scanner scnr = null;

		if (in != null) {
			scnr = new Scanner(in); 
		} else {
			throw new FileNotFoundException("Fichero de recurso no encontrado");
		}

		// Leyendo líneas del fichero con Scanner

		while (scnr.hasNextLine()) { // Mientras queden líneas por leer
			String linea = scnr.nextLine(); // Lee linea

			// Ejecuta método creaObjetoCliente pasando la linea leída como parámetro
			// creaObjetoCliente devuelve el objeto Cliente creado a partir de linea
			// El objeto devuelto es añadido al ArrayList listaClientes

			lista.add(creaObjetoCliente(linea));
		}
		scnr.close();

	}

	static void grabaClientes(ArrayList<Cliente> clientes, String fichero) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(fichero);

			/*
			 * Para añadir al final de un fichero ya existente FileWriter fichero = new
			 * FileWriter("c:/fichero.txt",true);
			 */
			pw = new PrintWriter(fw);

			for (Cliente c : clientes) {
				pw.println(c.objetoAString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally { // finally siempre se ejecuta tras el try haya o no excepción
			try {
				// En el finally para nos asegurarnos que se cierra el fichero.
				if (fw != null)
					fw.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

	}

	private static Cliente creaObjetoCliente(String linea) {

		// Divide la linea en trozos utilizando ";" como caracter separador
		// Cada trozo lo guarda en una posición del array tokens
		String[] tokens = linea.split(";");

		// Comvierto los datos de los campos antiguedad y facturación a sus respectivos
		// tipos númericos.

		int antiguedad = 0;
		float facturacion = 0;
		try {
			antiguedad = Integer.parseInt(tokens[3]);
		} catch (NumberFormatException e) {
			System.out.println("No se ha podido covertir a número el valor del campo antiguedad: " + tokens[3]);
			System.exit(-1);
		}

		try {
			facturacion = Float.parseFloat(tokens[4].replace(',', '.')); // En fichero origen el simbolo de dicimal me
																			// viene como coma.
																			// y tiene que ser punto porque sino
																			// fallaría.
		} catch (NumberFormatException e) {
			System.out.println("No se ha podido covertir a número el valor del campo Facturación: " + tokens[4]);
			System.exit(-1);
		}

		// Fijáos en el último pármetro del constructor.
		// Si una línea leída no tiene el último campo (Fax), como por ejemplo el tercer
		// registro del fichero,
		// al hacer split() de la línea genera un array de tamaño 12 en vez de 13.
		// En ese caso le asignamos a Fax la cadena vacía.

		Cliente cliente = new Cliente(tokens[0], tokens[1], tokens[2], antiguedad, facturacion, tokens[5], tokens[6],
				tokens[7], tokens[8], tokens[9], tokens[10], tokens[11], tokens.length == 13 ? tokens[12] : "");

		return cliente;
	}

	// Muestra información de clientes de España
	private static void muestraPorNacionalidad(ArrayList<Cliente> lista) {
		int totClientes = 0;
		float totFacturacion = 0;

		for (Cliente c : lista) {
			if (c.esEspañol()) {
				System.out.println("Registro " + ++totClientes);
				System.out.print(c.toString());
				totFacturacion += c.getFacturacion();

			}
		}

		System.out.printf("%35s: %20d\n", "Total clientes", totClientes);

		// Formato la coma indica que utilize caracteres de agrupación locales
		// 20.2f representa el numero flotante en campo de 20 posiciones y con 2
		// decimales.
		System.out.printf("%35s: %,20.2f\n", "Total facturación (€)", totFacturacion);
	}

	// Sobrecarga de muestraPorNacionalidad con parametro adicional para indicar el
	// país
	private static void muestraPorNacionalidad(ArrayList<Cliente> lista, String nac) {
		int totClientes = 0;
		float totFacturacion = 0;
		// final char TAB = '\t';

		for (Cliente c : lista) {

			if (c.getPais().toUpperCase().compareTo(nac.toUpperCase()) == 0) {
				System.out.println("Registro " + ++totClientes + " (" + c.getPais() + ")");
				System.out.print(c.toString());
				totFacturacion += c.getFacturacion();
			}
		}

		System.out.printf("%35s: %20d\n", "Total clientes", totClientes);

		// Formato la coma indica que utilize caracteres de agrupación locales
		// 20.2f representa el numero flotante en campo de 20 posiciones y con 2
		// decimales.
		System.out.printf("%35s: %,20.2f\n", "Total facturación (€)", totFacturacion);
	}

	private static void ordenaLista(List<Cliente> lista, char tipoOrden) {

		// Para que ordene bien los textos según alfabeto de la configuración local.
		Collator collator = Collator.getInstance();

		// También se puede indicar con un objeto Locale
		// Collator collator = Collator.getInstance(new Locale("es"));

		switch (tipoOrden) {

		case 'n': // Nombre
			Collections.sort(lista, new Comparator<Cliente>() {
				@Override
				public int compare(Cliente o1, Cliente o2) {
					return collator.compare(o1.getNombreContacto(), o2.getNombreContacto());

				}
			});

			break;

		case 'p': // Nombre

			Collections.sort(lista, new Comparator<Cliente>() {

				@Override
				public int compare(Cliente o1, Cliente o2) {
					return collator.compare(o1.getPais(), o2.getPais());

				}
			});

			break;

		case 'a': // Antiguedad
			Collections.sort(lista, new Comparator<Cliente>() {
				@Override
				public int compare(Cliente o1, Cliente o2) {
					return o1.getAntiguedad() - o2.getAntiguedad();

				}
			});

			break;

		case 'x': // País y cargoContacto en orden creciente
			Collections.sort(lista, new Comparator<Cliente>() {
				int orden;

				@Override
				public int compare(Cliente o1, Cliente o2) {
					if ((orden = collator.compare(o1.getPais(), o2.getPais())) != 0) {
						return orden;
					} else {
						return collator.compare(o1.getCargoContacto(), o2.getCargoContacto());
					}

				}
			});

			break;

		default:
			throw new IllegalArgumentException("Opción desconocida: " + String.valueOf(tipoOrden));
		}// switch
	} // ordenaLista

	static private void imprimeClientes(List<Cliente> l, String orden) {
		String cadFormato = "%-5s %-25s %6s %11s %-30s %-8s\n";
		System.out.println("*** " + orden + " ***");
		System.out.printf(cadFormato, "Id", "Nombre", "Ant", "Facturación", "Cargo", "País");
		for (Cliente c : l) {
			System.out.printf(cadFormato, c.getIdCliente(), c.getNombreContacto(), c.getAntiguedad(),
					c.getFacturacion(), c.getCargoContacto(), c.getPais());
		}
	}

}
