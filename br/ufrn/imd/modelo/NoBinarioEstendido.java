package br.ufrn.imd.modelo;

public class NoBinarioEstendido <T extends Comparable <T>> extends NoBinario<T> {
	private Integer qNosEsq;
	private Integer qNosDir;
	
	public NoBinarioEstendido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoBinarioEstendido(T dado) {
		super(dado);
		this.qNosEsq = 0;
		this.qNosDir = 0;		
		// TODO Auto-generated constructor stub
	}

	public NoBinarioEstendido(T dado, Integer qNosEsq, Integer qNosDir) {
		super(dado);
		this.qNosEsq = qNosEsq;
		this.qNosDir = qNosDir;
	}
	
	public Integer getqNosEsq() {
		return qNosEsq;
	}
	public void setqNosEsq(Integer qNosEsq) {
		this.qNosEsq = qNosEsq;
	}
	public Integer getqNosDir() {
		return qNosDir;
	}
	public void setqNosDir(Integer qNosDir) {
		this.qNosDir = qNosDir;
	}
	
	
}
