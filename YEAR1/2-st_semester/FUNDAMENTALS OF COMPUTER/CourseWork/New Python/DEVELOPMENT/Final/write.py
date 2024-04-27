from datetime import datetime # have to introduce in cw doc

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
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    acceptable_timestamp = timestamp.replace(":", "_").replace(" ", "-")
    file_path = f"Invoices/purchased/{acceptable_timestamp}_{name}Purchased.txt"
    with open(file_path, 'w') as file:
        for invoice in list_of_writable_invoices:
            file.write(invoice + '\n\n')
            print(invoice + '\n\n')
    with open (file_path, 'a') as files:
        files.write(f"""ALL TOTAL: {allTotal} (Included VAT)""")
        print(f"""ALL TOTAL: {allTotal} (Included VAT)""")


def write_generated_return_invoice_to_file(list_of_returnable_invoices, user_info, allTotal):
    name = user_info["name"]
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    acceptable_timestamp = timestamp.replace(":", "_").replace(" ", "-")
    file_path = f"Invoices/return/{acceptable_timestamp}_{name}Returned.txt"
    with open(file_path, 'w') as file:
        for invoice in list_of_returnable_invoices:
            file.write(invoice + '\n\n')
            print(invoice + '\n\n')
    with open (file_path, 'a') as files:
        files.write(f"""ALL TOTAL: {allTotal} (Included Fine)""")
        print(f"""ALL TOTAL: {allTotal} (Included Fine)""")