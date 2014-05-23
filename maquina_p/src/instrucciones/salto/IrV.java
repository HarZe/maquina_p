package instrucciones.salto;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Booleano;
import valores.Entero;
import valores.Valor;
import instrucciones.Instruccion;

public class IrV implements Instruccion {

	protected Entero pd;
	
	public IrV(Entero pd) {
		this.pd = pd;
	}

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.isEmpty())
			throw new Exception("IR_V: pila vacia");
		
		Valor op = st.pop();
		if (op instanceof Booleano) {
			if ((boolean) op.getValor())
				cp.set((int) pd.getValor());
			else
				cp.incr();
		}
		else
			throw new Exception("IR_V: operando no booleano");
	}

}
