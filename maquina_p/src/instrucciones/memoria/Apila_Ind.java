package instrucciones.memoria;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Booleano;
import valores.Entero;
import valores.Valor;
import instrucciones.Instruccion;

public class Apila_Ind implements Instruccion {

	public Apila_Ind() {

	}

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() == 0)
			throw new Exception("APILA_IND: pila vacia");
		
		Valor op = st.pop();
		if (op instanceof Entero) {
			st.push(md.get((int) op.getValor()));
			cp.incr();
		}
		else
			throw new Exception("APILA_IND: operando no entero (direccion)");
	}

}
