
#The function reads the contents of a file.
def read_item(file_name):
    #Empty list
    item_list = []
    # read the contents of a file line by line.
    with open(file_name, 'r') as file:
        for line in file:
            data = line.strip().split(', ')
            item = {
                'serial_number': int(data[0]),
                'name': data[1],
                'brand': data[2],
                'price': float(data[3].replace('$', '')),
                'quantity': int(data[4])
            }
            #add the `item` dictionary to the `item_list` list.
            item_list.append(item)
    return item_list
