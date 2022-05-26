# Intent API

Intent API, is used to determine what to return as response/answer back to the user.
This is done with the information it gets back from the NLP API, this information is the 
Intent and Entity(key:value) of the sentence. Based on the intent and the entity the Intent API 
knows what to return back to the user.

example : <br>
localhost:9090/intent?key="key"&value="value"<br>
localhost:9090/currentWeather?key=location&value=Aruba
<br>
key:value in this case is the key:value of the entity

