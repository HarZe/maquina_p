import maquina_p.MaquinaP;
import parser.Parser;


public class Main {

	public static void main(String[] args) {
		try {
			MaquinaP maquina = new MaquinaP(Parser.instDeArchivo(args[0]));
			maquina.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
