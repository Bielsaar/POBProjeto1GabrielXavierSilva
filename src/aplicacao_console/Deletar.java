package aplicacao_console;

import fachada.Fachada;

public class Deletar {
	public Deletar(){
		try {
			Fachada.inicializar();
			try {
				Fachada.apagarVisualizacao(2);
				System.out.println("apagou visualização numero 2");
			}
			catch (Exception e) {System.out.println(e.getMessage());}

			try {
				Fachada.apagarVisualizacao(4);
				System.out.println("apagou visualização numero 4");
			} 
			catch (Exception e) {System.out.println(e.getMessage());}

			




		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
		}

		System.out.println("fim do programa");
	}



	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}
