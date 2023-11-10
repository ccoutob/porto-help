package org.example.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.banco.exception.IdNotFoundException;
import org.example.model.Veiculo;


public class VeiculoDao {

private Connection conexao;
	
	public VeiculoDao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void cadastrarVeiculo(Veiculo veiculo) throws ClassNotFoundException, SQLException {
	    PreparedStatement stm = this.conexao.prepareStatement("INSERT INTO TB_VEICULO (cd_veiculo, nm_marca, nm_modelo, nr_ano) values (?, ?, ?, ?)");
	    stm.setInt(1, veiculo.getId());
	    stm.setString(2, veiculo.getMarca());
	    stm.setString(3, veiculo.getModelo());
	    stm.setInt(4, veiculo.getAno());
	    stm.executeUpdate();
	}
	
	private Veiculo parse(ResultSet resultado) throws SQLException {

		int id = resultado.getInt("cd_veiculo");
		String marca = resultado.getString("nm_marca");
		String modelo = resultado.getString("nm_modelo");
		int ano = resultado.getInt("nr_ano");

		Veiculo veiculo = new Veiculo(id, marca, modelo, ano);
		return veiculo;
	}
	
	public List<Veiculo> listarVeiculo() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_veiculo");

		ResultSet resultado = stm.executeQuery();

		List<Veiculo> lista = new ArrayList<Veiculo>();

		while (resultado.next()) {
			Veiculo veiculo = parse(resultado);

			lista.add(veiculo);
		}

		return lista;
	}
	
	public Veiculo pesquisaPorId(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conexao.prepareStatement("select * from" + " tb_veiculo where cd_veiculo = ?");

		stm.setInt(1, id);

		ResultSet resultado = stm.executeQuery();

		if (!resultado.next()) {

			throw new IdNotFoundException("Veiculo não encontrado");
		}
		Veiculo veiculo = parse(resultado);

		return veiculo;
	}
	
	public List<Veiculo> pesquisaPorNome(String modelo) throws SQLException{

		PreparedStatement stm = conexao.prepareStatement("select * from tb_veiculo where nm_modelo like ?");

		stm.setString(1, "%"+modelo+"%");

		ResultSet resultado = stm.executeQuery();

		List<Veiculo> lista = new ArrayList<>();

		while (resultado.next()) {
			Veiculo veiculo = parse(resultado);
			lista.add(veiculo);
		}

		return lista;
	}
	
	public void atualizarVeiculo(Veiculo veiculo) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conexao.prepareStatement("update tb_veiculo set "
				+ "nm_modelo = ?, nm_marca = ?, nr_ano = ? where cd_veiculo = ?");

		stm.setString(1, veiculo.getModelo());
		stm.setString(2, veiculo.getMarca());
		stm.setInt(3, veiculo.getAno());
		stm.setInt(4, veiculo.getId());

		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Veiculo não encontrado para atualizar");
	}
	
	public void removerVeiculo(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

			PreparedStatement stm = conexao.prepareStatement("delete from tb_veiculo where cd_veiculo = ?");

			stm.setInt(1, id);

			int linha = stm.executeUpdate();
			
			if (linha == 0)
				throw new IdNotFoundException("Veiculo não encontrado para remoção");
		}
}
