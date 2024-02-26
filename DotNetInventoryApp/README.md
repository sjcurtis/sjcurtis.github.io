# Inventory Application

## Description
This application is used to track inventory and quantities. Application also allows for users to enroll and login. Users can create their own list of inventory where they can maintain quantities. Users can also delete items from their inventory.

## Usage

### Add migration
Changes to the database structure need to be added to a migration before they can be applied to a database update. Use the following command to create a migration file. Change 'Initial' to something describing the database change.

`dotnet ef migrations add Initial`

### Apply Migrations

The update command applies migrations to the database. Migrations need to be created using the migrations add command.

`dotnet ef database update`

### Building the Project

The build command will build the project. Check the output window for build errors.

`dotnet build`

### Running the Project

Running the project will start an IIS Express server. Open a browser and navigate to https://localhost:7023. Use the following command to start the application.

`dotnet run`

## References
- https://learn.microsoft.com/en-us/ef/