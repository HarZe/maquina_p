package parser;

import instrucciones.Instruccion;
import instrucciones.aritmeticas.Division;
import instrucciones.aritmeticas.Modulo;
import instrucciones.aritmeticas.Multiplicacion;
import instrucciones.aritmeticas.Negacion;
import instrucciones.aritmeticas.Resta;
import instrucciones.aritmeticas.Suma;
import instrucciones.cinta.Escribe;
import instrucciones.cinta.Lee;
import instrucciones.logicas.Distinto;
import instrucciones.logicas.Igual;
import instrucciones.logicas.Mayor;
import instrucciones.logicas.MayorOIgual;
import instrucciones.logicas.Menor;
import instrucciones.logicas.MenorOIgual;
import instrucciones.logicas.No;
import instrucciones.logicas.O;
import instrucciones.logicas.Y;
import instrucciones.memoria.Apila_Dir;
import instrucciones.memoria.Apila_Ind;
import instrucciones.memoria.Clonar;
import instrucciones.memoria.Desapila_Dir;
import instrucciones.memoria.Desapila_Ind;
import instrucciones.memoria.Libera;
import instrucciones.memoria.Reserva;
import instrucciones.pila.Apila;
import instrucciones.pila.Dup;
import instrucciones.salto.IrA;
import instrucciones.salto.IrF;
import instrucciones.salto.IrInd;
import instrucciones.salto.IrV;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import valores.Booleano;
import valores.Entero;
import valores.Valor;

public class Parser {

	public static ArrayList<Instruccion> instDeArchivo(String archivo) throws Exception {
		ArrayList<Instruccion> inst = new ArrayList<Instruccion>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File(archivo));
			
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				
				linea = linea.toUpperCase();
				
				if (linea.startsWith("APILA(")) {
					String var = linea.substring("APILA(".length());
					var = var.substring(0, var.length() - 1);
					inst.add(new Apila(valorDeString(var)));
				}
				
				else if (linea.startsWith("APILA_DIR(")) {
					String var = linea.substring("APILA_DIR(".length());
					var = var.substring(0, var.length() - 1);
					Valor v = valorDeString(var);
					if (v instanceof Entero)
						inst.add(new Apila_Dir((Entero) v));
					else
						throw new Exception("Parser: direccion incorrecta");
				}
				
				else if (linea.startsWith("DESAPILA_DIR(")) {
					String var = linea.substring("DESAPILA_DIR(".length());
					var = var.substring(0, var.length() - 1);
					Valor v = valorDeString(var);
					if (v instanceof Entero)
						inst.add(new Desapila_Dir((Entero) v));
					else
						throw new Exception("Parser: direccion incorrecta");
				}
				
				else if (linea.startsWith("IR_A(")) {
					String var = linea.substring("IR_A(".length());
					var = var.substring(0, var.length() - 1);
					Valor v = valorDeString(var);
					if (v instanceof Entero)
						inst.add(new IrA((Entero) v));
					else
						throw new Exception("Parser: salto incorrecto");
				}
				
				else if (linea.startsWith("IR_V(")) {
					String var = linea.substring("IR_V(".length());
					var = var.substring(0, var.length() - 1);
					Valor v = valorDeString(var);
					if (v instanceof Entero)
						inst.add(new IrV((Entero) v));
					else
						throw new Exception("Parser: salto incorrecto");
				}
				
				else if (linea.startsWith("IR_F(")) {
					String var = linea.substring("IR_F(".length());
					var = var.substring(0, var.length() - 1);
					Valor v = valorDeString(var);
					if (v instanceof Entero)
						inst.add(new IrF((Entero) v));
					else
						throw new Exception("Parser: salto incorrecto");
				}
				
				else if (linea.startsWith("RESERVA(")) {
					String var = linea.substring("RESERVA(".length());
					var = var.substring(0, var.length() - 1);
					Valor v = valorDeString(var);
					if (v instanceof Entero)
						inst.add(new Reserva((Entero) v));
					else
						throw new Exception("Parser: salto incorrecto");
				}
				
				else if (linea.startsWith("LIBERA(")) {
					String var = linea.substring("LIBERA(".length());
					var = var.substring(0, var.length() - 1);
					Valor v = valorDeString(var);
					if (v instanceof Entero)
						inst.add(new Libera((Entero) v));
					else
						throw new Exception("Parser: salto incorrecto");
				}
				
				else if (linea.startsWith("CLONAR(")) {
					String var = linea.substring("CLONAR(".length());
					var = var.substring(0, var.length() - 1);
					Valor v = valorDeString(var);
					if (v instanceof Entero)
						inst.add(new Clonar((Entero) v));
					else
						throw new Exception("Parser: salto incorrecto");
				}
				
				else if (linea.equalsIgnoreCase("APILA_IND"))
					inst.add(new Apila_Ind());
				
				else if (linea.equalsIgnoreCase("DESAPILA_IND"))
					inst.add(new Desapila_Ind());
				
				else if (linea.equalsIgnoreCase("LEE"))
					inst.add(new Lee());
				
				else if (linea.equalsIgnoreCase("ESCRIBE"))
					inst.add(new Escribe());
				
				else if (linea.equalsIgnoreCase("SUMA"))
					inst.add(new Suma());
				
				else if (linea.equalsIgnoreCase("RESTA"))
					inst.add(new Resta());
				
				else if (linea.equalsIgnoreCase("MUL"))
					inst.add(new Multiplicacion());
				
				else if (linea.equalsIgnoreCase("DIV"))
					inst.add(new Division());
				
				else if (linea.equalsIgnoreCase("MOD"))
					inst.add(new Modulo());
				
				else if (linea.equalsIgnoreCase("NEG"))
					inst.add(new Negacion());
				
				else if (linea.equalsIgnoreCase("Y"))
					inst.add(new Y());
				
				else if (linea.equalsIgnoreCase("O"))
					inst.add(new O());
				
				else if (linea.equalsIgnoreCase("NO"))
					inst.add(new No());
				
				else if (linea.equalsIgnoreCase("IGUAL"))
					inst.add(new Igual());
				
				else if (linea.equalsIgnoreCase("DISTINTO"))
					inst.add(new Distinto());
				
				else if (linea.equalsIgnoreCase("MENOR"))
					inst.add(new Menor());
				
				else if (linea.equalsIgnoreCase("MAYOR"))
					inst.add(new Mayor());
				
				else if (linea.equalsIgnoreCase("MENOROIGUAL"))
					inst.add(new MenorOIgual());
				
				else if (linea.equalsIgnoreCase("MAYOROIGUAL"))
					inst.add(new MayorOIgual());
				
				else if (linea.equalsIgnoreCase("DUP"))
					inst.add(new Dup());
				
				else if (linea.equalsIgnoreCase("IR_IND"))
					inst.add(new IrInd());
				
				/*else if (!linea.startsWith("//") || linea.length() != 0)
					System.err.println("Cannot parse: " + linea);*/
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (sc != null)
			sc.close();
		
		return inst;
	}
	
	protected static Valor valorDeString(String s) throws Exception {
		Valor v = null;
		
		try {
			int n = Integer.parseInt(s);
			v = new Entero(n);
			return v;
		}
		catch (NumberFormatException e) {	 }
		
		try {
			boolean b = Boolean.parseBoolean(s);
			v = new Booleano(b);
			return v;
		}
		catch (NumberFormatException e) { }
		
		throw new Exception("Parser: valor no reconocido");
	}
}
