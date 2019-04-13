package br.seb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.seb.dao.implementacao.ProfessorDAOIF;
import br.seb.entidades.Aluno;
import br.seb.entidades.Nota;
import br.seb.entidades.Professor;

public class ProfessorDAOBDR implements ProfessorDAOIF{

    private Connection conexao;

    public ProfessorDAOBDR() {
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/seb?uSETimezone=true&serverTimezone=UTC", "es1", "es1");
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Error: por favor, reinicie o sistema.");
        }
    }
    
    @Override
    public boolean cadastrar(Professor professor) {
    	if(verificaAntesDeCadastrarLoginSenha(professor.getMatriculaProfessor())){
	        String sql = "UPDATE professor SET login= ?, senha = ? WHERE matricula_professor = ?";
	
	        try {
	            PreparedStatement stmt = conexao.prepareStatement(sql);
	            stmt.setString(1, professor.getLogin());
	            stmt.setString(2, professor.getSenha());
	            stmt.setInt(3, professor.getMatriculaProfessor());
	            stmt.execute();
	            stmt.close();	
	            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");	         
	        }
	        catch (SQLException e) {
	        }
	        return true;
    	}else {
    		JOptionPane.showMessageDialog(null, "Matricula incorreta ou o cadastro ja existe no sistema! Prescione enter para voltar.");
    		return false;
    	}
    }

    private ArrayList<Professor> listar() {
        ArrayList<Professor> lista = new ArrayList<>();
        try {
            String sql = "SELECT login, senha, matricula_professor FROM professor";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                int matricula = rs.getInt("matricula_professor");
                Professor professores = new Professor(null, null, login, senha, matricula);
                lista.add(professores);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
        }
        return lista;
    }

    @Override
    public int login(String login, String senha) {
    	ArrayList<Professor> listaProfessor = new ArrayList<>(listar());
        for(Professor professorAtual: listaProfessor) {
        	try {
        		if (professorAtual.getLogin().equals(login) && professorAtual.getSenha().equals(senha)) {
        			return professorAtual.getMatriculaProfessor();
        		}
        	} catch (NullPointerException e){
        		return 0;
        	}
        }
        return 0;
    } 
    
    private boolean verificaAntesDeCadastrarLoginSenha(int matricula) {
    	ArrayList<Professor> listaProfessor = new ArrayList<>(listar());
        for(Professor professorAtual: listaProfessor) {
            try{
            	if (professorAtual.getMatriculaProfessor() == matricula && professorAtual.getLogin().isEmpty()) {
            		return true;
            	}
            }
        	catch (NullPointerException e) {
                return true;
        	}
        }
        return false;
    }
    
    @Override
 	public boolean inserirNota(int matriculaProfessor, int matriculaAluno, String opcao, double nota) {
    	int idNota = 0;
    	String sql = "SELECT n.id "
				+"FROM aluno a, nota n, disciplina d, aluno_tem_nota_disciplina atnd "
				+"WHERE '"+matriculaProfessor+"' = d.matricula_professorFK and atnd.matricula_alunoFK = '"+matriculaAluno+"'"
						+ "and matricula_aluno = '"+matriculaAluno+"' "
						+ "and  atnd.id_notaFK = n.id and atnd.codigo_disciplinaFK = d.codigo_disciplina";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				idNota = rs.getInt("n.id");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
		}  
		sql = " ";
		switch(opcao){
	        case "1":    	        	
	        	sql = "UPDATE nota SET bimestre1 = ? WHERE id = ?";   	        	
	        break;
	        case "2":    	        	
	        	sql = "UPDATE nota SET bimestre2 = ? WHERE id = ?";   	        	
	        break;
	        case "3":	    	        	
	        	sql = "UPDATE nota SET reavaliacao1 = ? WHERE id = ?";   	        	
	        break;
	        case "4":	    	        	
	        	sql = "UPDATE nota SET bimestre3 = ? WHERE id = ?";    	        	
	        break;
	        case "5":	    	        	
	        	sql = "UPDATE nota SET bimestre4 = ? WHERE id = ?";    	        	
	        break;
	        case "6":	    	        	
	        	sql = "UPDATE nota SET reavaliacao2 = ? WHERE id = ?";    	        	
	        break;
		}  
		if(sql.equals(" ")) {
			 JOptionPane.showMessageDialog(null, "Opcao invalida!");
			 return false;
		}else {
			if(idNota!= 0){
		 		try {
		 			PreparedStatement stmt = conexao.prepareStatement(sql);
		            stmt.setDouble(1, nota);
		            stmt.setInt(2, idNota);
		 			stmt.execute();
		 			stmt.close();
		 			JOptionPane.showMessageDialog(null, "Nota inserida com sucesso! Pressione a tecla enter para voltar.");
		 			return true;
		 		} catch (SQLException e) {
		 		}
			}
		}
		return false;
 	}

    @Override
    public ArrayList<Aluno> listarAlunos(int matriculaProfessor) {
        ArrayList<Aluno> lista = new ArrayList<>();
        try {
            String sql = "SELECT a.matricula_aluno, a.nome, n.bimestre1, n.bimestre2, n.bimestre3, n.bimestre4, "
            				+ "n.reavaliacao1, n.reavaliacao2 "
            			+ "FROM aluno a, nota n, disciplina d, aluno_tem_nota_disciplina atnd "
            			+ "WHERE '"+matriculaProfessor+"' = d.matricula_professorFK and atnd.matricula_alunoFK = a.matricula_aluno "
            				+ "and  atnd.id_notaFK = n.id and atnd.codigo_disciplinaFK = d.codigo_disciplina";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("a.nome");
                int matricula = rs.getInt("a.matricula_aluno");
                double bimestre1 = rs.getDouble("n.bimestre1");
                double bimestre2 = rs.getDouble("n.bimestre2");
                double bimestre3 = rs.getDouble("n.bimestre3");
                double bimestre4 = rs.getDouble("n.bimestre4");
                double reavaliacao1 = rs.getDouble("n.reavaliacao1");
                double reavaliacao2 = rs.getDouble("n.reavaliacao2");
                Aluno aluno = new Aluno(nome, null, null, null, matricula);
                Nota nota = new Nota(bimestre1, bimestre2, bimestre3, bimestre4, reavaliacao1, reavaliacao2);
                ArrayList<Nota> notas = new ArrayList<>();
                notas.add(nota);
                aluno.setNotas(notas);
                lista.add(aluno);
            }
            rs.close();
            stmt.close();          
        } catch (SQLException e) {
        }
        return lista;   
    }
}