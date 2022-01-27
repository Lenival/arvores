package br.ufrn.imd.modelo;

public class NoBinarioEstendido <T extends Comparable <T>> extends NoBinario<T> {
	private Integer qNosEsq;
	private Integer qNosDir;
	private NoBinarioEstendido<T> irmaoDireito;
	private NoBinarioEstendido<T> irmaoEsquedo;
	
	public NoBinarioEstendido() {
		super();
		this.qNosEsq = 0;
		this.qNosDir = 0;
		this.irmaoDireito = null;
	}

	public NoBinarioEstendido(T dado) {
		super(dado);
		this.qNosEsq = 0;
		this.qNosDir = 0;
		this.irmaoDireito = null;
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
	
	public NoBinarioEstendido<T> getIrmaoDireito() {
		return irmaoDireito;
	}
	public void setIrmaoDireito(NoBinarioEstendido<T> irmaoDireito) {
		this.irmaoDireito = irmaoDireito;
	}

	public NoBinarioEstendido<T> getIrmaoEsquedo() {
		return irmaoEsquedo;
	}
	public void setIrmaoEsquedo(NoBinarioEstendido<T> irmaoEsquedo) {
		this.irmaoEsquedo = irmaoEsquedo;
	}
	
	
	
}
