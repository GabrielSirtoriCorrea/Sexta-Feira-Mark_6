import speech_recognition as sr 
import pyttsx3, random, json
from unicodedata import normalize
from socket import socket, AF_INET, SOCK_STREAM

def Recognition():
    recognizer = sr.Recognizer()

    with sr.Microphone() as source:
        recognizer.adjust_for_ambient_noise(source)

        print('Say:')

        sound = recognizer.listen(source)
        speech = recognizer.recognize_google(sound, language='pt').lower()
        print('You said: ', speech)

        return ' ' + speech + ' '

def speak(text):
    speaker = pyttsx3.init('sapi5')
    speaker.say(text)
    speaker.runAndWait()

def responseSelector():
    return random.randint(3, 5)

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

    print('Filtered: ', phraseFiltered)
    return phraseFiltered


class ServerConnection:
    def __init__(self):
        file = open('E:/Sexta-Feira-Mark_6/Configurations.json', 'r')
        configs =  json.load(file)['FridayConfigs']

        self.connection = socket(AF_INET, SOCK_STREAM, 0)
        #connection.connect((configs['ServerHost'], configs['Port'] ))      
        self.connection.connect(('192.168.0.5', 3000 ))     

        message = json.dumps({
                            'header': 'gazeboindustries09082004',
                            'request': 'getDevicesJsons'
        
        }).encode()

        self.connection.send(message)

        self.devicesJson = json.loads(self.connection.recv(1024).decode())

    def send(self, dict):
        message = json.dumps(dict).encode()

        self.connection.send(message)

        return self.connection.recv(1024)
   





