from Functions import ServerConnection, setRequestJson
import os

connection = ServerConnection()

while True:
    response = connection.send(setRequestJson('getDevicesStatus', 'Server', 0, '.com'))

    if response['Friday']['action'] == 1: 
        os.startfile(os.getcwd().replace('Sexta-Feira(A.I.)', '') + 'Sexta-FeiraInterface/dist/Sexta-FeiraInterface.jar')
        print('Started')
        break