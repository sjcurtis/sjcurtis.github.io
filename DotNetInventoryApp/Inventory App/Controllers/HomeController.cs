using Inventory_App.Data;
using Inventory_App.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Diagnostics;

namespace Inventory_App.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private readonly InventoryContext _context;
        private readonly IHttpContextAccessor _httpContext;

        public HomeController(ILogger<HomeController> logger, InventoryContext context, IHttpContextAccessor httpContext)
        {
            _logger = logger;
            _context = context;
            _httpContext = httpContext;
        }

        /// <summary>
        /// Returns Index view.
        /// </summary>
        public IActionResult Index()
        {
            return View();
        }

        /// <summary>
        /// Returns the shared Error view.
        /// </summary>
        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }

        /// <summary>
        /// Validates the login credentials and returns user to the inventory dashboard.
        /// </summary>
        /// <param name="username">User's username</param>
        /// <param name="password">User's password</param>
        /// <returns>Redirects user to inventory dashboard on successful login else returns an error to current page.</returns>
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> LoginAsync(string username, string password)
        {

            if (ModelState.IsValid)
            {
                var user = await _context.Users.FirstOrDefaultAsync(m => m.Username == username && m.Password == password);

                if (user != null) 
                {
                    _httpContext.HttpContext.Session.SetString("username", user.Username);
                    _httpContext.HttpContext.Session.SetInt32("userId", user.UserId);
                    return RedirectToAction("Index", "Inventories", new { userId = user.UserId });
                }
            }

            return View("Index");
        }
    }
}