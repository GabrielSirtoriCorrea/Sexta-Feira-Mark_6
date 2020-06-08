import mysql.connector as mysqlConnector

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

    def getDevicesJsons(self):
        self.dataBaseCursor.execute('SELECT DeviceName, Device FROM Device')
        return self.dataBaseCursor.fetchall()

    def insertInteraction(self, key1, key2, key3, res1, res2, res3):
        self.dataBaseCursor.execute('INSERT INTO Interactions(KeyWord1, KeyWord2, KeyWord3, Response1, Response2, Response3) VALUES (%s,%s,%s,%s,%s,%s)', (key1, key2, key3, res1, res2, res3))
        self.dataBaseConnector.commit()

    def insertCommand(self, key1, key2, key3, res1, res2, res3, command):
        self.dataBaseCursor.execute('INSERT INTO Interactions(KeyWord1, KeyWord2, KeyWord3, Response1, Response2, Response3, Command) VALUES (%s,%s,%s,%s,%s,%s,%s)', (key1, key2, key3, res1, res2, res3, command))
        self.dataBaseConnector.commit()

    def insertHomeWork(self, type, subject, homeWork, delivery, desc):
        self.dataBaseCursor.execute('INSERT INTO HomeWorkManagement(HomeWorkType, HomeWorkSubject, HomeWork, HomeWorkDelivery, HomeWorkDescription) VALUES (%s,%s,%s,%s,%s)', (type, subject, homeWork, delivery, desc))
        self.dataBaseConnector.commit()

    def insertProject(self, name, language):
        self.dataBaseCursor.execute('INSERT INTO Projects(ProjectName, ProjectLanguages) VALUES (%s,%s)', (name, language))
        self.dataBaseConnector.commit()

    def insertDevice(self, name, desc, json):
        self.dataBaseCursor.execute('INSERT INTO Device(DeviceName, DeviceDescription, DeviceJson) VALUES (%s,%s,%s)', (name, desc, json))
        self.dataBaseConnector.commit()
    
    def getInteractionsHeader(self):
        self.dataBaseCursor.execute('DESC Interactions')
        return self.dataBaseCursor.fetchall()

