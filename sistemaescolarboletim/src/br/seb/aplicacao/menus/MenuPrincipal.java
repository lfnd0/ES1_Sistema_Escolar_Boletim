package br.seb.aplicacao.menus;

import javax.swing.JOptionPane;

public class MenuPrincipal {
	
	public void menuPrincipal(){
		JOptionPane.showMessageDialog(null, "Bem-vindo ao Sistema Escolar Boletim\n");
		String opcao = JOptionPane.showInputDialog("Por favor, escolha uma opcao: \n"
				+ "1 - Admistrador\n"
				+ "2 - Aluno\n"
				+ "3 - Professor\n"); 
		switch (opcao) {
		case "1":
			MenuAdministrador menuAdministrador = new MenuAdministrador();
			menuAdministrador.login();
			break;
		case "2":
			MenuAluno menuAluno = new MenuAluno();
			menuAluno.menuAlunoOpcoes();
			break;
		case "3":
			MenuProfessor menuProfessor = new MenuProfessor();
			menuProfessor.menuProfessorOpcoes();
			break;
		}
		JOptionPane.showMessageDialog(null, "Opcao invalida! Pressione enter para tentar novamente!");
		menuPrincipal();
	}
}