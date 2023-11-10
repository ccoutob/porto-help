package org.example.banco.teste;

import java.sql.Connection;

import org.example.banco.dao.VeiculoDao;
import org.example.banco.factory.ConnectionFactory;
import org.example.model.Veiculo;

public class TesteVeiculo {{
	
	try {
		Connection conexao = null;
		conexao = ConnectionFactory.getConnection();
		
		Veiculo veiculo = new Veiculo(1, "Fiat", "Uno", 1984);
		
		VeiculoDao dao = new VeiculoDao(conexao);
		
		dao.cadastrarVeiculo(veiculo);
		
		System.out.println("Cadastrou");
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
