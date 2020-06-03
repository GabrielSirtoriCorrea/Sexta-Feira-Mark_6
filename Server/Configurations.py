import json

def serverConfigs():
    file = open('Configurations.json', 'r')
    return json.load(file)['ServerConfigs']

def setConfigs(list):
    file = open('Configurations.json', 'w')
    json.dump(list, file, indent=4)