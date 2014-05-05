package instrucciones.aritmeticas;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;

public class Suma implements Instruccion {

	public Suma() {
		
	}
	
	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() < 2)
			throw new Exception("SUMA: faltan operandos");
		
		Valor op1 = st.pop();
		Valor op2 = st.pop();
		if (op1 instanceof Entero && op2 instanceof Entero) {
			st.push(new Entero(((int) op1.getValor()) + ((int) op2.getValor())));
			cp.incr();
		}
		else
			throw new Exception("SUMA: operandos no enteros");
	}

}
