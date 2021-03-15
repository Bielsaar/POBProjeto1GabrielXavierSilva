package dao;

import java.util.List;
import com.db4o.query.Query;

import modelo.Assunto;
import modelo.Usuario;
import modelo.Video;

public class DAOVideo extends DAO<Video>{

	public Video read(Object chave) {
		String link = (String) chave;
		
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("link").constrain(link);
		List<Video> resultado = q.execute();
		if(resultado.size()>0)
			return resultado.get(0);
		else
			return null;
	}
	
	public List<Video> consultarVideoPorAssunto(String palavra){
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("assuntos").descend("palavra").constrain(palavra);
		List<Video> resultados = q.execute();
		if(resultados.size()==0)
			return null;
		else
			return resultados;
		}
	
	public List<Video> consultarVideoPorUsuario(String email){
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("visualizacoes").descend("usuario").descend("email").constrain(email);
		List<Video> resultados = q.execute();
		if(resultados.size()==0)
			return null;
		else
			return resultados;

	}

	
	
	

}
