# Sexta-Feira MARK 6
   Sexta-Feira, é uma assistente virtual desenvolvida para auxiliar o usuário nas tarefas
do dia-a-dia, podendo mostrar imagens, realizar pesquisas na internet etc.

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

### Estrutura

  Interactions   |    Projects     |    HomeWorkManagement   |         Device         |
---------------- | --------------- | ----------------------- | ---------------------- |
   KeyWordID     |    ProjectID    |       HomeWorkID        |        DeviceID        |
   KeyWord1      |   ProjectType   |       HomeWorkType      |       DeviceName       |
   KeyWord2      |     Project     |      HomeWorkSubject    |    DeviceDescription   |
   KeyWord3      |                 |         HomeWork        |      DeviceActions     |
   response1     |                 |      HomeWorkDelivery   |                        |
   response2     |                 |    HomeWorkDescription  |                        |
   response3     |                 |                         |                        |
   Command       |                 |                         |                        |

## Servidor
   O servidor, é responsavel pela comunicação entre os subsistemas, ele tem acesso ao banco de dados e envia as
informações conforme as requisições. Para a conexão entre o servidor e seus clientes, foi utilizado socket, 
com o protocolo TCP/IP para comunicação

### Estrutura do servidor
   Para criarmos a estrutura base do servidor, utilizamos a bibiloteca socketserver, assim, podemos criar uma 
classe extendida de **socketserver.BaseRequestHandler** para o gerenciamento de vários clientes ao mesmo tempo, ]
separados por Threads. 


   ```class ClientManage(socketserver.BaseRequestHandler):
         def handle(self):
         dataBaseConnection = DataBaseConnection()

         dateTimeNow= datetime.now()
         print(f'Connected by {self.client_address} at {dateTimeNow.hour}:{dateTimeNow.minute} ')

         while True:
               data = self.request.recv(5800).decode()
               print(data)

               try:
                  if data:
                     clientRequest = json.loads(data)

               except:
                  print('error')

      server = socketserver.ThreadingTCPServer((host, port), ClientManage)
      server.serve_forever()

```

### Comunicação banco de dados
   Para a comunicação com o banco de dados MySQL, utilizamos a biblioteca mysqlConnector. Para instala-la,
utilizamos o PIP.

   ```install mysql-connector-python```

   Após a instalação da biblioteca, foi criada uma classe para o gerenciamento do banco de dados. No método
construtor, iniciamos a conexão com o banco e definimos o cursor. Em seguida, foram criados os métodos: get(),
update(), delete() e insert() para cada tabela.

   ```
   import mysql.connector as mysqlConnector
   from datetime import datetime

   class DataBaseConnection:
      def __init__(self):
         self.dataBaseConnector =  mysqlConnector.connect(host='localhost', user='root', password='Gazao015', 
                                                         database='sextafeiradatabase')

         self.dataBaseCursor = self.dataBaseConnector.cursor()

      def getInteractions(self):
         self.dataBaseCursor.execute('SELECT * FROM Interactions')
         return self.dataBaseCursor.fetchall()

      def insertHomeWork(self, Type, subject, homeWork, delivery, desc):
         self.dataBaseCursor.execute('INSERT INTO HomeWorkManagement(HomeWorkType, HomeWorkSubject, HomeWork, HomeWorkDelivery, HomeWorkDescription) VALUES (%s,%s,%s,%s,%s)', (Type, subject, homeWork, delivery, desc))
         self.dataBaseConnector.commit()

      def updateDevice(self, updateId, name, desc, actions):
         self.dataBaseCursor.execute(f"UPDATE Device SET DeviceName = '{name}', DeviceDescription = '{desc}', DeviceActions = '{actions}' WHERE DeviceID = '{updateId}' ")
         self.dataBaseConnector.commit()

      def deleteProject(self, deleteID):
         self.dataBaseCursor.execute('DELETE FROM Projects WHERE ProjectID = %d' % (deleteID))
         self.dataBaseConnector.commit()

         (...)
   ```


## Sexta-Feira
   A Sexta-Feira é a assistente virtual. Ela é separada em 2 partes Interface e Sexta-Feira

### Sexta-Feira (A.I.)
   Sexta-Feira é o cérebro de tudo, ela é responsável pelo reconhecimento de voz do usuário, conexão com o 
servidor etc.

   Para termos um assistente virtual funcional, nós temos de ter uma atenção especial para o reconhecimento de 
voz. É ele o responsável por garantir a interação do usuário com o software, se não temos um bom reconhecimento, o software não é eficiente.

   Tendo isso em mente, foi inventada uma estratégia para o melhor reconhecimento. O uso de **PALAVRAS CHAVE**,
**RESPOSTAS** e **COMANDOS**. Esses três termos, formam uma **INTERAÇÃO** que o usuário pode.
ter com o software.

   No banco de dados estão armazenadas todas as interações, quando o software é iniciado ele se conecta com o
servidor, e pede todas as interações e armazena em uma lista para serem tradas. 

   Após o programa receber as interações do servidor, ele entra em um loop de repetição na qual este fica 
escutando o microfone do usuário a espera de um audio.

   Ao escutar algo do microfone, o software pega as interações contidas na lista, e faz um loop até que
todas as **Palavras chave** de uma interação estejam na frase. Quando isso acontece, o software responde 
através de **Sínteze de voz** uma das respostas contidas nessa interação, escolhida de forma aleatória. 

   Existe também a possibilidade do software executar um comando, nesse caso, o comando contido na interação,
é o **Nome do método a ser executado**, todos esses métodos estão no arquivo **COMMANDS.PY**, esse método é chamado
através de um outro método chamado **CallCommands**, executado no arquivo principal **SEXTA-FEIRA.PY**.


   #### Reconhecimento de voz
   Para fazermos o reconhecimento de voz, utilizamos a biblioteca **SpeechRecognition**, podemos instala-la 
   com o pip.

      ```pip install SpeechRecognition```

         Todo o processo de reconhecimento de voz, é executado no método **Recognition()** e chamado no arquivo
      principal **Sexta-Feira(A.i.)**

      ```def Recognition():
            recognizer = sr.Recognizer() #Instanciamos o objeto da classe principal 

            try:
               with sr.Microphone() as source: # Definimos o microfone
                     recognizer.adjust_for_ambient_noise(source) # Regulamos o ruído do microfone

                     print('Say:')

                     sound = recognizer.listen(source) # Escutamos o microfone
                     speech = recognizer.recognize_google(sound, language='pt').lower() # Recebemos o reconhecimento
                     print('You said: ', speech)

                     return ' ' + speech + ' ' # Retornamos a frase dita
            except:
               return ''
      ```

   #### Sínteze de voz
   Outra parte importante para garantirmos a boa interação do usuário com o software é a **SÍNTEZE DE VOZ**. Ela
   é responsável por responder ao usuário utilizando o **Narrador do Windows**, assim dando 
   a impressão para o usuário que ele está falando com alguém real.

         Para realizar a sínteze de voz, utilizamos a biblioteca **pyttsx3**, e instalamos ela novamente com o pip

      ```pip install pyttsx3```

         Para que o software não interrompa sua execução para "falar" as respostas, importamos a classe
      **Threadiing** já contida no Python, essa bibiloteca contém a classe **Thread**, que nos permite criar
      processos paralelos dentro do mesmo programa, então foi criada uma classe **Speaker**, subclasse de Thread, dentro do método **Run()** da classe, escrevemos o código a ser executado de forma paralela, depois iniciamos a Thread instanciando um objeto da classe Speaker, e executamos o método **start()**.

      ```class Speaker(Thread):
            def __init__(self, text):
               Thread.__init__(self)
               self.text = text

            def run(self):
               speaker = pyttsx3.init('sapi5')
               speaker.say(self.text)
               speaker.runAndWait()

         def speak(text):
            speak = Speaker(text)
            speak.start()

      ```

   #### Comunicação com servidor
   Para fazermos a conexão com servidor utilizamos a biblioteca **Socket** ja contida no python, e utilizamos o
   protocolo TCP/IP para comunicação.

   A conexão com o servidor é estabelecida através da classe **ServerConnection**, que inicializa a conexão no
   seu método construtor e envia e recebe as informações pelo método **send()**.

      ``` class ServerConnection:
            def __init__(self):
               try:
                     file = open('E:/Sexta-Feira-Mark_6/Configurations.json', 'r') 
                     configs =  json.load(file)['FridayConfigs'] #Pegamos o ip do servidor no arquivo Configurations.json

                     self.connection = socket(AF_INET, SOCK_STREAM, 0) #Definimos o protocolo TCP/IP
                     self.connection.connect((configs['ServerHost'], configs['Port'] )) # Conectamos com o servidor
               except:
                     print('ERRO AO CONECTAR-SE COM O SERVIDOR')      
               
            def send(self, message):
               self.connection.send(message.encode()) #Enviamos os dados

               return json.loads(self.connection.recv(5800).decode('utf-8')) # retorna a resposta enviada pelo servidor

      ```

   Os dados enviados ao servidor, são enviados em forma de estrutura de dados **JSON**. Para formatarmos os
   dados nessa estrutura, utilizamos o método **setRequestJson()**, e depois enviamos pelo socket.

      ```def setRequestJson(request, receiverID, action, url):
            requestJson = json.dumps({
                        'header': 'gazeboindustries09082004',
                        'receiverID': receiverID,
                        'request': request,
                        'action': action,
                        'url': url
               
               })

            return requestJson
      ```

   #### Comunicação com a Interface
   Para que possamos mostrar imagens e dados na interface, precisamos criar um método para a comunicação entre
   a **Sexta-Feira(A.I.)** e a **Interface** fazendo com que elas trabalhem em conjunto. Com isso em mente, foi criado o 
   arquivo **FridayComunication.json**, ele é o responsável por fazer a troca de informações entre a **Sexta-Feira** e a 
   **Interface**. 

   No entanto, para termos uma comunicação em tempo real, precisamos reescrever este arquivo várias vezes. Então
   foi criado o método **setFridayComunication()**, ele é o responsável por reescrever esse arquivo json quando alguma
   comunicação for necessária

      ``` def setFridayComunication(action, content, url):
            filePath = 'E:/Sexta-Feira-Mark_6/Sexta-FeiraInterface/src/com/friday/FridayComunication.json'
            readFile = open(filePath, 'r') # Abrimos o arquivo em formato leitura
            newJson = json.load(readFile) # Colocamos os dados em um dicionário

            print(newJson)

            newJson['action'] = action
            newJson['content'] = content # Sobreescrevemos os dados
            newJson['url'] = url

            writeFile = open(filePath, 'w') # Abrimos o arquivo novamente, agora em formato de escrita
            json.dump(newJson, writeFile, indent=4) # Escrevemos o dicionário com os novos dados
      ``` 

      Todos esses métodos estão contidos no arquivo **Functions.py**


### Interface
   A Interface é responsável por mostrar imagens, tabelas e dados para a melhor visualização e compreensão    
do usuário conforme o pedido da Sexta-feira(A.I.). Ela foi construída em JAVA, e utilizando o JavaFX para
a criação do layout. 

   #### Layout
      - Tela inicial
   
      - Exemplo de Imagem

      - Exemplo de Tabela

   #### Comunicação com Sexta-Feira(A.I.)
   Para que possamos ler os dados contidos no **FridayComunication.json** e comunicarmos com Sexta-Feira(A.I.),
   foi criada a classe **FridayComunication.JAVA**, ela é responsável por ler o arquivo json, e retornar os dados
   necessários através do método **readJsonFile()**.
   
      ```public class FridayComunication {
            private static JSONObject jsonObject;
            private static JSONParser parser; # Declaração dos atributos necessários

            public static JSONObject readJsonFile() {
               parser = new JSONParser(); # Instanciando objeto da classe JSONParser para lermos o arquivo

               try {
                     jsonObject = (JSONObject) parser.parse(new FileReader("E:\\Sexta-Feira-Mark_6\\SextaFeiraInterface\\src\\com\\friday\\FridayComunication.json")); 
               } catch (ParseException | IOException e) {
                     e.printStackTrace();
               }

               return jsonObject; # Retornando os dados em um JSONObject
            }
         } 
      
      ```

   Agora que ja temos os dados necessários, podemos saber qual ação devemos tomar, e essa informação está no campo **Action** do JSONObject, 
   sendo assim, no SceneController, classe que controla os itens don layout, podemos fazer o **Switch()** desse dado e executar a os comandos 
   correspondentes, se existir algum dado que deve ser passado da Sexta-Feira(A.I.) para a Interface, colocamos isso no campo **Content** do 
   Json, ou no caso de uma imagem, colocamos o caminho para o arquivo no campo **Url** do Json.Fazemos tudo isso no método **connectionLoop()**.

      ```private void connectionLoop() {
         response = FridayComunication.readJsonFile();
         action = Integer.parseInt(response.get("action").toString());

         switch (action) {
               case 0:
                  tableView.setVisible(false);
                  imageView.setVisible(false);
                  break;

               case 1:
                  
                  if (!tableView.getColumns().contains(commandColumn)) {
                     imageView.setVisible(false);
                     setTableData();

                     addInteractionsColumns();

                  }

                  break;

               case 2:
                  if (!tableView.getColumns().contains(homeWorkColumn)) {
                     imageView.setVisible(false);
                     setTableData();
                     addHomeWorksColumns();
                  }
                  break;

               case 3:
                  if (!tableView.getColumns().contains(projectColumn)) {
                     imageView.setVisible(false);
                     setTableData();
                     addProjectsColumns();
                  }
                  break;

               case 4:

                  if (!tableView.getColumns().contains(DeviceColumn)) {
                     imageView.setVisible(false);
                     setTableData();

                     addDevicesColumns();


                  }
                  break;

               case 5:
                  tableView.setVisible(false);

                  imagePath = response.get("url").toString();
                  System.out.println(imagePath);
                  imageFile = new File(imagePath);
                  image = new Image(imageFile.toURI().toString());
                  imageView.setImage(image);
                  imageView.setVisible(true);


               default:
                  break;
         }
      }
      
      ```

   Pronto. Agora ja conseguimos ler os dados e fazer as ações pedidas, porém, precisamos que tudo isso seja
   executado continuamente, em um **loop infinito**. Para isso configuramos um EventHandler, criamos um keyFrame
   para fazermos um loop desse Event, e depois colocamos isso no TimeLine.

      ```public void initialize(URL url, ResourceBundle rb) {

         EventHandler handler = new EventHandler() { 

               @Override
               public void handle(Event event) {
                  setClock();
                  connectionLoop();
               }

         };

         KeyFrame frame = new KeyFrame(Duration.millis(1000), handler);
         Timeline timeline = new Timeline();
         timeline.getKeyFrames().add(frame);
         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.play();

      }
      
      ```   

## App
   O app é responsável pela interação total do usuário com o banco de dados através do servidor, é nele em que o 
usuário pode adicionar interações, tarefas, atualizar um projeto, excluir um device etc.

   Para a construção do App, foi utilizado o Android Studio com o JAVA como linguagem de programação, além de xml
para a construção dos layouts. 

   ### Permições 
   Antes de começar a criar os layouts e as classes JAVA, primeiro precisamos definir as permições que o nosso app precisa ter 
   para acessarmos alguns recursos do dispositivo, no nosso caso, precisamos da permição de acesso a internet, para que possamos 
   fazer a  conexão com o servidor.

      <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
      <uses-permission android:name="android.permission.INTERNET" />

   ### Layouts
   Para a criação desse projeto, foi escolhido a utilização do recurso **Bottom navigation** para uma melhor
   experiência do usuário, em decorrência disso, foi utilizado **FRAGMENTS** para a construção dos layouts, assim,
   não precisamos colocar o Bottom navigation em todas as activities e apenas na MainActivity.

      - Imagens layout

   ### Comunicação com servidor
   A comunicação com o servidor, foi feita novamente com **Socket** e protocolo TCP/IP. Foi criada a classe ServerConnection para o
   gerenciamento da conexão. É importante ressaltar, que ao utilizarmos sockets no **Android**, temos que obrigatoriamente tornarmos a 
   nossa classe ServerConnection uma **Subclasse** de **AsyncTask**, para que possamos fazer os request em **BACKGROUND** através do 
   método **doInBackground()** descendente da classe AsyncTask.

      ```public class ServerConnection extends AsyncTask<JSONObject, Integer, ArrayList<JSONArray>> {
            private String IP = "gazeboindustries.hopto.org"; // Utilizando um DNS
            private int port = 5000;
            private Socket socket;
            private PrintWriter out;
            private BufferedReader in;
            private JSONObject jsonRequest;
            private JSONObject jsonResponse;
            private String data;
            private ArrayList<JSONArray> list = null;
            private JSONArray arrayResponse;
            private char[] buffer;
            private boolean msgStatus = false;


            @Override
            protected ArrayList<JSONArray> doInBackground(JSONObject... params) {
               try {
                     if(params[0].get("request").equals("getDevicesStatus")) {
                        this.socket = new Socket(IP, port);
                        this.out = new PrintWriter(this.socket.getOutputStream(), true);
                        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

                        this.out.println(params[0]);

                        this.msgStatus = true;

                        try {
                           sleep(125);
                        } catch (InterruptedException e) {
                           e.printStackTrace();
                        }

                        this.buffer = new char[this.socket.getReceiveBufferSize()];

                        this.in.read(this.buffer);

                        this.data = new String(this.buffer);

                        this.jsonResponse = new JSONObject(this.data);

                        System.out.println(this.jsonResponse);

                        this.socket.close();
                        this.out.close();
                        this.in.close();

                     }else{
                        this.socket = new Socket(IP, port);
                        this.out = new PrintWriter(this.socket.getOutputStream(), true);
                        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

                        this.out.println(params[0]);

                        this.msgStatus = true;

                        try {
                           sleep(125);
                        } catch (InterruptedException e) {
                           e.printStackTrace();
                        }

                        this.buffer = new char[this.socket.getReceiveBufferSize()];

                        this.in.read(this.buffer);

                        this.data = new String(this.buffer);

                        this.jsonResponse = new JSONObject(this.data);

                        System.out.println(this.jsonResponse);

                        this.list = new ArrayList<>();

                        for (int c = 0; c < jsonResponse.length(); c++) {
                           this.arrayResponse = (JSONArray) this.jsonResponse.get(Integer.toString(c));
                           this.list.add(arrayResponse);
                        }

                        this.socket.close();
                        this.out.close();
                        this.in.close();
                     }

               } catch (IOException | JSONException e) {
                     System.out.println("DEU ERRO");
                     e.printStackTrace();
               }
               return null;
            } 

      ```

   Apesar de ser de simples implementação, a classe AsyncTask nos atrapalha um pouco na hora de retornarmos os valores recebidos,
   então, para deixarmos mais simples o trabalho, criamos o método sendRequest(), que executa o método doInBackground() e nos retorna 
   os valores recebidos. Desse modo, precisamos apenas instanciar o objeto da classe ServerConnection e chamarmos o método sendRequest.

      ```public ArrayList<JSONArray> sendRequest(JSONObject request){
            try {
                  execute(request);
                  while(this.list == null){
                     sleep(100);
                  }
            } catch (InterruptedException e) {
                  e.printStackTrace();
            }

            return this.list;

         }
      ```
   Após isso, toda a parte de comunicação com o servidor e envio de requests já está feita, agora precisamosdefinir o que será enviado,
   nesse caso, utilizamos **JSON** para colocarmos as nossas informações em uma estrutura e criamos os métodos **prepareRequest()** para
   cada tipo de estrutura, assim, podemos passar os dados como parâmetro, receber o request ja prepará-lo, e depois enviá-lo.  

      ```public JSONObject prepareRequest(String request){
            try {
                  this.jsonRequest = new JSONObject();
                  this.jsonRequest.put("header", "gazeboindustries09082004");
                  this.jsonRequest.put("request", request);
            } catch (JSONException e) {
                  e.printStackTrace();
            }
            return jsonRequest;
         }
      
         public JSONObject prepareAddInteraction(String request, String key1, String key2, String key3, String res1, 
                                                String res2, String res3, String command){
            try {
                  this.jsonRequest = new JSONObject();
                  this.jsonRequest.put("header", "gazeboindustries09082004");
                  this.jsonRequest.put("request", request);
                  this.jsonRequest.put("keyWord1", key1);
                  this.jsonRequest.put("keyWord2", key2);
                  this.jsonRequest.put("keyWord3", key3);
                  this.jsonRequest.put("response1", res1);
                  this.jsonRequest.put("response2", res2);
                  this.jsonRequest.put("response3", res3);
                  this.jsonRequest.put("command", command);

            } catch (JSONException e) {
                  e.printStackTrace();
            }
            return jsonRequest;
         }

         public JSONObject prepareUpdateDevice(String request, String deleteName, int updateId, String device, 
                                                String desc, int actions){
            try {
                  this.jsonRequest = new JSONObject();
                  this.jsonRequest.put("header", "gazeboindustries09082004");
                  this.jsonRequest.put("request", request);
                  this.jsonRequest.put("deleteName", deleteName);
                  this.jsonRequest.put("updateId", updateId);
                  this.jsonRequest.put("device", device);
                  this.jsonRequest.put("description", desc);
                  this.jsonRequest.put("actions", actions);

            } catch (JSONException e) {
                  e.printStackTrace();
            }
            return jsonRequest;
         }

         (...)
      ``` 

   ### Activity principal
   Pelo fato da Activity principal ser a única no nosso app, ela tem a responsabilidade de comportar todos os
   fragments, através do FrameLayout, e o bottom Navigation. Quando ela é iniciada, procura pelo fragment
   principal da aplicação, que, nesse caso, é o HomeFragment.

   ### Fragments
   Os fragments, apesar de serem diferentes de uma activity, também precisam obrigatóriamente de uma classe
   JAVA de controle para cada fragment. Tendo isso em mente, foi necessário criar uma estrutura de pastas para 
   melhor organização do projeto, permitindo assim uma melhor compreensão. Então foi criada a seguinte estrutura:

   - Imagem estrutura

   Como o objetivo desse App é **Editar os dados do Banco de Dados**, precisamos ter a possibilidade de 
   inserir, atualizar, excluir e visualizar os dados das 4 tabelas (Interactions, Device, Projects, 
   HomeWorkManagement). Para que isso seja possível foram criados 3 tipos de fragments para cada tabela.

   - Fragment Principal: Esse fragment, é iniciado quando o respectivo ícone no Bottom navigation for
      clicado. Ele é responsável por **Mostrar** os dados contidos na tabela através de um ListView

   - View Fragment: Esse fragment é iniciado quando um determinado item do ListView for clicado. Ele mostra com
   mais detalhes a tupla da tabela, é ele o responsável por **Editar** ou **Excluir** a tupla.

   - Add Fragment: Esse fragment é iniciado quando o botão **Adicionar** localizado no Fragment principal é 
      clicado. Ele é responsável por adicionar uma nova tupla no banco de dados.

   Agora já temos cada fragment com sua responsabilidade. E ja temos o controle total do banco de dados pelo app.


# Considerações finais
   Após 3 meses trabalhando nesse projeto, finalmente cheguei em uma versão que me agrada. Porém considero longe 
   da versão final. Ainda existem implementações que devem ser feitas, como por exemplo, o Acesso da localização
   do telefone através do app, além de adicionar mais interações, comandos e devices.
   
