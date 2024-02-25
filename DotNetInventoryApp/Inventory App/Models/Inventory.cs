namespace Inventory_App.Models
{
    public class Inventory
    {
        public int Id { get; set; }
        public int? UserId { get; set; }

        public string Name { get; set; }
        public int Quantity { get; set; }

    }
}
