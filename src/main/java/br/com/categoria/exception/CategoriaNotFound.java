package br.com.categoria.exception;

public class CategoriaNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoriaNotFound(String msg){
        super(msg);
    }

    public CategoriaNotFound(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
