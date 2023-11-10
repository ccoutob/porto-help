package org.example.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.banco.exception.IdNotFoundException;
import org.example.model.Carga;

public class CargaDao {

private Connection conexao;
	
	public CargaDao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void cadastrarCarga(Carga carga) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conexao.prepareStatement("INSERT INTO" + 
		" TB_CARGA (cd_carga, ds_carga, nr_peso) "
		+ "values (?, ?, ?)");

		stm.setInt(1, carga.getId());
		stm.setString(2, carga.getDescricao());
		stm.setDouble(3, carga.getPeso());

		stm.executeUpdate();
	}
	
	private Carga parse(ResultSet resultado) throws SQLException {

		int id = resultado.getInt("cd_carga");
		String descricao = resultado.getString("ds_carga");
		double peso = resultado.getDouble("nr_peso");

		Carga carga = new Carga(id, descricao, peso);
		return carga;
	}
	
	public List<Carga> listarCarga() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_carga");

		ResultSet resultado = stm.executeQuery();

		List<Carga> lista = new ArrayList<Carga>();

		while (resultado.next()) {
			Carga carga = parse(resultado);

			lista.add(carga);
		}
		return lista;
	}
	
	public Carga pesquisaPorId(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conexao.prepareStatement("select * from tb_carga where cd_carga = ?");

		stm.setInt(1, id);

		ResultSet resultado = stm.executeQuery();

		if (!resultado.next()) {

			throw new IdNotFoundException("Id de carga não especificado");
		}
		Carga carga = parse(resultado);

		return carga;
	}
	
	public List<Carga> pesquisaPorNome(String descricao) throws SQLException{

		PreparedStatement stm = conexao.prepareStatement("select * from tb_carga where ds_carga like ?");

		stm.setString(1, "%"+descricao+"%");

		ResultSet resultado = stm.executeQuery();

		List<Carga> lista = new ArrayList<>();

		while (resultado.next()) {
			Carga carga = parse(resultado);
			lista.add(carga);
		}
		return lista;
	}
	
	public void atualizarCarga(Carga carga) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conexao.prepareStatement("update tb_carga set "
				+ "ds_carga = ?, nr_peso = ? where cd_carga = ?");

		stm.setString(1, carga.getDescricao());
		stm.setDouble(2, carga.getPeso());
		stm.setInt(3, carga.getId());

		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new IdNotFoundException("Carga não encontrada para atualização");
	}
	
	public void removerCarga(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

			PreparedStatement stm = conexao.prepareStatement("delete from tb_carga where cd_carga = ?");

			stm.setInt(1, id);

			int linha = stm.executeUpdate();
			
			if (linha == 0)
				throw new IdNotFoundException("Carga não encontrada para remoção");
		}
}
