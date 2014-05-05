package valores;

public class Entero implements Valor {

	protected int v;
	
	public Entero(int v) {
		this.v = v;
	}
	
	@Override
	public Object getValor() {
		return v;
	}

	@Override
	public void setValor(Object v) {
		this.v = (int) v;
	}
	
	public String toString() {
		return "" + v;
	}

}
