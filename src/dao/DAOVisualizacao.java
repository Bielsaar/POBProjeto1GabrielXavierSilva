package dao;


import java.util.List;

import com.db4o.query.Query;
import modelo.Visualizacao;

public class DAOVisualizacao extends DAO<Visualizacao>{

	public Visualizacao read(Object chave) {
		int id = (int) chave;
		
		Query q = manager.query();
		q.constrain(Visualizacao.class);
		q.descend("id").constrain(id);
		List<Visualizacao> resultado = q.execute();
		if(resultado.size()>0)
			return resultado.get(0);
		else
			return null;
	}
	
	
	public Visualizacao localizarVisu(int id) {
		Query q = manager.query();
		q.constrain(Visualizacao.class);
		q.descend("id").constrain(id);
		List<Visualizacao> result = q.execute(); 
		if(result.size()>0)
			return  result.get(0);
		else
			return null;
	
	}

}
