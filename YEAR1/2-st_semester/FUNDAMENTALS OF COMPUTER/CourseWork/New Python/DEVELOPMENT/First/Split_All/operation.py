from datetime import datetime
import os as s

def calculate_grand_total_and_Vat(prices):
    """
    Calculate VAT and grand total based on the given price.

    Args:
    - prices (int): The price of the item.

    Returns:
    - tuple: A tuple containing the VAT amount and the grand total.
    """
    price = int(prices)
    vat_percentage = 13
    vatAmount = 0  
    grand_total = 0 
    vatAmount = (price * vat_percentage) / 100
    grand_total = price + vatAmount
    return vatAmount, grand_total


def check_filename(user_name):
    """
    Check if an invoice file exists for the given user name.

    Args:
    - user_name (str): The user name to check.

    Returns:
    - str or None: The file name if found, otherwise None.
    """
    file_path = "Invoices/purchased/"
    files = s.listdir(file_path)

    # Iterate over the list of files
    for file_name in files:
        # Extract the user name from the file name
        name_part = file_name.split('Purchased.txt')[0]

        # Check if the given user name matches any part of the file name
        if user_name == name_part:
            return file_name

    # If no matching file found
    return None



def calculate_total_price(file_path):
    """
    Calculate and append the total purchased amount to an invoice file.

    Args:
    - file_path (str): The path to the invoice file.
    """
    final_price = 0
    with open(file_path, 'r') as file:
        for line in file:
            if "Grand Total Rs:" in line:
                price_str = line.split(":")[1].strip()  # Remove extra spaces
                price = float(price_str.split()[0])
                final_price += price
    with open(file_path, 'a') as file:
        file.write("===================================================|\n")
        file.write(f"| Total Purchased:             {final_price: <20}|\n")
        file.write("===================================================|\n")



def calculate_expiration_date_of_purchased(given_months):
    """
    Calculate the expiration date of a purchased land.

    Args:
    - given_months (int): The duration of the purchase in months.

    Returns:
    - str: The expiration date in the format 'YYYY-MM-DD'.
    """
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    date_expried = timestamp.split("-")
    purchased_date = int(date_expried[1])
    exp_new_month = purchased_date + int(given_months)
    date_expried[1] = str(exp_new_month)

    final_date = "-".join(date_expried)
    return final_date



def get_purchase_user_input():
    """
    Prompt the user to enter their name and the duration of land purchase.

    Returns:
    - tuple: A tuple containing the user's name and the duration of purchase.
    """
    while True:
        name = input("Enter your name: \n")
        if name.strip():  # Check if the name is not empty or whitespace
            break
        else:
            print("Name cannot be empty. Please enter your name.")

    while True:
        given_months = input("For how many months would you like to rent the land?: ")
        if given_months.isdigit() and int(given_months) > 0:  # Check if given_months is a positive integer
            break
        else:
            print("Please enter a valid number of months.")

    return name, given_months



def get_return_user_input():
    """
    Prompt the user to enter their name and the number of months they've been using the land.

    Returns:
    - tuple: A tuple containing the user's name and the number of months.
    """
    while True:
        name = input("Enter your name: \n")
        if name.strip():  # Check if the name is not empty or whitespace
            break
        else:
            print("Name cannot be empty. Please enter your name.")

    while True:
        return_month = input("Please enter the current number of months going on: ")
        if return_month.isdigit() and int(return_month) > 0:  # Check if return_month is a positive integer
            break
        else:
            print("Please enter a valid number of months.")

    return name, int(return_month)


def return_purchased_expire_month(file_path):
    """
    Extract the expiration month of a purchased land from an invoice file.

    Args:
    - file_path (str): The path to the invoice file.

    Returns:
    - list of dict: List of dictionaries containing 'kittano' as key and 'months' as value for each entry.
    """
    expiration_months = []
    kittano = None
    with open(file_path, 'r') as file:
        for line in file:
            if "| null      |" in line:
                # Extract the kittano from the line
                kittano = line.split("|")[1].strip()
                kitta = int(kittano)
            elif "Purchased Expiration:" in line and kittano is not None:
                # Extract the purchased date from the line
                purchased_date = line.split(":")[1].strip()
                # Extract the month from the purchased date
                purchase_month = int(purchased_date.split('-')[1])
                expiration_months.append({'kitta': kitta, 'months': purchase_month})
                kittano = None

    # If there are no purchased expiration dates found for the kittano, return an empty list
    return expiration_months


def calculate_late_return_fine(return_month, purchased_expire_month):
    """
    Calculate the late return fine based on the return month and the month when the land was purchased.

    Args:
    - return_month (int): The month when the land is returned.
    - purchased_expire_month (int or None): The month when the purchased land expires.

    Returns:
    - int: The total fine amount.
    """
    if purchased_expire_month is not None and purchased_expire_month < return_month:
        fine_per_month = 1000
        late_months = return_month - purchased_expire_month
        total_fine = late_months * fine_per_month
        return total_fine
    else:
        return 0

    


def refine_expire_month(purchased_expire_months, kitta_no):
    """
    Refine the list of purchased expiration months to find the months for a specific kitta.

    Args:
    - purchased_expire_months (list of dict): List of dictionaries containing 'kitta' and 'months'.
    - kitta_no (str): The kitta identifier to search for.

    Returns:
    - int or None: The months for the specified kitta, or None if the kitta is not found.
    """
    int_kitta = int(kitta_no)
    for entry in purchased_expire_months:
        if entry['kitta'] == int_kitta:
            return entry['months']
    return None


def calculate_total_fine(path):
    total_fine = 0
    with open(path, 'r') as files:
        for line in files:
            if "Total Fine:" in line:
                fine_amount = line.split("|")[1].split(":")[1]
                fine_int = int(fine_amount)
                total_fine += fine_int
    return total_fine