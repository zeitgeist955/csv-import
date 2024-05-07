# csv-import
Java tool to read csv and store it in a SQL DB

# TODOs
- FIX reader with scanner not detecting any data (delimiter issue ?)
- H2 DB not launching but no stack / therefore no data stored on csv reading
- Issue with bulking this much data into an Azure SQL at once ? by batch maybe ?
- add a few more ways to read CSV's :
  - Jackson : https://stackoverflow.com/questions/22485041/how-to-easily-process-csv-file-to-listmyclass
- Find a way to generate huge CSV to have realistic performance measure and expectations
  - is there any issues with a file with many duplicated data ?
  - If so, generate with random data with a CSVwriter ?
  - Maybe a fake dump from real database ?
- Optimize the process as much as possible (no reread/write data uselessly)
- Detect if the CSV has a header line and skip it if it does

# Documentation
- 3 ways to read CSV files : https://www.baeldung.com/java-csv-file-array
- Stackoverflow discussion about this problematic : https://stackoverflow.com/questions/55084846/fastest-way-to-read-a-csv-file-java
- Github project of csv comparaison (outdated since 2018) : https://github.com/uniVocity/csv-parsers-comparison
- Check Spring Batch avec les chunk et les pattern de résilience intégré

# NB
Doesn't stay on after launch because there are no web server included
Exit code 0 means no error
