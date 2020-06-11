import socketserver, json, time, os
from datetime import datetime
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
                        
                        if clientRequest['request'] == 'getDevicesJsons':
                            device = convertList(dataBaseConnection.getDevices())

                            self.request.send(json.dumps(device).encode())

                        elif clientRequest['request'] == 'getInteractions':
                            interactions = convertList(dataBaseConnection.getInteractions())

                            self.request.send(json.dumps(interactions).encode())

                        elif clientRequest['request'] == 'getInteractionsHeader':
                            header = convertHeader(dataBaseConnection.getInteractionsHeader())

                            self.request.send(json.dumps(header).encode())

                        elif clientRequest['request'] == 'getDevicesStatus':
                            print('Enviado')
                            self.request.send(json.dumps(getDevicesStatus()).encode())

                        elif clientRequest['request'] == 'setDevicesStatus':
                            try:
                                setDevicesStatus(clientRequest['receiverID'], clientRequest['action'], clientRequest['url'])
                                self.request.send(json.dumps(getDevicesStatus()).encode())
                            except:
                                print('ERROR')
                        
                else:
                    break

            except:
                print('error')

server = socketserver.ThreadingTCPServer((host, port), ClientManage)
server.serve_forever()
