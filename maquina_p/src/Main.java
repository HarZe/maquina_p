import maquina_p.MaquinaP;
import parser.Parser;


public class Main {

	public static void main(String[] args) {
		try {
			MaquinaP maquina = new MaquinaP(Parser.instDeArchivo("input.txt"));
			maquina.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
