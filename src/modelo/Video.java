package modelo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Video {
	private String link;
	private String nome;
	private double media;
	private List<Assunto> assuntos = new ArrayList<>();
	private List<Visualizacao> visualizacoes = new ArrayList<>();

	
	public Video(String link, String nome) {
		this.link = link;
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public String getLink() {
		return link;
	}
	
	public double getMedia() {
		return media;
	}
	public List<Visualizacao> getVisualizacoes() {
		return visualizacoes;
	}
	public List<Assunto> getAssuntos() {
		return this.assuntos;
	}

	public void adicionar(Assunto a) {
		assuntos.add(a);
	}
	public void adicionar(Visualizacao vis) {
		visualizacoes.add(vis);
	}
	public void remover(Visualizacao vis) {
		 Iterator<Visualizacao> itr = visualizacoes.iterator();
		 while (itr.hasNext()) 
	        { 
	            Visualizacao x = (Visualizacao)itr.next(); 
	            if (x.equals(vis)) {
	                itr.remove(); 
	                break;
	            }
	        } 
	}

	public void atualizarMedia() {
		int quant = visualizacoes.size();
		double med = 0;
		for(Visualizacao vi: visualizacoes) {
			double nota = vi.getNota();
			double aux = nota/quant;
			med += aux;;
		}
		
		this.media = med;
	}
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (visualizacoes == null) {
			if (other.visualizacoes != null)
				return false;
		} else if (!visualizacoes.equals(other.visualizacoes))
			return false;
		return true;
	}
	@Override
	public String toString() {
		String texto = "Video:\n  [" + (link != null ? "link = " + link + ", " : "") + (nome != null ? "nome = " + nome + ", " : "")
				+ "media = " + media ;
		
		texto+=", assuntos = ";
		for(Assunto a : assuntos) {
			texto += a.getPalavra() + ", ";
		}
		texto+="\n visualizacoes => ";
		for(Visualizacao vis : visualizacoes) {
			texto += vis;
		}
		return texto;
	}

	
}
