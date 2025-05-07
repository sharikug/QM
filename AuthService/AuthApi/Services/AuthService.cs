using AuthApi.Models;
using Microsoft.EntityFrameworkCore;
using System.Security.Cryptography;
using System.Text;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;

public class AuthService
{
    private readonly AppDbContext _context;
    private readonly IConfiguration _config;

    public AuthService(AppDbContext context, IConfiguration config)
    {
        _context = context;
        _config = config;
    }

    public async Task<bool> Register(RegisterRequest request)
    {
        if (await _context.Users.AnyAsync(u => u.Email == request.Email)) return false;

        CreatePasswordHash(request.Password, out byte[] hash, out byte[] salt);

        var user = new User
        {
            Username = request.Username,
            Email = request.Email,
            PasswordHash = hash,
            PasswordSalt = salt
        };

        _context.Users.Add(user);
        await _context.SaveChangesAsync();
        return true;
    }

    public async Task<string?> Login(LoginRequest request)
    {
        var user = await _context.Users.SingleOrDefaultAsync(u => u.Email == request.Email);
        if (user == null || !VerifyPasswordHash(request.Password, user.PasswordHash, user.PasswordSalt))
            return null;

        return GenerateJwtToken(user);
    }

    private void CreatePasswordHash(string password, out byte[] hash, out byte[] salt)
    {
        using var hmac = new HMACSHA512();
        salt = hmac.Key;
        hash = hmac.ComputeHash(Encoding.UTF8.GetBytes(password));
    }

    private bool VerifyPasswordHash(string password, byte[] hash, byte[] salt)
    {
        using var hmac = new HMACSHA512(salt);
        var computedHash = hmac.ComputeHash(Encoding.UTF8.GetBytes(password));
        return hash.SequenceEqual(computedHash);
    }

    private string GenerateJwtToken(User user)
    {
        var claims = new[]
        {
            new Claim(ClaimTypes.NameIdentifier, user.Id.ToString()),
            new Claim(ClaimTypes.Name, user.Username)
        };

        var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_config["Jwt:Key"]));
        var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

        var token = new JwtSecurityToken(
            claims: claims,
            expires: DateTime.Now.AddHours(1),
            signingCredentials: creds);

        return new JwtSecurityTokenHandler().WriteToken(token);
    }
}
