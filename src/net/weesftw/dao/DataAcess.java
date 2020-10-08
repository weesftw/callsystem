package net.weesftw.dao;

import java.util.List;

public interface DataAcess<T> 
{
	public boolean create(T e);
	public T read(String args);
	public boolean update(T e);
	public boolean delete(T e);
	public List<T> list();
}
