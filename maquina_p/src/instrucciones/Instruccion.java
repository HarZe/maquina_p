package instrucciones;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Valor;

public interface Instruccion {

	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp) throws Exception;
	
}
