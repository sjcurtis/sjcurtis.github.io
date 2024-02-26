# Animal Shelter Notebook
This project consists of a Jupyter Notebook that connects to a MongoDB via a Python CRUD API. The database has a collect that contains data from an animal shelter.

## Getting Started
I recommend running this from VS Code. Dependencies may be required to start the project. See the dependencies. The connection string, in the notebook and Python script, may need to be modified depending upon your setup.

### Install MongoDB Locally

### Install MongoDB Tools
https://www.mongodb.com/try/download/database-tools

# Create admin user
Use the following commands to add an admin user to the database.
`use admin`
`db.createUser( { user: "admin", pwd: "1234", roles: [ "userAdminAnyDatabase" ] } )`

# List users
User the following command to verify the user was added.
`db.getUsers()`

### Add Animal Shelter Collection
The following command will use the admin password, created in the previous step, and import the anmial collection from the aac_shelter_outcomes.csv.

`./mongoimport.exe --username="admin" --password="1234" --port=27017 --host=127.0.0.1 --db AAC --collection animals --authenticationDatabase admin --drop ./AAC.animals.csv --type=csv --fields="rec_num","age_upon_outcome","animal_id","animal_type","breed","color","date_of_birth","datetime","monthyear","name","outcome_subtype","outcome_type","sex_upon_outcome","location_lat","location_long","age_upon_outcome_in_weeks"`
I recommend running this from VS Code. Dependencies may be required to start the project. See the dependencies.

### Dependencies Needed for Jupyter

- pip install -U --force-reinstall ipykernel
- pip install jupyter-dash
- pip install pandas
- pip install matplotlib
- pip install pymongo