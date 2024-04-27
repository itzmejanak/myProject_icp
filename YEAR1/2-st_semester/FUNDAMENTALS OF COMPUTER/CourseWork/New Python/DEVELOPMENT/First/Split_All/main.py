from operation import *
from read import *
from write import *
import os as s

def main():
    """
    Display a menu for managing land transactions, allowing users to:
    1. View and manage purchased land.
    2. Return previously purchased land.
    3. Exit the program.
    """
    print("""
    1. Purchased Land
    2. Return Land
    3. Show Lands
    4. Exit 
    """)
    user_choice = validation(input("Please choose an option from the menu: \n"))
    create_directories()
    
    if user_choice == 1:
        show_lands()
    elif user_choice == 2:
        return_lands()
    elif user_choice == 3:
        show_lands()
    elif user_choice == 4:
        print("Exiting the program.")
        exit()
    else:
        print("Invalid choice. Please choose a number between 1 and 3.")
        main()


def validation(user_choice):
    """
    Validate user input to ensure it's a number.
    
    Args:
    - user_choice (str): The user's input.
    
    Returns:
    - int: The user's choice as an integer.
    """
    while not user_choice.isdigit():
        print("Sorry, that's not a valid choice. Please enter a number.")
        user_choice = input("Please choose an option from the menu: \n")
    return int(user_choice)



def create_directories():
    """
    Create folders to store invoices if they don't exist already.
    """
    directories = ['Invoices/purchased', 'Invoices/return']
    for directory in directories:
        if not s.path.exists(directory):
            s.makedirs(directory)
            print(f"Created directory: {directory}")




def show_lands():
    """
    Display a list of available lands with their details.
    """
    list_data = show_only_values()
    print("{:<10} | {:<20} | {:<15} | {:<10} | {:<10} | {:<15}".format(
        "KITTA NO", "LOCATION", "DIRECTION", "ANNA", "PRICE", "AVAILABILITY"))
    print("-" * 94)
    for record in list_data:
        print("{:<10} | {:<20} | {:<15} | {:<10} | {:<10} | {:<15}".format(*record))
    print("-" * 94)
    print("\n")
    purchase_land()




def return_lands():
    """
    Prompt the user to confirm returning a land or return to the purchase menu.
    """
    print("Thank you for using our land rental and return service 😊.\n")
    while True:
        choic_for_sure = input("Are you sure you want to return this land? (yes) or return to purchase? (Press Enter): ")
        if choic_for_sure.lower() == "yes":
            return_kitta = input("\nEnter your Land ID: ")
            returning_user_info(return_kitta)
            break
        elif choic_for_sure.lower() == "":
            show_lands()
            break
        else:
            print("\nSorry, I didn't catch that. Please enter 'yes' or press Enter to return to purchase options.\n")


def returning(kitta_no):
    """
    Mark a land as available for return based on its KITTA NO.

    Args:
    - kitta_no (str): The unique identifier of the land.

    This function searches for the land with the given KITTA NO,
    marks it as available, and updates the data file accordingly.
    """
    data = data_into_list()
    for item in data:
        if item["id"] == kitta_no:
            item["availability"] = "Available"
            print("Successfully returned land with KITTA NO: ", kitta_no)
            break 
    else:
        print("KITTA NO", kitta_no, "not found in the data.")

    save_data_to_file(data)



def purchase_land():
    """
    Prompt the user to confirm purchasing a land or return to the main menu.
    """
    print("Welcome to our land purchase service. 😊\n")
    while True:
        choice_for_sure = input("Are you sure you want to proceed? (yes) or return to the main menu? (Press Enter): ")
        if choice_for_sure.lower() == "yes":
            purchase_kitta = input("\nKindly provide the Land ID you'd like to purchase: ")
            purchasing_user_info(purchase_kitta)
            break
        elif choice_for_sure.lower() == "":
            main()
            break
        else:
            print("\nSorry, I didn't catch that. Please enter 'yes' or press Enter to return to the main menu.\n")


# Purchasing kitta given by user
def purchasing(purchasing_kitta):
    """
    Purchase a land based on its KITTA NO.

    Args:
    - purchasing_kitta (str): The unique identifier of the land to purchase.

    This function searches for the land with the given KITTA NO,
    marks it as not available for purchase, and updates the data file accordingly.
    """
    data = data_into_list()
    for item in data:
        if item["id"] == purchasing_kitta:
            if item["availability"] == "Available":
                item["availability"] = "Not Available"
                print("\nCongratulations! You've successfully purchased Land ID: ", purchasing_kitta)
                break 
            else:
                print("Sorry, this land is already rented. 🥱")
                break
    else:
        print("KITTA NO", purchasing_kitta, "not found in our records. 🙅🏼")

    save_data_to_file(data)



# Generating user invoice who have purchased it
def purchasing_user_info(kitta_no):
    """
    Generate an invoice for the user who has purchased a land.

    Args:
    - kitta_no (str): The unique identifier of the purchased land.

    This function collects user information, calculates invoice details,
    generates an invoice, and saves it to a file.
    """
    data = data_into_list()
    found = False
    for item in data:
        if item["id"] == kitta_no:
            found = True
            if item["availability"] == "Available":
                name, given_months = get_purchase_user_input()
                item['Name'] = name
                item['Months'] = given_months
                file_name = check_filename(name)

                vat_amount, grandTotal = calculate_grand_total_and_Vat(item["price"])

                timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                expired = calculate_expiration_date_of_purchased(given_months)

                item['Timestamp'] = timestamp
                remarks = "null"
                invoice_data = generate_purchased_invoice_data(item, name, given_months, vat_amount, grandTotal, expired, remarks)

                if file_name is None:
                    purchasing(kitta_no)
                    file_path = f"Invoices/purchased/{name}Purchased.txt"

                    with open(file_path, 'w') as file:
                        file.write(invoice_data)
                    main()

                else:
                    purchasing(kitta_no)
                    file_path = f"Invoices/purchased/{file_name}"
                    
                    with open(file_path, 'a') as file:
                        file.write("\n")
                        file.write(invoice_data)
                    calculate_total_price(file_path) 
                    main()
            else:
                print("\nSorry, this land is already rented. 🥱")
                main()
                break

    if not found:
        print("\nKitta number", kitta_no, "not found in our records. 🙅🏼\n")
        main()



def returning_user_info(kitta_no):
    """
    Generate an invoice for the user who is returning a land.

    Args:
    - kitta_no (str): The unique identifier of the returned land.

    This function collects user information, generates an invoice for the return,
    calculates any applicable fines, and saves the invoice to a file.
    """
    data = data_into_list()
    found = False
    for item in data:
        if item["id"] == kitta_no:
            found = True
            if item["availability"] == "Not Available":
                name, return_month = get_return_user_input()
                item['Name'] = name
                file_name = check_filename(name)

                timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                item['Timestamp'] = timestamp
                remarks = "null"
                invoice_data = generate_return_invoice_data(item, name, return_month, timestamp, remarks)

                if file_name is not None:
                    returning(kitta_no)
                    file_path = f"Invoices/return/{name}Return.txt"
                    file_path_purchased = f"Invoices/purchased/{name}Purchased.txt"
                    with open(file_path, 'a') as file:
                        file.write(invoice_data)
                    purchased_expire_months = return_purchased_expire_month(file_path_purchased)
                    each_purchase_exp_months = refine_expire_month(purchased_expire_months, kitta_no)
                    fine_amount = calculate_late_return_fine(return_month, each_purchase_exp_months)
                    write_fine_to_invoice(file_path, fine_amount)
                    toatal_fine = calculate_total_fine(file_path)
                    write_total_fine_to_invoice(file_path, toatal_fine)
                else:
                    print("No Purchased found for this user name")
                    main()
                    break
            else:
                print("\nWe apologize, but this land has not been rented out. Therefore, it cannot be returned. 🥱")
                main()
                break


    if not found:
        print("KITTA NO", kitta_no, "not found in our records. 🙅🏼")
        main()


def exit():
    """
    Close the program.
    """
    s.close

if __name__ == "__main__":
    main()
