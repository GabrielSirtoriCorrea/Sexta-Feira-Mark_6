# Sexta-Feira MARK 6
Sexta-Feira, é uma assistente virtual desenvolvida para auxiliar o usuário nas tarefas do dia-a-dia, podendo mostrar
imagens, realizar pesquisas na internet etc.

No início, o projeto contia as seguintes ideias:

- Uma assistente virtual para auxiliar nas atividades do dia-a-dia com uma interface de interação com usuário
- Um banco de dados para o armazenamento de dados necessários
- Um sistema de Internet das coisas (IoT) na qual seria possivel fazer comunicação entre dispositivos
- Um aplicativo para o acesso a todas as informações do banco de dados

Tendo as ideias em mente o projeto foi separado em 4 subsistemas:

- Banco de dados 
- Servidor
- Sexta-feira
- App
 
## Banco de dados

O banco de dados foi criado com o MySQL, ele é responsável por armazenar todos os dados do sistema.

### Tabelas

- Interactions - Responsável por armazenar todas as conversas e comandos com a Sexta-Feira (Assistente virtual)
- Projects - Responsável por armazenar projetos pessoais
- HomeWorkManagement - Responsável por armazenar tarefas escolares
- Device - Responsável por armazenar os dispositivos conectados ao servidor

### Colunas

- Interactions
   - KeyWordID
   - KeyWord1
   - KeyWord2
   - KeyWord3
   - response1
   - response2
   - response3
   - Command

- Projects
   - ProjectID
   - ProjectType
   - Project

- HomeWorkManagement
   - HomeWorkID
   - HomeWorkType
   - HomeWorkSubject
   - HomeWork
   - HomeWorkDelivery
   - HomeWorkDescription

- Device
   - DeviceID
   - DeviceName
   - DeviceDescription
   - DeviceActions

## Servidor

O servidor, é responsavel pela comunicação entre os subsistemas, ele tem acesso ao banco de dados e envia as
informações conforme as requisições.

## Sexta-Feira

A Sexta-Feira é a assistente virtual. Ela é separada em 2 partes Interface e Sexta-Feira

- Sexta-Feira é o cérebro de tudo, ela é responsável pelo reconhecimento de voz do usuário, conexão com o servidor etc.

- A Interface é responsável por mostrar imagens, tabelas e dados para a melhor visualização e compreensão do usuário conforme o pedido da Sexta-feira(Cérebro)

Exemplo de imagem com a sexta-feira

## App

O app é responsável pela interação total do usuário com o banco de dados através do servidor, nele é possivel adicionar novas interações, visualizar os dados do banco etc.


 

