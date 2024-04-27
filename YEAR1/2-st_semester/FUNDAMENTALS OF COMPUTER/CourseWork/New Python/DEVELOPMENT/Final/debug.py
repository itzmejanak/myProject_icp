# user_data = {'name': 'Alice', 'contact': '9876543210', 'months': 12, 'land_data': {'101': 6, '102': 7}}
# print(user_data['land_data'].keys())


# def grab_purchased_month(user_info):
#     name = user_info["name"]
#     file_path = f"Invoices/purchased/{name}Purchased.txt"
#     expiration_months = {}
#     kittano = None
#     with open(file_path, 'r') as file:
#         for line in file:
#             if "| null      |" in line:
#                 # Extract the kittano from the line
#                 kittano = line.split("|")[1].strip()
#                 kitta = int(kittano)
#             elif "Months:" in line and kittano is not None:
#                 # Extract the purchased date from the line
#                 purchased_date = line.split(":")[1].strip()
#                 # Extract the month from the purchased date
#                 purchase_month = int(purchased_date)
#                 expiration_months[kitta] = purchase_month
#                 kittano = None
#     return expiration_months

# print(grab_purchased_month(user_info={'name': 'janak', 'contact': '9876543210', 'months': 18, 'land_data': {'103': 3, '104': 4, '105': 5}}))



# import os

# def grab_purchased_month():
#     expiration_months = {}
#     kittano = None
#     name = "janak"
#     file_paths = [file for file in os.listdir("Invoices/purchased/") if name in file]
#     # print(file_paths)

#     for file_name in file_paths:
#         file_path = os.path.join("Invoices/purchased/", file_name)
#         with open(file_path, 'r') as file:
#             for line in file:
#                 if "| null      |" in line:
#                     # Extract the kittano from the line
#                     kittano = line.split("|")[1].strip()
#                     kitta = int(kittano)
#                 elif "Months:" in line and kittano is not None:
#                     # Extract the purchased date from the line
#                     purchased_date = line.split(":")[1].strip()
#                     # Extract the month from the purchased date
#                     purchase_month = int(purchased_date)
#                     expiration_months[kitta] = purchase_month
#                     kittano = None

#     return expiration_months
# print(grab_purchased_month())



# to update 
# grab_pur_month  from operation
# timestamp_into file  from write            ######## done
# check filename   from operation


# import os as s
# def check_filename(user_name):
#     file_path = "Invoices/purchased/"
#     files = s.listdir(file_path)

#     for file_name in files:
#         name_part = file_name.split('Purchased.txt')[0]

#         if user_name == name_part:
#             return file_name
        
#     return None
# print(check_filename)




