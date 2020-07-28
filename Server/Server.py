import socketserver, json, time, os
from datetime import *
from DataBase import DataBaseConnection
from Configurations import serverConfigs, setConfigs

configs = serverConfigs()

host = configs['Host']
port = configs['Port']

print('---SERVER STARTED---')

def convertList(dataBaseList): 
    indexes = list()

    for deviceIndex in range(0, len(dataBaseList)):
        indexes.append(str(deviceIndex))

    dictionary = dict(zip(indexes, dataBaseList))

    print(dictionary)
    return dictionary

def convertHeader(header):
    headerList = list()

    for index in range(0, len(header)):
        headerList.append(header[index][0])

    dictionary = dict(zip(['Header'], [headerList]))

    return dictionary

def getDevicesStatus():
    file = open('E:/Sexta-Feira-Mark_6/Server/DevicesStatus.json', 'r')
    return json.load(file)
                                                                                                                                                                                                                                                                                                                                                            
def setDevicesStatus(receiverID, action, url):
    readFile = open('E:/Sexta-Feira-Mark_6/Server/DevicesStatus.json', 'r')
    
    newJson = json.load(readFile)

    print(newJson)

    newJson[receiverID]['action'] = action
    newJson[receiverID]['url'] = url

    writeFile = open('E:/Sexta-Feira-Mark_6/Server/DevicesStatus.json', 'w')
    json.dump(newJson, writeFile, indent=4)

class ClientManage(socketserver.BaseRequestHandler):
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

                    if clientRequest['header'] == 'gazeboindustries09082004':
                        
                        if clientRequest['request'] == 'startFriday':
                            os.startfile('E:\\Sexta-Feira-Mark_6\\Sexta-FeiraInterface\\dist\\Sexta-FeiraInterface.jar')
                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'getDevices':
                            device = convertList(dataBaseConnection.getDevices())

                            self.request.send(json.dumps(device).encode())

                        elif clientRequest['request'] == 'getInteractions':
                            interactions = convertList(dataBaseConnection.getInteractions())

                            self.request.send(json.dumps(interactions).encode())

                        elif clientRequest['request'] == 'getProjects':
                            projects = convertList(dataBaseConnection.getProjects())
                            self.request.send(json.dumps(projects).encode())

                        elif clientRequest['request'] == 'getHomeWorks':
                            listHomeWorks = list()
                            dataBaseHomeWorks = dataBaseConnection.getHomeWorks()

                            for homeWork in dataBaseHomeWorks:
                                homeWork = list(homeWork)
                                date = datetime.strftime(homeWork[4], '%d/%m/%Y')
                                homeWork[4] = date

                                listHomeWorks.append(homeWork)

                            homeWorkconverted = convertList(listHomeWorks)

                            self.request.send(json.dumps(homeWorkconverted).encode())

                        elif clientRequest['request'] == 'getDevicesStatus':
                            print('Enviado')
                            self.request.send(json.dumps(getDevicesStatus()).encode())

                        elif clientRequest['request'] == 'setDevicesStatus':
                            setDevicesStatus(clientRequest['receiverID'], clientRequest['action'], clientRequest['url'])
                            self.request.send(json.dumps(getDevicesStatus()).encode())
                        
                        elif clientRequest['request'] == 'insertInteraction':
                            print('adding')

                            dataBaseConnection.insertInteraction(clientRequest['keyWord1'], 
                            clientRequest['keyWord2'],
                            clientRequest['keyWord3'],
                            clientRequest['response1'],
                            clientRequest['response2'],
                            clientRequest['response3'],
                            clientRequest['command'])

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'insertDevice':
                            dataBaseConnection.insertDevice(clientRequest['device'], 
                            clientRequest['description'],
                            clientRequest['actions'])

                            readFile = open('E:/Sexta-Feira-Mark_6/Server/DevicesStatus.json', 'r')
    
                            newJson = json.load(readFile)

                            print(newJson)

                            newJson[clientRequest['device']] = {'action': 0, 'url': '.com'}

                            writeFile = open('E:/Sexta-Feira-Mark_6/Server/DevicesStatus.json', 'w')
                            json.dump(newJson, writeFile, indent=4)

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'insertHomeWork':
                            print('adding')
                            dataBaseConnection.insertHomeWork(clientRequest['type'], 
                            clientRequest['subject'],
                            clientRequest['homeWork'],
                            clientRequest['delivery'],
                            clientRequest['description'])

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'insertProject':
                            dataBaseConnection.insertProject(clientRequest['type'], clientRequest['project'])

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'deleteInteraction':
                            print('Deleting')
                            dataBaseConnection.deleteInteraction(clientRequest['deleteId'])

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'deleteDevice':
                            dataBaseConnection.deleteDevice(clientRequest['deleteId'])

                            readFile = open('E:/Sexta-Feira-Mark_6/Server/DevicesStatus.json', 'r')
    
                            newJson = json.load(readFile)

                            print(newJson)

                            del newJson[clientRequest['deleteName']]

                            writeFile = open('E:/Sexta-Feira-Mark_6/Server/DevicesStatus.json', 'w')
                            json.dump(newJson, writeFile, indent=4)
                            
                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'deleteHomeWork':
                            dataBaseConnection.deleteHomeWork(clientRequest['deleteId'])

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'deleteProject':
                            dataBaseConnection.deleteProject(clientRequest['deleteId'])

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'updateProject':
                            dataBaseConnection.updateProject(clientRequest['updateId'], 
                            clientRequest['type'],
                            clientRequest['project'])

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'updateDevice':
                            dataBaseConnection.updateDevice(clientRequest['updateId'], 
                            clientRequest['device'],
                            clientRequest['description'],
                            clientRequest['actions'])

                            readFile = open('E:/Sexta-Feira-Mark_6/Server/DevicesStatus.json', 'r')
    
                            newJson = json.load(readFile)

                            print(newJson)

                            del newJson[clientRequest['deleteName']]

                            newJson[clientRequest['device']] = {'action': 0, 'url': '.com'}

                            writeFile = open('E:/Sexta-Feira-Mark_6/Server/DevicesStatus.json', 'w')
                            json.dump(newJson, writeFile, indent=4)

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'updateHomeWork':
                            dataBaseConnection.updateHomeWork(clientRequest['updateId'], 
                            clientRequest['type'],
                            clientRequest['subject'],
                            clientRequest['homeWork'],
                            clientRequest['delivery'],
                            clientRequest['description'])

                            self.request.send(json.dumps({'requestStatus': True}).encode())

                        elif clientRequest['request'] == 'updateInteraction':
                            dataBaseConnection.updateInteraction(clientRequest['updateId'], 
                            clientRequest['keyWord1'],
                            clientRequest['keyWord2'],
                            clientRequest['keyWord3'],
                            clientRequest['response1'],
                            clientRequest['response2'],
                            clientRequest['response3'],
                            clientRequest['command'])

                            self.request.send(json.dumps({'requestStatus': True}).encode())
                else:
                    break

            except:
                print('error')

server = socketserver.ThreadingTCPServer((host, port), ClientManage)
server.serve_forever()