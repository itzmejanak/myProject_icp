
#Creatw write bill function
def write_bill(file_name, item_list):

    with open(file_name, 'w') as file:
        for item in item_list:
            line = f"{item['serial_number']}, {item['name']}, {item['brand']}, ${item['price']}, {item['quantity']}\n"
            file.write(line)
