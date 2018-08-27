# language: pt

@criarconta
Funcionalidade: Criar conta no Facebook

  COMO usuário
  QUERO criar uma conta no Facebook
  PARA acessar o site e fazer gestão da minha configuração

  Cenario: Cadastro no site
    Dado que eu acesse um site para gegar dados ficticios de uma pessoa
    Quando realizo cadastro no Facebook com esses dados
    Então eu devo conseguir ver o e-mail para validar os dados da conta
    E acessar a conta
    E entrar no menu de configurações/segurança
    E alterar a minha senha
    E encerrar sessão
    E logar com a nova senha
