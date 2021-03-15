package fachada;


import java.util.List;

import dao.DAO;
import dao.DAOAssunto;
import dao.DAOIncrementalId;
import dao.DAOUsuario;
import dao.DAOVideo;
import dao.DAOVisualizacao;
import modelo.*;

public class Fachada {
	
	private static DAOVideo daovideo = new DAOVideo();
	private static DAOAssunto daoassunto = new DAOAssunto();
	private static DAOUsuario daousuario = new DAOUsuario();
	private static DAOVisualizacao daovisualizacao = new DAOVisualizacao();
	private static DAOIncrementalId daoincrementalid= new DAOIncrementalId();
	
	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}
	
	
	public static Video cadastrarVideo(String link, String nome, String palavra) throws  Exception{
		DAO.begin();
		Video vi = daovideo.read(link);
		if(vi != null) {
			DAO.rollback();
			throw new Exception("Video j� cadastrado: " + link);
		}
		Assunto ass = daoassunto.read(palavra);
		if(ass == null) {
			ass = new Assunto(palavra);
			daoassunto.create(ass);
		}
		vi = new Video(link, nome);
		vi.adicionar(ass);
		daovideo.create(vi);
		
		ass = daoassunto.read(palavra);
		ass.adicionar(vi);
		daoassunto.update(ass);
		
		DAO.commit();
		return vi;
		
	}
	
	public static void adicionarAssunto(String link, String palavra) throws  Exception{
		DAO.begin();
		Video vi = daovideo.read(link);
		if(vi == null) {
			DAO.rollback();
			throw new Exception("Video n�o cadastrado: " + link);
		}
		Assunto ass = daoassunto.read(palavra);
		if(ass == null) {
			daoassunto.create(new Assunto(palavra));
		} else {
			List<Assunto> assus = vi.getAssuntos();
			for (Assunto a: assus) {
				if (a.getPalavra() == palavra) {
					DAO.rollback();
					throw new Exception("Video j� contem esse assunto! -> " + palavra);
				}
			}
		}
		
		ass = daoassunto.read(palavra);
		vi.adicionar(ass);
		daovideo.update(vi);
		
		ass.adicionar(vi);
		daoassunto.update(ass);
		
		DAO.commit();
	}
	
	public static Visualizacao registrarVisualizacao(String link, String email, int nota) throws  Exception{
		DAO.begin();
		
		if(nota > 5 || nota < 1) {
			DAO.rollback();
			throw new Exception("Por favor, p�r uma nota entre 1 e 5!" );
		}
		
		Video vi = daovideo.read(link);
		if(vi == null) {
			DAO.rollback();
			throw new Exception("Video n�o existe: " + link);
		}
		Usuario us = daousuario.read(email);
		if(us == null) {
			Usuario us2 = new Usuario(email);
			daousuario.create(us2);
		}
		us = daousuario.read(email);
		IncrementalId counter = (IncrementalId) daoincrementalid.resgatar();
		if(counter == null) {
			counter = new IncrementalId();
			daoincrementalid.create(counter);
		}
		counter = (IncrementalId) daoincrementalid.resgatar();
		int id = counter.incrementAndGet();
		daoincrementalid.update(counter);
		
		Visualizacao visu = new Visualizacao(id, nota, us, vi);
		daovisualizacao.create(visu);
		us.adicionar(visu);
		daousuario.update(us);
		vi.adicionar(visu);
		vi.atualizarMedia();
		daovideo.update(vi);
		DAO.commit();
		return visu;
	}
	
	public static Visualizacao localizarVisualizacao(int id) {
		return daovisualizacao.localizarVisu(id);
		
		
	}
	
	public static void apagarVisualizacao(int id) throws  Exception{
		DAO.begin();
		Visualizacao vi = daovisualizacao.read(id);
		if (vi==null) {
			DAO.rollback();
			throw new Exception("Visualiza��o inexistente:" + id);
		}
		Video v = vi.getVideo();
		Usuario us = vi.getUsuario();
		
		v.remover(vi);
		us.remover(vi);

		daovideo.update(v);
		daousuario.update(us);
		daovisualizacao.delete(vi);
		DAO.commit();
	}
	
	public static List<Video> listarVideos() {
		return daovideo.readAll();
	}
	
	public static List<Visualizacao> listarVisualizacao() {
		return daovisualizacao.readAll();
	}
	
	public static List<Usuario> listarUsuarios() {
		return daousuario.readAll();
	}
	public static List<Assunto> listarAssuntos() {
		return daoassunto.readAll();
	}
	
	public static List<Video> consultarVideosPorAssunto(String palavra) {
		return daovideo.consultarVideoPorAssunto(palavra);
	}
	
	public static List<Video> consultarVideosPorUsuario(String email) {
		return daovideo.consultarVideoPorUsuario(email);
	}
	
	public static List<Usuario> consultarUsuariosPorVideo(String link) {
		return daousuario.consultarUsuariosPorVideo(link);
	}
	
}
