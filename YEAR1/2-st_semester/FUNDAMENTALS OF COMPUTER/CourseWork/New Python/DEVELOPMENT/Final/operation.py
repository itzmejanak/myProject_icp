from read import *
import os as s
from datetime import datetime


def get_refined_user_purchased_info():
    user_data = {}

    # ++++++++++++++++++++++++++++++++++++ TAKING LAND KITTA NO AND MONTHS START +++++++++++++++++++++++++++++++++++++++++++++++++++++
    land_data = {}
    purchase_multiple = input("\n\033[1m🟢 Do you want to Rent multiple lands? (yes/no): \033[0m").lower()

    if purchase_multiple == 'yes':
        while True:
            land_id = input("\033[1m🌟 Enter a Kitta No (and press 'Enter' to finish): \033[0m")
            if land_id.lower() == '':
                break
            else:
                availability = purchasing_availability_check(land_id)
                if availability:
                    if land_id in land_data:
                        print(f"❌ You've already specified the duration for Kitta No {land_id}.")
                    else:
                        while True:
                            purchased_month = input(f"\033[1m📅 How many months would you like to rent this {land_id} kitta ?:  \033[0m")
                            if purchased_month.isdigit() and int(purchased_month) > 0:
                                land_data[land_id] = int(purchased_month)
                                break
                            else:
                                print("❌ Please enter a valid number of months.")
                else:
                    print(f"❌ This land {land_id} is not available in our records for purchase.")
                    
    elif purchase_multiple == 'no':
        while True:
            land_id = input("\033[1m🌟 Enter a Kitta No: \033[0m")
            availability = purchasing_availability_check(land_id)
            if availability:
                purchased_month = input(f"\033[1m📅 How many months would you like to rent this {land_id} kitta:  \033[0m")
                if purchased_month.isdigit() and int(purchased_month) > 0:
                    land_data[land_id] = int(purchased_month)
                    break  # Exit the loop if a valid Kitta No and number of months are provided
                else:
                    print("❌ Please enter a valid number of months.")
            else:
                print(f"❌ This land {land_id} is not available in our records for purchase.")
    else:
        print(f"🔺 Invalid choice {purchase_multiple}. Please enter 'yes' or 'no'.")
    user_data['land_data'] = land_data
    # ++++++++++++++++++++++++++++++++++++ TAKING LAND KITTA NO AND MONTHS END +++++++++++++++++++++++++++++++++++++++++++++++++++++
    # ++++++++++++++++++++++++++++++++++++ TAKING USER NAME START +++++++++++++++++++++++++++++++++++++++++++++++++++++
    while True:
        name = input("👤 Enter your name: ")
        if name.strip():
            if not any(char.isdigit() for char in name):
                user_data['name'] = name
                break
            else:
                print(f"❌ The name {name} contains integers. Please enter a valid name.")
        else:
            print("🔺 Name cannot be empty. Please enter your name.")
    # ++++++++++++++++++++++++++++++++++++ TAKING USER NAME END +++++++++++++++++++++++++++++++++++++++++++++++++++++

    # ++++++++++++++++++++++++++++++++++++ TAKING USER CONTACT START +++++++++++++++++++++++++++++++++++++++++++++++++++++
    while True:
        contact = input("📞 Enter your contact number: ")
        if contact.isdigit() and len(contact) == 10:
            user_data['contact'] = contact
            break 
        else:
            print("🔺 Invalid contact number. Please enter a 10-digit number.")
    # ++++++++++++++++++++++++++++++++++++ TAKING USER CONTACT END +++++++++++++++++++++++++++++++++++++++++++++++++++++

    return user_data



def purchasing_availability_check(entered_kitta):
    datas = data_into_list()
    for data in datas:
        if data["id"] == entered_kitta and data["availability"] == "Available":
            return [entered_kitta]
    else:
        return []
# ________________________________________________PART 1 END___________________________________________



# ________________________________________________PART 2 START___________________________________________
# No ii
def user_purchased_full_data(user_info, raw_datas):
    purchased_list = []
    datas_to_write = raw_datas
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    for data in datas_to_write:
        if str(data['id']) in user_info['land_data'].keys():
            price = int(data['price'])
            purchased_month = user_info['land_data'][str(data['id'])]
            vatAmount, grandTotal = calculate_grand_total_and_vat(price)
            Remarks = "null"
            if data['availability'] == 'Available':
                data['availability'] = 'Not Available'
            data_with_user_info = {
                'id': data['id'],
                'location': data['location'],
                'direction': data['direction'],
                'Anna': data['anna'],
                'price': data['price'],
                'Remarks': Remarks,
                'name': user_info['name'],
                'contact': user_info['contact'],
                'months': purchased_month,
                'vat_amount': vatAmount,
                'Grand_Total': grandTotal,
                'Timestamp' : timestamp,
                'duration': calculate_expiration_date(purchased_month)
            }
            purchased_list.append(data_with_user_info)
    
    return purchased_list, datas_to_write


def calculate_grand_total_and_vat(prices):
    vat_percentage = 13
    vatAmount = (prices * vat_percentage) / 100
    grand_total = prices + vatAmount
    return vatAmount, grand_total



def calculate_expiration_date(month):
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    date_expried = timestamp.split("-")
    this_month = int(date_expried[1])
    exp_new_month = this_month + int(month)
    date_expried[1] = str(exp_new_month)

    final_date = "-".join(date_expried)
    return final_date
# ________________________________________________PART 2 END___________________________________________


# ________________________________________________PART 3 START___________________________________________
def generate_purchased_invoices(datas):
    invoices = []
    allTotal = 0
    for item in datas:
        allTotal += int(item["Grand_Total"])
        invoice = f"""
                                    ICP Rental Pokhara
                                10, Hospital Chowk, Pokhara

Customer Details:                                                         Date: {item['Timestamp'].split()[0]}
Name: {item['name']}
Address: {item["location"]}
Phone: {item['contact']}

+---------------------+------------------+-------------+---------+----------+-----------------+
|     Kitaa Number    |    Location      |  Direction  | Total Anna | Price in Rs |   Remarks |
+---------------------+------------------+-------------+---------+----------+-----------------+
|        {item['id']: <12} |   {item['location']: <14} |    {item['direction']: <8} |    {item['Anna']: <7} |  {item['price']: <10} | {item['Remarks']: <10}|
+---------------------+------------------+-------------+---------+----------+-----------------+

Total Rs: {item["price"]}
VAT (13%) Rs: {item['vat_amount']}
Grand Total Rs: {item['Grand_Total']}

Additional Data:
Months:            {item['months']: <20}              
Purchased Duration:    {item['duration']: <20}

=================================================================================================
"""
        invoices.append(invoice)
    return invoices, allTotal

# ________________________________________________PART 3 END___________________________________________

# Purchased Part end
##################################################################################################################





# Return Part Start
###################################################################################################################

# ________________________________________________PART 1 START___________________________________________
def get_refined_user_return_info():
    user_data = {}

    user_data['land_data'] = get_selected_return_land_data()
    
    while True:
        name = input("👤 Enter your name: ")
        status = check_filename(name)
        if name.strip():
            if status:
                user_data['name'] = name
                break
            else:
                print(f"❌ The user with name {name} hasn't purchased any land")
        else:
            print("❌ Name cannot be empty. Please enter your name.")

    while True:
        contact = input("📞 Enter your contact number: ")
        if contact.isdigit() and len(contact) == 10:
            user_data['contact'] = contact
            break 
        else:
            print("❌ Invalid contact number. Please enter a 10-digit number.")

    return user_data


def get_selected_return_land_data():
    land_data = {}
    return_multiple = input("\n\033[1m🟢 Do you want to return multiple lands? (yes/no): \033[0m").lower()

    if return_multiple == 'yes':
        while True:
            land_id = input("\033[1m🌟 Enter a Kitta no (and press 'Enter' to finish): \033[0m")
            if land_id.lower() == '':
                break
            elif not land_id.isdigit():
                print("🔺 Invalid Kitta NO. Please enter a number.")
                continue

            availability = checking_availability_of_return(land_id)
            if availability:
                if land_id in land_data:
                    print(f"❌ You've already specified the duration for Kitta No {land_id}.")
                else:
                    while True:
                        return_month = input(f"\033[1m📅 In How many months you have Return this {land_id} kitta: \033[0m")
                        if return_month.isdigit() and int(return_month) > 0:
                            land_data[land_id] = int(return_month)
                            break
                        else:
                            print("❌ Please enter a valid number of months.")
            else:
                print(f"❌ This land {land_id} is not available so, we can't return.")
                
    elif return_multiple == 'no':
        while True:
            land_id = input("\033[1m🌟 Enter a Kitta No: \033[0m")
            availability = checking_availability_of_return(land_id)
            if availability:
                return_month = input(f"\033[1m📅 In How many months you have Return this {land_id} kitta  \033[0m")
                if return_month.isdigit() and int(return_month) > 0:
                    land_data[land_id] = int(return_month)
                    break  
                else:
                    print("❌ Please enter a valid number of months.")
            else:
                print(f"❌ This land {land_id} is not available in our records for return.")
    else:
        print("🔺 Invalid choice. Please enter 'yes' or 'no'.")

    return land_data



def checking_availability_of_return(land_id):
    datas = data_into_list()
    for data in datas:
        if data['id'] == land_id and data['availability'] == 'Not Available':
            return True

    return False


def check_filename(user_name):
    file_path = "Invoices/purchased/"
    files = s.listdir(file_path)

    for file_name in files:
        name_part = file_name.split('_')[-1].split('Purchased.txt')[0]

        if user_name == name_part:
            return file_name
        
    return None

# ________________________________________________PART 1 END___________________________________________



# ________________________________________________PART 2 START___________________________________________

def user_return_full_data(user_info, raw_datas):
    returned_list = []
    datas_to_write = raw_datas
    month_purchased = grab_purchased_month(user_info)
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    for data in datas_to_write:
        if str(data['id']) in user_info['land_data'].keys():
            return_month = user_info['land_data'][str(data['id'])]
            purchased_month = month_purchased[int(data['id'])]
            fine = calculate_fine(return_month, purchased_month)
            Remarks = "null"
            if data['availability'] == 'Not Available':
                data['availability'] = 'Available'
            data_with_user_info = {
                'id': data['id'],
                'location': data['location'],
                'direction': data['direction'],
                'Anna': data['anna'],
                'price': data['price'],
                'Remarks': Remarks,
                'name': user_info['name'],
                'contact': user_info['contact'],
                'months': return_month,
                'fine': fine,
                'Timestamp' : timestamp
            }
            returned_list.append(data_with_user_info)
    
    return returned_list, datas_to_write


def grab_purchased_month(user_info):
    expiration_months = {}
    kittano = None
    name = user_info["name"]
    file_paths = [file for file in s.listdir("Invoices/purchased/") if name in file]

    for file_name in file_paths:
        file_path = s.path.join("Invoices/purchased/", file_name)
        with open(file_path, 'r') as file:
            for line in file:
                if "| null      |" in line:
                    # Extract the kittano from the line
                    kittano = line.split("|")[1].strip()
                    kitta = int(kittano)
                elif "Months:" in line and kittano is not None:
                    # Extract the purchased date from the line
                    purchased_date = line.split(":")[1].strip()
                    # Extract the month from the purchased date
                    purchase_month = int(purchased_date)
                    expiration_months[kitta] = purchase_month
                    kittano = None

    return expiration_months


def calculate_fine(month_return, purchased_month):
    fine_per_month = 1000
    fines = 0
    if month_return > purchased_month:
        over_time = month_return - purchased_month
        fines = over_time * fine_per_month
    else:
        fines = 0 
    return fines


# ________________________________________________PART 2 END___________________________________________




# ________________________________________________PART 3 START___________________________________________

def generate_returned_invoices(datas):
    invoices = []
    allTotal = 0
    for item in datas:
        # Calculate VAT and Grand Total
        price = int(item['price'])
        fine = int(item["fine"])
        vat_amount = price * 0.13
        grand_total = price + vat_amount + fine
        allTotal += grand_total
        
        invoice = f"""
                                ICP Rental Pokhara
                            10, Hospital Chowk, Pokhara

Customer Details:                                                     Date: {item['Timestamp'].split()[0]}
Name: {item['name']}
Address: {item["location"]}
Phone: {item['contact']}

+---------------------+------------------+-------------+---------+----------+-----------------+
|     Kitaa Number    |    Location      |  Direction  | Total Anna | Price in Rs |   Remarks |
+---------------------+------------------+-------------+---------+----------+-----------------+
|        {item['id']: <12} |   {item['location']: <14} |    {item['direction']: <8} |    {item['Anna']: <7} |  {item['price']: <10} | {item['Remarks']: <10}|
+---------------------+------------------+-------------+---------+----------+-----------------+

Total Rs: {price}                                                                      
VAT (13%) Rs: {vat_amount}
Grand Total Rs: {grand_total} (Included Fine)                                                FINE: {item['fine']}

Additional Data:
Months:            {item['months']: <20}              
Returned Date:    {item['Timestamp']: <20}

=================================================================================================
"""
        invoices.append(invoice)
    return invoices, allTotal
# ________________________________________________PART 3 END_____________________________________________