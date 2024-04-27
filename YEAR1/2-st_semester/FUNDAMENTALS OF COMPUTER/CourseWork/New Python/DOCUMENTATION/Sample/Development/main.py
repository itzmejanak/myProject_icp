# main.py
import display
import opreation
import read
import opreation

def main():
    
    #  call the `read_item` function from the `read` module and passing the file name "items.txt" as an argument. 
    item_list = read.read_item("items.txt")
    
    banner= """
██████╗░░█████╗░████████╗  ██████╗░███████╗███╗░░██╗████████╗░█████╗░██╗░░░░░
██╔══██╗██╔══██╗╚══██╔══╝  ██╔══██╗██╔════╝████╗░██║╚══██╔══╝██╔══██╗██║░░░░░
██████╦╝██║░░██║░░░██║░░░  ██████╔╝█████╗░░██╔██╗██║░░░██║░░░███████║██║░░░░░
██╔══██╗██║░░██║░░░██║░░░  ██╔══██╗██╔══╝░░██║╚████║░░░██║░░░██╔══██║██║░░░░░
██████╦╝╚█████╔╝░░░██║░░░  ██║░░██║███████╗██║░╚███║░░░██║░░░██║░░██║███████╗
╚═════╝░░╚════╝░░░░╚═╝░░░  ╚═╝░░╚═╝╚══════╝╚═╝░░╚══╝░░░╚═╝░░░╚═╝░░╚═╝╚══════╝

░██████╗███████╗██████╗░██╗░░░██╗██╗░█████╗░███████╗
██╔════╝██╔════╝██╔══██╗██║░░░██║██║██╔══██╗██╔════╝
╚█████╗░█████╗░░██████╔╝╚██╗░██╔╝██║██║░░╚═╝█████╗░░
░╚═══██╗██╔══╝░░██╔══██╗░╚████╔╝░██║██║░░██╗██╔══╝░░
██████╔╝███████╗██║░░██║░░╚██╔╝░░██║╚█████╔╝███████╗
╚═════╝░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░╚════╝░╚══════╝
"""
    print(banner)
    
    # create an infinite loop. It means that the code inside the loop will keep executing repeatedly until a break statement is encountered.
    while(True):
        
        # print a menu for the user to choose from. It displays four options: 
        # 1. Display Items
        # 2. Rent Items
        # 3. Return Items
        # 4. Exit
        print("Enter 1. Display Items")
        print("Enter 2. Rent Items")
        print("Enter 3. Return Items")
        print("Enter 4. Exit")
        
        try:
            #take user input from the console and converting it into an integer. 
            a=int(input("Enter a number here: "))
            print()
           # executing different actions based on the user's input.
            if(a==1):
                display.display_table(item_list)
            elif(a==2):
                display.display_table(item_list)
                opreation.rent(item_list)
            elif(a==3):
                opreation.return_item(item_list,)
            elif(a==4):
                print("Thank you for Trusting bot rental services")
                break
            else:
                print("Please enter a valid choice from 1-4")
        # handle the ValueError exception that may occur  when converting the user's input into an integer.
        except ValueError:
            print("Please input as suggested.")
main()
