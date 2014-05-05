package instrucciones.memoria;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;

public class Desapila_Dir implements Instruccion {

	protected Entero dir;
	
	public Desapila_Dir(Entero dir) {
		this.dir = dir;
	}
	
	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp) throws Exception {
		if (st.size() == 0)
			throw new Exception("DESAPILAR_DIR(" + dir.toString() + "): pila vacia");

		md.set((int) dir.getValor(), st.pop());
		cp.incr();
	}

}
