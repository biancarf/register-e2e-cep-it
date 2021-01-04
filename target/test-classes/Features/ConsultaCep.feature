Feature: Teste cep da API viacep
  
  Descirption: Testar o retorno do cep via chamada da url https://viacep.com.br/ws

  Scenario Outline: Consulta valida de cep
    Given usuário efetue uma consulta de cep via api
    When inserir "<cep>" valido
    Then Sistema de retornar "<statuscode>"

    Examples: 
      | cep      | statuscode |
      | 08140100 |        200 |
      | 08140120 |        200 |
      | 88056482 |        200 |
      | 88054001 |        200 |
