package instrucciones.memoria;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;
import instrucciones.Instruccion;

public class Reserva implements Instruccion {

	protected Entero tam;
	
	public Reserva(Entero tam) {
		this.tam = tam;
	}

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		
		st.push(new Entero(md.reserva((int) tam.getValor())));
		cp.incr();
	}

}
