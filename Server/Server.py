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

class ClientManage(socketserver.BaseRequestHandler):
    def handle(self):
        dataBaseConnection = DataBaseConnection()

        dateTimeNow= datetime.now()
        print(f'Connected by {self.client_address} at {dateTimeNow.hour}:{dateTimeNow.minute} ')

        while True:
            data = self.request.recv(1024).decode()
            print(data)

            try:
                if data:
                    clientRequest = json.loads(data)

                    if clientRequest['header'] == 'gazeboindustries09082004':
                        
                        if clientRequest['request'] == 'getDevicesJsons':
                            '''devicesJsons = dataBaseConnection.getDevices()
                            devicesIndex = list()

                            for deviceIndex in range(1, len(devicesJsons)+1):
                                devicesIndex.append('Device ' + str(deviceIndex))

                            device = dict(zip(devicesIndex, devicesJsons))
                            print(device)'''
                            device = convertList(dataBaseConnection.getDevices())

                            self.request.send(json.dumps(device).encode())

                        if clientRequest['request'] == 'getInteractions':
                            interactions = convertList(dataBaseConnection.getInteractions())

                            self.request.send(json.dumps(interactions).encode())

                        if clientRequest['request'] == 'getInteractionsHeader':
                            header = convertHeader(dataBaseConnection.getInteractionsHeader())

                            self.request.send(json.dumps(header).encode())

                        if clientRequest['request'] == 'sendToOrther':
                            print('Enviado')
                            self.request.send(json.dumps(clientRequest).encode())
                        
                else:
                    break

            except:
                print('error')

server = socketserver.ThreadingTCPServer((host, port), ClientManage)
server.serve_forever()
