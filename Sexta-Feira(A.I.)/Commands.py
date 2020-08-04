import Functions, os
from datetime import datetime

def getDirectory(path):
    return os.getcwd().replace('Sexta-FeiraInterface', '') + path

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
    response = connection.send(Functions.setRequestJson('getHomeWorks', 'Interface', 2, '.com'))
    Functions.setFridayComunication(2, response, ".com")

def sendProjectsToInterface(speech, connection):
    response = connection.send(Functions.setRequestJson('getProjects', 'Interface', 3, '.com'))
    Functions.setFridayComunication(3, response, ".com")

def sendDevicesToInterface(speech, connection):
    response = connection.send(Functions.setRequestJson('getDevices', 'Interface', 4, '.com'))
    Functions.setFridayComunication(4, response, ".com")

def sendPeriodicTableToInterface(speech, connection):
    Functions.setFridayComunication(5, None, getDirectory('Images\TabelaPeriodica.jpg'))

def sendPoliticalBrazilToInterface(speech, connection):
    Functions.setFridayComunication(5, None, getDirectory('Images\Brasil Político.jpg'))

def sendEletronicDestToInterface(speech, connection):
    Functions.setFridayComunication(5, None, getDirectory('Images\diagrama-de-pauling.jpg'))

def sendAnglesTableToInterface(speech, connection):
    Functions.setFridayComunication(5, None, getDirectory('Images\TabelaSenoCossenoTangente.png'))

def sendFisicEquationsToInterface(speech, connection):
    Functions.setFridayComunication(5, None, getDirectory('Images\Equações Dinamica.png'))

