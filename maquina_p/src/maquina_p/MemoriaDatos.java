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
		
		Hueco izda = huecoPos(p, 1);
		Hueco dcha = parteHueco(izda, p, 1);
		
		if (izda.tam == 0)
			huecos.remove(izda);
		if (dcha.tam > 0)
			huecos.add(dcha);
		
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
		int nHuecos = huecos.size();
		
		for (Hueco h : huecos)
			if (h.tam >= tam) { 
				int pos = h.pos;
				
				h.pos += tam;	// Se avanza la posicion tanto como se haya reservado
				h.tam -= tam;	// Se reduce el tamaño tanto como se haya reservado
				
				return pos; // Se devuelve la posicion del primer hueco valido
			}
		
		ampliar(elems + tam); // Si no habia hueco en toda la memoria, se amplia la memoria en "tam"
		Hueco ultimo = huecos.get(nHuecos - 1);	// Se usa el ultimo hueco donde seguro que cabe, y se opera como en el bucle
		ultimo.pos += tam;
		ultimo.tam -= tam;
		return ultimo.pos;
	}
	
	public void libera(int pos, int tam) throws Exception {
		int nHuecos = huecos.size();
		
		// Si no hay huecos antes de la zona a liberar
		if (huecos.get(0).pos > pos) {
			for (int i = pos; i < pos + tam; i++)
				memoria[i] = null;
			huecos.add(new Hueco(pos, tam));
		}
		
		// Cuando se situa entre 2 huecos
		Hueco izda = null;
		Hueco dcha = null;
		for (int i = 0; i < nHuecos - 2; i++)
			if (pos > huecos.get(i+1).pos && pos < huecos.get(i).pos) {
				izda = huecos.get(i);
				dcha = huecos.get(i+1);
			}
		
		if (izda == null && dcha == null)
			throw new Exception("LIBERA: no se encontro espacio a liberar");
		
		int finIzda = izda.pos + izda.tam;
		int finDcha = dcha.pos + dcha.tam;
		
		// Si el hueco se une al de la izquierda
		if (finIzda == pos)
			izda.tam += tam;
		
		// Si el hueco se une al de la derecha
		else if (pos + tam == dcha.pos) {
			dcha.pos -= tam;
			dcha.pos += tam;
		}
		
		// Si el hueco es interno, se crea y agrega
		else if (pos >= finIzda && pos + tam <= dcha.pos)
			huecos.add(new Hueco(pos, tam));
		
		else
			throw new Exception("LIBERA: la zona de memoria indicada contiene espacio ya libre");
		
		// Libera el espacio poniendo null
		for (int i = pos; i < pos + tam; i++)
			memoria[i] = null;
	}
	
	public void clonar(int origen, int destino, int tam) throws Exception {
		if (Math.abs(destino - origen) < tam)
			throw new Exception("CLONA: las zona de memoria se solapan");
		
		for (int i = 0; i < tam; i++)
			memoria[destino + i] = memoria[origen + i];
	}
	
	protected Hueco huecoPos(int pos, int tam) {
		int nHuecos = huecos.size();
		
		// Se devuelve el primer hueco valido encontrado para esa posicion
		for (int i = 1; i < nHuecos - 1; i++)
			if (pos < huecos.get(i).pos && huecos.get(i - 1).tam >= tam)
				return huecos.get(i - 1);
		
		// Si ninguno sirvio, se da el ultimo
		return huecos.get(nHuecos - 1);
	}
	
	// Parte un hueco reservando dentro un espacio tam
	// El atributo es la parte izquierda tras el corte
	// El hueco devuelto es la nueva parte creada a la derecha
	// Ambos huecos pueden tener tamaño 0 tras ser llamada la funcion
	protected Hueco parteHueco(Hueco h, int pos, int tam) {
		int nPos = pos + tam;
		int finHueco = h.pos + h.tam;
		
		if (pos < h.pos || nPos > finHueco)	// Si no cabe, devuelve null
			return null;
		
		h.tam = pos - h.pos;	// El nuevo tamaño del hueco izquierdo es el sitio entre su posicion y la posicion del corte
		return new Hueco(nPos, finHueco - nPos);	// El tamaño del hueco derecho es el sitio entre el fin del corte y el fin del hueco inicial
	}
	
	// Amplia la memoria al doble de la posicion requerida (por eficiencia)
	protected void ampliar(int t) {
		if (2*t <= elems)
			return;
		
		Valor[] nuevaMem = new Valor[2*t];
		for (int i = 0; i < elems; i++)
			nuevaMem[i] = memoria[i];
		for (int i = elems; i < 2*t; i++)
			nuevaMem[i] = null;
		
		Hueco ultimo = huecos.get(huecos.size() - 1);
		ultimo.tam += 2*t - elems;
		
		elems = 2*t;
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
			return pos - o.pos;
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
