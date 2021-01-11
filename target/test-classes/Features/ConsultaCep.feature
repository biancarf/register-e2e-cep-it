Feature: Teste cep da API viacep
  
  Descirption: Testar o retorno do cep via chamada 
  							da url https://viacep.com.br/ws

  Scenario Outline: Consultar cep valido
    Given usuario deseje efetuar uma consulta via get na api de cep
    When inserir "<cep>" valido
    Then sistema deve retornar o status como "<statuscode>"

    Examples: 
      | cep      | statuscode |
      | 08140100 |        200 |
      | 08140120 |        200 |
      | 88056482 |        200 |
      | 88054001 |        200 |

  Scenario Outline: Validar busca de cep por logradouro
    Given usuario deseje efetuar uma consulta via get na api de cep
    When inserir no eindpoint <estado>/<nomeCidade>/<nomeRua>/json
    Then sistema deve retornar o status como "<statuscode>"
    And os numeros de cep <numeroCepA>
    And o <numeroCepB> relacionados ao logradouro.

    Examples: 
      | estado | nomeCidade      | nomeRua | statuscode | numeroCepA  | numeroCepB  |
      | "SC"   | "Florianopolis" | "Vilac" |        200 | "88054-000" | "88054-000" |

  Scenario Outline: Validar erro quando o cep não existir
    Given usuario deseje efetuar uma consulta via get na api de cep
    When inserir o "<cep>" invalido
    Then sistema deve retornar o status como "<statuscode>"
    And o <msgErro> no retorno.

    Examples: 
      | cep      | statuscode | msgErro |
      | 01111100 |        200 | "true"  |
