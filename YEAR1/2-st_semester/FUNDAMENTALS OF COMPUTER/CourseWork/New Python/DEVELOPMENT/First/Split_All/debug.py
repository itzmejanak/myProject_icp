# import os as s

# def check_filename(user_name="janak"):
#     """
#     Check if an invoice file exists for the given user name.

#     Args:
#     - user_name (str): The user name to check.

#     Returns:
#     - bool: True if the file exists, False otherwise.
#     """
#     file_path = "Invoices/purchased/"
#     files = s.listdir(file_path)

#     # Iterate over the list of files
#     for file_name in files:
#         # Extract the user name from the file name
#         name_part = file_name.split('Purchased.txt')[0]

#         # Check if the given user name matches any part of the file name
#         if user_name in name_part:
#             return True

#     # If no matching file found
#     return False

# # Example usage:
# filename_exists = check_filename("janak")
# print(filename_exists)  # This will print True if the file exists, False otherwise



# def return_purchased_expire_month(file_path):
#     """
#     Extract the expiration month of a purchased land from an invoice file.

#     Args:
#     - file_path (str): The path to the invoice file.

#     Returns:
#     - list of dict: List of dictionaries containing 'kittano' as key and 'months' as value for each entry.
#     """
#     expiration_months = []
#     kittano = None
#     with open(file_path, 'r') as file:
#         for line in file:
#             if "| null      |" in line:
#                 # Extract the kittano from the line
#                 kittano = line.split("|")[1].strip()
#                 kitta = int(kittano)
#             elif "Purchased Expiration:" in line and kittano is not None:
#                 # Extract the purchased date from the line
#                 purchased_date = line.split(":")[1].strip()
#                 # Extract the month from the purchased date
#                 purchase_month = int(purchased_date.split('-')[1])
#                 expiration_months.append({'kitta': kitta, 'months': purchase_month})
#                 kittano = None

#     # If there are no purchased expiration dates found for the kittano, return an empty list
#     return expiration_months
# filess = "Invoices/purchased/janakPurchased.txt"

# print(return_purchased_expire_month(filess))


# kitta = 101
# purchased_expire_months = return_purchased_expire_month(filess)

# def refine_expire_month(purchased_expire_months, kitta_no):
#     """
#     Refine the list of purchased expiration months to find the months for a specific kitta.

#     Args:
#     - purchased_expire_months (list of dict): List of dictionaries containing 'kitta' and 'months'.
#     - kitta_no (str): The kitta identifier to search for.

#     Returns:
#     - int or None: The months for the specified kitta, or None if the kitta is not found.
#     """
#     for entry in purchased_expire_months:
#         if entry['kitta'] == kitta_no:
#             print(entry['months'])
#     return None
# refine_expire_month(purchased_expire_months, kitta)


def calculate_total_fine(path):
    total_fine = 0
    with open(path, 'r') as files:
        for line in files:
            if "Total Fine:" in line:
                fine_amount = line.split("|")[1].split(":")[1]
                fine_int = int(fine_amount)
                total_fine += fine_int
    return total_fine


file_path = f"Invoices/return/janakReturn.txt"
calculate_total_fine(file_path)
