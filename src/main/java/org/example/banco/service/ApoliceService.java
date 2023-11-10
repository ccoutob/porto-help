package org.example.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.example.banco.dao.ApoliceDao;
import org.example.banco.exception.BadInfoException;
import org.example.banco.exception.IdNotFoundException;
import org.example.banco.factory.ConnectionFactory;

import org.example.model.Apolice;


public class ApoliceService {

private ApoliceDao apoliceDao;
	
	public ApoliceService() throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		apoliceDao = new ApoliceDao(conexao);
	}
	
	public void cadastrar(Apolice apolice) throws ClassNotFoundException, SQLException, BadInfoException {
		validar(apolice);
		apoliceDao.cadastrarApolice(apolice);
	}

	private void validar(Apolice apolice) throws BadInfoException {

		if (apolice.getNumero() < 0) {
			throw new BadInfoException("Numero invalido, deve ser positivo");
		}

		if (apolice.getSeguradora() == null || apolice.getSeguradora().length() > 100) {
			throw new BadInfoException("Seguradora invalida, nao pode ser nulo e no maximo 100 caracteres");
		}
		if (apolice.getValor() < 0) {
			throw new BadInfoException("O valor precisa ser especificado");
		}
		if (apolice.getDataInicio() == null) {
			throw new BadInfoException("A data inicial deve ser informada");
		}
		
		if (apolice.getDataFim() == null) {
			throw new BadInfoException("A data final deve ser informada");
		}
	}
	
	public void atualizar(Apolice apolice) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		validar(apolice);
		apoliceDao.atualizarApolice(apolice);
	}
	
	public void remover(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException {
		apoliceDao.removerApolice(codigo);
	}
	
	public List<Apolice> listar() throws ClassNotFoundException, SQLException{
		return apoliceDao.listarApolice();
	}
	
	public List<Apolice> pesquisarPorNome(String nome) throws SQLException{
		return apoliceDao.pesquisaPorNome(nome);
	}
	
	public Apolice pesquisar(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException{
		Apolice a = apoliceDao.pesquisaPorId(codigo);
		
		return a;
	}
}
