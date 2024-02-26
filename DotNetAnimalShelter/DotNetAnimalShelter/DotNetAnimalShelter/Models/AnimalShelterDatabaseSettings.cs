//////////////////////////////////////////////////////////////////////////
/// Developer: Shawn Curtis
/// Contact: shawn.curtis@snhu.edu
/// Date: 2/11/2024
/// Version: 1.0.0
/// Purpose: Stores the connection information for the database as a model.
//////////////////////////////////////////////////////////////////////////
namespace DotNetAnimalShelter.Models
{
	public class AnimalShelterDatabaseSettings
	{
		public string ConnectionString { get; set; } = null!;

		public string DatabaseName { get; set; } = null!;

		public string CollectionName { get; set; } = null!;
	}
}
