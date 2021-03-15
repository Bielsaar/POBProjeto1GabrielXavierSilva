package dao;

import java.util.List;

import com.db4o.query.Query;
import modelo.Assunto;
import modelo.Video;

public class DAOAssunto extends DAO<Assunto>{

	public Assunto read(Object chave) {
		String palavra = (String) chave;
		
		Query q = manager.query();
		q.constrain(Assunto.class);
		q.descend("palavra").constrain(palavra);
		List<Assunto> resultado = q.execute();
		if (resultado.size()>0) 
			return resultado.get(0);
		else
			return null;
	}
	
	
	
}
