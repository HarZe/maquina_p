package instrucciones.memoria;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;

public class Apila_Dir implements Instruccion {

	protected Entero dir;
	
	public Apila_Dir(Entero dir) {
		this.dir = dir;
	}
	
	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp) throws Exception {
		Valor v = md.get((int) dir.getValor());
		
		if (v == null)
			throw new Exception("APILA_DIR: valor invalido de memoria, dir: " + dir.getValor());
		
		st.push(v);
		cp.incr();
	}

}
