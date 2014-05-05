package instrucciones.logicas;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Booleano;
import valores.Entero;
import valores.Valor;

public class Menor implements Instruccion {

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() < 2)
			throw new Exception("MENOR: faltan operandos");
		
		Valor op1 = st.pop();
		Valor op2 = st.pop();
		if (op1 instanceof Entero && op2 instanceof Entero) {
			st.push(new Booleano(((int) op2.getValor()) < ((int) op1.getValor())));
			cp.incr();
		}
		else
			throw new Exception("MENOR: operandos no enteros");
	}

}
