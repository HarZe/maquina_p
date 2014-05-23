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

public class MemoriaDatos extends HashMap<Integer, Valor> {

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

	public MemoriaDatos() {
		super();
		lista.add(new Hueco(INICIO, TAM, true));
	}

	public void clonar(int dest, int ori, int tam) {
		for (int i = ori; i < ori + tam; i++) {
			Valor v = this.get(dest);
			if (v == null) {
				v = new Entero(0);
			}
			this.put(i, v);
			dest++;
		}
	}

	public void libera(int pos, int tamaño) {
		int dirTmp = pos;
		int cantidadTmp = tamaño;
		Collections.sort(this.lista, comparator);

		for (Hueco espacio : this.lista) {
			if (espacio.start_dir <= dirTmp && espacio.start_dir + espacio.tam >= dirTmp) {
				if (dirTmp + espacio.tam < dirTmp + cantidadTmp) {
					if (espacio.getStart_dir() < dirTmp) {

						int tam = dirTmp - espacio.getStart_dir();
						int tam2 = (espacio.getStart_dir() + espacio.getTam())
								- dirTmp;
						this.lista.add(new Hueco(espacio.getStart_dir(), tam,
								espacio.isLibre()));
						
						espacio.setTam(tam2);
						espacio.setStart_dir(dirTmp);
						espacio.setLibre(true);

						dirTmp += tam2;
						cantidadTmp -= tam2;

					} else {
						espacio.setLibre(true);
						dirTmp += espacio.getTam();
						cantidadTmp -= espacio.getTam();

					}

				} else if (espacio.start_dir + espacio.tam == dirTmp + cantidadTmp) {
					if (espacio.getStart_dir() < dirTmp) {

						int tam = dirTmp - espacio.getStart_dir();
						this.lista.add(new Hueco(espacio.getStart_dir(), tam,
								espacio.isLibre()));

						int tam2 = (espacio.getStart_dir() + espacio.getTam())
								- dirTmp;
						espacio.setTam(tam2);
						espacio.setStart_dir(dirTmp);
						espacio.setLibre(true);

						dirTmp += tam2;
						cantidadTmp -= tam2;

					} else {
						espacio.setLibre(true);
						dirTmp += espacio.getTam();
						cantidadTmp -= espacio.getTam();

					}
					break;

				} else {

					int tam1 = dirTmp - espacio.getStart_dir();
					this.lista.add(new Hueco(espacio.getStart_dir(), tam1,
							espacio.isLibre()));

					int tam3 = espacio.getStart_dir() - dirTmp + cantidadTmp;
					this.lista.add(new Hueco(dirTmp + cantidadTmp, tam3,
							espacio.isLibre()));

					espacio.setLibre(true);
					espacio.setTam(espacio.getTam() - tam1 - tam3);
					espacio.setStart_dir(espacio.getStart_dir() + tam1);

					dirTmp += espacio.getTam();
					cantidadTmp -= espacio.getTam();

					break;
				}
			}
		}

		ArrayList<Hueco> espacios = new ArrayList<Hueco>();

		Collections.sort(this.lista, comparator);
		Iterator<Hueco> it = this.lista.iterator();
		Hueco eAnt = it.next();
		while (it.hasNext()) {
			Hueco espacio = it.next();
			if (eAnt.isLibre() && espacio.isLibre()) {
				espacio.setStart_dir(eAnt.getStart_dir());
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
		int tamaño, dir = 0;
		for (Hueco hueco : this.lista) {
			tamaño = hueco.getTam();
			dir = hueco.getStart_dir();
			if (tamaño > reserva) {
				this.lista.add(new Hueco(dir, tamaño, false));
				hueco.setTam(tamaño - reserva);
				hueco.setStart_dir(dir + reserva);
				hueco.setLibre(false);
				break;
			} else if (tamaño == reserva) {
				hueco.setLibre(false);
				break;
			}
		}
		return dir;
	}

	public class Hueco {
		private int start_dir, tam;
		private boolean libre;

		public Hueco(int dir, int tam, boolean libre) {
			this.start_dir = dir;
			this.libre = libre;
			this.tam = tam;
		}

		public boolean isLibre() {
			return libre;
		}

		public void setLibre(boolean libre) {
			this.libre = libre;
		}

		public int getStart_dir() {
			return start_dir;
		}

		public void setStart_dir(int dir_com) {
			this.start_dir = dir_com;
		}

		public int getTam() {
			return tam;
		}

		public void setTam(int tam) {
			this.tam = tam;
		}

		public int compareTo(Hueco hueco) {
			if (this.start_dir < hueco.start_dir) {
				return 1;
			} else if (this.start_dir > hueco.start_dir) {
				return -1;
			}
			return 0;
		}
	}

}
