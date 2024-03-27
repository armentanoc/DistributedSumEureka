# DistributedSumEureka: Sistemas Distribuídos e Microsserviços

Este projeto é parte de uma aplicação que utiliza o serviço de registro e descoberta do Eureka. 
O `app-eureka-server` é responsável por atuar como o servidor de registro e descoberta para os serviços em um ambiente baseado em microserviços. 

![image](https://github.com/armentanoc/DistributedSumEureka/assets/88147887/c324b1ae-8cf7-4d39-b24e-d88e3deffce5)
<!--https://excalidraw.com/#json=cU-dt0KZ0WHGgqK6W07Td,iu2cOJBYuHIBpA5IRLDn9g-->

1. O `eureka-client-a` é responsável por solicitar uma soma aleatória para o `eureka-client-b`;
2. O `eureka-client-b` se comunica com o `eureka-client-c` solicitando um número aleatório;
3. O `eureka-client-c` devolve para o `eureka-client-b` um número aleatório;
4. O `eureka-client-b` performa a soma entre o número aleatório recebido e um novo número aleatório gerado e devolve para o `eureka-client-a`;
5. O `eureka-client-a` exibe o resultado da soma ou um erro que eventualmente possa ocorrer. 

## Configuração do Ambiente

Certifique-se de ter o Java Development Kit (JDK) e o Apache Maven instalados em sua máquina.

## Como Executar

1. Clone este repositório para o seu ambiente local.
2. Navegue até o diretório raiz do projeto.
3. Execute os projetos.

## Executando o `app-eureka-server`

<div align="center" display="flex">
<img src="https://github.com/armentanoc/DistributedSumEureka/assets/88147887/ab0d494f-8d77-4467-b5d2-512859001039" height="500px">
</div>

## Aplicações descobertas pelo `app-eureka-server`

<div align="center" display="flex">
<img src="https://github.com/armentanoc/DistributedSumEureka/assets/88147887/17bd67be-d543-4e5e-b8d3-069f5a7c171d" height="500px">
</div>

## Requisição somente a App C

<div align="center" display="flex">
<img src="https://github.com/armentanoc/DistributedSumEureka/assets/88147887/a03f4f4b-30fc-4357-b3f8-f4258511b343" height="500px">
</div>

## Requisição a App B (que chama a App C)

<div align="center" display="flex">
<img src="https://github.com/armentanoc/DistributedSumEureka/assets/88147887/6eac49d8-2dfe-41f7-b10b-2442d6955b1b" height="500px">
</div>

## Requisição na App A (que chama a AppB, que chama a AppC)

<div align="center" display="flex">
<img src="https://github.com/armentanoc/DistributedSumEureka/assets/88147887/6e519a8d-9b59-44a2-b498-a7c35e618bf5" height="500px">
</div>
