## Coracias

Coracias is a library to convert List<Object> to Html Table based on custom or default instructions.

## Usage

### CoraciasInstructions 

### CoraciasFormatter 

### CoraciasDataTypeStrategy 

### CoraciasExport 

### CoraciasDataToModel

 
### Sample

```java
val coracias = Coracias.Builder<DataEntity>()
    .coraciasFormatter(
        DefaultCoraciasFormatter()
            .jsr310DateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd hh.MM.ss.SSS"))
            .jsr310DateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .classicDateTimeFormatter(SimpleDateFormat("yyyy-MM-dd hh:MM:ss"))
            .build()
    )
    .coraciasTypeStrategy(DefaultCoraciasDataTypeStrategy())
    .createHeader(true)
    .build()
coracias.format(list, "table_name", null)
```

## What is the meaning of Coracias

    Coracias is a genus of the rollers, an Old World family of near passerine birds related to the kingfishers and bee-eaters.
    
[Coracias](https://en.wikipedia.org/wiki/Coracias)
