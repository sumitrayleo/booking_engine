package com.ehi.msi.enterprisecarshare.executor;

/**
 * Piece of execution which is implemented for FutureFixedTask execution
 * 
 */
public interface Task extends Runnable {
	public String getId();
}
