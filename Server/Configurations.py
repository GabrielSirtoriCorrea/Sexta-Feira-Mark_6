import json

def fridayConfigs():
    file = open('Configurations.json', 'r')
    return json.load(file)['FridayConfigs']

def interfaceConfigs():
    file = open('Configurations.json', 'r')
    return json.load(file)['InterfaceConfigs']

def serverConfigs():
    file = open('Configurations.json', 'r')
    return json.load(file)['ServerConfigs']

def setConfigs(list):
    file = open('Configurations.json', 'w')
    json.dump(list, file, indent=4)