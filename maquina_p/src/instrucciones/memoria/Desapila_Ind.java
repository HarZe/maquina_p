package instrucciones.memoria;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;
import instrucciones.Instruccion;

public class Desapila_Ind implements Instruccion {

	public Desapila_Ind() {

	}

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() < 2)
			throw new Exception("DESAPILA_IND: faltan operandos");
		
		Valor v = st.pop();
		Valor d = st.pop();
		
		if (d instanceof Entero) {
			md.set((int) d.getValor(), v);
			cp.incr();
		}
		else
			throw new Exception("APILA_IND: direccion no valida");
	}

}
