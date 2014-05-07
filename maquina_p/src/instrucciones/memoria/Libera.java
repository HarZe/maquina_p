package instrucciones.memoria;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;
import instrucciones.Instruccion;

public class Libera implements Instruccion {

	protected Entero tam;
	
	public Libera(Entero tam) {
		this.tam = tam;
	}

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() == 0)
			throw new Exception("LIBERA(" + tam.toString() + "): pila vacia");
		
		Valor pos = st.pop();
		
		if (pos instanceof Entero) {
			md.libera((int) pos.getValor(), (int) tam.getValor());
			cp.incr();
		}
		else
			throw new Exception("LIBERA: direccion invalida");
	}

}
