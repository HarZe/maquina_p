package instrucciones.pila;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Valor;
import instrucciones.Instruccion;

public class Dup implements Instruccion {

	public Dup() {

	}

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.isEmpty())
			throw new Exception("DUP: pila vacia");
				
		st.push(st.peek());
		cp.incr();
	}

}
