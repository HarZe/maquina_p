package instrucciones.memoria;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;

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
			Valor v = md.get((int) op.getValor());
			
			if (v == null)
				v = new Entero(0);
			
			st.push(v);
			cp.incr();
		}
		else
			throw new Exception("APILA_IND: operando no entero (direccion)");
	}

}
