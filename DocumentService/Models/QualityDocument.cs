namespace DocumentService.Models;

public class QualityDocument
{
    public int Id { get; set; }
    public string Title { get; set; }
    public string Author { get; set; }
    public string Category { get; set; }
    public DateTime Date { get; set; }
    public string FilePath { get; set; }
}