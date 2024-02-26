//////////////////////////////////////////////////////////////////////////
/// Developer: Shawn Curtis
/// Contact: shawn.curtis@snhu.edu
/// Date: 2/11/2024
/// Version: 1.0.0
/// Purpose: Returns data from database to view. Also handles modifying 
/// chart data and formating charts.
//////////////////////////////////////////////////////////////////////////
using AnimalShelterApi.Services;
using ChartJSCore.Helpers;
using ChartJSCore.Models;
using DotNetAnimalShelter.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace DotNetAnimalShelter.Controllers
{
    public class HomeController : Controller
    {
		private readonly AnimalShelterService _animalShelterService;
		private readonly List<Animal> _animals;

		public HomeController(AnimalShelterService animalShelterService)
        {
            _animalShelterService = animalShelterService;

			// Pull the list of animals just once for the report to reduce the time it takes to pull data.
			_animals = _animalShelterService.GetAnimals();
		}

		public IActionResult Index()
        {

			List<string> breeds = new();
			List<double?> breedCounts = new();
			List<string> sexUponOutcome = new();
			List<double?> sexUponOutcomeCounts = new();
			List<string> animalTypes = new();
			List<double?> animalTypeCounts = new();
			List<string> outcomeTypes = new();
			List<double?> outcomeTypeCounts = new();


			// Call function to populate breeds and counts 
			GetBreedCountData(ref breeds, ref breedCounts);
			GetSexUponOutcomeCountData(ref sexUponOutcome, ref sexUponOutcomeCounts);
			GetAnimalTypeCountData(ref animalTypes, ref animalTypeCounts);
			GetOutcomeCountData(ref outcomeTypes, ref outcomeTypeCounts);

			// Create charts
			Chart breedChart = GeneratePieChart("Count of Breeds", breeds, breedCounts);
			Chart sexUponOutcomeChart = GeneratePieChart("Count of Outcomes", sexUponOutcome, sexUponOutcomeCounts);
			Chart animalTypeChart = GeneratePieChart("Count of Animal Types", animalTypes, animalTypeCounts);
			Chart outcomeTypeChart = GeneratePieChart("Count of Outcome Types", outcomeTypes, outcomeTypeCounts);

			// Pass chart to view
			ViewData["BreedCountData"] = breedChart;
			ViewData["SexUponOutcomeCountData"] = sexUponOutcomeChart;
			ViewData["AnimalTypeCountData"] = animalTypeChart;
			ViewData["OutcomeTypeCountData"] = outcomeTypeChart;

			return View(_animals);
        }

		/// <summary>
		/// Returns a list of breeds and a corresponding list of quantities.
		/// </summary>
		/// <param name="breeds">A list of breeds to append additional breeds to.</param>
		/// <param name="values">A list of breed quantities to append additional breed quantities to.</param>
		private void GetBreedCountData(ref List<string> breeds, ref List<double?> values)
		{
			// Group by breed and get count of breeds that have quantities above 50
			var breedCount = _animals
						.GroupBy(animal => animal.Breed)
						.Select(group => new {
							Breed = group.Key,
							Count = group.Count()
						}).Where(group => group.Count > 50);


			// Separate breed and count into a separate lists.
			foreach (var item in breedCount)
			{
				breeds.Add(item.Breed);
				values.Add(item.Count);
			}
		}

		/// <summary>
		/// Returns a list of animal types and a corresponding list of quantities.
		/// </summary>
		/// <param name="outcome">A list of animal types to append additional values to.</param>
		/// <param name="values">A list of animal types quantities to append additional quantities to.</param>
		private void GetAnimalTypeCountData(ref List<string> outcome, ref List<double?> values)
		{			
			// Group by sex upon outcome
			var animalTypeCount = _animals
			.GroupBy(animal => animal.AnimalType)
			.Select(group => new
			{
				Type = group.Key,
				Count = group.Count()
			});


			// Separate type and count into a separate lists.
			foreach (var item in animalTypeCount)
			{
				outcome.Add(item.Type);
				values.Add(item.Count);
			}
		}

		/// <summary>
		/// Returns a list of sex upon outcome and a corresponding list of quantities.
		/// </summary>
		/// <param name="outcome">A list of sex upon outcome to append additional values to.</param>
		/// <param name="values">A list of sex upon outcome quantities to append additional quantities to.</param>
		private void GetSexUponOutcomeCountData(ref List<string> outcome, ref List<double?> values)
		{
			// Group by animal type
			var sexUponOutcomeCount = _animals
			.GroupBy(animal => animal.SexUponOutcome)
			.Select(group => new
			{
				SexUponOutcome = group.Key,
				Count = group.Count()
			});


			// Separate type and count into a separate lists.
			foreach (var item in sexUponOutcomeCount)
			{
				outcome.Add(item.SexUponOutcome);
				values.Add(item.Count);
			}
		}

		/// <summary>
		/// Returns a list of outcome types and a corresponding list of quantities.
		/// </summary>
		/// <param name="outcome">A list of outcomes to append additional values to.</param>
		/// <param name="values">A list of outcome quantities to append additional quantities to.</param>
		private void GetOutcomeCountData(ref List<string> outcome, ref List<double?> values)
		{
			// Group by animal type
			var outcomeCount = _animals
			.GroupBy(animal => animal.OutcomeType)
			.Select(group => new
			{
				Outcome = group.Key,
				Count = group.Count()
			});


			// Separate type and count into a separate lists.
			foreach (var item in outcomeCount)
			{
				outcome.Add(item.Outcome);
				values.Add(item.Count);
			}
		}

		/// <summary>
		/// Generic pie chart function used to create pie charts that have a similar style and functionality
		/// </summary>
		/// <param name="chartTitle">A string used as the tile for the chart</param>
		/// <param name="labels">A list of values for the chart labels</param>
		/// <param name="values">A list of values for the chart values</param>
		/// <returns></returns>
		private Chart GeneratePieChart(string chartTitle, List<string> labels, List<double?> values)
		{
			
			// Create a list of colors to be used for the pie chart.
			List<ChartColor> colors = GenerateColors(labels.Count);

			// Create pie chart
			Chart chart = new()
			{
				Type = Enums.ChartType.Pie
			};

			Data data = new()
			{
				Labels = labels
			};

			PieDataset dataset = new()
			{
				Label = chartTitle,
				BackgroundColor = colors,
				HoverBackgroundColor = colors,
				Data = values
			};

			data.Datasets = new List<Dataset>
			{
				dataset
			};

			chart.Data = data;

			return chart;

		}


		/// <summary>
		/// Generates a list of colors to be used with a pie chart
		/// </summary>
		/// <param name="numOfColors">The number of colors to be added to the list.</param>
		/// <returns>Returns a list of chart colors.</returns>
		private List<ChartColor> GenerateColors(int numOfColors)
		{
			List<ChartColor> colors = new List<ChartColor>();
			var random = new Random();

			for (int i = 0; i < numOfColors; i++)
			{
				colors.Add(ChartColor.FromHexString(String.Format("#{0:X6}", random.Next(0x1000000))));
			}

			return colors;
		}

		[ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}