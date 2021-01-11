# Author: Daniel
# Date
# Description
Feature: feature para testar funcionalidade para cadastro de usuário

  Scenario: Cadastrar usuário válido
    Given que o usuario não esteja cadastrado no sistema
    And que o usuário preenche os campos obrigatórios nome com o valor "Aline"
    And o sobrenome com "Dante Matilde"
    And o e-mail "teste_dante@teste.com"
    And o telefone "4812345672"
    And o genero "FeMale"
    And o pais "Switzerland"
    And a data "17" de "July" de "1992"
    And a senha com a confirmação "Teste23a"
    When clicar em "submit"
    Then o usuário é cadastrado com sucesso

  Scenario: Validar mensagem de erro quando cadastra usuário já cadastrado
    Given que o usuario possua cadastro e tente cadastra com o mesmo nome "Dante"
    And o sobrenome com "Aroteste"
    And o e-mail "dante_teste@teste.com"
    And o telefone "4812345678"
    And o genero "Male"
    And o pais "Brazil"
    And a data "19" de "July" de "1990"
    And a senha com a confirmação "Teste23a"
    When clicar em "submit"
    Then Sistema apresenta mensagem de erro "Email already exists"
    And mensagem "Phone number already exists"
