import socketserver, json, time, os
from datetime import datetime
from DataBase import DataBaseConnection
from Configurations import serverConfigs, setConfigs

configs = serverConfigs()

host = configs['Host']
port = configs['Port']

print(configs['ID'])

print('---SERVER STARTED---')


class ClientManage(socketserver.BaseRequestHandler):
    def handle(self):
        dataBaseConnection = DataBaseConnection()

        dateTimeNow= datetime.now()
        print(f'Connected by {self.client_address} at {dateTimeNow.hour}:{dateTimeNow.minute} ')

        while True:
            data = self.request.recv(1024).decode('utf-8')
            print(data)

            try:
                if data:
                    clientRequest = json.loads(data)

                    if clientRequest['header'] == 'gazeboindustries09082004':
                        
                        if clientRequest['request'] == 'startFriday':
                            print(dataBaseConnection.getDevices())
                else:
                    break

            except:
                print('error')

server = socketserver.ThreadingTCPServer((host, port), ClientManage)
server.serve_forever()
