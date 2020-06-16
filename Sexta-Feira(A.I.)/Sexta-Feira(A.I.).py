import Functions
from Commands import callCommand

connection, interactions = Functions.setup()

while True:  
    speech = Functions.languageUnderstanding(Functions.Recognition())
 
    for interaction in interactions:
        if interaction[1][1] in speech and interaction[1][2] in speech and interaction[1][3] in speech:
            callCommand(interaction[1][7], speech, connection)
            Functions.speak(interaction[1][Functions.responseSelector()])
            break

