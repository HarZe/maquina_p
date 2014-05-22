package maquina_p;

import instrucciones.Instruccion;

import java.util.ArrayList;
import java.util.Stack;

import valores.Valor;

public class MaquinaP {

	protected MemoriaDatos md;
	protected ArrayList<Instruccion> mp;
	protected Stack<Valor> pila;
	
	protected Cp cp;
	
	public MaquinaP(ArrayList<Instruccion> programa) {
		this.md = new MemoriaDatos();
		this.mp = programa;
		this.pila = new Stack<Valor>();
		cp = new Cp();
	}
	
	public void ejecutar() {
		try {
			while (cp.get() < mp.size()) {
				mp.get(cp.get()).ejecutar(pila, md, cp);
				
			}
		}
		catch (Exception e) {
			System.out.println("MD=\n" + md.toString());
			System.out.println("CP=" + cp.get());
			System.out.println("PILA=" + pila.toString());
			e.printStackTrace();
		}
	}
	
	public class Cp {
		
		protected int cp;
		
		public Cp() {
			cp = 0;
		}
		
		public int get() {
			return cp;
		}
		
		public void set(int n) {
			cp = n;
		}
		
		public void incr() {
			cp++;
		}
	}
}
