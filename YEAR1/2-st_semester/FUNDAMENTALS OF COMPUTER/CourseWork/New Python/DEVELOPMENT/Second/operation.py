from read import *
import os as s
from datetime import datetime


# Purchased Part start
##################################################################################################################
# ________________________________________________PART 1 START___________________________________________
def get_refined_user_purchased_info():
    user_data = {}

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
            print(f"🔺 Name cannot be empty {name}. Please enter your name.")
# ++++++++++++++++++++++++++++++++++++ TAKING USER NAME END +++++++++++++++++++++++++++++++++++++++++++++++++++++



# ++++++++++++++++++++++++++++++++++++ TAKING USER CONTACT START +++++++++++++++++++++++++++++++++++++++++++++++++++++
    while True:
        contact = input("📞 Enter your contact number: ")
        if contact.isdigit() and len(contact) == 10:
            user_data['contact'] = contact
            break 
        else:
            print(f"🔺 Invalid contact number {contact}. Please enter a 10-digit number.")
# ++++++++++++++++++++++++++++++++++++ TAKING USER CONTACT END +++++++++++++++++++++++++++++++++++++++++++++++++++++



# ++++++++++++++++++++++++++++++++++++ TAKING USER PURCHASING MONTHS START +++++++++++++++++++++++++++++++++++++++++++++++++++++
    while True:
        months = input("📅 For How many months do you want to Purchase lands? ")
        if months.isdigit() and int(months) > 0:
            user_data['month'] = int(months)
            break
        else:
            print(f"❌ Please enter a valid number of months. ({months})")
# ++++++++++++++++++++++++++++++++++++ TAKING USER PURCHASING MONTHS END +++++++++++++++++++++++++++++++++++++++++++++++++++++



# ++++++++++++++++++++++++++++++++++++ TAKING LAND KITTA NO START +++++++++++++++++++++++++++++++++++++++++++++++++++++
    land_ids = []
    
    purchase_multiple = input("\n\033[1m🟢 Do you want to purchased multiple lands? (yes/no): \033[0m").lower()

    if purchase_multiple == 'yes':
        while True:
            land_id = input("\033[1m🌟 Enter a Land ID (and type 'done' to finish): \033[0m")
            if land_id.lower() == 'done':
                break
            else:
                # Check if the returned list from checking_availability() is not empty
                availability = checking_availability(land_id)
                if availability:
                    if land_id not in land_ids:
                        land_ids.extend(availability)
                    else:
                        print(f"✔️__This land {land_id} is already selected")
                else:
                    print(f"❌ This land {land_id} is not Available in our records for purchase 🤳")
    # If user wants to purchase only one land
    elif purchase_multiple == 'no':
        land_id = input("\033[1m🌟 Enter a Land ID: \033[0m")
        availability = checking_availability(land_id)
        if availability:
            land_ids.append(land_id)
        else:
            print(f"❌ This land {land_id} is not Available in our records for purchase 🤳")
    else:
        print(f"🔺 Invalid choice {purchase_multiple}. Please enter 'yes' or 'no'.")

        
    user_data['land_ids'] = land_ids

# ++++++++++++++++++++++++++++++++++++ TAKING LAND KITTA NO END +++++++++++++++++++++++++++++++++++++++++++++++++++++

    return user_data



def checking_availability(entered_kitta):
    datas = data_into_list()
    for data in datas:
        if data["id"] == entered_kitta and data["availability"] == "Available":
            return [entered_kitta]
    else:
        # If the loop completes without finding a matching land ID, print a message and return an empty list
        print(f"This land {entered_kitta} is not available in our records")
        return []
# ________________________________________________PART 1 END___________________________________________



# ________________________________________________PART 2 START___________________________________________
# No ii
def user_purchased_full_data(user_info, raw_datas):
    purchased_list = []
    datas_to_write = raw_datas
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    for data in datas_to_write:
        if str(data['id']) in user_info['land_ids']:
            price = int(data['price'])
            purchased_duration = calculate_purchased_duration(user_info)
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
                'months': user_info['month'],
                'vat_amount': vatAmount,
                'Grand_Total': grandTotal,
                'Timestamp' : timestamp,
                'duration' : purchased_duration
            }
            purchased_list.append(data_with_user_info)
    
    return purchased_list, datas_to_write


def calculate_grand_total_and_vat(prices):
    vat_percentage = 13
    vatAmount = (prices * vat_percentage) / 100
    grand_total = prices + vatAmount
    return vatAmount, grand_total



def calculate_purchased_duration(user_info):
    purchased_for = user_info['month']
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    date_expried = timestamp.split("-")
    this_month = int(date_expried[1])
    exp_new_month = this_month + purchased_for
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

    while True:
        name = input("👤 Enter your name: ")
        status = check_filename(name)
        if name.strip():
            if status:
                user_data['name'] = name
                break
            else:
                print(f"❌ The user with name {name} Dosen't purchased any land")
        else:
            print(f"❌ Name cannot be empty {name}. Please enter your name.")

    while True:
        contact = input("📞 Enter your contact number: ")
        if contact.isdigit() and len(contact) == 10:
            user_data['contact'] = contact
            break 
        else:
            print(f"❌ Invalid contact number {contact}. Please enter a 10-digit number.")

    # Input and validate the number of months
    while True:
        months = input("📅 How many months have you used the purchased lands? ")
        if months.isdigit() and int(months) > 0:
            user_data['months'] = int(months)
            break
        else:
            print("🔺 Please enter a valid number of months.")

    user_data['land_ids'] = get_selected_return_land_ids()

    return user_data




def get_selected_return_land_ids():
    land_ids = []
    return_multiple = input("\n\033[1m🟢 Do you want to return multiple lands? (yes/no): \033[0m").lower()

    # If user wants to return multiple lands
    if return_multiple == 'yes':
        while True:
            land_id = input("\033[1m🌟 Enter a Kitta NO (and type 'done' to finish): \033[0m")
            if land_id.lower() == 'done':
                break
            elif not land_id.isdigit():
                print(f"🔺 Invalid Kitta NO {land_id}. Please enter a number.")
                continue

            availability = checking_availability_of_return(land_id)
            if availability and land_id not in land_ids:
                land_ids.append(land_id)
            elif land_id in land_ids:
                print(f"✔️__This land with Kitta NO {land_id} is already selected.")
            else:
                print(f"❌ This land {land_id} is not Available so, we can't return")
     # If user does not want to return multiple lands
    elif return_multiple == 'no':
        land_id = input("\033[1m🌟 Enter a Kitta NO: \033[0m")
        if land_id.isdigit():
            availability = checking_availability_of_return(land_id)
            if availability:
                land_ids.append(land_id)
            else:
                print(f"❌ This land {land_id} is not Available so, we can't return")
        else:
            print(f"🔺 Invalid Kitta NO {land_id}. Please enter a number.")

    # If user provides an invalid input
    else:
        print(f"🔺 Invalid choice {return_multiple}. Please enter 'yes' or 'no'.")


    return land_ids


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
        name_part = file_name.split('Purchased.txt')[0]

        if user_name == name_part:
            return file_name
        
    return None

# ________________________________________________PART 1 END___________________________________________



# ________________________________________________PART 2 START___________________________________________

def user_return_full_data(user_info, raw_datas):
    returned_list = []
    datas_to_write = raw_datas
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    for data in datas_to_write:
        if str(data['id']) in user_info['land_ids']:
            month = int(user_info['months'])
            fine = calculate_fine(month, user_info)
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
                'months': user_info['months'],
                'fine': fine,
                'Timestamp' : timestamp
            }
            returned_list.append(data_with_user_info)
    
    return returned_list, datas_to_write


def grab_purchased_month(user_info):
    name = user_info["name"]
    file_path = f"Invoices/purchased/{name}Purchased.txt"
    month = 0
    with open(file_path, 'r') as files:
        for line in files:
            if "Months:" in line:
                exact = line.strip().split(":")[1]
                month = int(exact)
    return month



def calculate_fine(month_return, user_return_info):
    month_purchased = grab_purchased_month(user_return_info)
    fine_per_month = 1000
    fine = 0
    if month_return > month_purchased:
        over_time = month_return - month_purchased
        fine = over_time * fine_per_month
    return fine


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
Purchased Date:    {item['Timestamp']: <20}      
"""
        invoices.append(invoice)
    return invoices, allTotal
# ________________________________________________PART 3 END_____________________________________________