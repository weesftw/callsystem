package net.weesftw.manager;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.weesftw.exception.CepNotFoundException;

public final class CepAPI 
{
	private String cep, logradouro, bairro, localidade, uf, ddd, country;
	
	public CepAPI(String id) throws ParserConfigurationException, SAXException, IOException, CepNotFoundException
	{
		if(!id.isEmpty())
		{
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document d = b.parse("https://viacep.com.br/ws/" + id + "/xml");
			
			NodeList l = d.getElementsByTagName("xmlcep");
			
			for(int i = 0; i < l.getLength(); i++)
			{
				Node n = l.item(i);
				
				if(n.getNodeType() == Node.ELEMENT_NODE)
				{
					Element e = (Element) n;
					
					if(e.getElementsByTagName("erro").item(i) == null)
					{
						cep = e.getElementsByTagName("cep").item(i).getTextContent();
						logradouro = e.getElementsByTagName("logradouro").item(i).getTextContent();
						bairro = e.getElementsByTagName("bairro").item(i).getTextContent();
						localidade = e.getElementsByTagName("localidade").item(i).getTextContent();
						uf = e.getElementsByTagName("uf").item(i).getTextContent();
						ddd = e.getElementsByTagName("ddd").item(i).getTextContent();				
					}
					else
					{
						throw new CepNotFoundException();
					}
				}
			}
		}
	}	
	
	public CepAPI(String cep, String logradouro, String bairro, String localidade, String uf, String ddd, String country) 
	{		
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ddd = ddd;
		this.country = country;
	}
	
	public String getCep()
	{
		return cep;
	}
	
	public String getLogradouro()
	{
		return logradouro;
	}
	
	public String getBairro()
	{
		return bairro;
	}
	
	public String getLocalidade()
	{
		return localidade;
	}
	
	public String getUf()
	{
		return uf;
	}
	
	public String getDDD()
	{
		return ddd;
	}
	
	public String getCountry()
	{
		return country;
	}
}
