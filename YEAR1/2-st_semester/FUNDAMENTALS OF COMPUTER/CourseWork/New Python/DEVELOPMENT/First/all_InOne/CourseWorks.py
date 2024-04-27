from datetime import datetime
import os as s

def main():
    print("""
    1. Purchased Land
    2. Return Land
    3. Exit 
    """)
    user_choice = validation(input("What you want to do: \n"))
    
    if user_choice == 1:
        show_lands()
    elif user_choice == 2:
        return_lands()
    elif user_choice == 3:
        print("Exiting the program.")
        exit()
    else:
        print("Invalid choice. Please choose a number between 1 and 3.")
        main()



def validation(user_choice):
    while not user_choice.isdigit():
        print("Invalid input. Please enter a valid integer.")
        user_choice = input("What you want to do: \n")
    return int(user_choice)


def data_into_list(filename="data.txt"):
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


def save_data_to_file(datas):
    with open("data.txt", 'w') as file:
        for data in datas:
            modified_data = ", ".join(data.values()) + "\n"
            file.write(modified_data)


def json_data_to_list():
    data = data_into_list()
    values_list = []
    for record in data:
        only_values = record.values()
        values_list.append(only_values)
    return values_list



def show_lands():
    list_data = json_data_to_list()
    print("{:<10} | {:<20} | {:<15} | {:<10} | {:<10} | {:<15}".format(
        "KITTA NO", "LOCATION", "DIRECTION", "ANNA", "PRICE", "AVAILABILITY"))
    print("-" * 94)
    for record in list_data:
        print("{:<10} | {:<20} | {:<15} | {:<10} | {:<10} | {:<15}".format(*record))
    print("-" * 94)
    print("\n")
    purchase_land()



def return_lands():
    print("Thanks for using rent and return it into the given period 😊..\n")
    while True:
        choic_for_sure = input("Are you sure for it (yes) or return for Purchase (Ok): ")
        if choic_for_sure.lower() == "yes":
            return_kitta = input("\nEnter your Kitta number: ")
            returning_user_info(return_kitta)
            break
        elif choic_for_sure.lower() == "ok":
            show_lands()
            break
        else:
            print("\nInvalid choice. Please enter 'yes' or 'ok'.\n")



def returning(kitta_no):
    data = data_into_list()
    for item in data:
        if item["id"] == kitta_no:
            item["availability"] = "Available"
            print("Successfully Return KITTA NO:", kitta_no)
            break 
    else:
        print("KITTA NO", kitta_no, "not found in the data.")

    save_data_to_file(data)
     

def purchase_land():
    print("You are welcome for purchase 😊..\n")
    while True:
        choice_for_sure = input("Are you sure for it (yes) or return to the main menu (Ok): ")
        if choice_for_sure.lower() == "yes":
            purchase_kitta = input("\nEnter Kitta number which you want: ")
            purchasing_user_info(purchase_kitta)
            break
        elif choice_for_sure.lower() == "ok":
            main()
            break
        else:
            print("\nInvalid choice. Please enter 'yes' or 'ok'.\n")


# Purchasing kitta given by user
def purchasing(purchasing_kitta):
    data = data_into_list()
    for item in data:
        if item["id"] == purchasing_kitta:
            if item["availability"] == "Available":
                item["availability"] = "Not Available"
                print("\nSuccessfully Purchased KITTA NO:", purchasing_kitta)
                break 
            else:
                print("This Land Was Already Rented")
                break
    else:
        print("KITTA NO", purchasing_kitta, "not found in the data.")

    save_data_to_file(data)


# Generating user invoice who have purchased it
def purchasing_user_info(kitta_no):
    data = data_into_list()
    found = False
    for item in data:
        if item["id"] == kitta_no:
            found = True
            if item["availability"] == "Available":
                name = input("Enter your name: \n")
                date_time = input("For how many months ?: ")
                item['Name'] = name
                item['Months'] = date_time
                file_name = check_filename(name)

                vat_amount, grandTotal = calculate_grand_total_and_Vat(item["price"])
                 # Adding timestamp
                timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                item['Timestamp'] = timestamp
                data = f"""
                                    ICP Rental Pokhara
                                10, Hospital Chowk, Pokhara

Customer Details:                                                         Date: {timestamp.split()[0]}
Name: {name}
Address: {item["location"]}
Phone: 980000000

+---------------------+------------------+-------------+---------+----------+-----------------+
|     Kitaa Number    |    Location      |  Direction  | Total Anna | Price in Rs |   Name    |
+---------------------+------------------+-------------+---------+----------+-----------------+
|        {item['id']: <12} |   {item['location']: <14} |    {item['direction']: <8} |    {item['anna']: <7} |  {item['price']: <10} | {name: <10}|
+---------------------+------------------+-------------+---------+----------+-----------------+

Total Rs: {item["price"]}
VAT (13%) Rs: {vat_amount}
Grand Total Rs: {grandTotal}

Additional Data:
Months:            {date_time: <20}              
Purchased Date:    {timestamp: <20}      
"""
                if file_name is None:
                    purchasing(kitta_no)
                    file_path = f"Invoices/purchased/{name}Purchased.txt"

                    with open(file_path, 'w') as file:
                        file.write(data)
                    main()

                else:
                    purchasing(kitta_no)
                    file_path = f"Invoices/purchased/{file_name}"
                    
                    with open(file_path, 'a') as file:
                        file.write("\n")
                        file.write(data)
                    calculate_total_price(file_path) 
                    main()
            else:
                print("\nThis Land Was Already Rented 🥱")
                main()
                break

    if not found:
        print("\nKitta number", kitta_no, "not found in the data. 🙅🏼\n")
        main()

        
def calculate_total_price(file_path):
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



def calculate_grand_total_and_Vat(prices):
    price = int(prices)
    vat_percentage = 13
    vatAmount = 0  
    grand_total = 0 
    vatAmount = (price * vat_percentage) / 100
    grand_total = price + vatAmount
    return vatAmount, grand_total



def returning_user_info(kitta_no):
    data = data_into_list()
    found = False
    for item in data:
        if item["id"] == kitta_no:
            found = True
            if item["availability"] == "Not Available":
                name = input("Enter your name: \n")
                return_month = int(input("Enter your returning Month: "))
                item['Name'] = name
                file_name = check_filename(name)

                # Adding timestamp
                timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                item['Timestamp'] = timestamp

                data = f"""
                                    ICP Rental Pokhara
                                10, Hospital Chowk, Pokhara

Customer Details:                                                         Date: {timestamp.split()[0]}
Name: {name}
Address: {item["location"]}
Phone: 980000000

+---------------------+------------------+-------------+---------+----------+-----------------+
|     Kitaa Number    |    Location      |  Direction  | Total Anna | Price in Rs |   Name    |
+---------------------+------------------+-------------+---------+----------+-----------------+
|        {item['id']: <12} |   {item['location']: <14} |    {item['direction']: <8} |    {item['anna']: <7} |  {item['price']: <10} | {name: <10}|
+---------------------+------------------+-------------+---------+----------+-----------------+

Total: Rs null
VAT (13%) Rs: Rs null*vat/100
Grand Total Rs: 3000

Additional Data:
Months:            {return_month: <20}              
Return Date:    {timestamp: <20}      
"""
                if file_name is not None:
                    returning(kitta_no)
                    file_path = f"Invoices/return/{name}Return.txt"
                    file_path_purchased = f"Invoices/purchased/{name}Purchased.txt"
                    with open(file_path, 'a') as file:
                        file.write(data)
                    purchased_month = return_purchased_month(file_path_purchased)
                    if purchased_month < return_month:
                        fine_per_month = 1000
                        late_months = return_month -  purchased_month
                        total_fine = late_months * fine_per_month
                        with open(file_path, 'a') as file:
                            file.write("===================================================|\n")
                            file.write(f"| Total Fine:                  {total_fine: <20}|\n")
                            file.write("===================================================|\n")
                    else:
                        with open(file_path, 'a') as file:
                            no_fine = "No Fine"
                            file.write("===================================================|\n")
                            file.write(f"| Total Fine:                  {no_fine: <20}|\n")
                            file.write("===================================================|\n")
                    main()
                else:
                    print("No Purchased found for this user name")
            else:
                print("This Land Was not Taken for rent 🥱")
                break

    if not found:
        print("KITTA NO", kitta_no, "not found in the data.")


def return_purchased_month(file_path):
    with open(file_path, 'r') as file:
        for line in file:
            if "Purchased Date:" in line:
                # Extract the purchased date from the line
                purchased_date = line.split(":")[1].strip()
                # Extract the month from the purchased date
                purchase_month = int(purchased_date.split('-')[1])
                return purchase_month
    # If the purchased date is not found, return None
    return None


def check_filename(user_name):
    file_path = "Invoices/purchased/"
    files = s.listdir(file_path)

    # Iterate over the list of files
    for file_name in files:
        # Extract the user name from the file name
        name_part = file_name.split('Purchased.txt')[0]

        # Check if the given user name matches any part of the file name
        if user_name in name_part:
            return file_name

    # If no matching file found
    return None

def exit():
    s.close

if __name__ == "__main__":
    main()


# remening
    # - multiple same user purchased
    # - calculate rent