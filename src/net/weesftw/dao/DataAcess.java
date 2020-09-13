package net.weesftw.dao;

import java.util.List;

public interface DataAcess<T> 
{
	public boolean add(T e);
	public boolean search(String args);
	public boolean update(String... args);
	public boolean remove(String args);
}
