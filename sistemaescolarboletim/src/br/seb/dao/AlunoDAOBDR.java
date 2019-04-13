package br.seb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.seb.dao.implementacao.AlunoDAOIF;
import br.seb.entidades.Aluno;
import br.seb.entidades.Disciplina;
import br.seb.entidades.Nota;

public class AlunoDAOBDR implements AlunoDAOIF{

    private Connection conexao;

    public AlunoDAOBDR() {
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/seb?useTimezone=true&serverTimezone=UTC", "es1", "es1");
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Error: por favor, reinicie o sistema.");
        }
    }
    
    @Override
    public boolean cadastrar(Aluno aluno) {
    	if(verificaAntesDeCadastrarLoginSenha(aluno.getMatriculaAluno())){
	    	String sql = "update aluno set login= ?, senha = ? where matricula_aluno = ?";
	
	        try {
	            PreparedStatement stmt = conexao.prepareStatement(sql);
	            stmt.setString(1, aluno.getLogin());
	            stmt.setString(2, aluno.getSenha());
	            stmt.setInt(3, aluno.getMatriculaAluno());
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

    private ArrayList<Aluno> listar() {
        ArrayList<Aluno> lista = new ArrayList<>();
        try {
            String sql = "select login, senha, matricula_aluno from aluno";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                int matricula = rs.getInt("matricula_aluno");
                Aluno aluno = new Aluno(null, null, login, senha, matricula);
                lista.add(aluno);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
        }   
        return lista;
    }

    @Override
    public int login(String login, String senha) {
    	ArrayList<Aluno> listaAluno = new ArrayList<>(listar());
        for(Aluno alunoAtual: listaAluno) {
        	try {
	        	if(alunoAtual.getLogin().equals(login) && alunoAtual.getSenha().equals(senha)) {
	        		return alunoAtual.getMatriculaAluno();
	        	}
        	} catch(NullPointerException e) {
        		return 0;
            }
        }
        return 0;
    }
    
    private boolean verificaAntesDeCadastrarLoginSenha(int matricula) {
    	ArrayList<Aluno> listaAluno = new ArrayList<>(listar());
        for(Aluno alunoAtual: listaAluno) {
            try{
            	if (alunoAtual.getMatriculaAluno() == matricula && alunoAtual.getLogin().isEmpty()) {}
            }
        	catch (NullPointerException e) {
                return true;
        	}
        }
        return false;
    }
    
    @Override
    public ArrayList<Aluno> listarNotasAluno(int matriculaAluno) {
        ArrayList<Aluno> lista = new ArrayList<>();
        ArrayList<Nota> notas = new ArrayList<>();   
        ArrayList<Disciplina> disciplinas = new ArrayList<>();   
        try {
            String sql = "SELECT d.nome, n.bimestre1, n.bimestre2, n.bimestre3, n.bimestre4, "
            				+ "n.reavaliacao1, n.reavaliacao2 "
            			+ "FROM aluno a, nota n, disciplina d, aluno_tem_nota_disciplina atnd "
            			+ "WHERE atnd.matricula_alunoFK = '"+matriculaAluno+"' and matricula_aluno = '"+matriculaAluno+"' "
            				+ "and  atnd.id_notaFK = n.id and atnd.codigo_disciplinaFK = d.codigo_disciplina";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Aluno aluno = null;
            Nota nota = null;
            Disciplina disciplina = null;
            while (rs.next()) {
                String nome = rs.getString("d.nome");
                double bimestre1 = rs.getDouble("n.bimestre1");
                double bimestre2 = rs.getDouble("n.bimestre2");
                double bimestre3 = rs.getDouble("n.bimestre3");
                double bimestre4 = rs.getDouble("n.bimestre4");
                double reavaliacao1 = rs.getDouble("n.reavaliacao1");
                double reavaliacao2 = rs.getDouble("n.reavaliacao2");         
                disciplina = new Disciplina();
                disciplina.setNome(nome);
                disciplinas.add(disciplina);
                nota = new Nota(bimestre1, bimestre2, bimestre3, bimestre4, reavaliacao1, reavaliacao2);
                notas.add(nota);
                aluno = new Aluno(nome, null, null, null, 0);
                aluno.setNotas(notas);
                aluno.setDisciplinas(disciplinas);
                lista.add(aluno);
                notas = new ArrayList<Nota>();
                disciplinas = new ArrayList<Disciplina>();
            }     
            rs.close();
            stmt.close();        
        } catch (SQLException e) {
        }
        return lista;
    }
}