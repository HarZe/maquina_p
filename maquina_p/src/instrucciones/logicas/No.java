package instrucciones.logicas;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Booleano;
import valores.Valor;

public class No implements Instruccion {

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() == 0)
			throw new Exception("NO: pila vac�a");
		
		Valor op = st.pop();
		if (op instanceof Booleano) {
			st.push(new Booleano(! ((boolean) op.getValor())));
			cp.incr();
		}
		else
			throw new Exception("NO: operando no booleano");
	}

}
