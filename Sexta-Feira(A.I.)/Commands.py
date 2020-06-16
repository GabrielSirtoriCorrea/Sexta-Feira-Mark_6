import Functions, os
from datetime import datetime

def callCommand(command, speech, connection):
    commands = globals()

    try:
        commands[command](speech, connection)
    except:
        print('No command corresponding')

def dateTime(speech, connection):
    Functions.speak(f'Agora são {datetime.now().hour} horas e {datetime.now().minute} minutos ')

def updateFriday(speech, connection):
    Functions.speak('Certo. Estou baixando as atualizações de meu sistema')
    os.system('git pull origin master')

def sendCloseToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 0, '.com'))

def sendInteractionsToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 1, '.com'))

def sendHomeWorksToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 2, '.com'))

def sendProjectsToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 3, '.com'))

def sendDevicesToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 4, '.com'))

def sendPeriodicTableToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 5, 'E:\\Sexta-Feira-Mark_6\\Images\\TabelaPeriodica.jpg'))

