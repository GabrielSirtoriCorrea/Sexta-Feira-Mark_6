import socketserver, json, time, os
from datetime import datetime
from DataBase import DataBaseConnection
from Configurations import serverConfigs, setConfigs

configs = serverConfigs()

#host = configs['Host']
#port = configs['Port']

host = '192.168.0.5'
port = 3000


print('---SERVER STARTED---')


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
                            devicesJsons = dataBaseConnection.getDevices()
                            devicesIndex = list()

                            for deviceIndex in range(1, len(devicesJsons)+1):
                                devicesIndex.append('Device ' + str(deviceIndex))

                            device = dict(zip(devicesIndex, devicesJsons))
                            print(device)
                            self.request.send(json.dumps(device).encode())
                else:
                    break

            except:
                print('error')

server = socketserver.ThreadingTCPServer((host, port), ClientManage)
server.serve_forever()
