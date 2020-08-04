import speech_recognition as sr 
import pyttsx3, random, json
from unicodedata import normalize
from socket import socket, AF_INET, SOCK_STREAM
from datetime import datetime
from threading import Thread

def Recognition():
    recognizer = sr.Recognizer()

    try:
        with sr.Microphone() as source:
            recognizer.adjust_for_ambient_noise(source)

            print('Say:')

            sound = recognizer.listen(source)
            speech = recognizer.recognize_google(sound, language='pt').lower()
            print('You said: ', speech)

            return ' ' + speech + ' '
    except:
        return ''

class Speaker(Thread):
    def __init__(self, text):
        Thread.__init__(self)
        self.text = text

    def run(self):
        speaker = pyttsx3.init('sapi5')
        speaker.say(self.text)
        speaker.runAndWait()

def speak(text):
    speak = Speaker(text)
    speak.start()

def responseSelector():
    return random.randint(4, 6)

def languageUnderstanding(phrase):
    phraseFilter = [' o ', ' a ', ' os ', ' as ', ' um ', ' uma ', ' uns ', ' umas ', ' ante ', ' apos ',
                    ' ate ', ' com ',' contra ', ' de ' ,
                    ' desde ', ' entre ', ' para ', ' perante ', ' por ', ' sem ', ' sob ', ' sobre ',
                    ' como ', ' e ', ' ainda ', ' tambem ', ' contudo ', ' entretanto ', ' mas ', ' entanto ',
                    ' porem ', ' todavia ', ' assim ', ' entao ', ' logo ', ' pois ', ' porque ', ' por ',
                    ' que ', ' para ', ' no ',' na ']

    phraseFiltered = normalize('NFKD', phrase).encode('ASCII', 'ignore').decode('ASCII')
    
    for word in phraseFilter:
        if word in phraseFiltered:
            phraseFiltered = phraseFiltered.replace(word, ' ')

    print('Filtered:', phraseFiltered)
    return phraseFiltered

def setRequestJson(request, receiverID, action, url):
    requestJson = json.dumps({
                'header': 'gazeboindustries09082004',
                'receiverID': receiverID,
                'request': request,
                'action': action,
                'url': url
        
        })

    return requestJson

def setup():
    setFridayComunication(0, None, ".com")

    server = ServerConnection()

    interactions = list(server.send(setRequestJson('getInteractions', 'server', 1, ".com")).items())

    hour = int(datetime.now().hour)
    if 5 <= hour <= 11:
        speak('Bom dia chefe!')
    elif 12 <= hour <= 17:
        speak('Boa tarde chefe!')
    else:
        speak('Boa noite chefe!')


    return [server, interactions]

class ServerConnection:
    def __init__(self):
        try:
            file = open('/Sexta-Feira-Mark_6/Configurations.json', 'r')
            configs =  json.load(file)['FridayConfigs']

            self.connection = socket(AF_INET, SOCK_STREAM, 0)
            self.connection.connect((configs['ServerHost'], configs['Port'] ))
        except:
            print('ERRO AO CONECTAR-SE COM O SERVIDOR')      
        
    def send(self, message):
        self.connection.send(message.encode())

        return json.loads(self.connection.recv(5800).decode('utf-8'))

def setFridayComunication(action, content, url):
    filePath = 'E:/Sexta-Feira-Mark_6/Sexta-FeiraInterface/src/com/friday/FridayComunication.json'
    readFile = open(filePath, 'r')
    newJson = json.load(readFile)

    print(newJson)

    newJson['action'] = action
    newJson['content'] = content
    newJson['url'] = url

    writeFile = open(filePath, 'w')
    json.dump(newJson, writeFile, indent=4)



