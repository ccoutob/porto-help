package org.example.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.example.banco.exception.IdNotFoundException;
import org.example.model.Apolice;

public class ApoliceDao {
    private Connection conexao;

    public ApoliceDao(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void cadastrarApolice(Apolice apolice) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = this.conexao.prepareStatement("INSERT INTO TB_APOLICE (cd_apolice, nr_numero, nm_seguradora, nr_valor, dt_inicio, dt_fim) values (SQ_TB_APOLICE.NEXTVAL, ?, ?, ?, ?, ?)");
        stm.setInt(1, apolice.getNumero());
        stm.setString(2, apolice.getSeguradora());
        stm.setDouble(3, apolice.getValor());
        stm.setTimestamp(4, Timestamp.valueOf(apolice.getDataInicio()));
        stm.setTimestamp(5, Timestamp.valueOf(apolice.getDataFim()));
        stm.executeUpdate();
    }

    
    private Apolice parse(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("cd_apolice");
        int numero = resultado.getInt("nr_numero");
        String seguradora = resultado.getString("nm_seguradora");
        double valor = resultado.getDouble("nr_valor");
        Timestamp dataInicio = resultado.getTimestamp("dt_inicio");
        Timestamp dataFim = resultado.getTimestamp("dt_fim");
        Apolice apolice = new Apolice(id, numero, seguradora, valor, dataInicio.toLocalDateTime(), dataFim.toLocalDateTime());
        return apolice;
    }
    
    public List<Apolice> listarApolice() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = this.conexao.prepareStatement("SELECT * FROM tb_apolice");
        ResultSet resultado = stm.executeQuery();
        List<Apolice> lista = new ArrayList();

        while (resultado.next()) {
            Apolice apolice = this.parse(resultado);
            lista.add(apolice);
        }

        return lista;
    }
    
    public Apolice pesquisaPorId(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        PreparedStatement stm = this.conexao.prepareStatement("select * from tb_apolice where cd_apolice = ?");
        stm.setInt(1, id);
        ResultSet resultado = stm.executeQuery();
        if (!resultado.next()) {
            throw new IdNotFoundException("Id de apolice não especificado");
        } else {
            Apolice apolice = this.parse(resultado);
            return apolice;
        }
    }

    public List<Apolice> pesquisaPorNome(String seguradora) throws SQLException {
        PreparedStatement stm = this.conexao.prepareStatement("select * from tb_apolice where nm_seguradora like ?");
        stm.setString(1, "%" + seguradora + "%");
        ResultSet resultado = stm.executeQuery();
        List<Apolice> lista = new ArrayList();

        while (resultado.next()) {
            Apolice apolice = this.parse(resultado);
            lista.add(apolice);
        }

        return lista;
    }
    
    public void atualizarApolice(Apolice apolice) throws ClassNotFoundException, SQLException, IdNotFoundException {
        PreparedStatement stm = this.conexao.prepareStatement("update tb_apolice set nr_numero = ?, nm_seguradora = ?, nr_valor = ?, dt_inicio = ?, dt_fim = ? where cd_apolice = ?");
        stm.setInt(1, apolice.getNumero());
        stm.setString(2, apolice.getSeguradora());
        stm.setDouble(3, apolice.getValor());
        stm.setTimestamp(4, Timestamp.valueOf(apolice.getDataInicio()));
        stm.setTimestamp(5, Timestamp.valueOf(apolice.getDataFim()));
        stm.setInt(6, apolice.getCodigo());
        int linha = stm.executeUpdate();
        if (linha == 0) {
            throw new IdNotFoundException("Apolice não encontrada para atualização");
        }
    }

    public void removerApolice(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        PreparedStatement stm = this.conexao.prepareStatement("delete from tb_apolice where cd_apolice = ?");
        stm.setInt(1, id);
        int linha = stm.executeUpdate();
        if (linha == 0) {
            throw new IdNotFoundException("Apolice não encontrada para remoção");
        }
    }
}