# ClyvoCare-OS
Projeto para o Challenge 2026 em Java.
📌 **Sobre o Projeto**

O ClyvoCare OS é uma API REST desenvolvida em Java com Spring Boot com o objetivo de promover a continuidade do cuidado veterinário através de monitoramento preventivo, alertas inteligentes e histórico longitudinal do pet.

A solução foi criada com base no desafio proposto pela CLYVO VET — Infraestrutura do futuro da medicina veterinária digital, focando em:

prevenção veterinária
engajamento contínuo do tutor
redução do abandono de tratamentos
acompanhamento clínico inteligente
centralização do histórico do pet

---

🎯 **Objetivo da API**

O sistema terá funcionalidades inteligentes capazes de:

-gerar alertas preventivos

-calcular risco de saúde do pet

-recomendar cuidados por fase da vida

-monitorar vacinas atrasadas

-acompanhar tratamentos ativos

-fornecer histórico longitudinal completo


---

🛠️ **Tecnologias Utilizadas**

Java 21

Spring Boot

Spring Data JPA

Spring Validation

Spring Cache

Oracle Database

Swagger / OpenAPI

Maven

Lombok

---

📂 **Estrutura do Projeto**

src/main/java/com/clyvocare

controller

service

repository

dto

entity

exception

config

---


📌 **Funcionalidades da API**
✅ Cadastro de Tutores

Permite:

cadastrar tutor

listar tutores

buscar tutor por ID

atualizar tutor

deletar tutor

**Endpoints**

POST /tutores

GET /tutores

GET /tutores/{id}

PUT /tutores/{id}

DELETE /tutores/{id}



✅ Cadastro de Pets

Permite:

cadastrar pets

vincular pets aos tutores

listar pets

buscar pets por espécie

buscar pets por risco

atualizar dados do pet

Endpoints

POST /pets

GET /pets

GET /pets/{id}

PUT /pets/{id}

DELETE /pets/{id}

GET /pets/especie?nome=cachorro

GET /pets/risco?nivel=alto


✅ Controle de Vacinas

Permite:

registrar vacina

verificar vacinas atrasadas

listar histórico vacinal

Endpoints

POST /vacinas

GET /vacinas

GET /vacinas/atrasadas

GET /vacinas/pet/{id}

✅ Controle de Consultas

Permite:

registrar consultas

registrar observações clínicas

acompanhar retornos pendentes

Endpoints

POST /consultas

GET /consultas

GET /consultas/pet/{id}

GET /consultas/retornos

✅ Controle de Tratamentos

Permite:

registrar medicamentos

controlar tratamentos ativos

identificar abandono de tratamento

Endpoints

POST /tratamentos

GET /tratamentos

GET /tratamentos/ativos

GET /tratamentos/pet/{id}

---

⭐ **DIFERENCIAL DA API**
✅ Sistema Inteligente de Alertas

A API será capaz de gerar alertas automáticos com base nas informações clínicas do pet.

Exemplos:

vacina atrasada

check-up geriátrico recomendado

tratamento abandonado

obesidade

retorno pendente

Endpoint

GET /alertas/pet/{id}

✅ Score de Risco do Pet

O sistema calculará automaticamente o nível de risco do pet com base em:

idade

vacinas atrasadas

obesidade

frequência de consultas

tratamentos pendentes

Níveis:

BAIXO

MÉDIO

ALTO

Endpoint

GET /pets/risco/{id}

✅ Recomendações Preventivas

O sistema irá recomendar cuidados específicos de acordo com a fase da vida do pet.

Exemplos

Filhote

reforço vacinal

vermífugo

Adulto

controle de peso

check-up anual

Idoso

exames cardíacos

acompanhamento articular

Endpoint

GET /recomendacoes/pet/{id}

✅ Histórico Longitudinal do Pet

A API fornecerá uma visão completa da jornada de saúde do pet.

Informações exibidas:

consultas

vacinas

tratamentos

alertas

nível de risco

Endpoint

GET /pets/{id}/historico

✅ Dashboard Clínico

O sistema fornecerá métricas gerais para clínicas veterinárias.

Exemplos:

pets em risco

vacinas atrasadas

tratamentos ativos

taxa de adesão

Endpoint

GET /dashboard
