# 🌍 AlertaSolo - Sistema de Monitoramento de Locais de Risco

Projeto de API RESTful para gerenciamento de sensores instalados em áreas de risco de deslizamento de terra. Sensores como ESP32 monitoram variáveis ambientais e auxiliam na detecção de possíveis desastres naturais.

## Integrantes:


# Integrantes:
 - Vinicius Leandro de Araujo Bernardes RM554728 TURMA 2TDSPY
 - Edvan Davi Murilo Santos do Nascimento RM554733 TURMA 2TDSPZ
- Rafael Romanini de Oliveira RM554637 TURMA 2TDSPZ


### O Sistema atualmente conta com:
#### - Gerenciamento de usuários(cadastro/atualização/leitura de dados e remoção);
#### - Gerenciamento de locais(cadastro/atualização/leitura de dados e remoção);
#### - Gerenciamento de sensores(cadastro/leitura de dados);

## 💡 Objetivo
Desenvolver uma aplicação que:
- Comunique-se com ESP32 via rede/local.
- Receba valores de umidade/tremor/inclinação obtidos pelo ESP32 com modulos conectados dos sensores.
- Envie notificações para os usuários que habitam na região alertando sobre o possivel deslizamento + dicas de como lidar com a situação.


## ⚙️ Etapas para rodar o projeto:
  - 1 Instale o JDK 21 caso não tenha em sua máquina
  - 2 Coloque suas credenciais do oracle sql developer no arquivo application.properties
  - 3 Teste as requisições pelo postman/insomnia ou swagger assim como foram feitas nos prints demonstrativos



## Entidades do projeto:
- ### Usuario
- - idUsuario
  - nome
  - cpf
  - idade
  - cidade
  - uf
  - dataCadastro (obtida automaticamente quando cadastrado o usuário)

- ### Login
- - id
  - usuario (Relação OneToOne com Usuario)
  - email
  - senha

- ### LocalRisco
- - idLocal
  - nomeLocal
  - latitude
  - longitude
  - cidade
  - uf
  - grauRisco
  - ativo

- ### Sensor
- - idSensor
  - localRisco (Relação ManyToOne com LocalRisco)
  - codigoEsp32
  - status
  - tipoSensor (Para quando o esp32 não tiver todos os sensores como modulos, caso tenha colocar Multissensor(Umidade, Inclinação, Tremor)
  - dataInstalação (obtida automaticamente quando cadastrado o sensor)
 
## Requisições da parte de gerenciamento de usuário:

### Cadastrar usuário:

- url: http://localhost:8080/api/usuario
- metodo: POST
- {
- - "nome": "string",
- - "cpf": "string",
- - "idade": 120,
- - "cidade": "string",
- - "uf": "string",
- - "email": "string",
- - "senha": "string"
- }

![cadastroUser](https://github.com/user-attachments/assets/98acdcfc-83e4-497f-8827-86a124c768af)

### Visualizar usuário especifico:

- url: http://localhost:8080/api/usuario/{id do usuario}
- metodo: GET

![getUser](https://github.com/user-attachments/assets/60f1a5ca-d745-4f22-90a7-b1e673bbf7c5)

### Atualizar dados de login do usuário:

- url: http://localhost:8080/api/usuario/{id do usuario}
- metodo: PUT
- {
 - - "nome": "string",
 - - "cpf": "string",
 - - "idade": 120,
 - - "cidade": "string",
 - - "uf": "string",
 - - "email": "string", **Precisa somente prencher o campo que ira modificar, ou seja email ou senha, ou os dois**
 - - "senha": "string"
- }

![attUser](https://github.com/user-attachments/assets/91a2f4bf-9628-4110-bb02-6f99650e120d)

### Deletar usuário:

- url: http://localhost:8080/api/usuario/{id do usuario}
- metodo: DELETE

![delUser](https://github.com/user-attachments/assets/0b220d60-15fb-4b25-9949-e9871e8964fa)


## Requisições da parte de gerenciamento de local:

### Cadastrar local:

- url: http://localhost:8080/api/local-risco
- metodo: POST
- {
 - - "nomeLocal": "string",
 - - "latitude": 0,
 - - "longitude": 0,
 - - "cidade": "string",
 - - "uf": "string",
 - - "grauRisco": "string",
 - - "ativo": true
- }

![cadLocal](https://github.com/user-attachments/assets/a3031dea-7e6b-4fdd-a30e-b18b47105eeb)

### Atualizar grau de risco/atividade(se ainda é um local de risco) do local:

- url: http://localhost:8080/api/local-risco/{id do local}
- metodo: PUT

- {
 - - "nomeLocal": "string",
 - - "latitude": 0,
 - - "longitude": 0,
 - - "cidade": "string",
 - - "uf": "string",
 - - "grauRisco": "string", **Precisa somente prencher o campo que ira modificar, ou seja grauRisco ou ativo, ou os dois**
 - - "ativo": true
}

![attLocal](https://github.com/user-attachments/assets/72738423-46fd-422a-bd3c-3d7dd8452d53)

### Visualizar local especifico:

- url: http://localhost:8080/api/local-risco/{id do local}
- metodo: GET

![getLocalEsp](https://github.com/user-attachments/assets/f4c30c67-7c8e-47b1-8d13-2b6c21c2d2a8)

### Visualizar todos os locais de risco:

- url: http://localhost:8080/api/local-risco
- metodo: GET

![listarTodosLocal](https://github.com/user-attachments/assets/16f07f74-bd34-4476-acfd-61aa2178b08c)

### Deletar local:

- url: http://localhost:8080/api/local-risco/{id do local}
- metodo: DELETE

![deleteLocal](https://github.com/user-attachments/assets/88180ac7-b958-4dc0-b94b-b2f141e89ca1)


## Requisições da parte de gerenciamento de sensor:

### Cadastrar sensor:

- url: http://localhost:8080/api/sensor
- metodo: POST

- {
  - - "idLocalRisco": 0,
  - - "codigoEsp32": "string",
  - - "status": "string",
  - - "tipoSensor": "string"
- }

![cadSensor](https://github.com/user-attachments/assets/5dbb82e7-f998-4e6b-b5bc-c71a156be049)

### Visualizar sensor especifico:

- url: http://localhost:8080/api/sensor/{id do sensor}
- metodo: GET

![lerSensor](https://github.com/user-attachments/assets/b9ac29b6-c449-49de-bd17-0d93a708780f)

### Verificar Risco de deslizamento:

- url : http://localhost:8080/api/sensor/verificar-risco
- metodo: POST
- {
- - "idLocal": 5,
- - "umidade": 42.5,
- - "inclinacao": 40,
- - "tremor": 6.2
- }

  ![verificarRiscoSensor](https://github.com/user-attachments/assets/dae2c9cc-4fc7-49ec-9b84-a7e068b31020)














