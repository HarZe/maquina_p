package instrucciones.aritmeticas;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;

public class Division implements Instruccion {

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() < 2)
			throw new Exception("DIV: faltan operandos");
		
		Valor op1 = st.pop();
		Valor op2 = st.pop();
		if (op1 instanceof Entero && op2 instanceof Entero) {
			st.push(new Entero(((int) op2.getValor()) / ((int) op1.getValor())));
			cp.incr();
			//System.out.println(op1.toString() + " " + op2.toString() + " " + ((int) op2.getValor()) / ((int) op1.getValor()));
		}
		else
			throw new Exception("DIV: operandos no enteros");
	}

}
