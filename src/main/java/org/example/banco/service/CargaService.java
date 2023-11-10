package org.example.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.example.banco.dao.CargaDao;
import org.example.banco.exception.BadInfoException;
import org.example.banco.exception.IdNotFoundException;
import org.example.banco.factory.ConnectionFactory;
import org.example.model.Carga;

public class CargaService {

private CargaDao cargaDao;
	
	public CargaService() throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		cargaDao = new CargaDao(conexao);
	}
	
	public void cadastrar(Carga carga) throws ClassNotFoundException, SQLException, BadInfoException {
		validar(carga);
		cargaDao.cadastrarCarga(carga);
	}

	private void validar(Carga carga) throws BadInfoException {
		
		if (carga.getDescricao() == null || carga.getDescricao().length() > 200) {
			throw new BadInfoException("Descricao invalida, nao pode ser nulo e no maximo 200 caracteres");
		}
		if (carga.getPeso() < 0) {
			throw new BadInfoException("A carga não pode ser nula");
		}
		
	}
	
	public void atualizar(Carga carga) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		validar(carga);
		cargaDao.atualizarCarga(carga);
	}
	
	public void remover(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException {
		cargaDao.removerCarga(codigo);
	}
	
	public List<Carga> listar() throws ClassNotFoundException, SQLException{
		return cargaDao.listarCarga();
	}
	
	public List<Carga> pesquisarPorNome(String nome) throws SQLException{
		return cargaDao.pesquisaPorNome(nome);
	}
	
	public Carga pesquisar(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException{
		Carga c = cargaDao.pesquisaPorId(codigo);
		return c;
	}
}
