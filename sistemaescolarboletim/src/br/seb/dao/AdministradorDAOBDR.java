package br.seb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.seb.dao.implementacao.AdministradorDAOIF;
import br.seb.entidades.Aluno;
import br.seb.entidades.Disciplina;
import br.seb.entidades.Professor;

public class AdministradorDAOBDR implements AdministradorDAOIF{

    private Connection conexao;

    public AdministradorDAOBDR() {
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/seb?useTimezone=true&serverTimezone=UTC", "es1", "es1");
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Error: por favor, reinicie o sistema.");
        }
    }
   
    @Override
    public boolean loginAdministrador(String loginDigitado, String senhaDigitada) { 
    	String loginAdministrador = "";
    	String senhaAdministrador = "";
        try {
            String sql = "SELECT login, senha "
            		+ "FROM administrador";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            loginAdministrador = rs.getString("login");
            senhaAdministrador = rs.getString("senha");  
            rs.close();
            stmt.close();
        } catch (SQLException e) {
        }   
        if (loginAdministrador.equals(loginDigitado) && senhaAdministrador.equals(senhaDigitada)) {
            return true;
        } else {
        	return false;
        }
    }
    
    @Override
	public boolean inserirAluno(Aluno aluno) {
		String sql = "INSERT INTO aluno (nome, data_nascimento) VALUES (?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getDataNascimento());
			stmt.execute();
			stmt.close();
			JOptionPane.showMessageDialog(null, "Aluno(a) cadastrado(a) com sucesso! Pressione a tecla enter para voltar.");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: nao foi possivel cadastrar o(a) aluno(a).");
			return false;
		}
	}

	@Override
	public boolean verificarAluno(int matricula) {
		ArrayList<Aluno> listaAluno = new ArrayList<>(listarAlunosParaConsulta());
		for (Aluno alunoAtual : listaAluno) {
			if (matricula == alunoAtual.getMatriculaAluno()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deletarAluno(int matriculaAluno) {
		if (verificarAluno(matriculaAluno)) {

			String sql = "DELETE FROM aluno WHERE matricula_aluno = ?";

			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, matriculaAluno);
				stmt.executeUpdate();
				stmt.close();
				JOptionPane.showMessageDialog(null, "Aluno deletado!");
			} catch (SQLException e) {
			}
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "A matricula informada nao consta no sistema!");
			return false;
		}
	}
	
	@Override
    public ArrayList<Aluno> listarAlunosParaConsulta() {
        ArrayList<Aluno> lista = new ArrayList<>();
        try {
            String sql = "SELECT nome, data_nascimento, matricula_aluno FROM aluno";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String dataNascimento = rs.getString("data_nascimento");
                int matricula = rs.getInt("matricula_aluno");
                Aluno aluno = new Aluno(nome, dataNascimento, matricula);
                lista.add(aluno);
            }
            rs.close();
            stmt.close();          
        } catch (SQLException e) {
        }
        return lista;
    } 
	
	@Override
	public ArrayList<Aluno> listarAlunos() {
		ArrayList<Aluno> lista = new ArrayList<>();
		ArrayList<Disciplina> disciplinas = new ArrayList<>();
		try {
			String sql = "SELECT a.matricula_aluno, a.nome, a.data_nascimento, d.nome, d.codigo_disciplina, acd.codigo_disciplinaFK, acd.matricula_alunoFK "
						+ "FROM aluno a, disciplina d, aluno_cursa_disciplina acd "
						+ "WHERE a.matricula_aluno = acd.matricula_alunoFK and d.codigo_disciplina = acd.codigo_disciplinaFK";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int matriculaAnterior = 0;	
			Aluno aluno = null;	
			Disciplina disciplina = null;
			while (rs.next()) {
				String nomeAluno = rs.getString("a.nome");
				String dataNascimento = rs.getString("a.data_nascimento");
				int matricula = rs.getInt("a.matricula_aluno");
				String nomeDisciplina = rs.getString("d.nome");
				disciplina = new Disciplina(nomeDisciplina);
				if(matriculaAnterior == matricula || matriculaAnterior == 0) {
					disciplinas.add(disciplina);
					aluno = new Aluno(nomeAluno, dataNascimento, null, null, matricula);
				}else {
					aluno.setDisciplinas(disciplinas);
					lista.add(aluno);
					disciplinas = new ArrayList<>();
					aluno = new Aluno(nomeAluno, dataNascimento, null, null, matricula);
					disciplina = new Disciplina(nomeDisciplina);
					disciplinas.add(disciplina);
				}			
				matriculaAnterior = matricula;				
			}
			aluno.setDisciplinas(disciplinas);
			lista.add(aluno);
			rs.close();
			stmt.close();
		} catch (SQLException e) {
		}
		return lista;
	}
	
	@Override
	public boolean verificarCodigoDisciplinaRelacionadoMatriculaProfessor(int codigoDisciplina, int matriculaProfessor){
		String sql = "SELECT p.matricula_professor "
				+"FROM professor p, disciplina d "
				+"WHERE '"+codigoDisciplina+"' = '"+matriculaProfessor+"'";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					rs.close();
					stmt.close();
					return true;
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
			}
			return false;
	}

	@Override
	public boolean adicionarDisciplinaCursadaPorAluno(int matriculaAluno, int codigoDisciplina, int matriculaProfessor) {
		if (verificarAluno(matriculaAluno) && verificarDisciplina(codigoDisciplina)
				&& verificarCodigoDisciplinaRelacionadoMatriculaProfessor(codigoDisciplina, matriculaProfessor)) {
			String sql = "INSERT INTO aluno_cursa_disciplina (matricula_alunoFK, codigo_disciplinaFK) VALUES (?,?)";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, matriculaAluno);
				stmt.setInt(2, codigoDisciplina);
				stmt.execute();
				stmt.close();
				JOptionPane.showMessageDialog(null, "Aluno(a) atualizado(a) com sucesso! Pressione a tecla Enter para voltar.");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro: aluno(a) ja esta cadastrado na disciplina.");
				return false;
			}
			
			sql = "INSERT INTO nota (matricula_professorFK) VALUES ('"+matriculaProfessor+"')";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
			}
			
			int idNota = 0;
			sql = "SELECT n.id "
				+"FROM nota n";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					idNota = rs.getInt("n.id");
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
			}
			
			sql = "INSERT INTO aluno_tem_nota_disciplina (matricula_alunoFK,codigo_disciplinaFK,id_notaFK) VALUES (?,?,?)";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, matriculaAluno);
				stmt.setInt(2, codigoDisciplina);
				stmt.setInt(3, idNota);
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
			}		
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Erro: matricula do aluno(a) ou disciplina incorretas.");
			return false;
		}
	}
	
	@Override
	public boolean inserirProfessor(Professor professor) {
        String sql = "INSERT INTO professor (nome, data_nascimento) VALUES (?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getDataNascimento());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Professor(a) cadastrado(a) com sucesso! Pressione a tecla Enter para voltar.");
            return true;
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erro: nao foi possivel cadastrar o(a) Professor(a).");
        	return false;
        }
    }
    
    @Override
    public boolean verificarProfessor(int matricula) {
    	 ArrayList<Professor> listaProfessor = new ArrayList<>(listarProfessores());
         for(Professor professorAtual: listaProfessor) {
            if (matricula == professorAtual.getMatriculaProfessor()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean deletarProfessor(int matriculaProfessor) {
    	if(verificarProfessor(matriculaProfessor)){    		
	        String sql = "DELETE FROM professor WHERE matricula_professor = ?";	
	        try {
	            PreparedStatement stmt = conexao.prepareStatement(sql);
	            stmt.setInt(1, matriculaProfessor);
	            stmt.executeUpdate();
	            stmt.close();	
	            JOptionPane.showMessageDialog(null, "Professor deletado!");
	        } catch (SQLException e) {
	        }
	        return true;
    	}else {
    		JOptionPane.showMessageDialog(null, "A matricula informada nao consta no sistema!");
    		return false;
    	}
    }
    
    @Override
    public ArrayList<Professor> listarProfessores() {
        ArrayList<Professor> lista = new ArrayList<>();
        try {
            String sql = "SELECT nome, data_nascimento, matricula_professor FROM professor";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String dataNascimento = rs.getString("data_nascimento");
                int matricula = rs.getInt("matricula_professor");
                Professor professor = new Professor(nome, dataNascimento, null, null, matricula);
                lista.add(professor);
            }
            rs.close();
            stmt.close();            
        } catch (SQLException e) {
        }
        return lista;
    }
    
    public boolean verificaMatriculaProfessorNaDisciplina(int matriculaProfessor) {
		ArrayList<Disciplina> listaDisciplina = new ArrayList<>(listarDisciplinas());
		for (Disciplina disciplinaoAtual : listaDisciplina) {
			if (matriculaProfessor == disciplinaoAtual.getProfessor().getMatriculaProfessor()) {
				return true;
			}
		}
		return false;
	}
    
	@Override
	public boolean inserirDisciplina(Disciplina disciplina) {
		if(!verificaMatriculaProfessorNaDisciplina(disciplina.getProfessor().getMatriculaProfessor())) {
			String sql = "INSERT INTO disciplina (matricula_professorFK, nome, carga_horaria) VALUES (?,?,?)";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, disciplina.getProfessor().getMatriculaProfessor());
				stmt.setString(2, disciplina.getNome());
				stmt.setInt(3, disciplina.getCargaHoraria());
				stmt.execute();
				stmt.close();
				JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso! Pressione a tecla Enter para voltar.");
			} catch (SQLException e) {
			}
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Erro: professor informado ja esta relacionado a outra disciplina.");
			return false;
		}		
	}

	@Override
	public ArrayList<Disciplina> listarDisciplinas() {
		ArrayList<Disciplina> lista = new ArrayList<>();
		try {
			String sql = "SELECT codigo_disciplina, matricula_professorFK, carga_horaria, nome FROM disciplina";

			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int codigo = rs.getInt("codigo_disciplina");
				int matricula_professorFK = rs.getInt("matricula_professorFK");
				String nome = rs.getString("nome");
				int cargaHoraria = rs.getInt("carga_horaria");
				Professor professor = new Professor(null, null, null, null, matricula_professorFK);
				Disciplina disciplina = new Disciplina(codigo, nome, cargaHoraria, professor);
				lista.add(disciplina);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
		}		
		return lista;
	}

	@Override
	public boolean verificarDisciplina(int codigoDisciplina) {
		ArrayList<Disciplina> listaDisciplina = new ArrayList<>(listarDisciplinas());
		for (Disciplina disciplinaoAtual : listaDisciplina) {
			if (codigoDisciplina == disciplinaoAtual.getCodigoDisciplina()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deletarDisciplina(int codigoDisciplina, int matriculaProfessor) {
		if (verificarDisciplina(codigoDisciplina)) {
			String sql = "DELETE FROM disciplina WHERE codigo_disciplina = ?";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, codigoDisciplina);
				stmt.executeUpdate();
				stmt.close();
				JOptionPane.showMessageDialog(null, "Disciplina deletada!");
			} catch (SQLException e) {
			}			
			sql = "DELETE FROM nota WHERE matricula_professorFK = ?";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, matriculaProfessor);
				stmt.executeUpdate();
				stmt.close();
			} catch (SQLException e) {
			}	
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "O codigo informada nao consta no sistema!");
			return false;
		}
	}
}