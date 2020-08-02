import mysql.connector as mysqlConnector
from datetime import datetime

class DataBaseConnection:
    def __init__(self):
        self.dataBaseConnector =  mysqlConnector.connect(host='localhost', user='root', password='Gazao015', 
                                                        database='sextafeiradatabase')

        self.dataBaseCursor = self.dataBaseConnector.cursor()

    def getInteractions(self):
        self.dataBaseCursor.execute('SELECT * FROM Interactions')
        return self.dataBaseCursor.fetchall()

    def getHomeWorks(self):
        self.dataBaseCursor.execute('SELECT * FROM HomeWorkManagement')
        return self.dataBaseCursor.fetchall()

    def getProjects(self):
        self.dataBaseCursor.execute('SELECT * FROM Projects')
        return self.dataBaseCursor.fetchall()

    def getDevices(self):
        self.dataBaseCursor.execute('SELECT * FROM Device')
        return self.dataBaseCursor.fetchall()

    def insertInteraction(self, key1, key2, key3, res1, res2, res3, command):
        self.dataBaseCursor.execute('INSERT INTO Interactions(KeyWord1, KeyWord2, KeyWord3, Response1, Response2, Response3, Command) VALUES (%s,%s,%s,%s,%s,%s,%s)', (key1, key2, key3, res1, res2, res3, command))
        self.dataBaseConnector.commit()

    def insertHomeWork(self, Type, subject, homeWork, delivery, desc):
        self.dataBaseCursor.execute('INSERT INTO HomeWorkManagement(HomeWorkType, HomeWorkSubject, HomeWork, HomeWorkDelivery, HomeWorkDescription) VALUES (%s,%s,%s,%s,%s)', (Type, subject, homeWork, delivery, desc))
        self.dataBaseConnector.commit()

    def insertProject(self, type, name):
        self.dataBaseCursor.execute('INSERT INTO Projects(ProjectType, ProjectName) VALUES (%s,%s)', (type, name))
        self.dataBaseConnector.commit()

    def insertDevice(self, name, desc, actions):
        self.dataBaseCursor.execute('INSERT INTO Device(DeviceName, DeviceDescription, DeviceActions) VALUES (%s,%s,%s)', (name, desc, actions))
        self.dataBaseConnector.commit()


    def updateInteraction(self, updateId, key1, key2, key3, res1, res2, res3, command):
        self.dataBaseCursor.execute(f"UPDATE Interactions set KeyWord1 = '{key1}', KeyWord2 = '{key2}', KeyWord3 = '{key3}',  Response1 = '{res1}', Response2 = '{res2}', Response3 = '{res3}', Command = '{command}' WHERE InteractionId = '{updateId}' ")
        self.dataBaseConnector.commit()

    def updateHomeWork(self, updateId, type, subject, homeWork, delivery, desc):
        self.dataBaseCursor.execute(f"UPDATE HomeWorkManagement set HomeWorkType = '{type}', HomeWorkSubject = '{subject}', HomeWork = '{homeWork}', HomeWorkDelivery = '{delivery}', HomeWorkDescription = '{desc}' WHERE HomeWorkID ='{updateId}' ")
        self.dataBaseConnector.commit()

    def updateProject(self, updateId, type, name):
        self.dataBaseCursor.execute(f"UPDATE Projects SET ProjectName='{name}', ProjectType='{type}' WHERE ProjectID='{updateId}' ")
        self.dataBaseConnector.commit()

    def updateDevice(self, updateId, name, desc, actions):
        self.dataBaseCursor.execute(f"UPDATE Device SET DeviceName = '{name}', DeviceDescription = '{desc}', DeviceActions = '{actions}' WHERE DeviceID = '{updateId}' ")
        self.dataBaseConnector.commit()


    def deleteInteraction(self, deleteID):
        self.dataBaseCursor.execute('DELETE FROM Interactions WHERE InteractionID = %d' % (deleteID))
        self.dataBaseConnector.commit()

    def deleteDevice(self, deleteID):
        self.dataBaseCursor.execute('DELETE FROM Device WHERE DeviceID = %d' % (deleteID))
        self.dataBaseConnector.commit()

    def deleteHomeWork(self, deleteID):
        self.dataBaseCursor.execute('DELETE FROM HomeWorkManagement WHERE HomeWorkID = %d' % (deleteID))
        self.dataBaseConnector.commit()

    def deleteProject(self, deleteID):
        self.dataBaseCursor.execute('DELETE FROM Projects WHERE ProjectID = %d' % (deleteID))
        self.dataBaseConnector.commit()
