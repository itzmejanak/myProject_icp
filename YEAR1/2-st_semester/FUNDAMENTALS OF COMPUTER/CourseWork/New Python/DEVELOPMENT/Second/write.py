def save_modified_data_to_file(datas):
    """
    Save data to a text file.

    Args:
    - datas (list): A list of dictionaries representing the data to be saved.
    """
    with open("data.txt", 'w') as file:
        for data in datas:
            modified_data = ", ".join(data.values()) + "\n"
            file.write(modified_data)


def write_generated_invoice_to_file(list_of_writable_invoices, user_info, allTotal):
    name = user_info["name"]
    file_path = f"Invoices/purchased/{name}Purchased.txt"
    with open(file_path, 'w') as file:
        for invoice in list_of_writable_invoices:
            file.write(invoice + '\n\n')
    with open (file_path, 'a') as files:
        files.write(f""" ALL TOTAL:                                                      {allTotal} (Included Vat)""")


def write_generated_return_invoice_to_file(list_of_returnable_invoices, user_info, allTotal):
    name = user_info["name"]
    file_path = f"Invoices/return/{name}Returned.txt"
    with open(file_path, 'w') as file:
        for invoice in list_of_returnable_invoices:
            file.write(invoice + '\n\n')
    with open (file_path, 'a') as files:
        files.write(f""" ALL TOTAL:                                                      {allTotal} (Included Fine)""")