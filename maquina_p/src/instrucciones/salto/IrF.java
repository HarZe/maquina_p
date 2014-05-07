package instrucciones.salto;

import java.util.Stack;

import maquina_p.MaquinaP.Cp;
import maquina_p.MemoriaDatos;
import valores.Booleano;
import valores.Entero;
import valores.Valor;
import instrucciones.Instruccion;

public class IrF implements Instruccion {

	protected Entero pd;
	
	public IrF(Entero pd) {
		this.pd = pd;
	}

	@Override
	public void ejecutar(Stack<Valor> st, MemoriaDatos md, Cp cp)
			throws Exception {
		if (st.size() == 0)
			throw new Exception("IR_F: pila vacia, cp:" + cp.get());
		
		Valor op = st.pop();
		if (op instanceof Booleano) {
			if (! (boolean) op.getValor())
				cp.set((int) pd.getValor());
			else
				cp.incr();
		}
		else
			throw new Exception("IR_F: operando no booleano");
	}

}
