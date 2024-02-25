using Microsoft.Extensions.Hosting;

namespace Inventory_App.Models
{
    public class User
    {
        public int UserId { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public ICollection<Inventory> Inventories { get; } = new List<Inventory>();

    }
}
