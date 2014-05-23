package instrucciones.salto;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;
import instrucciones.Instruccion;

public class IrA implements Instruccion {

	protected Entero pd;
	
	public IrA(Entero pd) {
		this.pd = pd;
	}

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {		
		cp.set((int) pd.getValor());
	}

}
