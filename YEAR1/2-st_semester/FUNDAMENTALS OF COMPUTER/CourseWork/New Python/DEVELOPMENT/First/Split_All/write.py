import json

def save_data_to_file(datas):
    """
    Save data to a text file.

    Args:
    - datas (list): A list of dictionaries representing the data to be saved.
    """
    with open("data.txt", 'w') as file:
        for data in datas:
            modified_data = ", ".join(data.values()) + "\n"
            file.write(modified_data)


def generate_purchased_invoice_data(item, name, given_months, vat_amount, grandTotal, expired, remarks):
    """
    Generate invoice data for a purchased land.

    Args:
    - item (dict): A dictionary representing the purchased land.
    - name (str): The customer's name.
    - given_months (int): The duration of the purchase in months.
    - vat_amount (float): The VAT amount.
    - grandTotal (float): The total amount including VAT.
    - expired (str): The expiration date of the purchase.
    - remarks (str): Additional remarks for the invoice.

    Returns:
    - str: The generated invoice data as a string.
    """
    return f"""
                                    ICP Rental Pokhara
                                10, Hospital Chowk, Pokhara

Customer Details:                                                         Date: {item['Timestamp'].split()[0]}
Name: {name}
Address: {item["location"]}
Phone: 980000000

+---------------------+------------------+-------------+---------+----------+-----------------+
|     Kitaa Number    |    Location      |  Direction  | Total Anna | Price in Rs |   Remarks |
+---------------------+------------------+-------------+---------+----------+-----------------+
|        {item['id']: <12} |   {item['location']: <14} |    {item['direction']: <8} |    {item['anna']: <7} |  {item['price']: <10} | {remarks: <10}|
+---------------------+------------------+-------------+---------+----------+-----------------+

Total Rs: {item["price"]}
VAT (13%) Rs: {vat_amount}
Grand Total Rs: {grandTotal}

Additional Data:
Months:            {given_months: <20}              
Purchased Expiration:    {expired: <20}      
"""





def generate_return_invoice_data(item, name, return_month, timestamp, remarks):
    """
    Generate invoice data for returning a land.

    Args:
    - item (dict): A dictionary representing the returned land.
    - name (str): The customer's name.
    - return_month (int): The number of months the land was used.
    - timestamp (str): The timestamp of the return.
    - remarks (str): Additional remarks for the invoice.

    Returns:
    - str: The generated invoice data as a string.
    """
    return f"""
                                    ICP Rental Pokhara
                                10, Hospital Chowk, Pokhara

Customer Details:                                                         Date: {timestamp.split()[0]}
Name: {name}
Address: {item["location"]}
Phone: 980000000

+---------------------+------------------+-------------+---------+----------+-----------------+
|     Kitaa Number    |    Location      |  Direction  | Total Anna | Price in Rs |   Remarks |
+---------------------+------------------+-------------+---------+----------+-----------------+
|        {item['id']: <12} |   {item['location']: <14} |    {item['direction']: <8} |    {item['anna']: <7} |  {item['price']: <10} | {remarks: <10}|
+---------------------+------------------+-------------+---------+----------+-----------------+

Total: Rs null
VAT (13%) Rs: Rs null*vat/100
Grand Total Rs: 3000

Additional Data:
Months:            {return_month: <20}              
Return Date:    {timestamp: <20}      
"""


def write_fine_to_invoice(file_path, fine_amount):
    """
    Write the late return fine to the invoice file.

    Args:
    - file_path (str): The path to the invoice file.
    - fine_amount (int): The total fine amount.
    """
    with open(file_path, 'a') as file:
        file.write("===================================================|\n")
        if fine_amount > 0:
            file.write(f"| Total Fine:                  {fine_amount: <20}|\n")
        else:
            file.write("| Total Fine:                        0             |\n")
        file.write("===================================================|\n")



def write_total_fine_to_invoice(path, amount):
    with open(path, 'a') as file:
        file.write(f"""
|                                                                      TOTAL FINE: {amount}   |
""")



