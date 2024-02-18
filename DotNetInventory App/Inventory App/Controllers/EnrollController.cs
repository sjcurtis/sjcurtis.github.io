using Microsoft.AspNetCore.Mvc;
using Inventory_App.Data;
using Inventory_App.Models;

namespace Inventory_App.Controllers
{
    public class EnrollController : Controller
    {
        private readonly InventoryContext _context;

        public EnrollController(InventoryContext context)
        {
            _context = context;
        }

        // GET: Enroll/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Enroll/Create
        // Adds a new user to the database. Returns user to the create page if there's a problem.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("UserId,Username,Password")] User user)
        {
            if (ModelState.IsValid)
            {
                _context.Add(user);
                await _context.SaveChangesAsync();
                return RedirectToAction("Index", "Home");
            }
            return View(user);
        }
    }
}
