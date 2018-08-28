# CleverAppName
Demo app for potential uses of the foursquare places api. Application written in Kotlin.

Open app, home screen will load with a search input (EditText) and an action button (AppCompatButton). 
Users can input a location into the input box and then press the action button to run an async call using the Anko library (https://github.com/Kotlin/anko)
Once the call has been completed Anko calls the main (ui) thread to handle the data. 
The data is then deserialised from JSON format into kotlin classes through the use of the Gson (https://github.com/google/gson)
The recyclerview dataset is then updated and the adapter is notified causing the ui to update to reflect the changes made to the data set.


Using a basic split of Activity/Layout files as the implementation requires little data handling / logic and is small enough to be contained in a single activity as it is a proof of concept not designed to be a full implementation. 

Recyclerview used to display results due to lightweight nature and ability to display large amounts of data responsively with minimal load times.

Potential expansion from recyclerview to allow individual items to have a detailed view reached through an onClick function

