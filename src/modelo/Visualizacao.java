package modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Visualizacao {
	private int id;
	private String datahora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private int nota;
	private Usuario usuario;
	private Video video;
	
	public Visualizacao(int id, int nota, Usuario usuario, Video video) {
		this.id = id;
		this.nota = nota;
		this.usuario = usuario;
		this.video = video;
	}
	
	public int getId() {
		return id;
	}

	public String getDatahora() {
		return datahora;
	}

	public int getNota() {
		return this.nota;
	}
	
	public Video getVideo() {
		return this.video;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	@Override
	public String toString() {
		return " Visualizacao [id = " + id + 
				", datahora = " + datahora + 
				", nota = " + nota +
				"\n usuario = " + usuario.getEmail() + ", video = " + video.getNome() + "]";
	}

	
	
	

}
