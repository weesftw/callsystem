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

public class CepAPI 
{
	private String cep, logradouro, bairro, localidade, uf, ddd;
	
	public CepAPI(String id) throws ParserConfigurationException, SAXException, IOException
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
				
				cep = e.getElementsByTagName("cep").item(i).getTextContent();
				logradouro = e.getElementsByTagName("logradouro").item(i).getTextContent();
				bairro = e.getElementsByTagName("bairro").item(i).getTextContent();
				localidade = e.getElementsByTagName("localidade").item(i).getTextContent();
				uf = e.getElementsByTagName("uf").item(i).getTextContent();
				ddd = e.getElementsByTagName("ddd").item(i).getTextContent();
			}
		}
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
}
