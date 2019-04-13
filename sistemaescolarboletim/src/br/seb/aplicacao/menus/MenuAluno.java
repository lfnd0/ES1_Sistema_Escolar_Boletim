package br.seb.aplicacao.menus;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.seb.dao.AlunoDAOBDR;
import br.seb.entidades.Aluno;

public class MenuAluno {
	
	public void menuAlunoOpcoes(){		
        String opcao = JOptionPane.showInputDialog("Aluno(a) escolha uma opcao: \n"
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
            		matricula = Integer.parseInt(JOptionPane.showInputDialog("Aluno(a) digite a sua matricula: "));
            		login = JOptionPane.showInputDialog("Digite o login: ");
            		senha = JOptionPane.showInputDialog("Digite a senha: ");
    			} catch (java.lang.NumberFormatException e) {
    				JOptionPane.showMessageDialog(null, "Entrada invalida, digite a matricula correta!");
    				menuAlunoOpcoes();
    			}    		
        		Aluno aluno = new Aluno(null, null, login, senha, matricula);
        		AlunoDAOBDR alunoDAOBDR = new AlunoDAOBDR();
        		alunoDAOBDR.cadastrar(aluno);       		
        		menuAlunoOpcoes();
        	break;
        	case "2":      		
                login = JOptionPane.showInputDialog("Digite o login: ");
                senha = JOptionPane.showInputDialog("Digite a senha: ");               
                alunoDAOBDR = new AlunoDAOBDR();
                int matriculaAluno = alunoDAOBDR.login(login, senha);
                if(matriculaAluno>0){               	
                	JOptionPane.showMessageDialog(null, "Bem-vindo aluno(a)! Prescione a teclar enter para continuar.");                 
                    logado(matriculaAluno);
                }else{                   
                    opcao = JOptionPane.showInputDialog("Aluno(a) login incorreto!\n"
                    		+ "1 - Tentar novamente\n"
                    		+ "2 - Voltar\n"
                    		+ "3 - Sair\n");  
                    switch(opcao){
                    	case "1":
                    		menuAlunoOpcoes();
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
        menuAlunoOpcoes();     
    }
        		
    private void logado(int matriculaAluno){
        String opcao = JOptionPane.showInputDialog("Aluno(a) escolha uma opcao: \n"
                + "1 - Ver disciplinas e notas\n"
    			+ "2 - Sair\n");
    	switch(opcao){ 
	        case "1":        	        	
                AlunoDAOBDR alunoDAOBDR = new AlunoDAOBDR();
	        	ArrayList<Aluno> listaAluno = new ArrayList<>(alunoDAOBDR.listarNotasAluno(matriculaAluno));
	        	if(listaAluno.size() > 0) {
	        		JOptionPane.showMessageDialog(null, "Alunos: \n"+listaAluno); 
	        		logado(matriculaAluno);
	        	}else {
	        		JOptionPane.showMessageDialog(null, "Nao ha notas cadastrados(as) no momento!");        		
	        		logado(matriculaAluno);
	        	}	
	        break;
	        case "2": 	        	
	    		System.exit(0);
	        break;
    	}
    	JOptionPane.showMessageDialog(null, "Opcao invalida! Pressione enter para tentar novamente!");      
        logado(matriculaAluno);      
    }
}