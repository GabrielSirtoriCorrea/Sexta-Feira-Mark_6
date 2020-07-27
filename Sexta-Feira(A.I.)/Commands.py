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
    os.system('git fetch')
    Functions.speak('Estou instalado as atualizações e reiniciarei o computador')
    os.system('shutdown /r')
    os.system('git reset --hard origin/master')

def sendCloseToInterface(speech, connection):
    Functions.setFridayComunication(0, None, ".com")
    
def sendInteractionsToInterface(speech, connection):
    response = connection.send(Functions.setRequestJson('getInteractions', 'Interface', 1, '.com'))
    Functions.setFridayComunication(1, response, ".com")

def sendHomeWorksToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 2, '.com'))

def sendProjectsToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 3, '.com'))

def sendDevicesToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 4, '.com'))

def sendPeriodicTableToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 5, 'E:\\Sexta-Feira-Mark_6\\Images\\TabelaPeriodica.jpg'))

def sendPoliticalBrazilToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 5, 'E:\\Sexta-Feira-Mark_6\\Images\\Brasil Político.jpg'))

def sendEletronicDestToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 5, 'E:\\Sexta-Feira-Mark_6\\Images\\diagrama-de-pauling.jpg'))

def sendAnglesTableToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 5, 'E:\\Sexta-Feira-Mark_6\\Images\\TabelaSenoCossenoTangente.png'))

def sendFisicEquationsToInterface(speech, connection):
    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 5, 'E:\\Sexta-Feira-Mark_6\\Images\\Equações Dinamica.png'))

