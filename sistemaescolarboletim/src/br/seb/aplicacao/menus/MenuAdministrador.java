package br.seb.aplicacao.menus;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.seb.dao.AdministradorDAOBDR;
import br.seb.entidades.Aluno;
import br.seb.entidades.Disciplina;
import br.seb.entidades.Professor;

public class MenuAdministrador{

	public void login(){				
        String opcao = JOptionPane.showInputDialog("Administrador escolha uma opcao: \n" 
        		+ "1 - Entrar\n"
        		+ "2 - Voltar\n" 
        		+ "3 - Sair\n");      
        switch(opcao){
        	case "1":       		
                String login = JOptionPane.showInputDialog("Login: ");
                String senha = JOptionPane.showInputDialog("Senha: "); 
                AdministradorDAOBDR administradorDAOBDR = new AdministradorDAOBDR();
                if(administradorDAOBDR.loginAdministrador(login, senha)){               	
                	JOptionPane.showMessageDialog(null, "Bem-vindo administrador! Pressione a teclar enter para continuar!");
                    logado();                    
                }else{              	                  
                    opcao = JOptionPane.showInputDialog("Login incorreto!\n"
                    		+ "1 - Tentar de novo\n"
                    		+ "2 - Voltar\n"
                    		+ "3 - Sair\n");                
                    switch(opcao){
                    	case "1":
                    		login();
                    	break;
                    	case "2":
                    		MenuPrincipal menuPrincipal = new MenuPrincipal();
                    		menuPrincipal.menuPrincipal();
                    	break;
                    	case "3":
                    		System.exit(0);
                    	break;
                    }
                }                
        	break;
        	case "2":
        		MenuPrincipal menuPrincipal = new MenuPrincipal();
        		menuPrincipal.menuPrincipal();
        	break;
        	case "3":   		
        		System.exit(0);
        	break;
        }
        JOptionPane.showMessageDialog(null, "Opcao invalida! Pressione enter para tentar novamente!");      
        login();    
    }
	
	private void logado(){	
        String opcao = JOptionPane.showInputDialog("Administrador escolha uma opcao: \n"
        		+ "1 - Gerenciar alunos(as)\n"
        		+ "2 - Gerenciar professores(as)\n"
        		+ "3 - Gerenciar disciplinas\n" 
        		+ "4 - Sair\n");
    	switch(opcao){
    		case "1":
    			gerenciarAlunos();  			
    		break;
    		case "2":
    			gerenciarProfessores();  			
    		break;
    		case "3":
    			gerenciarDisciplinas();  			
    		break;
	        case "4":    		
	    		System.exit(0);
	    	break;
    	}
    	JOptionPane.showMessageDialog(null, "Opcao invalida! Pressione enter para tentar novamente!");      
        logado();        
    }
	
	private void gerenciarAlunos(){
		String opcao = JOptionPane.showInputDialog("Escolha uma opcao: \n"
                + "1 - Cadastrar aluno(a)\n" 
    			+ "2 - Listar alunos(as)\n"
    			+ "3 - Deletar aluno(a)\n"
    			+ "4 - Inserir disciplinas que o(a) aluno(a) cursa\n"
    			+ "5 - Voltar\n"
    			+ "6 - Sair\n");
    	switch(opcao){
	        case "1":        	
	            String nome = JOptionPane.showInputDialog("Nome do aluno: ");
	            String dataNascimento = JOptionPane.showInputDialog("Data de nascimento: ");
	            Aluno aluno = new Aluno(nome, dataNascimento, null, null, 0);
	            AdministradorDAOBDR administradorDAOBDR = new AdministradorDAOBDR();
	            administradorDAOBDR.inserirAluno(aluno);            
	            gerenciarAlunos();
	        break;
	        case "2":	        	
	        	administradorDAOBDR = new AdministradorDAOBDR();
	        	ArrayList<Aluno> listaAluno = new ArrayList<>(administradorDAOBDR.listarAlunosParaConsulta());
	        	if(listaAluno.size() > 0) {
	        		JOptionPane.showMessageDialog(null, "Todos alunos: \n"+listaAluno); 		
	        		try {
	        			listaAluno = new ArrayList<>(administradorDAOBDR.listarAlunos());
		        		if(listaAluno.size() > 0) {
			        		JOptionPane.showMessageDialog(null, "Alunos ja com disciplinas cadastradas: \n"+ listaAluno);        				
			        		gerenciarAlunos();
		        		}
					} catch (java.lang.NullPointerException e) {
						gerenciarAlunos();
	        		}
	        	}else {
	        		JOptionPane.showMessageDialog(null, "Nao ha alunos(as) cadastrados(as) no momento!");        		
	        		gerenciarAlunos();
	        	}
	        break;
	        case "3":     
	        	int matriculaAluno = 0;
	        	try {
	        		matriculaAluno = Integer.parseInt(JOptionPane.showInputDialog("Matricula do aluno: "));
				} catch (java.lang.NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Matricula invalida!");
					gerenciarAlunos();
				}
	            administradorDAOBDR = new AdministradorDAOBDR();
	            administradorDAOBDR.deletarAluno(matriculaAluno);	            
	            gerenciarAlunos();
	        break;
	        case "4":	     
	        	int codigoDisciplina = 0;
	        	matriculaAluno = 0;
	        	int matriculaProfessor = 0;
	        	try {
	        		codigoDisciplina = Integer.parseInt(JOptionPane.showInputDialog("Codigo da disciplina: "));
		            matriculaAluno = Integer.parseInt(JOptionPane.showInputDialog("Matricula do aluno: "));
		            matriculaProfessor = Integer.parseInt(JOptionPane.showInputDialog("Matricula do professor que ministra a disciplina: "));
				} catch (java.lang.NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Entrada incorreta, digite valores validos!");
					gerenciarAlunos();
				}
	            administradorDAOBDR = new AdministradorDAOBDR();
	            administradorDAOBDR.adicionarDisciplinaCursadaPorAluno(matriculaAluno, codigoDisciplina, matriculaProfessor);
	            gerenciarAlunos();
	        break;
	        case "5":
	        	logado();
	        break;
	        case "6":	        	
	        	System.exit(0);
	        break;
    	}
    	JOptionPane.showMessageDialog(null, "Opcao invalida! Pressione enter para tentar novamente!");      
        gerenciarAlunos();      
	}
	
	private void gerenciarProfessores(){	
		String opcao = JOptionPane.showInputDialog("Escolha uma opcao: \n"
                + "1 - Cadastrar professor(a)\n" 
    			+ "2 - Listar professor(as)\n"
    			+ "3 - Deletar professor(a)\n"
    			+ "4 - Voltar\n"
    			+ "5 - Sair\n");
    	switch(opcao){
	    	case "1": 	        	
	            String nome = JOptionPane.showInputDialog("Nome do professor: ");
	            String dataNascimento = JOptionPane.showInputDialog("Data de nascimento: ");
	            Professor professor = new Professor(nome, dataNascimento, null, null, 0);
	            AdministradorDAOBDR administradorDAOBDR = new AdministradorDAOBDR();
	            administradorDAOBDR.inserirProfessor(professor);            
	            gerenciarProfessores();
	        break;
	    	case "2":	        	
	        	administradorDAOBDR = new AdministradorDAOBDR();
	        	ArrayList<Professor> listaProfessor = new ArrayList<>(administradorDAOBDR.listarProfessores());
	        	if(listaProfessor.size() > 0) {
	        		JOptionPane.showMessageDialog(null, "Todos professores cadastrados:\n"+listaProfessor);        			        		
	        		gerenciarProfessores();	        		
	        	}else {
	        		JOptionPane.showMessageDialog(null, "Nao ha professore(as) cadastrados(as) no momento!");	        		
	        		gerenciarProfessores();
	        	}
	        break;
	    	case "3":
	    		int matriculaProfessor = 0;
	        	try {
		            matriculaProfessor = Integer.parseInt(JOptionPane.showInputDialog("Matricula do professor: "));
				} catch (java.lang.NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Entrada incorreta, digite uma matricula valida!");
					gerenciarProfessores();
				}
	            administradorDAOBDR = new AdministradorDAOBDR();
	            administradorDAOBDR.deletarProfessor(matriculaProfessor);            
	            gerenciarProfessores();
	        break;
	    	case "4":
	        	logado();
	        break;
	        case "5":        	
	        	System.exit(0);
	        break;
    	}
    	JOptionPane.showMessageDialog(null, "Opcao invalida! Pressione enter para tentar novamente!");      
        gerenciarProfessores();       
	}
	
	private void gerenciarDisciplinas(){	
		String opcao = JOptionPane.showInputDialog("Escolha uma opcao: \n"
                + "1 - Cadastrar disciplina\n" 
    			+ "2 - Listar disciplinas\n"
    			+ "3 - Deletar disciplina\n"
    			+ "4 - Voltar\n"
    			+ "5 - Sair\n");
    	switch(opcao){
    	case "1":    
    		int cargaHoraria = 0;
    		int matriculaProfessor = 0;
    		String nome = "";
        	try {
        		nome = JOptionPane.showInputDialog("Nome da disciplina: ");
                cargaHoraria = Integer.parseInt(JOptionPane.showInputDialog("Carga horaria da disciplina : "));
                matriculaProfessor = Integer.parseInt(JOptionPane.showInputDialog("Matricula do professor que ministra a disciplina: "));
			} catch (java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Entrada(s) incorreta(s), digite valores validos!");
				gerenciarDisciplinas();
			}
        	AdministradorDAOBDR administradorDAOBDR = new AdministradorDAOBDR();
            if(administradorDAOBDR.verificarProfessor(matriculaProfessor)) {
            	Professor professor = new Professor(null,null,null,null,matriculaProfessor);
            	Disciplina disciplina = new Disciplina(0, nome, cargaHoraria, professor);
            	administradorDAOBDR.inserirDisciplina(disciplina);            
	            gerenciarDisciplinas();
            } else {
            	JOptionPane.showMessageDialog(null, "A matricula digitada nao consta no sistema!");          	
            	gerenciarDisciplinas();
            }
    	break;
    	case "2":      	
        	administradorDAOBDR = new AdministradorDAOBDR();
        	ArrayList<Disciplina> listaDisciplina = new ArrayList<>(administradorDAOBDR.listarDisciplinas());
        	if(listaDisciplina.size() > 0) {
        		JOptionPane.showMessageDialog(null, "Disciplinas cadastradas: \n"+listaDisciplina);     		       		
        		gerenciarDisciplinas();       		
        	}else {
        		JOptionPane.showMessageDialog(null, "Nao ha disciplinas cadastradas no momento!");      		
        		gerenciarDisciplinas();
        	}
    	break;
        case "3": 
        	int codigoDisciplina = 0;
        	matriculaProfessor = 0;
        	try {
                codigoDisciplina = Integer.parseInt(JOptionPane.showInputDialog("Codigo da disciplina: "));
                matriculaProfessor = Integer.parseInt(JOptionPane.showInputDialog("Matricula do professor que leciona a disciplina: "));
			} catch (java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Entrada incorreta, digite valores validos!");
				gerenciarDisciplinas();
			}
            administradorDAOBDR = new AdministradorDAOBDR();
            administradorDAOBDR.deletarDisciplina(codigoDisciplina,matriculaProfessor);           
            gerenciarDisciplinas();
        break;
        case "4":
        	logado();
        break;
        case "5":     	
        	System.exit(0);
        break;
	}
    JOptionPane.showMessageDialog(null, "Opcao invalida! Pressione enter para tentar novamente!");    
    gerenciarDisciplinas();
	}
}