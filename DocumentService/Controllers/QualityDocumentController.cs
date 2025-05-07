using DocumentService.Data;
using DocumentService.Models;
using Microsoft.AspNetCore.Mvc;

namespace DocumentService.Controllers;

[ApiController]
[Route("api/[controller]")]
public class QualityDocumentController : ControllerBase
{
    private readonly DocumentDbContext _context;
    
    public QualityDocumentController(DocumentDbContext context)
    {
        _context = context;
    }

    [HttpPost]
    public async Task<IActionResult> CreateDocument([FromBody] QualityDocumentDto dto)
    {
        Console.WriteLine("POST recibido:");
        Console.WriteLine($"Title: {dto.Title}, Author: {dto.Author}, Category: {dto.Category}, Date: {dto.Date}, FilePath: {dto.FilePath}");
        
        if (!ModelState.IsValid)
            return BadRequest(ModelState);

        var document = new QualityDocument
        {
            Title = dto.Title,
            Author = dto.Author,
            Category = dto.Category,
            Date = dto.Date,
            FilePath = dto.FilePath
        };

        _context.QualityDocuments.Add(document);
        await _context.SaveChangesAsync();

        return Ok(document);
    }

    [HttpGet("{id}")]
    public IActionResult GetById(int id)
    {
        var doc = _context.QualityDocuments.Find(id);
        return doc == null ? NotFound() : Ok(doc);
    }

    [HttpGet]
    public IActionResult GetAll()
    {
        return Ok(_context.QualityDocuments.ToList());
    }
    
    [HttpPut("{id}")]
    public async Task<IActionResult> UpdateDocument(int id, [FromBody] QualityDocumentDto dto)
    {
        var existingDoc = await _context.QualityDocuments.FindAsync(id);
        if (existingDoc == null)
            return NotFound();

        existingDoc.Title = dto.Title;
        existingDoc.Author = dto.Author;
        existingDoc.Category = dto.Category;
        existingDoc.Date = dto.Date;
        existingDoc.FilePath = dto.FilePath;

        await _context.SaveChangesAsync();

        return Ok(existingDoc);
    }
    
    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteDocument(int id)
    {
        var doc = await _context.QualityDocuments.FindAsync(id);
        if (doc == null)
            return NotFound();

        _context.QualityDocuments.Remove(doc);
        await _context.SaveChangesAsync();

        return NoContent(); // 204 No Content
    }


}