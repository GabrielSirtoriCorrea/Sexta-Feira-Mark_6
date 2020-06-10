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

    connection.send(Functions.setRequestJson('setDevicesStatus', 'Interface', 1, ".com"))

def updateFriday(speech, connection):
    Functions.speak('Certo. Estou baixando as atualizações de meu sistema')
    os.system('git pull origin master')

