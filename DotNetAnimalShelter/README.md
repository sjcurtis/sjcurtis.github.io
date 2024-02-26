# .NET Animal Shelter
## Getting Started
This project uses a MongoDB to store a collection of data from an animal shelter. .NET connects to the database and will pull the entire collection and store it in a model. 
The model is passed to a view and displayed as a table for users. DataTables makes the table searchable with paging options. Pie charts are displayed below the table. The controller
parses the data to create specific charts. Chart data is passed to the view from the controller.

Use the following instructions to setup the project

### Install MongoDB Locally
You'll need to install a MongoDB locally or connect to an existing one. Use the instructions from the following link. https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/

### Install MongoDB Tools
The following tools are needed. https://www.mongodb.com/try/download/database-tools

# Create admin user
Use the following commands to add an admin user to the database. New databases won't have any users which prevent the collection from being imported.

`use admin`
`db.createUser( { user: "admin", pwd: "1234", roles: [ "userAdminAnyDatabase" ] } )`

# List users
User the following command to verify the user was added.

`db.getUsers()`

### Add Animal Shelter Collection
The following command will use the admin password, created in the previous step, and import the animal collection from the aac_shelter_outcomes.csv.

`./mongoimport.exe --username="admin" --password="1234" --port=27017 --host=127.0.0.1 --db AAC --collection animals --authenticationDatabase admin --drop ./AAC.animals.csv --type=csv --fields="rec_num","age_upon_outcome","animal_id","animal_type","breed","color","date_of_birth","datetime","monthyear","name","outcome_subtype","outcome_type","sex_upon_outcome","location_lat","location_long","age_upon_outcome_in_weeks"`

### Running the Application
Use the following command to run the application.

`dotnet run`

## References
The foundation of the charts are based on CanvasJS. Use the following link to get a better understanding of the charts.
https://canvasjs.com/docs/charts/integration/asp-net-mvc-charts/

The following link provides an example of integrating MongoDB and .NET. The documentation here will help you to understand the project better.
https://learn.microsoft.com/en-us/aspnet/core/tutorials/first-mongo-app?view=aspnetcore-8.0&tabs=visual-studio

DataTables handles the formating of tables. It also adds searching and paging. Use the following link to learn more about how to use DataTables.
https://datatables.net/examples/basic_init/zero_configuration.html