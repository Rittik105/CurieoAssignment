# Curieo Coding Assesment Assignment
## Generating Input files
  1. Open the inputGenerator.py file inside InputFileGenerator folder
  2. Change the initial_reserve, n_transaction, n_queries, task_type values
  3. Run the python file.

A file named Input.txt will be generated for the given specifications.

## Running the BankTransactionAssignment app
### Using Docker
  1. Open terminal inside the BankTransactionAssignment folder.
  2. Remove previous images (if exists).
     ```
     docker rmi -f image_name
     ```
  4. Build the docker image.
     ```
     docker build -t image_name .
     ```
  5. Run the docker image
     ```
     docker run --rm -it -v $(pwd)/mount:/app/data image_name
     ```
  6. Enter input file number and output file will be generated inside "mount" folder

### Without docker
  1. Compile the java classes inside src.
  2. Run the compiled "main".
  3. Enter input file number and output file will be generated inside "data" folder
