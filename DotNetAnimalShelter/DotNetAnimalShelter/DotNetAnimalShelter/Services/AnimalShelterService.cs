//////////////////////////////////////////////////////////////////////////
/// Developer: Shawn Curtis
/// Contact: shawn.curtis@snhu.edu
/// Date: 2/11/2024
/// Version: 1.0.0
/// Purpose: Used by the controller to interact with database.
//////////////////////////////////////////////////////////////////////////
using DotNetAnimalShelter.Models;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace AnimalShelterApi.Services;

public class AnimalShelterService
{
	private readonly IMongoCollection<Animal> _animalCollection;

	public AnimalShelterService(
		IOptions<AnimalShelterDatabaseSettings> animalShelterDatabaseSettings)
	{
		// Connection string to database
		var mongoClient = new MongoClient(
			animalShelterDatabaseSettings.Value.ConnectionString);


		// Database name
		var mongoDatabase = mongoClient.GetDatabase(
			animalShelterDatabaseSettings.Value.DatabaseName);

		// Animal Collection
		_animalCollection = mongoDatabase.GetCollection<Animal>(
			animalShelterDatabaseSettings.Value.CollectionName);
	}

	// Returns the entire collection of animals from the database.
	// Modify the skip to help debug the application. Skip will return less records and reduce the load time of the application.
	public List<Animal> GetAnimals() => _animalCollection.Find(_ => true).Skip(0).ToList();

}