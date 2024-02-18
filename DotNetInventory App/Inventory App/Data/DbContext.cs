using Inventory_App.Models;
using Microsoft.EntityFrameworkCore;

namespace Inventory_App.Data
{
    public class InventoryContext : DbContext
    {
        public InventoryContext(DbContextOptions<InventoryContext> options) : base(options) 
        { 
       
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            base.OnConfiguring(optionsBuilder); 

            // Connection string to local container running docker. Verify the port matches the port on the container.
            //optionsBuilder.UseSqlServer("Server=127.0.0.1,1433;Database=Inventory;User=SA;Password=Password1!;TrustServerCertificate=True");

            // Connection string to local database. Useful for working with IIS Express.
            optionsBuilder.UseSqlServer("Server=(LocalDb)\\MSSQLLocalDB;Database=Inventory;Integrated Security=True;TrustServerCertificate=True");
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Add a test user
            modelBuilder.Entity<User>().HasData(
                new User
                {
                    UserId = 1,
                    Username = "test",
                    Password = "password"                    
                }            
            );

            // Add sample inventory
            modelBuilder.Entity<Inventory>().HasData(
                new Inventory { Id = 1, UserId = 1, Name = "Inventory 1", Quantity = 1 },
                new Inventory { Id = 2, UserId = 1, Name = "Inventory 2", Quantity = 100 },
                new Inventory { Id = 3, UserId = 1, Name = "Inventory 3", Quantity = 200 }

            );

        }

        public DbSet<User> Users { get; set; }
        public DbSet<Inventory> Inventories { get; set; }
    }
}
