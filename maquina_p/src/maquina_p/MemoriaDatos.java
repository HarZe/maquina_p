package maquina_p;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import valores.Entero;
import valores.Valor;

public class MemoriaDatos extends HashMap<Integer, Valor>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3932189872155024135L;
	
	private final Comparator<Hueco> comparator = new Comparator<Hueco>() {
		@Override
		public int compare(Hueco e1, Hueco e2) {
			return e1.compareTo(e2);
		}
	};
	
	private static final int INICIO = 1024;
	private static final int TAM = 2048;
	
	public List<Hueco> lista = new LinkedList<Hueco>();
	
	public MemoriaDatos(){
		super();
		lista.add(new Hueco(INICIO, TAM, true));
	}

	public void clonar(int destino, int origen, int tamaño){
		int len=tamaño+destino;
		while (destino<len){
			Valor v = this.get(destino);
			if(v == null){
				v = new Entero(0);
			}
			this.put(origen, v);
			destino++;
			origen++;
		}
	}
	
	public void libera(int posicion, int tamaño){
		int dirPilaTmp = posicion;
		int cantidadTmp = tamaño;
		Collections.sort(this.lista, comparator);

		for (Hueco espacio : this.lista) {
			if (espacio.estaEnEsteEspacioDeDirecciones(dirPilaTmp)) {
				if (espacio.superaElTamanio(dirPilaTmp, cantidadTmp)) {
					if (espacio.getDir_com() < dirPilaTmp) {
						// Hay que dividir en dos partes

						int tam = dirPilaTmp - espacio.getDir_com();
						this.lista.add(new Hueco(espacio.getDir_com(), tam, espacio
								.isLibre()));

						int tam2 = (espacio.getDir_com() + espacio.getTam())
								- dirPilaTmp;
						espacio.setTam(tam2);
						espacio.setDir_com(dirPilaTmp);
						espacio.setLibre(true);

						dirPilaTmp += tam2;
						cantidadTmp -= tam2;

					} else /* Tienen la misma direccion */{
						espacio.setLibre(true);
						dirPilaTmp += espacio.getTam();
						cantidadTmp -= espacio.getTam();

					}

				} else if (espacio.loLiberaExacto(dirPilaTmp, cantidadTmp)) {
					if (espacio.getDir_com() < dirPilaTmp) {
						// Hay que dividir en dos partes

						int tam = dirPilaTmp - espacio.getDir_com();
						this.lista.add(new Hueco(espacio.getDir_com(), tam, espacio
								.isLibre()));

						int tam2 = (espacio.getDir_com() + espacio.getTam())
								- dirPilaTmp;
						espacio.setTam(tam2);
						espacio.setDir_com(dirPilaTmp);
						espacio.setLibre(true);
						
						dirPilaTmp += tam2;
						cantidadTmp -= tam2;

					} else /* Tienen la misma direccion <-> caso ideal */{
						espacio.setLibre(true);
						dirPilaTmp += espacio.getTam();
						cantidadTmp -= espacio.getTam();
						
					}
					break;

				} else /* No lo libera completamente */{
					// Hay que dividir en tres partes

					int tam1 = dirPilaTmp - espacio.getDir_com();
					this.lista.add(new Hueco(espacio.getDir_com(), tam1, espacio
							.isLibre()));

					int tam3 = espacio.getDir_com() - dirPilaTmp + cantidadTmp;
					this.lista.add(new Hueco(dirPilaTmp + cantidadTmp, tam3, espacio
							.isLibre()));

					espacio.setLibre(true);
					espacio.setTam(espacio.getTam() - tam1 - tam3);
					espacio.setDir_com(espacio.getDir_com() + tam1);
					
					dirPilaTmp += espacio.getTam();
					cantidadTmp -= espacio.getTam();

					break;
				}
			}
		}

		/* fusiona y simplifica la lista de espacios. */
		ArrayList<Hueco> espacios = new ArrayList<Hueco>();

		Collections.sort(this.lista, comparator);
		Iterator<Hueco> it = this.lista.iterator();
		Hueco eAnt = it.next();
		while (it.hasNext()) {
			Hueco espacio = it.next();
			if (eAnt.isLibre() && espacio.isLibre()) {
				espacio.setDir_com(eAnt.getDir_com());
				espacio.setTam(espacio.getTam() + eAnt.getTam());
				espacios.add(eAnt);
			}
			eAnt = espacio;
		}

		for (Hueco espacio : espacios) {
			this.lista.remove(espacio);
		}
	}
	
	public int reserva(int reserva) {
		boolean reservado = false;
		int tamaño, dir=0;
		for (Hueco hueco : this.lista){
			tamaño = hueco.getTam();
			dir = hueco.getDir_com();
			if (tamaño > reserva){
				this.lista.add(new Hueco(dir, tamaño, false));	
				hueco.setTam(tamaño-reserva);
				hueco.setDir_com(dir+reserva);		
				hueco.setLibre(false);						
				reservado = true;
				break;
			} else if (tamaño == reserva){				
				hueco.setLibre(false);
				reservado = true;
				break;
			}
		}
		return dir;
	}
	
	public class Hueco{
		private int dir_com, tam;
		private boolean libre;
		
		public Hueco(int dir, int tam, boolean libre) {
			this.dir_com = dir;
			this.libre = libre;
			this.tam = tam;
		}

		public boolean isLibre() {
			return libre;
		}

		public void setLibre(boolean libre) {
			this.libre = libre;
		}

		public int getDir_com() {
			return dir_com;
		}

		public void setDir_com(int dir_com) {
			this.dir_com = dir_com;
		}

		public int getTam() {
			return tam;
		}

		public void setTam(int tam) {
			this.tam = tam;
		}

		public int compareTo(Hueco hueco) {
			if (this.dir_com < hueco.dir_com){
				return 1;
			} else if (this.dir_com > hueco.dir_com){
				return -1;
			}
			return 0;
		}

		public boolean estaEnEsteEspacioDeDirecciones(Integer dir) {
			return dir_com <= dir && dir_com+tam >= dir;
		}

		public boolean superaElTamanio(Integer dir, Integer cantidad) {
			return dir+tam<dir+cantidad;
		}
		
		public boolean loLiberaExacto(Integer dir, Integer cantidad) {
			return dir_com+tam==dir+cantidad;
		}
		
	}
	
}
