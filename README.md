# CleverAppName
Demo app for potential uses of the foursquare places api. Application written in Kotlin.

# Getting Started
Download latest release. <br />
Install on emulator / device. <br />
Run App. <br />
  
Open app, home screen will load with a search input (EditText) and an action button (AppCompatButton) with custom background for an app specific click visual. <br />
Users can input a location into the input box and then press the action button to run an async call using the Anko library 
Once the call has been completed Anko calls the main (ui) thread to handle the data. <br />
The data is then deserialised from JSON format into kotlin classes through the use of the Gson. <br />
The recyclerview dataset is then updated and the adapter is notified causing the ui to update to reflect the changes made to the data set. <br />

# Built With
(https://github.com/Kotlin/anko) - Handling Async functions and subsequent UI/Main thread updates. <br />
(https://github.com/google/gson) - Deserializing JSON data. <br />

# More Info
Using a basic split of Activity/Layout files as the implementation requires little data handling / logic and is small enough to be contained in a single activity as it is a proof of concept not designed to be a full implementation.  <br />

Recyclerview used to display results due to lightweight nature and ability to display large amounts of data responsively with minimal load times. <br />

Potential expansion from recyclerview to allow individual items to have a detailed view reached through an onClick function

