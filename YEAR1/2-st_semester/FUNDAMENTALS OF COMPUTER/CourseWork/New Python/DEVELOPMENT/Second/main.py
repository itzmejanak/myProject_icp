import os as s
from operation import *
from read import *
from write import *

def main():
    """
    Display a menu for managing land transactions, allowing users to:
    1. View and manage purchased land.
    2. Return previously purchased land.
    3. Exit the program.
    """
    print("""
        ,-------------------------------------------------------------------,
        |                     Enchanced Lands Management                    |
        |                       Where Dreams Come True                      |
        |-------------------------------------------------------------------|
        |                                                                   |
        |                            L A N D S                              |
        |                                                                   |
        |       1.  Purchase Land            2.  Return Land                |
        |                                                                   |
        |       3.  View Owned Lands         4.  Exit                       |
        |                                                                   |
        '-------------------------------------------------------------------'
    """)
    user_choice = validation(input("Please choose an option from the menu: \n"))
    create_directories()
    
    if user_choice == 1:
        purchased_land()
    elif user_choice == 2:
        returned_land()
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
    # Fetching land data
    land_data = show_only_values()

    # Formatting header
    header = "{:<10} | {:<20} | {:<15} | {:<10} | {:<10} | {:<15}".format(
        "KITTA NO", "LOCATION", "DIRECTION", "ANNA", "PRICE", "AVAILABILITY")
    separator = "-" * len(header)

    # Displaying header
    print(header)
    print(separator)

    # Displaying land records
    for record in land_data:
        print("{:<10} | {:<20} | {:<15} | {:<10} | {:<10} | {:<15}".format(*record))

    # Displaying separator after records
    print(separator)
    print()

    # Invoking further actions
    main()

def purchased_land():
    # Prompt user for confirmation
    choice = input("Do you want to purchased lands? (yes/press (Enter) for main menu): ").lower()
    if choice == "yes":
        raw_datas = data_into_list()
        user_information = get_refined_user_purchased_info()    # output: {'name': 'janak', 'contact': '9848010622', 'months': 9, 'land_ids': ['101', '102']}
        # print(user_information) # Done
        user_purchased_data_with_user_info, datas_to_write_into_data_txt = user_purchased_full_data(user_information, raw_datas)
        # print(user_purchased_data_with_user_info,"/n/n")  # Done
        # print("#"*30)
        # print(datas_to_write_into_data_txt)               # Done
        list_of_writable_invoices, allToatal = generate_purchased_invoices(user_purchased_data_with_user_info)
        # print(list_of_writable_invoices)  # Done
        if list_of_writable_invoices:
            write_generated_invoice_to_file(list_of_writable_invoices, user_information, allToatal)
            save_modified_data_to_file(datas_to_write_into_data_txt)
            print(f"\nCongratulations, {user_information['name']}! You've successfully purchased Land IDs:\n\t{', '.join(user_information['land_ids'])}\n\tFor {user_information['month']} Months 🐱‍👤🤩")
        else:
            print("\nEmpty Data Found Can't purchased ! ")
        main()
    else:
        # Go back to main menu if user presses Enter or enters anything else
        print("\nGoing back to main menu.")
        main()




def returned_land():
    # Prompt user for confirmation
    choice = input("Do you want to return purchased lands? (yes/press (Enter) for main menu): ").lower()
    if choice == "yes":
        raw_datas = data_into_list()
        user_information = get_refined_user_return_info()
        # print(user_information)   # Done
        user_return_data_with_user_info, returned_datas_to_write_into_data_txt = user_return_full_data(user_information, raw_datas)
        # print(user_return_data_with_user_info) # Done
        # print("\n######\n")   
        # print(returned_datas_to_write_into_data_txt) # Done
        list_of_returnable_invoices, allTotal = generate_returned_invoices(user_return_data_with_user_info)
        if list_of_returnable_invoices:
            write_generated_return_invoice_to_file(list_of_returnable_invoices, user_information, allTotal)
            save_modified_data_to_file(returned_datas_to_write_into_data_txt)
            print(f"\nCongratulations, {user_information['name']}! You've successfully return Land IDs:\n\t{', '.join(user_information['land_ids'])}\n\tIn {user_information['months']} Months 🤷‍♀️👏")
        else:
            print("\nEmpty Data Found Can't Return ! ")
        main()
    else:
        # Go back to main menu 
        print("\nGoing back to main menu.")
        main()


if __name__ == "__main__":
    main()
