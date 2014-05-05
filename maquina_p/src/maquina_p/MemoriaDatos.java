package maquina_p;

import valores.Valor;

public class MemoriaDatos {

	protected Valor[] memoria;
	protected int elems;
	protected static int INICIAL = 32;
	
	public MemoriaDatos() {
		memoria = new Valor[INICIAL];
		elems = INICIAL;
		for (int i = 0; i < INICIAL; i++)
			memoria[i] = null;
	}
	
	public Valor get(int p) {
		return memoria[p];
	}
	
	public void set(int p, Valor v) {
		if (p - 1 > elems)
			ampliar(p);
		memoria[p] = v;
	}
	
	public String toString() {
		String s = "MP:";
		
		for (Valor v : memoria)
			if (v != null)
				s += " " + v.toString();
			else
				s += " 0";
		
		return s;
	}
	
	protected void ampliar(int t) {
		Valor[] nuevaMem = new Valor[2*t];
		for (int i = 0; i < elems; i++)
			nuevaMem[i] = memoria[i];
		for (int i = elems; i < 2*t; i++)
			nuevaMem[i] = null;
		elems = 2*t;
	}
}
