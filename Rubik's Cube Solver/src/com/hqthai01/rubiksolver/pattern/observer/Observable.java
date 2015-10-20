package com.hqthai01.rubiksolver.pattern.observer;

/**
 * @author Thái
 * Feb 3, 2015
 */
public interface Observable {
	
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers(Object obj);
	public void notifyObservers();
	public void setChanged();
	
}
