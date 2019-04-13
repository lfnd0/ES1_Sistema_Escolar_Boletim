package br.seb.aplicacao.menus;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.seb.dao.ProfessorDAOBDR;
import br.seb.entidades.Aluno;
import br.seb.entidades.Professor;

public class MenuProfessor {
	
	public void menuProfessorOpcoes(){	
        String opcao = JOptionPane.showInputDialog("Professor(a) escolha uma opcao: \n"
                + "1 - Criar login e senha\n"
                + "2 - Entrar\n"
                + "3 - Voltar\n"
                + "4 - Sair\n");
        switch(opcao){
        	case "1":   
        		int matricula = 0;
        		String login = "";
        		String senha = "";
            	try {
            		matricula = Integer.parseInt(JOptionPane.showInputDialog("Professor(a) digite a sua matricula: "));
            		login = JOptionPane.showInputDialog("Digite o login: ");
            		senha = JOptionPane.showInputDialog("Digite a senha: "); 
    			} catch (java.lang.NumberFormatException e) {
    				JOptionPane.showMessageDialog(null, "Entrada invalida, digite a matricula correta!");
    				menuProfessorOpcoes();
    			}        		
        		Professor professor = new Professor(null, null, login, senha, matricula);
        		ProfessorDAOBDR professorDAOBDR = new ProfessorDAOBDR();
        		professorDAOBDR.cadastrar(professor);        		
        		menuProfessorOpcoes();
        	break;
        	case "2":       		
        		login = JOptionPane.showInputDialog("Digite o login: ");
        		senha = JOptionPane.showInputDialog("Digite a senha: "); 
                professorDAOBDR = new ProfessorDAOBDR();
                int matriculaProfessor = professorDAOBDR.login(login, senha);
                if(matriculaProfessor>0){              	
                	JOptionPane.showMessageDialog(null, "Bem-vindo professor(a)! Pressione a teclar enter para continuar.");                 
                    logado(matriculaProfessor);
                }else{              	                  
                    opcao = JOptionPane.showInputDialog("Login incorreto!\n"
                    		+ "1 - Tentar novamente\n"
                    		+ "2 - Voltar\n"
                    		+ "3 - Sair\n");                   
                    switch(opcao){
                    	case "1":
                    		menuProfessorOpcoes();
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
        	case "3":
        		MenuPrincipal menuPrincipal = new MenuPrincipal();
        		menuPrincipal.menuPrincipal();
        	break;
        	case "4":     		
        		System.exit(0);
        	break;
        }
        JOptionPane.showMessageDialog(null, "Opcao invalida! Pressione enter para tentar novamente!");       
        menuProfessorOpcoes();      
    }
        		
    private void logado(int matriculaProfessor){  	   	
        String opcao = JOptionPane.showInputDialog("Professor(a) escolha uma opcao: \n"
                + "1 - Listar alunos(as)\n"
                + "2 - Adicionar nota ou atualizar nota\n"
    			+ "3 - Sair\n");
    	switch(opcao){
	        case "1":        	
	        	ProfessorDAOBDR professorDAOBDR = new ProfessorDAOBDR();
	        	ArrayList<Aluno> listaAluno = new ArrayList<>(professorDAOBDR.listarAlunos(matriculaProfessor));
	        	if(listaAluno.size() > 0) {
	        		JOptionPane.showMessageDialog(null, "Alunos: \n"+listaAluno); 
	        		logado(matriculaProfessor);
	        	}else {
	        		JOptionPane.showMessageDialog(null, "Nao ha alunos(as) cadastrados(as) no momento!");        		
	        		logado(matriculaProfessor);
	        	}	        	
	        break;
	        case "2":       	
	        	opcao = JOptionPane.showInputDialog("Professor(a) escolha uma opcao: \n"
	                    + "1 - Nota do bimestre 1\n"
	                    + "2 - Nota do bimestre 2\n"
	                    + "3 - Reavaliacao 1\n"
	        			+ "4 - Nota do bimestre 3\n"
	        			+ "5 - Nota do bimestre 4\n"
	        			+ "6 - Reavaliacao 2\n"
	        			+ "7 - Recuperacao Final\n"
	        			+ "8 - Voltar\n");
	        	if(opcao.equals("8")) {
	        		logado(matriculaProfessor);
	        	}else {
		        	int matriculaAluno = 0;
	            	try {
			        	matriculaAluno = Integer.parseInt(JOptionPane.showInputDialog("Digite a matricula do aluno: "));
	    			} catch (java.lang.NumberFormatException e) {
	    				JOptionPane.showMessageDialog(null, "Entrada invalida, digite a matricula correta!");
	    				logado(matriculaProfessor);
	    			}   
		        	professorDAOBDR = new ProfessorDAOBDR();
		        	listaAluno = new ArrayList<>(professorDAOBDR.listarAlunos(matriculaProfessor));
		        	for (Aluno alunoAtual : listaAluno) {
		        		if(alunoAtual.getMatriculaAluno() == matriculaAluno) {
		        			double nota = 0;
			            	try {
			        			nota = Double.parseDouble(JOptionPane.showInputDialog("Digite a nota: "));	     
			    			} catch (java.lang.NumberFormatException e) {
			    				JOptionPane.showMessageDialog(null, "Entrada invalida, digite uma nota valida!");
			    				logado(matriculaProfessor);
			    			} 
				            if(nota < 0 || nota > 10) {
				            	JOptionPane.showMessageDialog(null, "Nota irregular, digite uma nota valida!");
				            	logado(matriculaProfessor);
				            }
				            professorDAOBDR.inserirNota(matriculaProfessor, matriculaAluno, opcao, nota);    	        		
				    		logado(matriculaProfessor);
		        		}else {
		        			JOptionPane.showMessageDialog(null, "Matricula incorreta");
		        		}
		        	}
	        	}
            break;
	        case "3": 	        	
	    		System.exit(0);
	        break;
    	}
    	JOptionPane.showMessageDialog(null, "Opcao invalida! Pressione enter para tentar novamente!");        
        logado(matriculaProfessor);       
    }
}