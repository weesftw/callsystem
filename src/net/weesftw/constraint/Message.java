package net.weesftw.constraint;

public enum Message 
{
	ERROR, INVALID, IMAGE_INVALID, SUCCESSFULLY, UPDATE, INVALID_ARGUMENTS, FIELDS_EMPTY, USERNAME_NOT_FOUND, AUTHENTICATED_FAILED, AUTHENTICATED, NOT_EXISTS,
	NOT_FOUND, EXISTS, TICKET_UPDATED, SOLUTION_EMPTY, CHECKOUT_EMPTY, PURCHASE, PERMISSION;
	
	public String get(String args)
	{
		switch(this)
		{
		case ERROR:
			return "Ocorreu um erro entre nossa conex�o com a database, tente novamente mais tarde.";
		case AUTHENTICATED:
			return args + " autenticado com sucesso.";
		case FIELDS_EMPTY:
			return "Existem campos vazios a serem preenchidos.";
		case INVALID_ARGUMENTS:
			return "Alguns campos cont�m informa��es inv�lidas.";
		case SUCCESSFULLY:
			return args + " cadastrado com sucesso.";
		case USERNAME_NOT_FOUND:
			return "Nome de usuario n�o foi encontrado.";
		case AUTHENTICATED_FAILED:
			return "Nome de usuario ou senha incorretos.";
		case NOT_EXISTS:
			return args + " n�o foi encontrado ou n�o existe.";
		case NOT_FOUND:
			return args + " n�o est� cadastrada em nossa base.";
		case EXISTS:
			return args + " j� existe nos registros.";
		case TICKET_UPDATED:
			return "O chamado " + args + " foi atualizado.";
		case SOLUTION_EMPTY:
			return "Soluc�o esta vazia.";
		case CHECKOUT_EMPTY:
			return "Lista de compras est� vazia.";
		case PURCHASE:
			return "Compra realizada com sucesso.";
		case INVALID:
			return args + " invalido.";
		case UPDATE:
			return args + " atualizado com sucesso.";
		case IMAGE_INVALID:
			return "� necess�rio colocar uma imagem.";
		case PERMISSION:
			return "Voc� n�o tem permiss�o para acessar essas informac�es.";
		default:
			break;
		}
		
		return null;
	}
}
