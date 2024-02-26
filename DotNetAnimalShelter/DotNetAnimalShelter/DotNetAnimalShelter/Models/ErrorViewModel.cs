//////////////////////////////////////////////////////////////////////////
/// Developer: Shawn Curtis
/// Contact: shawn.curtis@snhu.edu
/// Date: 2/18/2024
/// Version: 1.0.0
/// Purpose: Error view model. Stores the error information as a model
/// for the error view.
//////////////////////////////////////////////////////////////////////////
namespace DotNetAnimalShelter.Models
{
    public class ErrorViewModel
    {
        public string? RequestId { get; set; }

        public bool ShowRequestId => !string.IsNullOrEmpty(RequestId);
    }
}