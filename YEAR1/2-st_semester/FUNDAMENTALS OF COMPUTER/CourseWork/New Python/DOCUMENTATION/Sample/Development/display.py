def display_table(item_list):
    print("+------+-------------------------------------+---------------------+---------------+----------+")
    print("│ SN   │  Name                               │ Brand               │ Price (5 days)│ Quantity │")
    print("+------+-------------------------------------+---------------------+---------------+----------+")
    for item in item_list:
        print("│ {:<4} │ {:<35} │ {:<20}│ ${:>12.2f} │ {:>8} │".format(
            item['serial_number'], item['name'][:35], item['brand'][:20],
            item['price'], item['quantity']))
    print("+------+-------------------------------------+---------------------+---------------+----------+")