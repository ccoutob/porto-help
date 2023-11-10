package org.example.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.example.banco.dao.VeiculoDao;
import org.example.banco.exception.BadInfoException;
import org.example.banco.exception.IdNotFoundException;
import org.example.banco.factory.ConnectionFactory;
import org.example.model.Veiculo;

public class VeiculoService {

	private VeiculoDao veiculoDao;
	
	public VeiculoService() throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		veiculoDao = new VeiculoDao(conexao);
	}
	
	public void cadastrar(Veiculo veiculo) throws ClassNotFoundException, SQLException, BadInfoException {
		validar(veiculo);
		veiculoDao.cadastrarVeiculo(veiculo);
	}

	private void validar(Veiculo veiculo) throws BadInfoException {

		if (veiculo.getModelo() == null || veiculo.getModelo().length() > 50) {
			throw new BadInfoException("Modelo invalido, nao pode ser nulo e no maximo 50 caracteres");
		}

		if (veiculo.getMarca() == null || veiculo.getMarca().length() > 50) {
			throw new BadInfoException("Marca invalida, nao pode ser nulo e no maximo 50 caracteres");
		}
	}
	
	public void atualizar(Veiculo veiculo) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
		validar(veiculo);
		veiculoDao.atualizarVeiculo(veiculo);
	}
	
	public void remover(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException {
		veiculoDao.removerVeiculo(codigo);
	}
	
	public List<Veiculo> listar() throws ClassNotFoundException, SQLException{
		return veiculoDao.listarVeiculo();
	}
	
	public List<Veiculo> pesquisarPorNome(String nome) throws SQLException{
		return veiculoDao.pesquisaPorNome(nome);
	}
	
	public Veiculo pesquisar(int codigo) throws ClassNotFoundException, SQLException, IdNotFoundException{
		Veiculo v = veiculoDao.pesquisaPorId(codigo);

		return v;
	}
}
