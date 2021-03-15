package dao;

import java.util.List;

import com.db4o.query.Query;
import modelo.IncrementalId;


public class DAOIncrementalId extends DAO<IncrementalId>{

		public IncrementalId read(Object chave) {
			int incId = (int) chave;
			
			Query q = manager.query();
			q.constrain(IncrementalId.class);
			q.descend("incId").constrain(incId);
			List<IncrementalId> resultado = q.execute();
			if (resultado.size()>0) 
				return resultado.get(0);
			else
				return null;
		}
		
		public IncrementalId resgatar() {
			Query q = manager.query();
			q.constrain(IncrementalId.class);
			q.descend("incId");
			List<IncrementalId> resultado = q.execute();
			if (resultado.size()>0)
				return resultado.get(0);
			else
				return null;
		}
}
