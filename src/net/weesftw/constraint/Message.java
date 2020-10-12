package net.weesftw.constraint;

public enum Message 
{
	ERROR, INVALID, SUCCESSFULLY, UPDATE, INVALID_ARGUMENTS, FIELDS_EMPTY, USERNAME_NOT_FOUND, AUTHENTICATED_FAILED, AUTHENTICATED, NOT_EXISTS,
	NOT_FOUND, EXISTS, TICKET_UPDATED, SOLUTION_EMPTY, CHECKOUT_EMPTY, PURCHASE;
	
	public String get(String args)
	{
		switch(this)
		{
		case ERROR:
			return "Ocorreu um erro entre nossa conexão com a database, tente novamente mais tarde.";
		case AUTHENTICATED:
			return args + " autenticado com sucesso.";
		case FIELDS_EMPTY:
			return "Existem campos vazios a serem preenchidos.";
		case INVALID_ARGUMENTS:
			return "Alguns campos contêm informações inválidas.";
		case SUCCESSFULLY:
			return args + " cadastrado com sucesso.";
		case USERNAME_NOT_FOUND:
			return "Nome de usuario não foi encontrado.";
		case AUTHENTICATED_FAILED:
			return "Nome de usuario ou senha incorretos.";
		case NOT_EXISTS:
			return args + " não foi encontrado ou não existe.";
		case NOT_FOUND:
			return args + " não está cadastrada em nossa base.";
		case EXISTS:
			return args + " já existe nos registros.";
		case TICKET_UPDATED:
			return "O chamado " + args + " foi atualizado.";
		case SOLUTION_EMPTY:
			return "Solucão esta vazia.";
		case CHECKOUT_EMPTY:
			return "Lista de compras está vazia.";
		case PURCHASE:
			return "Compra realizada com sucesso.";
		case INVALID:
			return args + " invalido.";
		case UPDATE:
			return args + " atualizado com sucesso.";
		default:
			break;
		}
		
		return null;
	}
}
