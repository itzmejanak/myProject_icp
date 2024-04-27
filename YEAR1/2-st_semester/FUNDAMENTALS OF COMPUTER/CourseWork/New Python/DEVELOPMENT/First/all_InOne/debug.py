# # for generating data 
# data_list = [
#     "101, Kathmandu, North, 4, 50000, Available",
#     "102, Pokhara, East, 5, 60000, Not Available",
#     "103, Lalitpur, South, 10, 100000, Available"
# ]

# # Convert the list of strings into a string representation
# data_str = '\n'.join(data_list)

# # Open the text file in write mode
# with open('data.txt', 'w') as file:
#     # Write the string representation of the list of strings to the file
#     file.write(data_str)




# for accessing data
# import json

# # Load data from the text file
# with open('data.txt', 'r') as file:
#     data = json.load(file)

# # Process each record
# for record in data:
#     values = record.values()
#     print(values)


# WHAT I LEARN HERE ?

#  converting txt data to json is possible by
# with open('data.txt', 'r') as file:
#     data = json.load(file)

#  converting json data to list is possible by
# for record in data:
#     only_values = list(record.values())


# if item["id"] == kitta_no:
# for key, value in item.items():
#                 print(key, value)
#             break  # Stop searching once the matching item is found

# import CourseWorks
# CourseWorks.purchase_land()


# for accesing the file name
# import os as s

# file = "Invoices/purchased/"
# files = s.listdir(file)

# print(files)



# import os as s
# def check_multiple_purchase(user_name):
#     file_path = "Invoices/purchased/"
#     files = os.listdir(file_path)

#     # Iterate over the list of files
#     for file_name in files:
#         name_part = ''.join(file_name.split('101Purchased.txt'))

#         if name_part == user_name:
#             print("success", user_name, " = ", name_part)
#             return file_name

# # Example usage
# filename = check_multiple_purchase("janak")
# print(filename)

# if filename is None:
#     print("File name not found")
# else:
#     print("You have a file")



# import os as s

# def check_multiple_purchase(user_name="janak"):
#     file_path = "Invoices/purchased/"
#     files = s.listdir(file_path)

#     # Iterate over the list of files
#     for file_name in files:
#         # Extract the user name from the file name
#         name_part = file_name.split('Purchased.txt')[0]
#         print("Name part extracted from file name:", name_part)  # Print name_part
#         # Check if the given user name matches any part of the file name
#         if user_name in name_part:
#             return file_name

#     # If no matching file found
#     return None

# # Testing the function
# print(check_multiple_purchase())


# file_path = "Invoices/purchased/janak107Purchased.txt"

# def calculate_total_price(file_path):
#     total_price = 0
#     with open(file_path, 'r') as file:
#         for line in file:
#             if "Price in Rs:" in line:
#                 print("Line:", line)  # Debugging output
#                 price_str = line.split(":")[1].strip()  # Remove extra spaces
#                 # print(price_str)
#                 price = int(price_str.split()[0])  # Extract the first part as price
#                 print("Price:", price)  # Debugging output
#                 total_price += price
#     print("Total Price:", total_price)

# calculate_total_price(file_path)





# file_path = "Invoices/purchased/janak107Purchased.txt"

# def remove_and_update_line(file_path, old_line, new_line):
#     with open(file_path, 'r') as file:
#         lines = file.readlines()

#     with open(file_path, 'w') as file:
#         for line in lines:
#             if old_line in line:
#                 file.write(new_line + '\n')  # Write the updated line
#             elif old_line not in line:
#                 file.write(line)

# # Example usage
# old_line = """
# ===================================================|
# | Total Price:                 195000              |
# ===================================================|"""
# new_line = """
# ===================================================|
# | Total Price:                 30999997            |
# ===================================================|"""
# remove_and_update_line(file_path, old_line, new_line)



# def get_purchase_month_from_invoice(file_path):
#     with open(file_path, 'r') as file:
#         for line in file:
#             if "Purchased Date:" in line:
#                 # Extract the purchased date from the line
#                 purchased_date = line.split(":")[1].strip()
#                 # Extract the month from the purchased date
#                 purchase_month = int(purchased_date.split('-')[1])
#                 return purchase_month
#     # If the purchased date is not found, return None
#     return None



# from datetime import datetime

# timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
# return_month = int(datetime.now().strftime("%m"))
# print(return_month)

# import os as s

# def check_multiple_purchase(user_name):
#     file_path = "Invoices/purchased/"
#     files = s.listdir(file_path)

#     # Iterate over the list of files
#     for file_name in files:
#         # Extract the user name from the file name
#         name_part = file_name.split('Purchased.txt')[0]

#         # Check if the given user name matches any part of the file name
#         if user_name in name_part:
#             print(file_name) 

#     # If no matching file found
#     return None

# check_multiple_purchase("janak")

# def calculate_total_price(file_path):
#     total_price = 0
#     with open(file_path, 'r') as file:
#         for line in file:
#             if "|     Kitaa Number" in line:
#                 # Skip the header line
#                 continue
#             elif "|" in line:
#                 # Split the line by '|'
#                 parts = line.split("|")
#                 print(parts)
#                 # Extract the price and name
#                 price_str = parts[-3].strip()  # Extract the price from the third last column
#                 price = int(price_str)
#                 total_price += price

#     # Writing total, VAT, and grand total to the file
#     with open(file_path, 'a') as file:
#         file.write("===================================================|\n")
#         file.write(f"| Total:                       Rs {total_price: <20}|\n")
#         file.write("===================================================|\n")



# # Example usage:
# # Example usage:
# file_path = f"Invoices/purchased/janakPurchased.txt"
# calculate_total_price(file_path)





def calculate_total_price(file_path): # HAVE TO ADD LATER
    final_price = 0
    with open(file_path, 'r') as file:
        for line in file:
            if "Grand Total Rs:" in line:
                price_str = line.split(":")[1].strip()  # Remove extra spaces
                price = int(price_str.split()[0])
                final_price += price
    with open(file_path, 'a') as file:
        file.write("===================================================|\n")
        file.write(f"| Total Price:                 {final_price: <20}|\n")
        file.write("===================================================|\n")

file_path = f"Invoices/purchased/janakPurchased.txt"
calculate_total_price(file_path)