package org.example.banco.teste;

import java.sql.Connection;
import java.time.LocalDate;

import org.example.banco.dao.ApoliceDao;
import org.example.banco.factory.ConnectionFactory;
import org.example.model.Apolice;

public class TesteApolice {{

	try {
		Apolice apolice = new Apolice(2, 155, "Haha", 20.5, LocalDate.of(2022, 11, 6), LocalDate.of(2023, 11, 6));
			
		Connection conexao = null;
		
		conexao = ConnectionFactory.getConnection();
		
		ApoliceDao dao = new ApoliceDao(conexao);
		
		dao.cadastrarApolice(apolice);
		
		System.out.println("Empresa Cadastrada!");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
}}
