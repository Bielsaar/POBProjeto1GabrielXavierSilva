package modelo;

public class IncrementalId {
	private int incId;
	
	public IncrementalId() {
		this.incId = 0;
	}
	
	
	//Foi usado a nomeclatura do Atomic Integer para facilitação (não tem nada haver com ele, além do nome)
	public int incrementAndGet() {
		this.incId++;
		return incId;
	}

}
