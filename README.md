# Sistema Escolar Boletim - SEB

> O SEB é uma plataforma desenvolvida com a linguagem de programação Java e o sistema de gestão de base de dados MySQL, sendo o objetivo principal da aplicação disponibilizar o cadastro de alunos, professores e disciplinas, além de armazenar as notas dos respectivos discentes do Instituto Municipal de Arapiraca - IMA (instituição fictícia).


## Ambiente para utilização do SEB

Para testar/utilizar a aplicação em um dispositivo desktop, será necessária a instalação das seguintes ferramentas:

  * [Java SE Development Kit 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
  * [Eclipse IDE](https://www.eclipse.org/downloads/)
  * [MySQL 8.0 + Workbench](https://dev.mysql.com/downloads/)


## Executando os Casos de Testes do SEB

**ATENÇÃO: para executar os testes do sistema recomenda-se que nenhuma alteração tenha sido realizada no arquivo ``seb.sql``, afim de que os casos de testes gerados sejam executados corretamente. Após executá-los, realize novamente a importação do banco de dados.**

Após a instalação e configuração das ferramentas citadas anteriormente, crie um usuário e uma conexão no MySQL com ``nome``, ``login`` e ``senha`` "es1", adicione ao mesmo todos os privilégios de usuário. Ao finalizar esta tarefa, realize a importação do banco de dados do SEB a partir do arquivo ``seb.sql`` no diretório ``database`` do projeto.

Para a execução dos casos de testes do SEB, certifique-se de ter adicionado o projeto no ``Eclipse IDE``. Após este passo, execute os testes com o ``JUnit Framework``.


## Executando o seb.jar

Após relizar a instalação e configuração do ``MySQL``, localize o arquivo `seb_v0.1.0.jar`. Abra o terminal do seu sistema operacional no mesmo diretório onde encontra-se o referido arquivo, então execute o seguinte comando:

```sh
java -jar seb_v0.1.0.jar
```

O ``Menu Principal`` do SEB aparecerá para você com as seguintes opções:

 * ``1 - Administrador``
 * ``2 - Aluno``
 * ``3 - Professor``

 Escolha o perfil ``1 - Administador`` digitando ``1``. Nos campos de login e senha insira "es1". Agora, a partir do acesso a este usuário, usufrua das funcionalidades implementadas para o administrador e consequentemente aos demais usuários.

# Time de desenvolvimento

  ## Time de desenvolvimento

  * [Danillo Rodrigues](https://github.com/danillobr)
  * [João Marcos](https://github.com/joaomarcoos)
  * [Luiz Fernando](https://github.com/lfnd0)
