using Microsoft.AspNetCore.Mvc;
using AuthApi.Models;

[ApiController]
[Route("api/[controller]")]
public class AuthController : ControllerBase
{
    private readonly AuthService _authService;

    public AuthController(AuthService authService)
    {
        _authService = authService;
    }

    [HttpPost("register")]
    public async Task<IActionResult> Register(RegisterRequest request)
    {
        var result = await _authService.Register(request);
        if (!result) return BadRequest("User already exists.");
        return Ok("User created.");
    }

    [HttpPost("login")]
    public async Task<IActionResult> Login(LoginRequest request)
    {
        var token = await _authService.Login(request);
        if (token == null) return Unauthorized("Invalid credentials.");
        return Ok(new { token });
    }

    [HttpPost("logout")]
    public IActionResult Logout()
    {
        // Si usas JWT, el logout se maneja en el cliente eliminando el token.
        return Ok("Logout successful (handled client-side).");
    }
}