package instrucciones.pila;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Valor;

public class Apila implements Instruccion {

	protected Valor v;
	
	public Apila(Valor v) {
		this.v = v;
	}
	
	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp) {
		st.push(v);
		cp.incr();
	}

}
