package maquina_p;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;

import valores.Valor;

public class MemoriaDatos {

	protected Valor[] memoria;
	protected int elems;
	protected static int INICIAL = 32;
	
	protected SortedList<Hueco> huecos;
	
	public MemoriaDatos() {
		memoria = new Valor[INICIAL];
		elems = INICIAL;
		for (int i = 0; i < INICIAL; i++)
			memoria[i] = null;
		huecos = new SortedList<Hueco>();
		huecos.add(new Hueco(0, elems));
	}
	
	public Valor get(int p) {
		return memoria[p];
	}
	
	public void set(int p, Valor v) {
		if (p - 1 > elems)
			ampliar(p);
		
		// TODO: encontrar que hueco estaria ocupando y actualizar la lista de huecos
		
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
	
	public int reserva(int tam) {
		// TODO
		return 0;
	}
	
	public void libera(int pos, int tam) {
		// TODO
	}
	
	public void clonar(int origen, int destino, int tam) {
		// TODO
	}
	
	protected void ampliar(int t) {
		Valor[] nuevaMem = new Valor[2*t];
		for (int i = 0; i < elems; i++)
			nuevaMem[i] = memoria[i];
		for (int i = elems; i < 2*t; i++)
			nuevaMem[i] = null;
		elems = 2*t;
		
		// TODO: actualizar el ultimo hueco de la lista
	}
	
	protected class Hueco implements Comparable<Hueco> {
		public int pos;
		public int tam;
		
		public Hueco(int pos, int tam) {
			this.pos = pos;
			this.tam = tam;
		}

		@Override
		public int compareTo(Hueco o) {
			return o.pos - pos;
		}
	}
	
	public class SortedList<E> extends AbstractList<E> {

	    private ArrayList<E> list = new ArrayList<E>();
	    
	    @Override 
	    public void add(int position, E e) {
	        list.add(e);
	        Collections.sort(list, null);
	    }

	    @Override
	    public E get(int i) {
	        return list.get(i);
	    }

	    @Override
	    public int size() {
	        return list.size();
	    }

	}
}
