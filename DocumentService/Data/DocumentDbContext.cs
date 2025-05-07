using DocumentService.Models;
using Microsoft.EntityFrameworkCore;

namespace DocumentService.Data;

public class DocumentDbContext : DbContext
{
    public DocumentDbContext(DbContextOptions<DocumentDbContext> options) : base(options) {}

    public DbSet<QualityDocument> QualityDocuments { get; set; }
}