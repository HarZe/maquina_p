package instrucciones.cinta;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Valor;

public class Escribe implements Instruccion {

	public Escribe() {
		
	}
	
	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp) throws Exception {
		if (st.size() == 0)
			throw new Exception("ESCRIBE: pila vacia");
		
		cp.incr();
		System.out.println(st.pop().toString());
	}

}
