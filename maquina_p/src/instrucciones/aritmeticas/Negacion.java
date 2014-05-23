package instrucciones.aritmeticas;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;

public class Negacion implements Instruccion {

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() == 0)
			throw new Exception("NEG: pila vacia");
		
		Valor op = st.pop();
		if (op instanceof Entero) {
			st.push(new Entero(- ((int) op.getValor())));
			cp.incr();
		}
		else
			throw new Exception("NEG: operando no entero");
	}

}
