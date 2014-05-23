package instrucciones.cinta;

import instrucciones.Instruccion;

import java.util.Scanner;
import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Booleano;
import valores.Entero;
import valores.Valor;

public class Lee implements Instruccion {
	
	protected static Scanner sc = new Scanner(System.in);

	public Lee() {
		
	}
	
	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp) throws Exception {
		String valor = sc.nextLine();
		try {
			int v = Integer.parseInt(valor);
			st.push(new Entero(v));
			cp.incr();
			return;
		}
		catch (NumberFormatException e) {	 }
		
		try {
			boolean v = Boolean.parseBoolean(valor);
			st.push(new Booleano(v));
			cp.incr();
			return;
		}
		catch (NumberFormatException e) { }
		
		throw new Exception("LEE: valor no reconocido");
	}

}
