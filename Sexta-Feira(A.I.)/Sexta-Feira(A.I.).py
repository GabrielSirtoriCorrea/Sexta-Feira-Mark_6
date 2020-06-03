import Functions

server = Functions.ServerConnection()

msg = {
        'header': 'gazeboindustries09082004',
        'request': 'getDevicesJsons'
        }


#print(server.send(msg))