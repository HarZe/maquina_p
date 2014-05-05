package instrucciones.logicas;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Booleano;
import valores.Valor;

public class Y implements Instruccion {

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() < 2)
			throw new Exception("Y: faltan operandos");
		
		Valor op1 = st.pop();
		Valor op2 = st.pop();
		if (op1 instanceof Booleano && op2 instanceof Booleano) {
			st.push(new Booleano(((boolean) op1.getValor()) && ((boolean) op2.getValor())));
			cp.incr();
		}
		else
			throw new Exception("Y: operandos no booleanos");
	}

}
