package instrucciones.logicas;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Booleano;
import valores.Entero;
import valores.Valor;

public class Igual implements Instruccion {

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() < 2)
			throw new Exception("IGUAL: faltan operandos");
		
		Valor op1 = st.pop();
		Valor op2 = st.pop();
		if (op1 instanceof Booleano && op2 instanceof Booleano) {
			st.push(new Booleano(((boolean) op1.getValor()) == ((boolean) op2.getValor())));
			cp.incr();
		}
		else if (op1 instanceof Entero && op2 instanceof Entero) {
			st.push(new Booleano(((int) op1.getValor()) == ((int) op2.getValor())));
			cp.incr();
		}
		else
			throw new Exception("IGUAL: operandos de distinto tipo");
	}

}
