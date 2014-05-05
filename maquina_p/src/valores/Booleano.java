package valores;

public class Booleano implements Valor {

	protected boolean v;
	
	public Booleano(boolean v) {
		this.v = v;
	}
	
	@Override
	public Object getValor() {
		return v;
	}

	@Override
	public void setValor(Object v) {
		this.v = (boolean) v;
	}
	
	public String toString() {
		return v ? "true" : "false";
	}
}
