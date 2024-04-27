def data_into_list(filename="data.txt"):
    """
    Read data from a text file and convert it into a list of dictionaries.

    Args:
    - filename (str): The name of the file to read from (default is "data.txt").

    Returns:
    - list: A list of dictionaries, each representing a record in the data file.
    """
    with open(filename, 'r') as file:
        contents = file.read()

    data = contents.split("\n")
    result = []

    for line in data:
        if line == "":
            continue
        values = line.split(", ")
        entry = {
            "id": values[0],
            "location": values[1],
            "direction": values[2],
            "anna": values[3],
            "price": values[4],
            "availability": values[5].strip()
        }
        result.append(entry)

    return result


def show_only_values():
    """
    Retrieve only the values from the records in the data file.

    Returns:
    - list: A list of lists, each containing the values of a record.
    """
    data = data_into_list()
    values_list = []
    for record in data:
        only_values = record.values()
        values_list.append(only_values)
    return values_list