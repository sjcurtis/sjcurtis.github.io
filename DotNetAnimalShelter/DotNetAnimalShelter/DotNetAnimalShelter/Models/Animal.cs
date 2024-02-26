//////////////////////////////////////////////////////////////////////////
/// Developer: Shawn Curtis
/// Contact: shawn.curtis@snhu.edu
/// Date: 2/11/2024
/// Version: 1.0.0
/// Purpose: Animal Model. Used to store information as it moves from
/// controller to view. Each property is also mapped to a field in 
/// the database.
//////////////////////////////////////////////////////////////////////////

using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace DotNetAnimalShelter.Models;

public class Animal
{
	[BsonId]
	[BsonRepresentation(BsonType.ObjectId)]
	public ObjectId? Id { get; set; }


	[BsonElement("rec_num")]
	public Int32? RecNum { get; set; } = null!;


	[BsonElement("age_upon_outcome")]
	public string AgeUponOutcome { get; set; } = null!;


	[BsonElement("animal_id")]
	public string AnimalId { get; set; } = null!;


	[BsonElement("animal_type")]
	public string AnimalType { get; set; } = null!;


	[BsonElement("breed")]
	public string Breed { get; set; } = null!;


	[BsonElement("color")]
	public string Color { get; set; } = null!;


	[BsonElement("date_of_birth")]
	public string DateOfBirth { get; set; } = null!;


	[BsonElement("datetime")]
	public string DateTime { get; set; } = null!;


	[BsonElement("monthyear")]
	public string MonthYear { get; set; } = null!;


	[BsonElement("name")]
	public string Name { get; set; } = null!;


	[BsonElement("outcome_subtype")]
	public string OutcomeSubtype { get; set; } = null!;

	[BsonElement("outcome_type")]
	public string OutcomeType { get; set; } = null!;


	[BsonElement("sex_upon_outcome")]
	public string SexUponOutcome { get; set; } = null!;


	[BsonElement("location_lat")]
	public Double LocationLat { get; set; }


	[BsonElement("location_long")]
	public Double LocationLong { get; set; }


	[BsonElement("age_upon_outcome_in_weeks")]
	public Double AgeUponOutcomeInWeeks { get; set; }
}

