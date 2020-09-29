package net.weesftw.dao;

import java.util.List;

public interface DataAcess<T> 
{
	public boolean add(T e);
	public T search(T e);
	public boolean update(T e);
	public boolean remove(T e);
	public List<T> list();
}
