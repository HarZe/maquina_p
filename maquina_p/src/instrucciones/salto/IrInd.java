package instrucciones.salto;

import instrucciones.Instruccion;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Entero;
import valores.Valor;

public class IrInd implements Instruccion {

	public IrInd() {
		
	}
	
	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() == 0)
			throw new Exception("IR_IND: pila vacia");
		
		Valor op = st.pop();
		if (op instanceof Entero)
			cp.set((int) op.getValor());
		else
			throw new Exception("IR_IND: operando no entero");
	}

}
