
import datetime
from datetime import date
import write

def rent(item_list):
    
    #promptthe user to enter the customer's name, address, and contact information.
    customer_name = input("Enter customer name>> ")
    customer_address = input("Enter address>> ")
    customer_contact = int(input("Enter the contact of customer>> "))
    
    #prompt the user to enter the serial number of the item they want to rent,
    serial_number = int(input("Enter the Serial Number of item>> "))
    quantity = int(input("Enter the quantity you want>> "))
    duration = int(input("Enter the number of days you want to Rent>> "))

    # call the `find_item_by_serial_number` function and passing the `item_list` and `serial_number` as
    # arguments and find the item in the `item_list` that matches the given `serial_number` and assign it to the variable `item`.
    item = find_item_by_serial_number(item_list, serial_number)
            
    #check if the variable `item` is `None` then the item with the given serial number was not found in the `item_list` and it prints "Items not found!" and returns from the function.
    # handle the case when the user enters an invalid serial number for an item.
    if item is None:
        print("Serial Number doesn't Exists!")
        return

    # check if the quantity of the item in stock is equal to the quantity requested by the user 
    # If they are equal
    if item['quantity'] == 0:
        print("Out of stock!")
        return

    # calculate the total amount to be paid for renting an item.
    total_amount = item['price'] * quantity * duration
    vat = round(.13*total_amount) 
    grand_total = float(total_amount + vat)
    # get the current date and format it as a string in the format "YYYY-MM-DD"
    rentdate = datetime.date.today().strftime("%Y-%m-%d")
            
    #Bill of rented Items
    invoice = f"|-----------------------RENT BILL---------------------|\n" \
            f" Customer: {customer_name}                   Date: {rentdate}\n" \
            f" Address: {customer_address}\n" \
            f" Contact No.: {customer_contact}\n" \
            f"|---------------------------ITEMS-----------------------|\n" \
            f" Items: {item['name']}\n" \
            f" Brand: {item['brand']}\n" \
            f" Quantity: {quantity}\n" \
            f" Duration: {duration} days\n" \
            f"|-------------------------------------------------------|\n" \
            f" Total: ${total_amount}\n" \
            f"|-------------------------------------------------------|\n" \
            f" VAT: ${vat}\n"\
            f"|-------------------------------------------------------|\n" \
            f" Grand Total: ${grand_total}\n" \
            f"|-------------------------------------------------------|\n" \
                
    #Decrease the quantity of the rented item from the total quantity available in the item list. 
    item['quantity'] -= quantity
    write.write_bill("items.txt", item_list)

    #creating a file name for the rental bill based on the customer's name
    bill = f"{customer_name.replace(' ', '_')}_rental_bill.txt"
    with open(bill, 'w') as file:
        file.write(invoice)

    # print a success message to indicate that the rental process was completed successfully.
    print("Rented successful!")
    print(invoice)
            
        
#Create function rent_item
def return_item(item_list):
    
    #prompt the user to enter the customer's name, the serial number of the item they
    # want to rent, and the quantity of the item they want to rent. 
    customer_name = input("Enter customer name>> ")
    serial_number = int(input("Enter the Serial Number of item>> "))
    quantity = int(input("Enter the quantity you want>> "))
    return_date = datetime.date.today().strftime("%Y-%m-%d")

    item = find_item_by_serial_number(item_list, serial_number)
            
    #check if the variable `item` is `None` then the item with the given serial number was not found in the `item_list` and it prints "Items not found!" and returns from the function.
    # handle the case when the user enters an invalid serial number for an item.
    if item is None:
        print("Serial Number Doesn't Exists!")
        return
    
    
    rental_duration =  int(input("Enter the rental duratiom: "))
    fine_amt = 20
    rental= input("Enter the date rented : ").split('-')
    yyyy,mm,dd=[int(item) for item in rental]
    rentalDate=date(yyyy,mm,dd)
    slow = (datetime.date.today() - rentalDate).days
        
    # check if the number of days the item was rented is less than or equal to therental duration
    if slow<=rental_duration:
        day=0
    else:
        day=slow-rental_duration
            
    # calculating the fine amount for returning the rented item late.
    fine = fine_amt * day*quantity
        
        
   # calculating the number of weeks (d) that the item was rented for.
    d=int(rental_duration/5)
        
    if rental_duration %5 ==0:
        d +=0
    else:
        d +=1
            
        
    # calculate the total amount to be paid for renting an item.
    total_amount = item['price'] * quantity * rental_duration
    vat = round(.13*total_amount) 
    grand_total = float(total_amount + vat + fine)     
    #Bill of rented Items
    invoice = f"|-----------------------RETURN BILL---------------------|\n" \
            f" Customer: {customer_name}                   Date: {return_date}\n" \
            f"|---------------------------ITEMS-----------------------|\n" \
            f" Items: {item['name']}\n" \
            f" Brand: {item['brand']}\n" \
            f" Quantity: {quantity}\n" \
            f" Duration: {rental_duration} days\n" \
            f" Delay: {day} days\n" \
            f"|-------------------------------------------------------|\n" \
            f" Total: ${total_amount}\n" \
            f"|-------------------------------------------------------|\n" \
            f" Fine: ${fine}\n" \
            f"|-------------------------------------------------------|\n" \
            f" VAT: ${vat}\n"\
            f"|-------------------------------------------------------|\n" \
            f" Grand Total: ${grand_total}\n" \
            f"|-------------------------------------------------------|\n" \
                
    #Increase the quantity of the rented item from the total quantity available in the item list. 
    item['quantity'] += quantity
    write.write_bill("items.txt", item_list)
            
            
            

    #creating a file name for the return  bill based on the customer's name
    bill = f"{customer_name.replace(' ', '_')}_return_bill.txt"
    with open(bill, 'w') as file:
        file.write(invoice)

    # print a success message to indicate that the rental process was completed successfully.
    print("Item Renturn successfully!")
    print(invoice)
    
#The function finds an item in a list based on its serial number.
def find_item_by_serial_number(item_list, serial_number):
    # iterated over each item in the `item_list` and checking if the `serial_number` of the item matches the given `serial_number`.
    for item in item_list:
        if item['serial_number'] == serial_number:
            return item
    return None
