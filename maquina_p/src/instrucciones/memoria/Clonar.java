package instrucciones.memoria;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;
import instrucciones.Instruccion;

public class Clonar implements Instruccion {

	protected Entero tam;
	
	public Clonar(Entero tam) {
		this.tam = tam;
	}

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() < 2)
			throw new Exception("CLONAR(" + tam.toString() + "): faltan operandos");
		
		Valor dest = st.pop();
		Valor orig = st.pop();
		
		if (dest instanceof Entero && orig instanceof Entero) {
			md.clonar((int) orig.getValor(), (int) dest.getValor(), (int) tam.getValor());
			cp.incr();
		}
		else
			throw new Exception("CLONAR: direccion invalida");
	}

}
