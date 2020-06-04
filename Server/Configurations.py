import json

def serverConfigs():
    file = open('E:/Sexta-Feira-Mark_6/Configurations.json', 'r')
    return json.load(file)['ServerConfigs']

def setConfigs(list):
    file = open('Configurations.json', 'w')
    json.dump(list, file, indent=4)