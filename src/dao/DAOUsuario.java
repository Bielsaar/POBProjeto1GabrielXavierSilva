package dao;

import java.util.List;

import com.db4o.query.Query;
import modelo.Usuario;
import modelo.Video;

public class DAOUsuario extends DAO<Usuario> {

	public Usuario read(Object chave) {
		String email = (String) chave;
		
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.descend("email").constrain(email);
		List<Usuario> resultado = q.execute();
		if(resultado.size()>0)
			return resultado.get(0);
		else
			return null;
		
	}
	
	public List<Usuario> consultarUsuariosPorVideo(String link){
		Query q = manager.query();
		q.constrain(Usuario.class);
		q.descend("visualizacoes").descend("video").descend("link").constrain(link);
		List<Usuario> resultados = q.execute();
		if(resultados.size()==0)
			return null;
		else
			return resultados;

	}
	
	

}
