package modelo;

public class IncrementalId {
	private int incId;
	
	public IncrementalId() {
		this.incId = 0;
	}
	
	
	//Foi usado a nomeclatura do Atomic Integer para facilita��o (n�o tem nada haver com ele, al�m do nome)
	public int incrementAndGet() {
		this.incId++;
		return incId;
	}

}
